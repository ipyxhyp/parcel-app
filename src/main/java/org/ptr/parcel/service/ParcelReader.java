package org.ptr.parcel.service;

import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Pattern;
import org.ptr.parcel.model.Parcel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 * Entry service for pushing Parcels objects via user input CLI
 * and writing once per minute grouped weights by postal codes
 * to the user output console
 *
 * */
@Service
@Scope("application")
public class ParcelReader implements Runnable{

    private Logger LOG  = LoggerFactory.getLogger(ParcelReader.class);

    private final Long ONCE_PER_MINUTE = 360000L;
    private volatile Boolean isQuit = Boolean.FALSE;
    private final static String QUIT = "quit";
    private final String regexPattern = "^\\d{1,3}\\.\\d{1,3}\\s\\d{5}$";
    private final String space = "\\s";
    private final String patternDescription = "<positive number (maximal 3 decimal places) . (dot) as decimal separator><space><fixed 5 digits>";

    private ParcelContent parcelContent;

    @Autowired
    public ParcelReader(ParcelContent parcelContent) {
        this.parcelContent = parcelContent;
    }

    /**
     * Runs user input reading thread , including the output writing inside timer task
     *
     * */
    @Override
    public void run() {

        Pattern pattern = Pattern.compile(regexPattern);
        // once per minute prints grouped weights by postal codes
        startPrintTask();

        final Scanner scanner = new Scanner(System.in);
        while(scanner.hasNext()){
            String input = scanner.nextLine();
            if(input != null && input.equals(QUIT)){
                LOG.info("quiting the reading based on command: {} ", input);
                isQuit = Boolean.TRUE;
                break;
            } else {
                if(!pattern.matcher(input).matches()){
                    LOG.info("input data does not match required pattern: {}, data {}", input, patternDescription);
                } else {
                    if(parcelContent != null){
                        String [] weightAndPostalCode = input.split(space);
                        final Parcel parcel = new Parcel(weightAndPostalCode[1] , Double.valueOf(weightAndPostalCode[0]));
                        parcelContent.addParcel(parcel);
                        LOG.info("parcel {} , added to parcel content {} ", parcelContent.getParcel(parcelContent.getSize() - 1), parcelContent);
                    }
                }
            }

        }
    }

    /**
     * Starts timer task once per minute for printing content of parcel storage object
     *
     * */
    private void startPrintTask() {

        final Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if(!isQuit){
                    printParcelContent();
                } else {
                    LOG.info("cancelling timer print task");
                    this.cancel();
                }
            }
        }, 0, ONCE_PER_MINUTE);
    }

    /**
     * Prints the content of stored parcels to output user console
     *
     * */
    private void printParcelContent() {

        if(parcelContent != null){
            LOG.info("parcelContent size {} ", parcelContent.getSize());
            parcelContent.groupWeightByPostalCodes();
        }
    }

}

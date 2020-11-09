package org.ptr.parcel.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.ptr.parcel.model.Parcel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Wrapper for keeping stored parcels
 *
 * */
@Component
public class ParcelContent {

    private Logger LOG  = LoggerFactory.getLogger(ParcelContent.class);

    private final List<Parcel> content = Collections.synchronizedList(new ArrayList<>());


    public ParcelContent() {
    }

    private List<Parcel> getContent() {
        return content;
    }

    public void addParcel(Parcel parcel){
        getContent().add(parcel);
    }

    public Parcel getParcel(Integer index){
        return getContent().get(index);
    }

    public void clearParcelContent(){
        getContent().clear();
    }

    public Integer getSize(){
        return getContent().size();
    }

    public Boolean isEmpty(){
        return getContent().isEmpty();
    }

    /**
     * Prints Parcels summed weight grouped by postal codes
     * */
    public void groupWeightByPostalCodes(){

        Map<String,Double> groupedParcels = getContent()
            .stream()
            .collect(Collectors.groupingBy(Parcel::getPostalCode,
                Collectors.summingDouble(Parcel::getWeight)));
        if(groupedParcels != null){
            LOG.info("parcels weights grouped by postal code {}, ", groupedParcels );
        }

    }

    @Override
    public String toString() {
        return "ParcelContent{" +
            "content=" + content +
            "size=" + content.size() +
            '}';
    }
}

package org.ptr.parcel.config;


import org.ptr.parcel.service.ParcelContent;
import org.ptr.parcel.service.ParcelReader;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ConcurrentTaskExecutor;

@Configuration
public class ParcelConfiguration {

    @Bean
    public TaskExecutor parcelTaskExecutor(){
        return new ConcurrentTaskExecutor();
    }

    @Bean
    public CommandLineRunner schedulingRunner(@Qualifier("parcelTaskExecutor") TaskExecutor taskExecutor) {
        return args -> taskExecutor.execute(new ParcelReader(new ParcelContent()));
    }


}

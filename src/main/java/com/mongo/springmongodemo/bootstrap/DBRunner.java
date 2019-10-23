package com.mongo.springmongodemo.bootstrap;

import com.mongo.springmongodemo.Repository.HotelRepository;
import com.mongo.springmongodemo.domain.Address;
import com.mongo.springmongodemo.domain.Hotel;
import com.mongo.springmongodemo.domain.Review;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;

@Component
public class DBRunner implements CommandLineRunner {

    private HotelRepository hotelRepository;

    public DBRunner(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Hotel marriot = new Hotel("Marriot",
                130,
                new Address("Paris","France"),
                Arrays.asList(
                        new Review("John",8,false),
                        new Review("Mary",7,true)
                ));

        Hotel ibis = new Hotel("Ibis",
                90,
                new Address("Bucharest","Romania"),
                Collections.singletonList(
                        new Review("Michael", 9, true)
                ));

        Hotel sofitel = new Hotel("Sofitel",
                200,
                new Address("Rome","Italy"),
                Collections.emptyList());

        //drop all
        hotelRepository.deleteAll();

        // Add hotels
        hotelRepository.saveAll(Arrays.asList(marriot,ibis,sofitel));


    }
}

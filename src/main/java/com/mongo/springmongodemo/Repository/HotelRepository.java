package com.mongo.springmongodemo.Repository;

import com.mongo.springmongodemo.domain.Hotel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;

public interface HotelRepository extends MongoRepository<Hotel,String>, QuerydslPredicateExecutor<Hotel>{
    List<Hotel> findByPricePerNightLessThan(int pricePerNight);
    List<Hotel> findByAddress_City(String city);
}

package com.mongo.springmongodemo.controller;

import com.mongo.springmongodemo.Repository.HotelRepository;
import com.mongo.springmongodemo.domain.Hotel;
import com.mongo.springmongodemo.domain.QHotel;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotels")
public class HotelController {
    private final HotelRepository hotelRepository;

    public HotelController(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    @GetMapping("/all")
    public List<Hotel> getAllHotels(){
        return hotelRepository.findAll();
    }

    @GetMapping("/{id}")
    public Hotel getHotel(@PathVariable String id){
        return hotelRepository.findById(id).orElse(null);
    }

    @GetMapping("/price/{maxPrice}")
    public List<Hotel> getByPricePerNight(@PathVariable int maxPrice){
        return hotelRepository.findByPricePerNightLessThan(maxPrice);
    }

    @GetMapping("/city/{city}")
    public List<Hotel> getByCity(@PathVariable String city){
        return hotelRepository.findByAddress_City(city);
    }

    @GetMapping("/country/{country}")
    public List<Hotel> getByCountry(@PathVariable String country){
        QHotel qHotel = new QHotel("hotel");

        BooleanExpression filterByCountry = qHotel.address.country.eq(country);
        List<Hotel> hotels = (List<Hotel>) hotelRepository.findAll(filterByCountry);

        return hotels;
    }

    @GetMapping("/recommended")
    public List<Hotel> getRecommended(){
        QHotel qHotel = new QHotel("hotel");

        BooleanExpression filterByPrice = qHotel.pricePerNight.lt(100);
        BooleanExpression filterByRating = qHotel.reviews.any().rating.gt(7);

        List<Hotel> hotels = (List<Hotel>) hotelRepository.findAll(filterByPrice.and(filterByRating));

        return hotels;
    }


    @PutMapping
    public void insert(@RequestBody Hotel hotel){
         hotelRepository.insert(hotel);
    }

    @PostMapping
    public void saveHotel(@RequestBody Hotel hotel){
        hotelRepository.save(hotel);
    }

    @DeleteMapping("/{id}")
    public void deleteHotel(@PathVariable String id){
        hotelRepository.deleteById(id);
    }
}

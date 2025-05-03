package com.restaurantReview.restaurant.services;

import com.restaurantReview.restaurant.domain.entities.Address;
import com.restaurantReview.restaurant.domain.entities.GeoLocation;

public interface GeoLocationService {
    GeoLocation geoLocate(Address address);
}

package com.restaurantReview.restaurant.services.impl;

import com.restaurantReview.restaurant.domain.RestaurantCreateUpdateRequest;
import com.restaurantReview.restaurant.domain.entities.Address;
import com.restaurantReview.restaurant.domain.entities.GeoLocation;
import com.restaurantReview.restaurant.domain.entities.Photo;
import com.restaurantReview.restaurant.domain.entities.Restaurant;
import com.restaurantReview.restaurant.exceptions.RestaurantNotFoundException;
import com.restaurantReview.restaurant.repositories.RestaurantRepository;
import com.restaurantReview.restaurant.services.GeoLocationService;
import com.restaurantReview.restaurant.services.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final GeoLocationService geoLocationService;

    @Override
    public Restaurant createRestaurant(RestaurantCreateUpdateRequest request) {
        Address address = request.getAddress();
        GeoLocation geoLocation = geoLocationService.geoLocate(address);
        GeoPoint geoPoint = new GeoPoint(geoLocation.getLatitude(), geoLocation.getLongitude());

        List<String> photoIds = request.getPhotoIds();
        List<Photo> photos = photoIds.stream().map(photoUrl -> Photo.builder()
                .url(photoUrl)
                .uploadDate(LocalDateTime.now())
                .build()).toList();

        Restaurant restaurant = Restaurant.builder()
                .name(request.getName())
                .cuisineType(request.getCuisineType())
                .contactInformation(request.getContactInformation())
                .address(address)
                .geoLocation(geoPoint)
                .operatingHours(request.getOperatingHours())
                .averageRating(0f)
                .photos(photos)
                .build();

        return restaurantRepository.save(restaurant);
    }

    @Override
    public Page<Restaurant> searchRestaurants(String query, Float minRating, Float latitude, Float longitude, Float radius, Pageable pageable) {
        if(null != minRating && (null == query || query.isEmpty())) {
            return restaurantRepository.findByAverageRatingGreaterThanEqual(minRating, pageable);
        }

        // Normalize min rating to be used in other queries
        Float searchMinRating = minRating == null ? 0f : minRating;

        // If there's a text, search query
        if (query != null && !query.trim().isEmpty()) {
            return restaurantRepository.findByQueryAndMinRating(query, searchMinRating, pageable);
        }

        // If there's a location search
        if (latitude != null && longitude != null && radius != null) {
            return restaurantRepository.findByLocationNear(latitude, longitude, radius, pageable);
        }
        // Otherwise we'll perform a non-location search
        return restaurantRepository.findAll(pageable);
    }

    @Override
    public Optional<Restaurant> getRestaurant(String id) {
        return restaurantRepository.findById(id);
    }

    @Override
    public Restaurant updateRestaurant(String id, RestaurantCreateUpdateRequest restaurant) {
        Restaurant existingRestaurant = getRestaurant(id)
                .orElseThrow(() -> new RestaurantNotFoundException("Restaurant with Id does not exist"));

        GeoLocation newGeoLocation = geoLocationService.geoLocate(restaurant.getAddress());
        GeoPoint newGeoPoint = new GeoPoint(newGeoLocation.getLatitude(), newGeoLocation.getLongitude());

        List<Photo> photos = restaurant.getPhotoIds().stream().map(photoUrl ->
                Photo.builder()
                        .url(photoUrl)
                        .uploadDate(LocalDateTime.now())
                        .build()
        ).collect(Collectors.toList());

        existingRestaurant.setName(restaurant.getName());
        existingRestaurant.setCuisineType(restaurant.getCuisineType());
        existingRestaurant.setContactInformation(restaurant.getContactInformation());
        existingRestaurant.setAddress(restaurant.getAddress());
        existingRestaurant.setGeoLocation(newGeoPoint);
        existingRestaurant.setOperatingHours(restaurant.getOperatingHours());
        existingRestaurant.setPhotos(photos);


        return restaurantRepository.save(existingRestaurant);
    }

    @Override
    public void deleteRestaurant(String id) {
        restaurantRepository.deleteById(id);
    }
}

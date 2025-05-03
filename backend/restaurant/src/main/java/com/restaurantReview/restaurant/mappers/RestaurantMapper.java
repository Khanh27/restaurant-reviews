package com.restaurantReview.restaurant.mappers;

import com.restaurantReview.restaurant.domain.RestaurantCreateUpdateRequest;
import com.restaurantReview.restaurant.domain.dtos.GeoPointDto;
import com.restaurantReview.restaurant.domain.dtos.RestaurantCreateUpdateRequestDto;
import com.restaurantReview.restaurant.domain.dtos.RestaurantDto;
import com.restaurantReview.restaurant.domain.dtos.RestaurantSummaryDto;
import com.restaurantReview.restaurant.domain.entities.Restaurant;
import com.restaurantReview.restaurant.domain.entities.Review;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RestaurantMapper {

    RestaurantCreateUpdateRequest toRestaurantCreateUpdateRequest(RestaurantCreateUpdateRequestDto dto);
    RestaurantDto toRestaurantDto(Restaurant restaurant);

    @Mapping(target="latitude", expression = "java(geoPoint.getLat())")
    @Mapping(target="longitude", expression = "java(geoPoint.getLon())")
    GeoPointDto toGeoPoint(GeoPoint geoPoint);

    @Mapping(source = "reviews", target = "totalReviews", qualifiedByName = "populateTotalReviews")
    RestaurantSummaryDto toSummaryDto(Restaurant restaurant);

    @Named("populateTotalReviews")
    default Integer populateTotalReviews(List<Review> reviews) {
        return reviews.size();
    }

}

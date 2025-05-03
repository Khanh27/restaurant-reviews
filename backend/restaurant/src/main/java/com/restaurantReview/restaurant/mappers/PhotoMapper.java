package com.restaurantReview.restaurant.mappers;

import com.restaurantReview.restaurant.domain.dtos.PhotoDto;
import com.restaurantReview.restaurant.domain.entities.Photo;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PhotoMapper {
    PhotoDto toDto(Photo photo);
}

package com.restaurantReview.restaurant.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TimeRange {
    //we want to find exact time slot hence the use of keyword
    @Field(type= FieldType.Keyword)
    private String openTime;

    @Field(type=FieldType.Keyword)
    private String closeTime;
}

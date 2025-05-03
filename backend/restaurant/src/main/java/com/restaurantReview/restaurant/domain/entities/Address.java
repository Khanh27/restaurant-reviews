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
@Builder //enables the creation of the current class objects using the builder pattern
public class Address {
    @Field(type= FieldType.Keyword)
    private String streetNumber;

    @Field(type=FieldType.Text)
    private String streetName;

    @Field(type=FieldType.Keyword)
    private String unit;

    @Field(type=FieldType.Keyword)
    private String state;

    @Field(type=FieldType.Keyword)
    private String postalCode;

    @Field(type=FieldType.Keyword)
    private String country;
}

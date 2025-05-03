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
public class OperatingHours {
    //nested tells elasticsearch to treat each time range as a nested object
    //maintaining the relationship between opening and closing time
    @Field(type= FieldType.Nested)
    private TimeRange monday; //time range for monday

    @Field(type=FieldType.Nested)
    private TimeRange tuesday;

    @Field(type=FieldType.Nested)
    private TimeRange wednesday;

    @Field(type=FieldType.Nested)
    private TimeRange thursday;

    @Field(type=FieldType.Nested)
    private TimeRange friday;

    @Field(type=FieldType.Nested)
    private TimeRange saturday;

    @Field(type=FieldType.Nested)
    private TimeRange sunday;

}

package com.restaurantReview.restaurant.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Data //generate getters, setters, etc.
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    @Field(type = FieldType.Keyword) //elasticsearch field types. this is for exact
                                     //matches
    private String id;

    @Field(type=FieldType.Text) //full-text search and partial matches
    private String username;

    @Field(type=FieldType.Text)
    private String givenName;

    @Field(type=FieldType.Text)
    private String familyName;
}

package com.restaurantReview.restaurant.manual;

import com.restaurantReview.restaurant.domain.entities.Restaurant;
import com.restaurantReview.restaurant.services.PhotoService;
import com.restaurantReview.restaurant.services.RestaurantService;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ResourceLoader;
import org.springframework.test.annotation.Rollback;

@SpringBootTest
@Tag("manual")
public class RestaurantDataLoaderTest {

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private PhotoService photoService;

    @Autowired
    private ResourceLoader resourceLoader;

    @Test
    @Rollback(false) // Allow changes to persist
    public void createSampleRestaurants() throws Exception {

    }
}

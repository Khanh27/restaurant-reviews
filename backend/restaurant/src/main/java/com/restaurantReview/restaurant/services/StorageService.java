package com.restaurantReview.restaurant.services;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

public interface StorageService {
    //store a file and return the id
    String store(MultipartFile file, String filename);

    //retrieve a file
    Optional<Resource> loadAsResource(String filename);
}

package com.tap.cont;

import java.io.File;

public class RestaurantUtils {

    public static File getImageFile(String imagePath) {
        if (imagePath != null && !imagePath.isEmpty()) {
            return new File(imagePath);
        } else {
            // Handle case where imagePath is null or empty
            return null; // or throw an exception, or return a default file
        }
    }
}

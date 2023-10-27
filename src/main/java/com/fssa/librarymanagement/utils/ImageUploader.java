package com.fssa.librarymanagement.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import com.cloudinary.Cloudinary;

/**
 * A utility class for uploading images to Cloudinary with custom configurations.
 * 
 * @author KishorMuruganandham
 * 
 */
public class ImageUploader {
	
    private Cloudinary cloudinary;

    /**
     * Initializes an ImageUploader with the specified Cloudinary instance.
     *
     * @param cloudinary The Cloudinary instance to use for image uploads.
     */
    public ImageUploader(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }

    /**
     * Uploads an image to Cloudinary with a custom public_id.
     *
     * @param imageStream The InputStream of the image to be uploaded.
     * @param userId      The user ID associated with the image.
     * @param userName    The user's name for display purposes.
     * @return A Map containing the upload result from Cloudinary.
     * @throws IOException If an error occurs during the upload.
     */
    public Map uploadImage(InputStream imageStream, String userId, String userName) throws IOException {
        // Read the InputStream into a byte array
        byte[] imageBytes = readInputStream(imageStream);

        // Set the public_id for the image upload
        Map<String, Object> options = new HashMap<>();
        options.put("public_id", "library/users/profile_picture/" + userId);
        options.put("overwrite", true);
        options.put("display_name", userName);

        return cloudinary.uploader().upload(imageBytes, options);
    }

    private byte[] readInputStream(InputStream inputStream) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[4096];
        int bytesRead;

        while ((bytesRead = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, bytesRead);
        }

        return outputStream.toByteArray();
    }
}

package com.example.demo.config;

public class S3Config {
    private final String region;
    private final String bucketName;

    public S3Config(String region, String bucketName) {
        this.region = region;
        this.bucketName = bucketName;
    }

    // Getters
    public String getRegion() {
        return region;
    }

    public String getBucketName() {
        return bucketName;
    }
}

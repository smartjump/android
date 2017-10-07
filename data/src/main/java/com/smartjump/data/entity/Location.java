package com.smartjump.data.entity;

/**
 *
 */
public class Location {

    private final float latitude;
    private final float longitude;
    private final float precision;

    public Location(float latitude, float longitude, float precision) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.precision = precision;
    }

    public float getLatitude() {
        return latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public float getPrecission() {
        return precision;
    }
}

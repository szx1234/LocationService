package com.example.songzhixin.locationservice.model;

/**
 * Created by songzhixin on 2017/7/20.
 */

public class Position {
    public float latitude;
    public float longitude;
    public float heading;
    public float speed;

    public String toString() {
        return "lat=" + latitude + "\nlon=" + longitude + "\nheading" + heading + "\nspeed" + speed;
    }
}

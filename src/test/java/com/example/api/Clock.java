package com.example.api;

import static java.lang.Math.abs;

public class Clock {
    public double angleClock(int hour, int minutes) {
        if (hour >= 12) {
            hour -= 12;
        }
        double hourAngle = hour * 30 + minutes * 1.0 / 60.0 * 30.0;

        double minAngle = minutes * 1.0 / 60.0 * 360.0;
        double angle = Math.abs(hourAngle - minAngle);
        double angle1 = 360.0 - angle;
        return angle < angle1 ? angle : angle1;
    }
}

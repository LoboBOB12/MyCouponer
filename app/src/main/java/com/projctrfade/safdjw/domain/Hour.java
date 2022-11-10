package com.projctrfade.safdjw.domain;

import java.io.Serializable;


public class Hour implements Serializable {

    private final QuarterHour[] quarters;

    private final int hourInteger;

    public Hour(QuarterHour[] quarters, int hourInteger) {
        this.quarters = quarters;
        this.hourInteger = hourInteger;
    }

    public QuarterHour[] getQuarters() {
        return quarters;
    }

    public int getHourInteger() {
        return hourInteger;
    }
}

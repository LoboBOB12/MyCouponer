package com.projctrfade.safdjw.domain;

import com.projctrfade.safdjw.domain.constants.HOUR_MODE;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class Day implements Serializable {
    //We hide the data itself for security and protection
    private final HOUR_MODE mode;
    private final Hour[] hours;

    public Day(HOUR_MODE mode, Hour[] hours) {
        this.mode = mode;
        this.hours = hours;

        List<String> list = new ArrayList();
    }


    public HOUR_MODE getMode() {
        return mode;
    }

    public Hour[] getHours() {
        return hours;
    }
}

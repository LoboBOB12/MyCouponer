package com.projctrfade.safdjw.domain;

import com.projctrfade.safdjw.domain.constants.COLOR;
import com.projctrfade.safdjw.domain.constants.ICON;
import com.projctrfade.safdjw.domain.constants.QUARTER;

import java.io.Serializable;


public class QuarterHour implements Serializable {
    private final int taskId;
    private final QUARTER quarter;
    private final boolean isActive;

    public QuarterHour(int taskId, QUARTER quarter, boolean isActive) {
        this.taskId = taskId;
        this.quarter = quarter;
        this.isActive = isActive;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public int getTaskId() {
        return taskId;
    }

    public QUARTER getQuarter() {
        return quarter;
    }
}

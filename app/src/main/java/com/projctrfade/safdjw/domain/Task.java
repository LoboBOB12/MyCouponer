package com.projctrfade.safdjw.domain;

import com.projctrfade.safdjw.domain.constants.COLOR;
import com.projctrfade.safdjw.domain.constants.ICON;

import java.io.Serializable;


public class Task implements Serializable {
    private final int taskId;
    private final String taskName;

    //these things need to be translated into Resource Ids
    private final ICON taskIcon;
    private final COLOR taskColor;

    public Task(int taskId, String taskName, ICON taskIcon, COLOR taskColor) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.taskIcon = taskIcon;
        this.taskColor = taskColor;
    }

    public int getTaskId() {
        return taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public ICON getTaskIcon() {
        return taskIcon;
    }

    public COLOR getTaskColor() {
        return taskColor;
    }
}

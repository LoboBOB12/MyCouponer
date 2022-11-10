package com.projctrfade.safdjw.ui.managehourview;

import com.projctrfade.safdjw.domain.Hour;
import com.projctrfade.safdjw.domain.Tasks;

public class HourViewModel implements IHourContract.ViewModel {
    private Hour hour;
    private Tasks tasks;

    @Override
    public void setHour(Hour hour) {
        this.hour = hour;
    }

    @Override
    public Hour getHour() {
        return this.hour;
    }

    @Override
    public void setTaskList(Tasks tasks) {
        this.tasks = tasks;
    }

    @Override
    public Tasks getTasks() {
        return this.tasks;
    }
}

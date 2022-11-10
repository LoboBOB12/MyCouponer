package com.projctrfade.safdjw.ui.dayview;

import com.projctrfade.safdjw.domain.Day;
import com.projctrfade.safdjw.domain.Tasks;

import java.util.Observable;
import java.util.Optional;


public class DayViewModel implements IDayViewContract.ViewModel {

    private Day day;
    private Tasks tasks;

    @Override
    public void setTasks(Tasks tasks) {
        this.tasks = tasks;
    }

    @Override
    public Tasks getTasks() {
        return this.tasks;
    }

    public void setDay(Day day) {
        this.day = day;
    }

    @Override
    public Day getDay() {
        return this.day;
    }
}

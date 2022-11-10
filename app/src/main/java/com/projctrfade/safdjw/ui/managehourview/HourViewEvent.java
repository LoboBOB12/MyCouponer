package com.projctrfade.safdjw.ui.managehourview;

import com.projctrfade.safdjw.domain.constants.QUARTER;
import com.projctrfade.safdjw.ui.managetaskview.TaskViewEvent;

public class HourViewEvent {
    private final HourViewEvent.Event event;
    private final Object value;
    private QUARTER quarter;
    private int position;
    private boolean isActive;

    public QUARTER getQuarter() {
        return quarter;
    }

    public int getPosition() {
        return position;
    }

    public boolean isActive() {
        return isActive;
    }

    public HourViewEvent(HourViewEvent.Event event, Object value){
        this.event = event;
        this.value = value;
    }

    private HourViewEvent(
            HourViewEvent.Event event,
            Object value,
            QUARTER quarter,
            int position){
        this.event = event;
        this.value = value;
        this.quarter = quarter;
        this.position = position;
        this.isActive = false;
    }

    private HourViewEvent(
            HourViewEvent.Event event,
            Object value,
            QUARTER quarter,
            boolean isActive){
        this.event = event;
        this.value = value;
        this.quarter = quarter;
        this.isActive = isActive;
        this.position = 0;
    }


    public static HourViewEvent getOnTaskSelectedEvent(
            QUARTER quarter,
            int position
            ){

        return new HourViewEvent(Event.ON_TASK_SELECTED, null, quarter, position);
    }


    public static HourViewEvent getOnQuarterToggleEvent(
            QUARTER quarter,
            boolean isActive
    ){

        return new HourViewEvent(Event.ON_QUARTER_TOGGLE, null, quarter, isActive);
    }


    public HourViewEvent.Event getEvent() {
        return event;
    }

    public Object getValue() {
        return value;
    }

    public enum Event {
        ON_QUARTER_TOGGLE,
        ON_DONE_CLICK,
        ON_TASK_SELECTED,
        ON_START
    }

}

package com.projctrfade.safdjw.ui.dayview;


public class DayViewEvent {

    private final Event event;
    private final Object value;

    public DayViewEvent(Event event, Object value){
        this.event = event;
        this.value = value;
    }

    public Event getEvent() {
        return event;
    }

    public Object getValue() {
        return value;
    }

    public enum Event {
        ON_START,
        ON_HOUR_SELECTED,
        ON_MANAGE_TASKS_SELECTED
    }
}

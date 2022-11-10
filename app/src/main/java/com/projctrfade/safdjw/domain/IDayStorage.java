package com.projctrfade.safdjw.domain;

import com.projctrfade.safdjw.common.Continuation;


public interface IDayStorage {

    public void getDay(Continuation<Day> continuation);

    public void updateDay(Day day, Continuation<Void> continuation);


    public void getHourWithMode(int hour, Continuation<Object[]> continuation);

    public void updateHour(Hour hour, Continuation<Void> continuation);
}

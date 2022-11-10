package com.projctrfade.safdjw.common;


public abstract class BaseViewLogic<T> {

    private boolean isCancelled = false;


    public void onCancel(){
        isCancelled = true;
    }

    public abstract void onViewEvent(T event);
}

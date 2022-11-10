package com.projctrfade.safdjw.common;

public interface Continuation<T> {
    public void onSuccess(T result);

    public void onException(Exception e);
}

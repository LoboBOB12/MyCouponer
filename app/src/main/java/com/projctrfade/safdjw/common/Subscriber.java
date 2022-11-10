package com.projctrfade.safdjw.common;

public interface Subscriber<EVENT> {

    public void onUpdateString(EVENT e, String s);

    public void onUpdateInt(EVENT e, int i);

    public void onUpdateBool(EVENT e, boolean b);

    public void onUpdateObject(EVENT e, Object o);
}

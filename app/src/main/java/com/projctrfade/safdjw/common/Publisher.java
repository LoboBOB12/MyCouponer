package com.projctrfade.safdjw.common;

import java.util.ArrayList;
import java.util.List;


public abstract class Publisher<EVENT> {
    List<Subscriber<EVENT>> subscribers = new ArrayList<>();

    protected void updateString(EVENT e, String s){
        for (Subscriber<EVENT> sub: subscribers) {
            sub.onUpdateString(e, s);
        }
    }

    protected void updateInt(EVENT e, int i){
        for (Subscriber<EVENT> sub: subscribers) {
            sub.onUpdateInt(e, i);
        }
    }

    protected void updateBool(EVENT e, boolean b){
        for (Subscriber<EVENT> sub: subscribers) {
            sub.onUpdateBool(e, b);
        }
    }

    //USE WITH CAUTION
    protected void updateObject(EVENT e, Object o){
        for (Subscriber<EVENT> sub: subscribers) {
            sub.onUpdateObject(e, o);
        }
    }
}

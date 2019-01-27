package com.yanheng.rxjava.javarx;

import com.yanheng.rxjava.L;

public class ConcreteWatcher implements  Watcher {
    private  String name ;
    public ConcreteWatcher(String s) {
        name=s;
    }

    @Override
    public void update(String string) {
        L.d("name "+name + " :"+string);
    }
}

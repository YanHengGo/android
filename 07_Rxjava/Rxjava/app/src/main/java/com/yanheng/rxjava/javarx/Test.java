package com.yanheng.rxjava.javarx;

public class Test {

    public void test(){
        Watched xiaoming = new ConcreateWatched();

        Watcher watcher1 = new ConcreteWatcher("1");
        Watcher watcher2 = new ConcreteWatcher("2");
        Watcher watcher3 = new ConcreteWatcher("3");

        xiaoming.addWatcher(watcher1);
        xiaoming.addWatcher(watcher2);
        xiaoming.addWatcher(watcher3);

        xiaoming.notifyWatchers("我想尿尿");
    }
}

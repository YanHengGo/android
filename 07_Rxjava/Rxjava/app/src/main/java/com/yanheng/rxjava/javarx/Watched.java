package com.yanheng.rxjava.javarx;

public interface Watched {
    void addWatcher(Watcher watcher);
    void removeWatcher(Watcher watcher);
    void notifyWatchers(String string);
}

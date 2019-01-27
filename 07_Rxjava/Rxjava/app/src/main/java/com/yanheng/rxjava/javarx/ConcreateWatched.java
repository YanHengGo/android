package com.yanheng.rxjava.javarx;

import java.util.ArrayList;
import java.util.List;

public class ConcreateWatched implements Watched {
    private List<Watcher> listwatcher = new ArrayList<>();
    @Override
    public void addWatcher(Watcher watcher) {
        listwatcher.add(watcher);
    }

    @Override
    public void removeWatcher(Watcher watcher) {
        listwatcher.remove(watcher);
    }

    @Override
    public void notifyWatchers(String string) {
        for(Watcher watcher : listwatcher){
            watcher.update(string);
        }
    }
}

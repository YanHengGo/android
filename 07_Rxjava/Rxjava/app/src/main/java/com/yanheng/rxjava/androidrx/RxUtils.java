package com.yanheng.rxjava.androidrx;


import com.yanheng.rxjava.L;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class RxUtils {
    public static void createObserable(){



        //定义被观察者
        Observable<String> observable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                L.d();
                emitter.onNext("hello ");
                L.d();
                emitter.onNext("hi");
                L.d();
                emitter.onComplete();
                L.d();
            }
        });
        Observer<String> observer = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                L.d(d.toString());
            }

            @Override
            public void onNext(String s) {
                L.d(s);

            }

            @Override
            public void onError(Throwable e) {
                L.d();

            }

            @Override
            public void onComplete() {
                L.d();

            }
        };
        observable.subscribe(observer);
    }
}

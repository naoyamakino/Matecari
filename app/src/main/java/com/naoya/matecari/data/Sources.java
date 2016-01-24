package com.naoya.matecari.data;

import android.util.Log;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;

/**
 * Created by Naoya on 16-01-24.
 */
public class Sources {
    private static final String TAG = "Sources";
    private Data memory = null;
    private Data disk = null;

    public void cleanMemory() {
        memory = null;
    }

    public Observable<Data> memory() {
        Observable<Data> observable = Observable.create(new Observable.OnSubscribe<Data>() {
            @Override
            public void call(Subscriber<? super Data> subscriber) {
                subscriber.onNext(memory);
                subscriber.onCompleted();
            }
        });
        return observable.compose(logSource("MEMORY"));
    }

    public Observable<Data> disk(String type) {
        Observable<Data> observable = Observable.create(new Observable.OnSubscribe<Data>() {
            @Override
            public void call(Subscriber<? super Data> subscriber) {

            }
        });
        return observable.doOnNext(new Action1<Data>() {
            @Override
            public void call(Data data) {
                memory = data;
            }
        }).compose(logSource("DISK"));
    }

    Observable.Transformer<Data, Data> logSource(final String source) {
        return new Observable.Transformer<Data, Data>() {
            @Override
            public Observable<Data> call(Observable<Data> observable) {
                return observable.doOnNext(new Action1<Data>() {
                    @Override
                    public void call(Data data) {
                        if (data == null) {
                            Log.d(TAG, source + " does not have any data.");
                        } else {
                            Log.d(TAG, source + " has data");
                        }
                    }
                });
            }
        };
    }
}

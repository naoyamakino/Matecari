package com.naoya.matecari.data;

import com.google.gson.Gson;

import android.content.Context;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;

import okio.BufferedSource;
import okio.Okio;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by Naoya on 16-01-24.
 */
public class Sources {
    private static final String TAG = "Sources";
    private Data memory = null;
    private Context mContext;
    private Gson mGson;

    public Sources(Context context, Gson gson) {
        mContext = context;
        mGson = gson;
    }

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

    public Observable<Data> disk(final String fileName) {
        Observable<Data> observable = Observable.create(
                new Observable.OnSubscribe<String>() {
                    @Override
                    public void call(Subscriber<? super String> subscriber) {
                        try {
                            InputStream is = mContext.getAssets().open(fileName);
                            BufferedSource source = Okio.buffer(Okio.source(is));
                            subscriber.onNext(source.readUtf8());
                            subscriber.onCompleted();

                        } catch (IOException exception) {
                            subscriber.onError(exception);
                        }
                    }
                }
        ).map(new Func1<String, Data>() {
            @Override
            public Data call(String jsonString) {
                return mGson.fromJson(jsonString, Data.class);
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

package com.naoya.matecari.ui;

import com.naoya.matecari.R;
import com.naoya.matecari.data.Data;
import com.naoya.matecari.data.Sources;
import com.squareup.picasso.Picasso;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Naoya on 16-01-23.
 */
public class PagerFragment extends Fragment {
    private static final String FILE_NAME = "data_source";

    @Bind(R.id.feed)
    RecyclerView mFeed;

    @Inject
    Picasso mPicasso;

    @Inject
    Sources mSources;

    Subscription mSubscription;

    public static PagerFragment newInstance(String fileName) {
        PagerFragment fragment = new PagerFragment();
        Bundle bundle = new Bundle();
        bundle.putString(FILE_NAME, fileName);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_all_pager, container, false);
        ButterKnife.bind(this, view);
        ((BaseActivity) getActivity()).inject(this);

        mFeed.setHasFixedSize(true);
        int numberOfColumns = getActivity().getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT ? 2: 3;
        mFeed.setLayoutManager(new GridLayoutManager(getActivity(), numberOfColumns));


        final String fileName = getArguments().getString(FILE_NAME);
        mSources.setFileName(fileName);
        mSubscription = Observable.concat(
                mSources.memory(),
                mSources.disk())
                .first(new Func1<Data, Boolean>() {
                    @Override
                    public Boolean call(Data data) {
                        return data != null;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Action1<Data>() {
                    @Override
                    public void call(Data data) {
                        mFeed.setAdapter(
                                new ItemAdapter(
                                        getActivity(),
                                        data.getData(),
                                        mPicasso));
                    }
                });
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mSubscription != null) mSubscription.unsubscribe();
    }
}

package com.naoya.matecari.ui;

import com.naoya.matecari.R;
import com.squareup.picasso.Picasso;

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

/**
 * Created by Naoya on 16-01-23.
 */
public class AllPagerFragment extends Fragment {
    private static final String DATA_SOURCE = "data_source";

    @Bind(R.id.feed)
    RecyclerView mFeed;

    @Inject
    Picasso mPicasso;

    Subscription mSubscription;

    public static AllPagerFragment newInstance(String dataSource) {
        AllPagerFragment fragment = new AllPagerFragment();
        Bundle bundle = new Bundle();
        bundle.putString(DATA_SOURCE, dataSource);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_all_pager, container, false);
        ButterKnife.bind(this, view);

        mFeed.setHasFixedSize(true);
        mFeed.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((BaseActivity) getActivity()).inject(this);

        mSubscription = Observable.concat()
        mFeed.setAdapter(new ItemAdapter(getActivity(), null, mPicasso));
    }
}

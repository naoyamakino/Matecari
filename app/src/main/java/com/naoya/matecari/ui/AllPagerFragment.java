package com.naoya.matecari.ui;

import com.naoya.matecari.R;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Naoya on 16-01-23.
 */
public class AllPagerFragment extends Fragment {

    @Bind(R.id.feed)
    RecyclerView mFeed;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_all_pager, container, false);
        ButterKnife.bind(this, view);

        mFeed.setHasFixedSize(true);
        mFeed.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        mFeed.setAdapter(new ItemAdapter(getActivity(), null));
        return view;
    }
}

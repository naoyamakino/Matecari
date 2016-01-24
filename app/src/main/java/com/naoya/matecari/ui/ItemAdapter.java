package com.naoya.matecari.ui;

import com.naoya.matecari.R;
import com.naoya.matecari.data.Data;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Naoya on 16-01-23.
 */
public class ItemAdapter extends ArrayAdapter<Data, ItemAdapter.ItemViewHolder> {

    static class ItemViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.image)
        ImageView image;

        @Bind(R.id.name)
        TextView name;

        @Bind(R.id.num_likes)
        TextView numLikes;

        @Bind(R.id.num_comments)
        TextView numComments;

        @Bind(R.id.price)
        TextView price;

        public ItemViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    private Context mContext;

    public ItemAdapter(Context context, List<Data> data) {
        super(data);
        mContext = context;
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        Data data = getItem(position);
        holder.name.setText(data.getName());
        holder.numComments.setText(String.valueOf(data.getNum_comments()));
        holder.numLikes.setText(String.valueOf(data.getNum_likes()));
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(mContext).inflate(R.layout.item_view, parent, false);
        final ItemViewHolder itemViewHolder = new ItemViewHolder(view);
        return itemViewHolder;
    }
}

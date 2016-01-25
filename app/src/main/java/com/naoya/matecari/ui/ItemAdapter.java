package com.naoya.matecari.ui;

import com.naoya.matecari.R;
import com.naoya.matecari.data.Item;
import com.squareup.picasso.Picasso;

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
public class ItemAdapter extends ArrayAdapter<Item, ItemAdapter.ItemViewHolder> {

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
    private Picasso mPicasso;

    public ItemAdapter(Context context, List<Item> items, Picasso picasso) {
        super(items);
        mContext = context;
        mPicasso = picasso;
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        Item item = getItem(position);
        holder.name.setText(item.getName());
        holder.numComments.setText(String.valueOf(item.getNum_comments()));
        holder.numLikes.setText(String.valueOf(item.getNum_likes()));
        holder.price.setText(
                String.format(
                        mContext.getString(R.string.price),
                        item.getPrice()
                ));

        mPicasso.load(item.getPhoto())
                .fit()
                .into(holder.image);
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(mContext).inflate(R.layout.item_view, parent, false);
        final ItemViewHolder itemViewHolder = new ItemViewHolder(view);
        return itemViewHolder;
    }
}

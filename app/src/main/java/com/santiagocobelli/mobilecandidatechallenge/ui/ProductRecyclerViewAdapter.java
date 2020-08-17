package com.santiagocobelli.mobilecandidatechallenge.ui;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.santiagocobelli.mobilecandidatechallenge.R;
import com.santiagocobelli.mobilecandidatechallenge.domain.Product;
import com.santiagocobelli.mobilecandidatechallenge.utils.GlideApp;

import java.util.List;

public class ProductRecyclerViewAdapter extends RecyclerView.Adapter<ProductRecyclerViewAdapter.ViewHolder> {

    private final List<Product> mValues;
    private OnProductListener mOnProductListener;

    public ProductRecyclerViewAdapter(List<Product> items, OnProductListener onProductListener) {
        mValues = items;
        mOnProductListener = onProductListener;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_product, parent, false);
        return new ViewHolder(view, mOnProductListener);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mIdView.setText(mValues.get(position).getTitle());
        holder.mContentView.setText("$ " + mValues.get(position).getPrice().intValue());
        GlideApp.with(holder.itemView).load(mValues.get(position).getImageUrl())
                .apply(new RequestOptions()
                        .dontTransform()
                        .format(DecodeFormat.PREFER_ARGB_8888)
                        .override(Target.SIZE_ORIGINAL))
                .into(holder.mImageView);
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public final ImageView mImageView;
        public Product mItem;

        OnProductListener onProductListener;

        public ViewHolder(@NonNull View view, OnProductListener onProductListener) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.item_title);
            mContentView = (TextView) view.findViewById(R.id.item_price);
            mImageView = (ImageView) view.findViewById(R.id.item_picture);
            this.onProductListener = onProductListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }

        @Override
        public void onClick(View view) {
            onProductListener.OnProductClick(getAdapterPosition());
        }
    }

    public interface OnProductListener{
        void OnProductClick(int position);
    }
}
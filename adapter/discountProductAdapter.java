package com.example.maandpadailygroceries.adapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.maandpadailygroceries.R;
import com.example.maandpadailygroceries.model.discountedProducts;

import java.util.List;


public class discountProductAdapter extends RecyclerView.Adapter<discountProductAdapter.discountProductViewHolder>{

     Context context;
     List<discountedProducts> discountedProductsList;

    public discountProductAdapter(Context context, List<discountedProducts> discountedProductsList) {
        this.context = context;
        this.discountedProductsList = discountedProductsList;
    }

    @NonNull
    @Override
    public discountProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.discounted_row_items,parent,false);
        return new discountProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull discountProductViewHolder holder, int position) {

        holder.discountImageView.setImageResource(discountedProductsList.get(position).getImageUrl());

    }

    @Override
    public int getItemCount() {
        return discountedProductsList.size();
    }

    public static class discountProductViewHolder extends RecyclerView.ViewHolder{
        ImageView discountImageView;
        public discountProductViewHolder(@NonNull View itemView) {

            super(itemView);
            discountImageView = itemView.findViewById(R.id.categoryImage);
        }
    }

}


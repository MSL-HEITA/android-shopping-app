package com.example.maandpadailygroceries.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.maandpadailygroceries.R;
import com.example.maandpadailygroceries.model.CartModel;
import com.example.maandpadailygroceries.model.ProductModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class CartAdapter  extends RecyclerView.Adapter<CartAdapter.CartViewHolder>{
    Context context;
    List<CartModel> cartModelList;

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.all_cart_items,parent,false);
        return new CartAdapter.CartViewHolder(view);
    }

    public CartAdapter(Context context, List<CartModel> cartModelList) {
        this.context = context;
        this.cartModelList = cartModelList;
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        holder.name.setText(cartModelList.get(position).getName());
        holder.price.setText(cartModelList.get(position).getPrice());
        holder.productImage.setImageResource(cartModelList.get(position).getImageUrl());



    }

    @Override
    public int getItemCount() {
        return cartModelList.size();
    }


    public static class CartViewHolder extends RecyclerView.ViewHolder {
        ImageView productImage, deleteImage;
        TextView price, name;
        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            productImage =  itemView.findViewById(R.id.imageView3);
            price = itemView.findViewById(R.id.name7);
            name = itemView.findViewById(R.id.name2);


        }
    }
}
package com.example.maandpadailygroceries.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.maandpadailygroceries.R;
import com.example.maandpadailygroceries.model.ProductModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.util.List;

public class ProductAdapter  extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder>{
    Context context;
    List<ProductModel> productModelList;


    @NonNull
    @Override
    public ProductAdapter.ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.all_product_row_items,parent,false);
        return new ProductAdapter.ProductViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.ProductViewHolder holder, int position) {
        holder.productImage.setImageResource(productModelList.get(position).getImageUrl());
        holder.name.setText(productModelList.get(position).getName());
        holder.price.setText(productModelList.get(position).getPrice());
        holder.qty.setText(productModelList.get(position).getQuantity());
        holder.unit.setText(productModelList.get(position).getUnit());

        holder.cartAdd.setOnClickListener(new View.OnClickListener(){
        @Override
            public void onClick(View v) {

            try {
                // get JSONObject from JSON file

                JSONObject obj = new JSONObject(loadJSONFromAsset());
                // create a json string will details of products
                String cartinput = "{\"name\":\" " + productModelList.get(position).getName() + " \" ,\"price\":\""+  productModelList.get(position).getPrice() + " \" ,\"image\": \"" + productModelList.get(position).getImageUrl() + "\"}";
                // fetch JSONArray named users
                JSONArray userArray = obj.getJSONArray("product");
                String i = userArray.toString().replace("[","");
                i = i.replace("]","");

                cartinput = cartinput.replace("}","},");

                String finalcart = "" + cartinput + i +  "";
                JSONObject  stringCart = new JSONObject(finalcart);

                Log.d("before array" ,stringCart.toString());
                userArray.put(stringCart);

                obj.put("product",userArray);
                Log.d("JSON" ,obj.toString() );

                writeJson(obj);

            } catch (JSONException e) {
                e.printStackTrace();
            }


            }

        });
    }

    public ProductAdapter(Context context, List<ProductModel> productModelList) {
        this.context = context;
        this.productModelList = productModelList;
    }

    @Override
    public int getItemCount() {
        return productModelList.size();
    }


    public static class ProductViewHolder extends RecyclerView.ViewHolder {
        ImageView productImage, cartAdd ;
        TextView name,price,qty,unit;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.productImage);
            name = itemView.findViewById(R.id.name4);
            price = itemView.findViewById(R.id.name5);
            qty = itemView.findViewById(R.id.name6);
            unit = itemView.findViewById(R.id.name3);
            cartAdd = itemView.findViewById(R.id.imageView6);

        }
    }

    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = context.openFileInput("cart.json");
            //InputStream is = context.getAssets().open("cart.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    public void writeJson(Object json){
        //OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput("cart.json", Context.MODE_PRIVATE));
         FileOutputStream outputStream ;
         String jsonString = json.toString();
        try {
        outputStream = context.openFileOutput("cart.json", Context.MODE_PRIVATE);
        outputStream.write(jsonString.getBytes());
        outputStream.close();
        Toast.makeText(context, "item added to cart", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
        e.printStackTrace();
    }
    }


}

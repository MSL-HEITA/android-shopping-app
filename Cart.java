package com.example.maandpadailygroceries;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.maandpadailygroceries.adapter.CartAdapter;

import com.example.maandpadailygroceries.model.CartModel;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class Cart extends AppCompatActivity {
    RecyclerView allCartRecycler;
    CartAdapter cartAdapter;
    List<CartModel> cartModelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        allCartRecycler = findViewById(R.id.allcart);

        // creating a new array object and assigning it to class list
        cartModelList= new ArrayList<>();


        TextView priceView = (TextView)findViewById(R.id.textView7);

        int totalPrice =0 ;

        try {
            // get JSONObject from JSON file
            JSONObject obj = new JSONObject(loadJSON());
            // fetch JSONArray named users
            JSONArray userArray = obj.getJSONArray("product");
            // implement for loop for getting users list data
            for (int i = 0; i < userArray.length(); i++) {
                // create a JSONObject for fetching single user data
                JSONObject userDetail = userArray.getJSONObject(i);
                // getting product details from a json file and create a instance cart model class and adding that to a list of type class
                cartModelList.add(new CartModel(userDetail.getString("name"),userDetail.getString("price"),userDetail.getInt("image")));
                String a = userDetail.getString("price").replace("N$ ","");
                totalPrice = totalPrice + Integer.valueOf(a.trim());

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        setCartRecycler(cartModelList);
        priceView.setText(String.valueOf(totalPrice));

    }

    private void setCartRecycler(List<CartModel> cartModeldataList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        allCartRecycler.setLayoutManager(layoutManager);
        cartAdapter = new CartAdapter(this, cartModeldataList);
        allCartRecycler.setAdapter(cartAdapter);

    }

    public String loadJSON() {
        String json = null;
        try {
            InputStream is = openFileInput("cart.json");
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

    public void backClick(View v){
        Intent i = new Intent(Cart.this,MainActivity.class);
        startActivity(i);

    }

    public void clearCart(View v){
        try {
            JSONObject obj = new JSONObject(loadJSONFromAsset());
            JSONArray userArray = new JSONArray(new ArrayList<String>());



            obj.put("product",userArray);
            Log.d("JSON" ,obj.toString() );

            writeJson(obj);



        }catch (JSONException e) {
            e.printStackTrace();
        }


    }

    public String loadJSONFromAsset() {
        String json = null;
        try {
            //InputStream is = context.openFileInput("cart.json");
            InputStream is = getAssets().open("cart.json");
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
            outputStream = openFileOutput("cart.json", Context.MODE_PRIVATE);
            outputStream.write(jsonString.getBytes());
            outputStream.close();
            Toast.makeText(this, "items removed from cart", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void buy_button(View v){
        Intent i = new Intent(Cart.this,Paymentmethod.class);
        startActivity(i);
    }
}
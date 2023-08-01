package com.example.maandpadailygroceries;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.maandpadailygroceries.model.CartModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ProductDetails extends AppCompatActivity {
    ImageView img;
    TextView productName,proPrice,Desc;
    String name,price,desc;


    int image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        // getting information from the main activity and assigning them to variables
        Intent i = getIntent();
        name = i.getStringExtra("name");
        image= i.getIntExtra("image",R.drawable.chicken);
        price= i.getStringExtra("price");
        desc= i.getStringExtra("desc");

        productName =findViewById(R.id.productName);
        img=findViewById(R.id.promage);
        proPrice=findViewById(R.id.prodPrice);
        Desc=findViewById(R.id.prodDesc);

        productName.setText(name);
        proPrice.setText(price);
        Desc.setText(desc);
        img.setImageResource(image);


    }
    public void backClick2(View v){
        Intent i = new Intent(ProductDetails.this,MainActivity.class);
        startActivity(i);


    }
    public void cartclick3(View v){
        Intent i = new Intent(ProductDetails.this,Cart.class);
        startActivity(i);


    }
    public String loadJSON() {
        String json = null;
        try {
            InputStream is = openFileInput("cart.json");
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
            outputStream = openFileOutput("cart.json", Context.MODE_PRIVATE);
            outputStream.write(jsonString.getBytes());
            outputStream.close();
            Toast.makeText(this, "item added to cart", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void addToCart(View v) {
        try {
            // get JSONObject from JSON file

            JSONObject obj = new JSONObject(loadJSON());
            // create a json string will details of products
            String cartinput = "{\"name\":\" " + name + " \" ,\"price\":\""+ price + " \" ,\"image\": \"" + image + "\"}";
            // fetch JSONArray named users
            JSONArray userArray = obj.getJSONArray("product");
            String i = userArray.toString().replace("[","");
            i = i.replace("]","");

            cartinput = cartinput.replace("}","},");

            String finalcart = "" + cartinput + i +  "";
            JSONObject  stringCart = new JSONObject(finalcart);


            userArray.put(stringCart);

            obj.put("product",userArray);


            writeJson(obj);

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }



}
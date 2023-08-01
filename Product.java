package com.example.maandpadailygroceries;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;


import com.example.maandpadailygroceries.adapter.ProductAdapter;

import com.example.maandpadailygroceries.model.ProductModel;

import java.util.ArrayList;
import java.util.List;

public class Product extends AppCompatActivity {
    RecyclerView allProductRecycler;
    ProductAdapter  productAdapter;
    List<ProductModel> productModelList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        allProductRecycler = findViewById(R.id.all_products_display);

        productModelList= new ArrayList<>();
        productModelList.add(new ProductModel("Chicken","Big juicy Chicken pieces ","N$ 54",
                "1.5","KG",R.drawable.chicken));
        productModelList.add(new ProductModel("Cheddar","crunchy Yummy","N$ 15",
                "750","G",R.drawable.cheddar));
        productModelList.add(new ProductModel("Milk","Fresh straight from the farm","N$ 18",
                "1","KG",R.drawable.milk));
        productModelList.add(new ProductModel("Sugar","That much needed energy","N$ 20",
                "1.5","KG",R.drawable.sugar));
        setProductRecycler(productModelList);

    }
    private void setProductRecycler(List<ProductModel> productdataList) {
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);
        allProductRecycler.setLayoutManager(layoutManager);
        productAdapter = new ProductAdapter(this, productdataList);
        allProductRecycler.setAdapter(productAdapter);

    }
    public void backClickProduct(View v){
        Intent back = new Intent(Product.this,MainActivity.class);
        startActivity(back);
        finish();
    }
    public void cartOnclick2(View v){
        Intent i = new Intent(Product.this,Cart.class);
        startActivity(i);
        finish();
    }
}
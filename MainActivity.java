package com.example.maandpadailygroceries;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.maandpadailygroceries.adapter.RecentlyViewedAdapter;
import com.example.maandpadailygroceries.adapter.discountProductAdapter;
import com.example.maandpadailygroceries.model.RecentlyViewed;
import com.example.maandpadailygroceries.model.discountedProducts;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView discountRecyclerView,recentlyViewedRecycler;
    discountProductAdapter discountAdapter;

    RecentlyViewedAdapter recentlyViewedAdapter;
    List<discountedProducts> discountedProductList ;

    List<RecentlyViewed> recentlyViewedList;
    ImageView allCategory;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        discountRecyclerView = findViewById(R.id.discountedRecycler);

        recentlyViewedRecycler = findViewById(R.id.recently_items);
        // creating a new instant of the discounted Products model class and add the class to a list of the class
        discountedProductList = new ArrayList<>();
        discountedProductList.add(new discountedProducts(1,R.drawable.monsterenergy));
        discountedProductList.add(new discountedProducts(2,R.drawable.milocheckers));
        discountedProductList.add(new discountedProducts(3,R.drawable.surf));



            // creating a new instant of the recently viewed model class and add the class to a list of the class
        recentlyViewedList = new ArrayList<>();
        recentlyViewedList.add(new RecentlyViewed("Chicken","Big juice Chicken pieces","N$ 54",
                "1.5","KG",R.drawable.chicken, R.drawable.chicken));
        recentlyViewedList.add(new RecentlyViewed("Cheddar","crunchy Yummy","N$ 15",
                "750","G",R.drawable.cheddar,R.drawable.cheddar));
        recentlyViewedList.add(new RecentlyViewed("Milk","Fresh straight from the farm","N$ 18",
                "1","KG",R.drawable.milk,R.drawable.milk));
        recentlyViewedList.add(new RecentlyViewed("Sugar","That much needed energy","N$ 20",
                "1.5","KG",R.drawable.sugar,R.drawable.sugar));



        setDiscountedRecycler(discountedProductList);

        setRecentlyRecycler(recentlyViewedList);

    }




        //setting discounted items the recyclerview
    private void setDiscountedRecycler(List<discountedProducts> dataList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        discountRecyclerView.setLayoutManager(layoutManager);
        discountAdapter = new discountProductAdapter(this , dataList);
        discountRecyclerView.setAdapter(discountAdapter );
    }


    //setting recently viewed items the recyclerview
    private void setRecentlyRecycler(List<RecentlyViewed> recentlyDataList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recentlyViewedRecycler.setLayoutManager(layoutManager);
        recentlyViewedAdapter = new RecentlyViewedAdapter(this , recentlyDataList);
        recentlyViewedRecycler.setAdapter(recentlyViewedAdapter);
    }


        // intent to sent user to the all products activity
    public void allProductdisplay(View v){
        Intent i = new Intent(MainActivity.this,Product.class);
        startActivity(i);
    }

    // intent to sent user to the card activity
    public void cartOnclick(View v){
        Intent i = new Intent(MainActivity.this,Cart.class);
        startActivity(i);
    }
}
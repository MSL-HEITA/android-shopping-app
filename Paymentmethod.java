package com.example.maandpadailygroceries;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class Paymentmethod extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

}

public void order(View v){
    Toast.makeText(this,"Your oder is on the way",Toast.LENGTH_LONG).show();
}

public void backClickproduct(View v){
    Intent i = new Intent(Paymentmethod.this,Product.class);
    startActivity(i);
}
}

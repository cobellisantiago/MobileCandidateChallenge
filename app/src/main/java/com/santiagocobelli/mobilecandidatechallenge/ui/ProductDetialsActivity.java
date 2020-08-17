package com.santiagocobelli.mobilecandidatechallenge.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.santiagocobelli.mobilecandidatechallenge.R;
import com.santiagocobelli.mobilecandidatechallenge.domain.Product;

public class ProductDetialsActivity extends AppCompatActivity {

    private Product product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detials);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        Product product = (Product) intent.getSerializableExtra("productSelected");

        // Capture the layout's TextView and set the string as its text
        TextView textView = findViewById(R.id.textViewProductTitle);
        textView.setText(product.getTitle());
    }
}
package com.santiagocobelli.mobilecandidatechallenge.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.santiagocobelli.mobilecandidatechallenge.R;
import com.santiagocobelli.mobilecandidatechallenge.domain.Product;
import com.santiagocobelli.mobilecandidatechallenge.utils.GlideApp;

public class ProductDetialsActivity extends AppCompatActivity {

    private Product product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detials);

        Intent intent = getIntent();
        Product product = (Product) intent.getSerializableExtra("productSelected");

        TextView textViewTitle = findViewById(R.id.textViewTitle);
        TextView textViewCondition = findViewById(R.id.textViewCondition);
        TextView textViewSoldQuantity = findViewById(R.id.textViewSoldQuantity);
        ImageView imageView = findViewById(R.id.imageView);
        TextView textViewPrice = findViewById(R.id.textViewPrice);

        textViewTitle.setText(product.getTitle());
        textViewCondition.setText(product.getConditionNew() == "new" ? "Nuevo" : "Usado" );
        textViewSoldQuantity.setText(product.getSoldQuantity() + " Vendidos");
        textViewPrice.setText(getString(R.string.productPrice,product.getPrice().intValue()));
        GlideApp.with(this).load(product.getImageUrl())
                .apply(new RequestOptions()
                        .dontTransform()
                        .format(DecodeFormat.PREFER_ARGB_8888)
                        .override(Target.SIZE_ORIGINAL))
                .into(imageView);
    }
}
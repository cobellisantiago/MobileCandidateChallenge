package com.santiagocobelli.mobilecandidatechallenge.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.app.Application;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.santiagocobelli.mobilecandidatechallenge.R;
import com.santiagocobelli.mobilecandidatechallenge.domain.Product;
import com.santiagocobelli.mobilecandidatechallenge.repositories.ProductRepository;

import java.util.List;

public class SearchProductsActivity extends AppCompatActivity {

    List<Product> listaDataSet;
    RecyclerView listViewProducts;
    ProductRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_products);
        listViewProducts = (RecyclerView) findViewById(R.id.listViewProducts);
    }

    public void OnClickBuscar(View view) {
        EditText editText = (EditText) findViewById(R.id.editTextSearchText);
        String searchText = editText.getText().toString();
        ProductRepository.getInstance().getProducts(miHandler, searchText);
    }

    Handler miHandler = new Handler(Looper.myLooper()){
        @Override
        public void handleMessage(Message msg) {
            Log.d("APP_2","Vuelve al handler " + msg.arg1);
            listaDataSet = ProductRepository.getInstance().getProducts();
            switch (msg.arg1 ){
                case ProductRepository.GETTING_PRODUCTS:
                    if(!listaDataSet.isEmpty()) {
                        adapter = new ProductRecyclerViewAdapter(listaDataSet);
                        listViewProducts.setAdapter(adapter);
                        listViewProducts.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    }
                    //TODO Buscar manera de que espere la respuesta para cargar la lista
                    break;
                case ProductRepository.ERROR_GETTING_PRODUCTS:
                    break;
            }
        }
    };
}
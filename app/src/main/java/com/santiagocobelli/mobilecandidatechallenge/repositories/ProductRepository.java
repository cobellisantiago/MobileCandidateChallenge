package com.santiagocobelli.mobilecandidatechallenge.repositories;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.santiagocobelli.mobilecandidatechallenge.domain.Product;
import com.santiagocobelli.mobilecandidatechallenge.repositories.rest.ProductRest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProductRepository {


        public static String SERVER = "https://api.mercadolibre.com/sites/MLA/";
        private List<Product> products;

        public static final int GETTING_PRODUCTS = 4;
        public static final int ERROR_GETTING_PRODUCTS = 9;

        private static ProductRepository INSTANCE;

    public ProductRepository(){}

        public static ProductRepository getInstance(){

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);


        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);

        if(INSTANCE == null){
            INSTANCE = new ProductRepository();
            INSTANCE.configurarRetrofit(httpClient);
            INSTANCE.products = new ArrayList<Product>();
        }
        return INSTANCE;
    }

        private Retrofit rf;
        private ProductRest productRest;

        private void configurarRetrofit(OkHttpClient.Builder httpClient){
        this.rf = new Retrofit.Builder().baseUrl(SERVER).addConverterFactory(GsonConverterFactory.create()).client(httpClient.build()).build();
        this.productRest = this.rf.create(ProductRest.class);
    }

        public void getProducts(final Handler h, String searchText){
        Call<Object> apiCall = this.productRest.getProducts(searchText);
        apiCall.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object > call, Response<Object> httpResponse) {
                if(httpResponse.isSuccessful()){
                    try {
                    JSONObject response = new JSONObject((Map) httpResponse.body());
                    JSONArray results = response.getJSONArray("results");
                    Type listType = new TypeToken<List<Product>>() {
                        }.getType();
                    products = new Gson().fromJson(String.valueOf(results),listType);
                    Message m = new Message();
                    m.arg1 = GETTING_PRODUCTS;
                    h.sendMessage(m);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<Object > call, Throwable t) {
                Message m = new Message();
                m.arg1 = ERROR_GETTING_PRODUCTS;
                h.sendMessage(m);
            }
        });
    }



        public List<Product> getProducts() {
        return products;
    }

}

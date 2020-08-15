package com.santiagocobelli.mobilecandidatechallenge.repositories.rest;

import com.google.gson.JsonObject;
import com.santiagocobelli.mobilecandidatechallenge.domain.Product;

import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ProductRest {

        @GET("search")
        Call<Object> getProducts(@Query("q") String searchText);

}

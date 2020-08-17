package com.santiagocobelli.mobilecandidatechallenge.domain;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.net.URL;

public class Product implements Serializable {

    @SerializedName("id")
    private String id;
    @SerializedName("title")
    private String title;
    @SerializedName("category_id")
    private String categoryId;
    @SerializedName("price")
    private Double price;
    @SerializedName("thumbnail")
    private String imageUrl;
    @SerializedName("condition")
    private String ConditionNew;
    @SerializedName("sold_quantity")
    private Integer soldQuantity;

    public Product() {
    }

    public Product(String id, String title, String categoryId, Double price, String imageUrl, String conditionNew, Integer soldQuantity) {
        this.id = id;
        this.title = title;
        this.categoryId = categoryId;
        this.price = price;
        this.imageUrl = imageUrl;
        ConditionNew = conditionNew;
        this.soldQuantity = soldQuantity;
    }

    public String getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public String getCategoryId() {
        return categoryId;
    }
    public Double getPrice() {
        return price;
    }
    public String getImageUrl(){ return imageUrl; }
    public String getConditionNew() {
        return ConditionNew;
    }
    public Integer getSoldQuantity() {
        return soldQuantity;
    }

    public void setId(String id) {
        this.id = id;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }
    public void setPrice(Double price) {
        this.price = price;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    public void setConditionNew(String conditionNew) {
        ConditionNew = conditionNew;
    }
    public void setSoldQuantity(Integer soldQuantity) {
        this.soldQuantity = soldQuantity;
    }
}

package com.sample.finalrazorpay.models.customer;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SingleCustomerModel {
    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("entity")
    @Expose
    public String entity;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("email")
    @Expose
    public String email;
    @SerializedName("contact")
    @Expose
    public String contact;
    @SerializedName("fail_existing")
    @Expose
    public boolean failExisting;
    @SerializedName("gstin")
    @Expose
    public String gstin;
    @SerializedName("created_at")
    @Expose
    public int createdAt;
}

package com.sample.finalrazorpay.models.customer;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllCustomersModel {
    @SerializedName("entity")
    @Expose
    public String entity;
    @SerializedName("count")
    @Expose
    public String count;
    @SerializedName("items")
    @Expose
    public List<SingleCustomerModel> singleCustomerModelList;

}

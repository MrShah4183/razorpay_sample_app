package com.sample.finalrazorpay.api;

/*import com.vasyerp.selfcheckout.models.login.CompanyCustomerBody;
import com.vasyerp.selfcheckout.models.login.LogIn;
import com.vasyerp.selfcheckout.models.orderlist.OrdersListResponse;
import com.vasyerp.selfcheckout.models.ordersummary.OrderSummary;
import com.vasyerp.selfcheckout.models.product.GetAllProducts;
import com.vasyerp.selfcheckout.models.product.Product;*/

import com.sample.finalrazorpay.models.customer.GetAllCustomersModel;
import com.sample.finalrazorpay.models.customer.RazorpayCustomer;
import com.sample.finalrazorpay.models.customer.SingleCustomerModel;
import com.sample.finalrazorpay.models.order.GetAllOrdersModel;
import com.sample.finalrazorpay.models.order.PostOrderData;
import com.sample.finalrazorpay.models.order.SingleOrderModel;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface Api {

    //https://api.razorpay.com/v1/customers
    @POST("customers")
    Call<SingleCustomerModel> createCustomer(
            @Body RazorpayCustomer razorpayCustomer,
            @Header("Authorization") String authKey
    );

    @GET("customers")
    Call<GetAllCustomersModel> getCustomersList(@Header("Authorization") String authKey);

    @GET("customers/{customerId}")
    Call<SingleCustomerModel> getSingleCustomer(
            @Path(value = "customerId", encoded = true) String customerId,
            @Header("Authorization") String authKey
    );

    @PUT("customers/{customerId}")
    Call<SingleCustomerModel> editCustomer(
            @Path(value = "customerId", encoded = true) String customerId,
            @Body JSONObject razorpayCustomer,
            @Header("Authorization") String authKey
    );

    @POST("orders")
    Call<SingleOrderModel> createOrder(
            @Body PostOrderData postOrderData,
            @Header("Authorization") String authKey
    );

    @GET("orders")
    Call<GetAllOrdersModel> getOrdersList(@Header("Authorization") String authKey);

    @GET("orders/{orderId}")
    Call<SingleOrderModel> getSingleOrder(
            @Path(value = "orderId", encoded = true) String orderId,
            @Header("Authorization") String authKey
    );

    @PUT("customers/{orderId}/payments")
    Call<SingleOrderModel> fetchPaymentByOrderId(
            @Path(value = "orderId", encoded = true) String orderId,
            //@Body JSONObject razorpayCustomer,
            @Header("Authorization") String authKey
    );



    /*@GET("/api/searchtypes/{Id}/filters")
Call<FilterResponse> getFilterList(
          @Path("Id") long customerId,*/


    //true
    /*{
    "id": "cust_JA30a65yWa9Boz",
    "entity": "customer",
    "name": "Shah Meet",
    "email": "shahmeeto1o82o@gmail.com",
    "contact": "8238757955",
    "gstin": null,
    "notes": [],
    "created_at": 1647933043
    }*/

    //false
    /*{
    "error": {
        "code": "BAD_REQUEST_ERROR",
        "description": "Customer already exists for the merchant",
        "source": "NA",
        "step": "NA",
        "reason": "NA",
        "metadata": {}
    }
    }*/

    //comapany loging using qr code scanning
    /*@POST("mpos/api/v2/get/companydetails")
    Call<ApiResponse<LogIn>> companyLogin(@Body CompanyCustomerBody companyCustomerBody);

    @GET("mpos/api/v2/getproductdata")
    Call<ApiResponse<List<GetAllProducts>>> getAllProductList(@Query("companyId") String companyId);

    @GET("mpos/api/v2/getproductbybarcode")
    Call<ApiResponse<Product>> getProductByBarCode(
            @Query("branchId") String branchId,
            @Query("barcode") String barcode,
            @Query("companyId") String companyId,
            @Query("yearinterval") String yearinterval);

    @GET("mpos/api/v2/getproductbyproductId")
    Call<ApiResponse<Product>> getProductByProductId(
            @Query("branchId") String branchId,
            @Query("productVarientId") String productVarientId,
            @Query("companyId") String companyId,
            @Query("yearinterval") String yearinterval);

    @GET("mpos/api/v2/allorders/")
    Call<ApiResponse<OrdersListResponse>> getAllOrderList(
            @Query("pageNo") int pageNo,
            @Query("limit") int limit,
            @Query("branchId") String branchId,
            @Query("companyId") String companyId,
            @Query("contactId") int contactId);


    @GET("/mpos/api/v2/orderdetails")
    Call<ApiResponse<OrderSummary>> getOrderSummary(
            @Query("branchId") int branchId,
            @Query("companyId") int companyId,
            @Query("salesId") int salesId);*/

}
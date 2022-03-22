package com.sample.finalrazorpay;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;
import com.sample.finalrazorpay.api.Api;
import com.sample.finalrazorpay.api.ApiAuthentication;
import com.sample.finalrazorpay.api.ApiGenerator;
import com.sample.finalrazorpay.databinding.ActivityMainBinding;
import com.sample.finalrazorpay.models.customer.GetAllCustomersModel;
import com.sample.finalrazorpay.models.customer.RazorpayCustomer;
import com.sample.finalrazorpay.models.customer.SingleCustomerModel;
import com.sample.finalrazorpay.models.order.GetAllOrdersModel;
import com.sample.finalrazorpay.models.order.PostOrderData;
import com.sample.finalrazorpay.models.order.SingleOrderModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements PaymentResultListener {
    private final String TAG = MainActivity.this.getClass().getSimpleName();
    ActivityMainBinding activityMainBinding;
    KProgressHUD kProgressHUD;
    RazorpayCustomer razorpayCustomerCreate;
    PostOrderData postOrderData;
    Api apiInterface;
    String orderId = "";
    static String baseUrl = "https://api.razorpay.com/v1/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(activityMainBinding.getRoot());
        kProgressHUD = KProgressHUD.create(MainActivity.this);
        kProgressHUD.setCancellable(false);
        kProgressHUD.setDimAmount(0.25f);

        apiInterface = ApiGenerator.getApi(baseUrl).create(Api.class);


        activityMainBinding.btn.setOnClickListener(v -> {
            razorpayCustomerCreate = new RazorpayCustomer();
            razorpayCustomerCreate.setName("MrShahss");
            razorpayCustomerCreate.setEmail("mrshah1234ss@gmail.com");
            razorpayCustomerCreate.setContact("7408509689");
            razorpayCustomerCreate.setFailExisting(false);
            //Call<ApiResponse<Product>> callProductByBarcode = api.getProductByBarCode(branchId, productId, companyId, financialYear);
            Call<SingleCustomerModel> callCreateCustomer = apiInterface.createCustomer(
                    razorpayCustomerCreate,
                    ApiAuthentication.getAuthToken()
            );

            callCreateCustomer.enqueue(new Callback<SingleCustomerModel>() {
                @Override
                public void onResponse(@NonNull Call<SingleCustomerModel> call, @NonNull Response<SingleCustomerModel> response) {
                    /*Log.e(TAG, "onResponse: success" + response.toString());
                    Log.e(TAG, "onResponse: success1" + response.isSuccessful());
                    Log.e(TAG, "onResponse: success2" + response.body());
                    Log.e(TAG, "onResponse: success3" + response.message());
                    Log.e(TAG, "onResponse: success4" + response.raw());*/

                    //Log.e("TAG", "response gson: " + new Gson().toJson(response.body()));

                    if (response.isSuccessful()) {
                        Log.e(TAG, "onResponse: create customer");
                        assert response.body() != null;
                        Log.e(TAG, "onResponse: " + response.body().getId());
                    } else {
                        Log.e(TAG, "onResponse: not create customer");
                    }
                }

                @Override
                public void onFailure(@NonNull Call<SingleCustomerModel> call, @NonNull Throwable t) {
                    Log.e(TAG, "onResponse: fail" + t.getMessage());
                }
            });
        });

        activityMainBinding.btnGetCustomer.setOnClickListener(v -> {
            Call<GetAllCustomersModel> callGetAllCustomersList = apiInterface.getCustomersList(
                    ApiAuthentication.getAuthToken()
            );

            callGetAllCustomersList.enqueue(new Callback<GetAllCustomersModel>() {
                @Override
                public void onResponse(@NonNull Call<GetAllCustomersModel> call, @NonNull Response<GetAllCustomersModel> response) {
                    if (response.isSuccessful()) {
                        assert response.body() != null;
                        Log.e(TAG, "onResponse: getAllCustomerListCount" + response.body().getCount());
                        Log.e(TAG, "onResponse: getAllCustomerListCount" + response.body().getSingleCustomerModelList().size());
                        Log.e(TAG, "onResponse: data" + Arrays.toString(response.body().getSingleCustomerModelList().toArray()));
                    } else {
                        Log.e(TAG, "onResponse: getAllCustomerListCount null no data found");
                    }
                }

                @Override
                public void onFailure(@NonNull Call<GetAllCustomersModel> call, @NonNull Throwable t) {
                    Log.e(TAG, "onFailure: " + t.getMessage());
                }
            });
        });

        activityMainBinding.btnGetSingleCustomer.setOnClickListener(v -> {
            String customerId = "cust_JA1SgTFoq40m55";
            Call<SingleCustomerModel> callGetSingleCustomer = apiInterface.getSingleCustomer(
                    customerId,
                    ApiAuthentication.getAuthToken()
            );

            callGetSingleCustomer.enqueue(new Callback<SingleCustomerModel>() {
                @Override
                public void onResponse(@NonNull Call<SingleCustomerModel> call, @NonNull Response<SingleCustomerModel> response) {
                    if (response.isSuccessful()) {
                        Log.e(TAG, "onResponse: getSingleCustomerData");
                        assert response.body() != null;
                        Log.e(TAG, "onResponse: " + response.body().getId());
                        Log.e(TAG, "onResponse: " + response.body().getName());
                    } else {
                        Log.e(TAG, "onResponse: getSingleCustomerData null");
                    }
                }

                @Override
                public void onFailure(@NonNull Call<SingleCustomerModel> call, @NonNull Throwable t) {
                    Log.e(TAG, "onFailure: " + t.getMessage());
                }
            });
        });

        activityMainBinding.btnUpdateCustomer.setOnClickListener(v -> {
            razorpayCustomerCreate = new RazorpayCustomer();
            razorpayCustomerCreate.setName("MrCrazy");
            //razorpayCustomerCreate.setEmail("mrshah1234@gmail.com");
            //razorpayCustomerCreate.setContact("7408509635");
            //razorpayCustomerCreate.setFailExisting(false);

            Gson gson = new Gson();
            String json = gson.toJson(razorpayCustomerCreate);
            try {
                JSONObject jsonObject = new JSONObject(json);
                String customerId = "cust_JA1SgTFoq40m55";
                Call<SingleCustomerModel> callEditCustomer = apiInterface.editCustomer(
                        customerId,
                        jsonObject,
                        ApiAuthentication.getAuthToken()
                );

                callEditCustomer.enqueue(new Callback<SingleCustomerModel>() {
                    @Override
                    public void onResponse(@NonNull Call<SingleCustomerModel> call, @NonNull Response<SingleCustomerModel> response) {
                        if (response.isSuccessful()) {
                            Log.e(TAG, "onResponse: update customer");
                            assert response.body() != null;
                            Log.e(TAG, "onResponse: " + response.body().getId());
                            Log.e(TAG, "onResponse: " + response.body().getName());
                        } else {
                            Log.e(TAG, "onResponse: not update customer");
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<SingleCustomerModel> call, @NonNull Throwable t) {
                        Log.e(TAG, "onFailure: " + t.getMessage());
                    }
                });
            } catch (JSONException e) {
                e.printStackTrace();
            }
        });

        activityMainBinding.btnCreateOrder.setOnClickListener(v -> {
            postOrderData = new PostOrderData();
            Random random = new Random();
            int totalAmount = random.nextInt(999999 - 10000) + 10000;
            //int intAmount = Math.round(Float.parseFloat(strAmount) * 100);
            //totalAmount = Math.round(Float.parseFloat(String.valueOf(totalAmount)) * 100);
            postOrderData.setAmount((double) totalAmount);
            postOrderData.setCurrency("INR");
            int no = random.nextInt(999 - 100) + 100;
            postOrderData.setReceipt("OrderNo" + no);

            Call<SingleOrderModel> callCreateOrder = apiInterface.createOrder(
                    postOrderData,
                    ApiAuthentication.getAuthToken()
            );

            callCreateOrder.enqueue(new Callback<SingleOrderModel>() {
                @Override
                public void onResponse(@NonNull Call<SingleOrderModel> call, @NonNull Response<SingleOrderModel> response) {
                    if (response.isSuccessful()) {
                        Log.e(TAG, "onResponse: order created");
                        assert response.body() != null;
                        Log.e(TAG, "onResponse: order data" + response.body().getId());
                        Log.e(TAG, "onResponse: order data" + response.body().toString());
                        orderId = response.body().getId();

                        Toast.makeText(MainActivity.this, "Your Payment Process Is Started", Toast.LENGTH_SHORT).show();

                        new Handler().postDelayed(() -> {
                            Log.e(TAG, "run: payment start");
                            makePayments(response.body());
                        }, 2000);

                    } else {
                        Log.e(TAG, "onResponse: order create fail");
                    }
                }

                @Override
                public void onFailure(@NonNull Call<SingleOrderModel> call, @NonNull Throwable t) {
                    Log.e(TAG, "onFailure: " + t.getMessage());
                }
            });
        });

        activityMainBinding.btnOrdersList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<GetAllOrdersModel> callAllOrdersList = apiInterface.getOrdersList(
                        ApiAuthentication.getAuthToken()
                );

                callAllOrdersList.enqueue(new Callback<GetAllOrdersModel>() {
                    @Override
                    public void onResponse(@NonNull Call<GetAllOrdersModel> call, @NonNull Response<GetAllOrdersModel> response) {
                        if (response.isSuccessful()) {
                            Log.e(TAG, "onResponse: getAllOrders");
                            assert response.body() != null;
                            Log.e(TAG, "onResponse: total" + response.body().getCount());
                            Log.e(TAG, "onResponse: array" + Arrays.toString(response.body().getSingleOrderModelList().toArray()));
                            /*Log.e(TAG, "onResponse: order data" + response.body().getId());
                            Log.e(TAG, "onResponse: order data" + response.body().toString());*/
                        } else {
                            Log.e(TAG, "onResponse: getAllOrders Fail");
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<GetAllOrdersModel> call, @NonNull Throwable t) {
                        Log.e(TAG, "onFailure: " + t.getMessage());
                    }
                });
            }
        });

        activityMainBinding.btnSingleOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String orderId = "";
                String oId = "order_JA6f63MFtvaDGP";
                if (orderId.trim().isEmpty()) {
                    oId = orderId;
                }
                Call<SingleOrderModel> callSingleOrder = apiInterface.getSingleOrder(
                        oId,
                        ApiAuthentication.getAuthToken()
                );

                callSingleOrder.enqueue(new Callback<SingleOrderModel>() {
                    @Override
                    public void onResponse(@NonNull Call<SingleOrderModel> call, @NonNull Response<SingleOrderModel> response) {
                        if (response.isSuccessful()) {
                            Log.e(TAG, "onResponse: getSingle Order Data");
                            assert response.body() != null;
                            Log.e(TAG, "onResponse: " + response.body().getId());
                            Log.e(TAG, "onResponse: " + response.body().getCurrency());
                        } else {
                            Log.e(TAG, "onResponse: getSingle Order Data fail");
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<SingleOrderModel> call, @NonNull Throwable t) {
                        Log.e(TAG, "onFailure: " + t.getMessage());
                    }
                });
            }
        });

        activityMainBinding.btnFetchPaymentByOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String oId = "order_JA6f63MFtvaDGP";
                if (orderId.trim().isEmpty()) {
                    oId = orderId;
                }

                Log.e(TAG, "onClick: set After few min");
                //Call<SingleOrderModel> cal
            }
        });

    }

    private void makePayments(SingleOrderModel singleOrderModel) {
        final Activity activity = this;
        final Checkout co = new Checkout();
        co.setKeyID("rzp_test_goYqawczzlm2Yp");


        try {
            JSONObject options = new JSONObject();
            options.put("name", "My Shah");
            options.put("description", "Checking Order API ");
            options.put("image", "https://i.postimg.cc/sxc8sSTj/IMG-20200929-WA0000.jpg");
            options.put("currency", singleOrderModel.getCurrency());
            options.put("amount", singleOrderModel.getAmount());
            options.put("order_id", singleOrderModel.getId());
            //notifyModel.setEmail(true);
            //        notifyModel.setSms(true);

            JSONObject preFill = new JSONObject();
            preFill.put("email", "shahmeeto1o82o@gmail.com");
            preFill.put("contact", "7575061808");
            options.put("prefill", preFill);

            JSONObject notify = new JSONObject();
            notify.put("sms", true);
            notify.put("email", true);
            options.put("notify", notify);

            options.put("reminder_enable", true);

            co.open(activity, options);
        } catch (Exception e) {
            Toast.makeText(activity, "Error in payment: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    @Override
    public void onPaymentSuccess(String s) {
        activityMainBinding.tv.setText("Successful payment ID :" + s);
    }

    @Override
    public void onPaymentError(int i, String s) {
        activityMainBinding.tv.setText("Failed and cause is :" + s);
        Toast.makeText(MainActivity.this, "Fail some reason.", Toast.LENGTH_SHORT).show();
    }
}
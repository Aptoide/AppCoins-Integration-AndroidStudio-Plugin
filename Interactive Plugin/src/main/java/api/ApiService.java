package api;

import com.google.gson.Gson;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

public class ApiService {
    private static final String BASE_URL = "http://localhost:8000/";

    public String makeApiCall() {
        // Initialize Retrofit client
        Retrofit retrofit = RetrofitClient.getClient(BASE_URL);

        // Create an instance of the API interface
        BillingIntegrationAPI api = retrofit.create(BillingIntegrationAPI.class);

        // Create a BillingRequest object
        BillingRequest request = new BillingRequest("1", "common", "implementation(\"io.catappult:android-appcoins-billing:\")");

        // Make the API call
        Call<ResponseBody> call = api.postBillingIntegration(request);

        final String[] result = {null};
        final CountDownLatch latch = new CountDownLatch(1);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    // Handle successful response
                    //System.out.println("Response: " + response.body().toString());
                    try {
                        String responseBody = response.body().string();
                        Gson gson = new Gson();
                        BillingResponse billingResponse = gson.fromJson(responseBody, BillingResponse.class);
                        result[0] = billingResponse.getNewCode();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                } else {
                    // Handle unsuccessful response
                    //System.out.println("Request failed with code: " + response.code());
                    result[0] = "Request failed with code: " + response.code();
                }
                latch.countDown();

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                // Handle failure
                //t.printStackTrace();
                result[0] = t.getMessage();
                latch.countDown();
            }
        });

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return result[0];
    }
}
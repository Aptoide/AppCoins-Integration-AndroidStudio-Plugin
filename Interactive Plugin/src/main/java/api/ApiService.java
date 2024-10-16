package api;

import com.google.gson.Gson;
import com.intellij.openapi.ui.Messages;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

public class ApiService {
    private static final String BASE_URL = "https://billing-integration-copilot.aptoide.com/";

    public String changesToGradle2(String snippetContext) {
        // Initialize Retrofit client
        Retrofit retrofit = RetrofitClient.getClient(BASE_URL);

        // Create an instance of the API interface
        BillingIntegrationAPI api = retrofit.create(BillingIntegrationAPI.class);

        // Create a BillingRequest object
        BillingRequest request = new BillingRequest("1", "common", snippetContext);

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

    public String changesToAndroidManifest(String snippetContext) {
        //permissions MANIFEST

        // Initialize Retrofit client
        Retrofit retrofit = RetrofitClient.getClient(BASE_URL);

        // Create an instance of the API interface
        BillingIntegrationAPI api = retrofit.create(BillingIntegrationAPI.class);

        // Create a BillingRequest object
        BillingRequest request = new BillingRequest("2", "common", snippetContext);

        // Make the API call
        Call<ResponseBody> call = api.postBillingIntegration(request);

        /**Messages.showMessageDialog(
                "Request: " + call.request().headers() + "|Body: " + call.request().body(),
                "File Info",
                Messages.getInformationIcon()
        );**/

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

    public String changesToAndroidManifest2(String snippetContext) {
        //queries MANIFEST

        // Initialize Retrofit client
        Retrofit retrofit = RetrofitClient.getClient(BASE_URL);

        // Create an instance of the API interface
        BillingIntegrationAPI api = retrofit.create(BillingIntegrationAPI.class);

        // Create a BillingRequest object
        BillingRequest request = new BillingRequest("3", "common", snippetContext);

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

    public String skuDetailsResponseListener(String snippetContext) {


        // Initialize Retrofit client
        Retrofit retrofit = RetrofitClient.getClient(BASE_URL);

        // Create an instance of the API interface
        BillingIntegrationAPI api = retrofit.create(BillingIntegrationAPI.class);

        // Create a BillingRequest object
        BillingRequest request = new BillingRequest("4.1", "java", snippetContext);

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

    public String queryInapps(String snippetContext) {


        // Initialize Retrofit client
        Retrofit retrofit = RetrofitClient.getClient(BASE_URL);

        // Create an instance of the API interface
        BillingIntegrationAPI api = retrofit.create(BillingIntegrationAPI.class);

        // Create a BillingRequest object
        BillingRequest request = new BillingRequest("4.2", "java", snippetContext);

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

    public String startPurchase(String snippetContext) {


        // Initialize Retrofit client
        Retrofit retrofit = RetrofitClient.getClient(BASE_URL);

        // Create an instance of the API interface
        BillingIntegrationAPI api = retrofit.create(BillingIntegrationAPI.class);

        // Create a BillingRequest object
        BillingRequest request = new BillingRequest("5", "java", snippetContext);

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

    public String onActivityResult(String snippetContext) {


        // Initialize Retrofit client
        Retrofit retrofit = RetrofitClient.getClient(BASE_URL);

        // Create an instance of the API interface
        BillingIntegrationAPI api = retrofit.create(BillingIntegrationAPI.class);

        // Create a BillingRequest object
        BillingRequest request = new BillingRequest("6.1", "java", snippetContext);

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


    public String purchasesUpdatedListener(String snippetContext) {


        // Initialize Retrofit client
        Retrofit retrofit = RetrofitClient.getClient(BASE_URL);

        // Create an instance of the API interface
        BillingIntegrationAPI api = retrofit.create(BillingIntegrationAPI.class);

        // Create a BillingRequest object
        BillingRequest request = new BillingRequest("6.2", "java", snippetContext);

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

    public String consumeResponseListener(String snippetContext) {


        // Initialize Retrofit client
        Retrofit retrofit = RetrofitClient.getClient(BASE_URL);

        // Create an instance of the API interface
        BillingIntegrationAPI api = retrofit.create(BillingIntegrationAPI.class);

        // Create a BillingRequest object
        BillingRequest request = new BillingRequest("7", "java", snippetContext);

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
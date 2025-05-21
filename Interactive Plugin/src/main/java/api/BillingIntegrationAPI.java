package api;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import okhttp3.ResponseBody;

public interface BillingIntegrationAPI {
    @POST("billing-integration")
    Call<ResponseBody> postBillingIntegration(@Body BillingRequest request);
}
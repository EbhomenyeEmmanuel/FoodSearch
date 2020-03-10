import com.esq.foodsearch.SearchListResponse;

import java.util.List;
import retrofit.Callback;
import retrofit.http.GET;

public interface FatSecretApiInterface {
    // API's endpoints
    @GET("/Default.aspx?screen=rapiref2&method=food.get")
    public void getResultsList( Callback<List<SearchListResponse>> callback);
    // SearchListResponse is POJO class to get the data from API, In above method we use List<SearchListResponse>
    // because the data in our API is starting from JSONArray
    // and callback is used to get the response from api and it will set it in our POJO class
}

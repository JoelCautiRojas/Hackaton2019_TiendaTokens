package joel.cauti.hackaton2019_tienda.data.services;

import joel.cauti.hackaton2019_tienda.BuildConfig;
import joel.cauti.hackaton2019_tienda.data.utils.RestUtil;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class TokenServiceManager {

    private static String BASE_URL = BuildConfig.URL_HOST;
    private static TokenService apiService;

    public static TokenService getApiService(String token){
        OkHttpClient httpClient = RestUtil.getHttpClientToken(token);
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(BASE_URL);
        builder.client(httpClient);
        builder.addConverterFactory(JacksonConverterFactory.create(RestUtil.getObjectMapperConfiguration()));
        Retrofit retrofit = builder.build();
        apiService = retrofit.create(TokenService.class);
        return apiService;
    }

    public static TokenService getApiService(){
        OkHttpClient httpClient = RestUtil.getHttpClient();
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(BASE_URL);
        builder.client(httpClient);
        builder.addConverterFactory(JacksonConverterFactory.create(RestUtil.getObjectMapperConfiguration()));
        Retrofit retrofit = builder.build();
        apiService = retrofit.create(TokenService.class);
        return apiService;
    }
}

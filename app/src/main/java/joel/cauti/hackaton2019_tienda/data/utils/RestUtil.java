package joel.cauti.hackaton2019_tienda.data.utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

import joel.cauti.hackaton2019_tienda.BuildConfig;
import okhttp3.ConnectionSpec;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

public class RestUtil {

    public static OkHttpClient getHttpClient() {

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.readTimeout(300, TimeUnit.SECONDS);
        httpClient.connectTimeout(300, TimeUnit.SECONDS);
        httpClient.connectionSpecs(Arrays.asList(ConnectionSpec.MODERN_TLS, ConnectionSpec.CLEARTEXT));
        if (BuildConfig.DEBUG) {
            httpClient.addInterceptor(getHttpLoggingInterceptor());
        }
        httpClient.cache(null);
        return httpClient.build();
    }

    public static OkHttpClient getHttpClientToken(final String token) {
        if (token.trim().length() > 0){
            final OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            httpClient.readTimeout(300, TimeUnit.SECONDS);
            httpClient.connectTimeout(300, TimeUnit.SECONDS);
            httpClient.connectionSpecs(Arrays.asList(ConnectionSpec.MODERN_TLS, ConnectionSpec.CLEARTEXT));
            httpClient.addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request request = chain.request().newBuilder().addHeader("Authorization", "Bearer " + token).build();
                    return chain.proceed(request);
                }
            });
            if (BuildConfig.DEBUG) {
                httpClient.addInterceptor(getHttpLoggingInterceptor());
            }
            httpClient.cache(null);
            return httpClient.build();
        }else{
            return getHttpClient();
        }
    }

    private static HttpLoggingInterceptor getHttpLoggingInterceptor(){
        HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor();
        logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return logInterceptor;
    }

    public static ObjectMapper getObjectMapperConfiguration() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        return mapper;
    }
}

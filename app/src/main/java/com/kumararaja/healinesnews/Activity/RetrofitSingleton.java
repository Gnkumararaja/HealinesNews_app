package com.kumararaja.healinesnews.Activity;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kumararaja.healinesnews.Apis;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitSingleton {

    private static HttpLoggingInterceptor logging;
    private static Retrofit retrofit;
    private static OkHttpClient.Builder httpClient;

    static Gson gson=new GsonBuilder()
            .setLenient()
            .create();

    private static Retrofit.Builder builder=new Retrofit.Builder()
            .baseUrl(Apis.URL)
            .addConverterFactory(GsonConverterFactory.create());

    public static <T> T createService(Class<T> serviceClass){
        if (logging==null){
            logging=new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        }
        if (httpClient==null){
            httpClient=new OkHttpClient.Builder();
            httpClient.addInterceptor(logging);
            httpClient.retryOnConnectionFailure(true);
            httpClient.connectTimeout(2, TimeUnit.MINUTES);
            httpClient.readTimeout(2,TimeUnit.MINUTES);
        }

        retrofit=builder.client(httpClient.retryOnConnectionFailure(true).build()).build();
        return retrofit.create(serviceClass);
    }

    public static OkHttpClient.Builder getUnsafeOkHttpClient(){
        try {
            final TrustManager[] trustManagers=new TrustManager[]{
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {

                        }

                        @Override
                        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {

                        }

                        @Override
                        public X509Certificate[] getAcceptedIssuers() {
                            return new X509Certificate[0];
                        }
                    }
            };

            final SSLContext sslContext=SSLContext.getInstance("SSL");
            sslContext.init(null,trustManagers,new java.security.SecureRandom());

            final SSLSocketFactory sslSocketFactory=sslContext.getSocketFactory();

            OkHttpClient.Builder builder=new OkHttpClient.Builder();
            builder.sslSocketFactory(sslSocketFactory,(X509TrustManager) trustManagers[0]);
            builder.hostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return false;
                }
            });

            return builder;
        } catch (Exception e){
            throw new RuntimeException(e);
        }

    }
}

package com.example.myfeedapplication.application.di;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.facebook.stetho.okhttp3.StethoInterceptor;

import java.io.IOException;
import java.net.CookieManager;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class UnsafeOkHttpClient {


    public static OkHttpClient getUnsafeOkHttpClient(Cache cache, Context context) {

        try {
            // Create a trust manager that does not validate certificate chains
            @SuppressLint("CustomX509TrustManager") final TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) {
                        }

                        @Override
                        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) {
                        }

                        @Override
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return new java.security.cert.X509Certificate[]{};
                        }
                    }
            };

            // Install the all-trusting trust manager
            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
            // Create an ssl socket factory with our all-trusting manager
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
            CookieManager cookieManager = new CookieManager();
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.cache(cache);
            builder.connectTimeout(10 ,TimeUnit.MINUTES)
                    .readTimeout(10, TimeUnit.MINUTES)
                    .writeTimeout(10, TimeUnit.MINUTES)
                    .addInterceptor(chain -> {
                        Request request = chain.request();

                        request = request.newBuilder()
                                .addHeader("Appversion", "1")
                                .addHeader("Appheader", "1")
                                .addHeader("Content-Type", "application/json")
                                .addHeader("Accept", "application/json")
                                .method(request.method(), request.body())
                                .addHeader("Cache-Control", "public, max-age=" + 5000)
                                .build();
                        try {
                            return chain.proceed(request);
                        } catch (IOException e) {
                            e.printStackTrace();
                            return chain.proceed(request);
                        }
                    })
                    .readTimeout(60, TimeUnit.SECONDS)
                    .connectTimeout(60, TimeUnit.SECONDS)
                    .addNetworkInterceptor(new StethoInterceptor());

            builder.sslSocketFactory(sslSocketFactory, (X509TrustManager) trustAllCerts[0]);
            builder.hostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });
            return builder.build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}


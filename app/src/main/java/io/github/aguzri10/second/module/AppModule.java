package io.github.aguzri10.second.module;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AppModule {
    private static final String BASE_URL = "https://newsapi.org/v2/";
    private static Retrofit retrofit = null;

    public static Retrofit getHeadline() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}

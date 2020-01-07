package io.github.aguzri10.second.module;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AppModule {

    private static final String BASE_URL = "https://newsapi.org/v2/";
    public static final String apiKey = "49e5243f2e9d418dbdb60f460e4e4ae9";
    public static final String country = "id";

    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}

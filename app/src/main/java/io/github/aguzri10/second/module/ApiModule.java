package io.github.aguzri10.second.module;

import io.github.aguzri10.second.model.Articles;
import io.github.aguzri10.second.model.ResponseModel;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiModule {

    @GET("top-headlines")
    Call<ResponseModel> getTopHeadline(
            @Query("country") String country,
            @Query("apiKey") String apiKey
    );

    @GET("top-headlines")
    Call<ResponseModel> getApi(
            @Query("country") String country,
            @Query("category") String category,
            @Query("apiKey") String apiKey
    );
}

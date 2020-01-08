package io.github.aguzri10.second.presenter;

import java.util.List;

import io.github.aguzri10.second.model.ResponseModel;
import io.github.aguzri10.second.module.ApiModule;
import io.github.aguzri10.second.module.AppModule;
import io.github.aguzri10.second.view.NewsView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryPresenter {

    NewsView view;

    public CategoryPresenter(NewsView view) {
        this.view = view;
    }

    public void getDataBussiness(String country, String category, String apiKey) {
        view.onShowProgress();

        ApiModule apiModule = AppModule.getClient().create(ApiModule.class);
        Call<ResponseModel> call = apiModule.getBussiness(country, category, apiKey);
        call.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                view.onHideProgress();
                if (response.isSuccessful() && response.body() != null) {
                    view.onGetResults(response.body().getArticles());
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                view.onHideProgress();
                view.onErrorResults(t.getLocalizedMessage());
            }
        });
    }

    public void getDataEntertainment(String country, String category, String apiKey) {
        view.onShowProgress();

        ApiModule apiModule = AppModule.getClient().create(ApiModule.class);
        Call<ResponseModel> call = apiModule.getEntertainment(country, category, apiKey);
        call.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                view.onHideProgress();
                if (response.isSuccessful() && response.body() != null) {
                    view.onGetResults(response.body().getArticles());
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                view.onErrorResults(t.getLocalizedMessage());
            }
        });
    }

    public void getDataHealth(String country, String category, String apiKey) {

        view.onShowProgress();

        ApiModule apiModule = AppModule.getClient().create(ApiModule.class);
        Call<ResponseModel> call = apiModule.getHealth(country, category, apiKey);
        call.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                view.onHideProgress();
                if (response.isSuccessful() && response.body() != null) {
                    view.onGetResults(response.body().getArticles());
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                view.onHideProgress();
                view.onErrorResults(t.getLocalizedMessage());
            }
        });
    }

    public void getDataSport(String country, String category, String apiKey) {

        view.onShowProgress();

        ApiModule apiModule = AppModule.getClient().create(ApiModule.class);
        Call<ResponseModel> call = apiModule.getSport(country, category, apiKey);
        call.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                view.onHideProgress();
                if (response.isSuccessful() && response.body() != null) {
                    view.onGetResults(response.body().getArticles());
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                view.onHideProgress();
                view.onErrorResults(t.getLocalizedMessage());
            }
        });
    }

    public void getDataScience(String country, String category, String apiKey) {

        view.onShowProgress();

        ApiModule apiModule = AppModule.getClient().create(ApiModule.class);
        Call<ResponseModel> call = apiModule.getScience(country, category, apiKey);
        call.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                view.onHideProgress();
                if (response.isSuccessful() && response.body() != null) {
                    view.onGetResults(response.body().getArticles());
                } else {
                    view.onErrorResults("Response not successful and no body!");
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                view.onHideProgress();
                view.onErrorResults(t.getLocalizedMessage());
            }
        });
    }
}

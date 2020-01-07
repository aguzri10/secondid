package io.github.aguzri10.second.presenter;

import io.github.aguzri10.second.model.ResponseModel;
import io.github.aguzri10.second.module.ApiModule;
import io.github.aguzri10.second.module.AppModule;
import io.github.aguzri10.second.view.NewsView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HeadlinePresenter {

    private NewsView view;

    public HeadlinePresenter(NewsView view) {
        this.view = view;
    }

    public void getDataHeadline(String country, String apiKey) {
        view.onShowProgress();

        ApiModule apiModule = AppModule.getClient().create(ApiModule.class);
        Call<ResponseModel> call = apiModule.getTopHeadline(country, apiKey);

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
}

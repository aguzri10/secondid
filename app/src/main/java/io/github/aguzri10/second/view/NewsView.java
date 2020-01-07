package io.github.aguzri10.second.view;

import java.util.List;

import io.github.aguzri10.second.model.Articles;

public interface NewsView {

    void onShowProgress();
    void onHideProgress();
    void onGetResults(List<Articles> articles);
    void onErrorResults(String message);
}

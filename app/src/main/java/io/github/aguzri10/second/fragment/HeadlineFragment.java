package io.github.aguzri10.second.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import io.github.aguzri10.second.R;
import io.github.aguzri10.second.activity.DetailActivity;
import io.github.aguzri10.second.adapter.HeadlineAdapter;
import io.github.aguzri10.second.model.Articles;
import io.github.aguzri10.second.presenter.HeadlinePresenter;
import io.github.aguzri10.second.view.HeadlineView;

public class HeadlineFragment extends Fragment implements HeadlineView {

    private static final String country = "id";
    private static final String apiKey = "49e5243f2e9d418dbdb60f460e4e4ae9";
    private RecyclerView recyclerView;
    private HeadlineAdapter adapter;
    private List<Articles> article;
    private HeadlineAdapter .ItemClickListerner itemClickListener;
    private HeadlinePresenter presenter;
    private SwipeRefreshLayout swipeRefresh;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_headline, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recycler_view);
        swipeRefresh = view.findViewById(R.id.swipe_refresh);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        presenter = new HeadlinePresenter(this);
        presenter.getDataHeadline(country, apiKey);

        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.getDataHeadline(country, apiKey);
            }
        });

        itemClickListener = (new HeadlineAdapter.ItemClickListerner() {
            @Override
            public void onItemClick(View view, int i) {
                String image = article.get(i).getUrlToImage();
                String title = article.get(i).getTitle();
                String author = article.get(i).getAuthor();
                String published = article.get(i).getPublishedAt();
                String content = article.get(i).getContent();

                Intent intent = new Intent(getContext(), DetailActivity.class);
                intent.putExtra("image", image);
                intent.putExtra("title", title);
                intent.putExtra("author", author);
                intent.putExtra("published", published);
                intent.putExtra("content", content);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onShowProgress() {
        swipeRefresh.setRefreshing(true);
    }

    @Override
    public void onHideProgress() {
        swipeRefresh.setRefreshing(false);
    }

    @Override
    public void onGetResults(List<Articles> articles) {
        adapter = new HeadlineAdapter(getContext(), articles, itemClickListener);
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);
        article = articles;
    }

    @Override
    public void onErrorResults(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }
}

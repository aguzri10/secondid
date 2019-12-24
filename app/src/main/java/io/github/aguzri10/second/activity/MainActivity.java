package io.github.aguzri10.second.activity;

import android.app.ProgressDialog;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

import io.github.aguzri10.second.R;
import io.github.aguzri10.second.adapter.HeadlineAdapter;
import io.github.aguzri10.second.model.Articles;
import io.github.aguzri10.second.model.ResponseModel;
import io.github.aguzri10.second.presenter.HeadlinePresenter;
import io.github.aguzri10.second.view.HeadlineView;

public class MainActivity extends AppCompatActivity implements HeadlineView {

    private static final String country = "id";
    private static final String apiKey = "49e5243f2e9d418dbdb60f460e4e4ae9";
    private HeadlineAdapter adapter;
    private HeadlinePresenter presenter;
    private HeadlineAdapter .ItemClickListerner itemClickListener;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefresh;
    private List<Articles> article;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setElevation(0);

        recyclerView = findViewById(R.id.recycler_view);
        swipeRefresh = findViewById(R.id.swipe_refresh);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        presenter = new HeadlinePresenter(this);
        presenter.getDataHeadline(country, apiKey);

        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.getDataHeadline(country, apiKey);
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
        adapter = new HeadlineAdapter(this, articles, itemClickListener);
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);
        article = articles;

    }

    @Override
    public void onErrorResults(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}

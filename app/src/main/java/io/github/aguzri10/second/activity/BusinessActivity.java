package io.github.aguzri10.second.activity;

import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import io.github.aguzri10.second.R;
import io.github.aguzri10.second.adapter.NewsAdapter;
import io.github.aguzri10.second.model.Articles;
import io.github.aguzri10.second.presenter.CategoryPresenter;
import io.github.aguzri10.second.view.NewsView;

import static io.github.aguzri10.second.module.AppModule.apiKey;
import static io.github.aguzri10.second.module.AppModule.country;

public class BusinessActivity extends AppCompatActivity implements NewsView {

    private CollapsingToolbarLayout collapsingToolbarLayout;
    private Toolbar toolbar;
    private static final String category = "business";
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefresh;
    private List<Articles> article;
    private NewsAdapter adapter;
    private NewsAdapter.ItemClickListerner itemClickListerner;
    private CategoryPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business);

        toolbar = findViewById(R.id.toolbar);
        recyclerView = findViewById(R.id.recycler_view);
        swipeRefresh = findViewById(R.id.swipe_refresh);

        setSupportActionBar(toolbar);
        toolbar.setTitle("Bussines");
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorWhite));

        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_arrow_back));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        presenter = new CategoryPresenter(this);
        presenter.getDataBussiness(country, category, apiKey);

        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.getDataBussiness(country, category, apiKey);
            }
        });

        itemClickListerner = (new NewsAdapter.ItemClickListerner() {
            @Override
            public void onItemClick(View view, int i) {
                String image = article.get(i).getUrlToImage();
                String title = article.get(i).getTitle();
                String author = article.get(i).getAuthor();
                String published = article.get(i).getPublishedAt();
                String content = article.get(i).getContent();

                Intent intent = new Intent(BusinessActivity.this, DetailActivity.class);
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
        adapter = new NewsAdapter(this, articles, itemClickListerner);
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);
        article = articles;
    }

    @Override
    public void onErrorResults(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}

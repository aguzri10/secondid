package io.github.aguzri10.second.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.github.aguzri10.second.R;
import io.github.aguzri10.second.activity.BusinessActivity;
import io.github.aguzri10.second.activity.DetailActivity;
import io.github.aguzri10.second.activity.EntertainmentActivity;
import io.github.aguzri10.second.activity.HealthActivity;
import io.github.aguzri10.second.model.Articles;
import io.github.aguzri10.second.presenter.HeadlinePresenter;
import io.github.aguzri10.second.view.NewsView;

import static io.github.aguzri10.second.module.AppModule.apiKey;
import static io.github.aguzri10.second.module.AppModule.country;

public class HomeFragment extends Fragment implements NewsView {

    @BindView(R.id.card_bussiness) CardView crBusiness;
    @BindView(R.id.card_entertainment) CardView crEntertainment;
    @BindView(R.id.card_health) CardView crHealth;
    @BindView(R.id.card_science) CardView crScience;
    @BindView(R.id.card_sports) CardView crSport;
    @BindView(R.id.card_technology) CardView crTechnology;
    @BindView(R.id.swipe_refresh) SwipeRefreshLayout swipeRefresh;
    @BindView(R.id.recycler_view) RecyclerView recyclerView;

    private List<Articles> article;
    private HeadlinePresenter presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
//        presenter = new HeadlinePresenter(this);
//        presenter.getDataHeadline(country, apiKey);

//        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                presenter.getDataHeadline(country, apiKey);
//            }
//        });
//
//        itemClickListener = (new HorizonAdapter.ItemClickListener() {
//            @Override
//            public void onItemClick(View view, int i) {
//                String image = article.get(i).getUrlToImage();
//                String title = article.get(i).getTitle();
//
//                Intent intent = new Intent(getContext(), DetailActivity.class);
//                intent.putExtra("image", image);
//                intent.putExtra("title", title);
//                startActivity(intent);
//            }
//        });

        crBusiness.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), BusinessActivity.class));
            }
        });

        crEntertainment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), EntertainmentActivity.class));
            }
        });

        crHealth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), HealthActivity.class));
            }
        });

        crSport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), HealthActivity.class));
            }
        });
    }

    @Override
    public void onShowProgress() {
//        swipeRefresh.setRefreshing(true);
    }

    @Override
    public void onHideProgress() {
//        swipeRefresh.setRefreshing(false);
    }

    @Override
    public void onGetResults(List<Articles> articles) {
//        adapter = new HorizonAdapter(getContext(), articles, itemClickListener);
//        adapter.notifyDataSetChanged();
//        recyclerView.setAdapter(adapter);
//        article = articles;
    }

    @Override
    public void onErrorResults(String message) {
//        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }
}
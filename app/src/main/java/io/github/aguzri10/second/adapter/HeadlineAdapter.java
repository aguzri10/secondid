package io.github.aguzri10.second.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import io.github.aguzri10.second.R;
import io.github.aguzri10.second.model.Articles;

public class HeadlineAdapter extends RecyclerView.Adapter<HeadlineAdapter.ViewHolder> {

    private Context context;
    private List<Articles> articles;
    private ItemClickListerner itemClickListener;

    public HeadlineAdapter(Context context, List<Articles> articles, ItemClickListerner itemClickListener) {
        this.context = context;
        this.articles = articles;
        this.itemClickListener = itemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.headline_item, viewGroup, false);
        return new ViewHolder(view, itemClickListener);
    }

    @Override
    public void onBindViewHolder(HeadlineAdapter.ViewHolder viewHolder, int i) {
        Articles article = articles.get(i);
        Picasso.get()
                .load(article.getUrlToImage())
                .into(viewHolder.ivImage);
        viewHolder.tvTitle.setText(article.getTitle());
        viewHolder.tvDesc.setText(article.getDescription());
        viewHolder.tvPublished.setText(article.getPublishedAt());
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tvTitle, tvDesc, tvPublished;
        ImageView ivImage;
        CardView cardView;
        ItemClickListerner itemClickListener;

        public ViewHolder(View itemView, ItemClickListerner itemClickListener) {
            super(itemView);

            ivImage = itemView.findViewById(R.id.iv_image);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvDesc = itemView.findViewById(R.id.tv_desc);
            tvPublished = itemView.findViewById(R.id.tv_published);
            cardView= itemView.findViewById(R.id.card_view);

            this.itemClickListener = itemClickListener;
            cardView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            itemClickListener.onItemClick(itemView, getAdapterPosition());
        }
    }

    public interface ItemClickListerner {
        void onItemClick(View view, int i);
    }
}

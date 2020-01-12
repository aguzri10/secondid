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

import butterknife.BindView;
import butterknife.ButterKnife;
import io.github.aguzri10.second.R;
import io.github.aguzri10.second.model.Articles;

public class TopAdapter extends RecyclerView.Adapter<TopAdapter.ViewHolder> {

    private Context context;
    private List<Articles> articles;
    private ItemClickListener itemClickListener;

    public TopAdapter(Context context, List<Articles> articles, ItemClickListener itemClickListener) {
        this.context = context;
        this.articles = articles;
        this.itemClickListener = itemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.headline_slide, viewGroup, false);
        return new ViewHolder(view, itemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Articles data = articles.get(i);
        String url_image = data.getUrlToImage();
        Picasso.get()
                .load(url_image)
                .into(viewHolder.ivImage);
        viewHolder.tvTitle.setText(data.getTitle());
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvTitle;
        ImageView ivImage;
        CardView cardView;
        ItemClickListener itemClickListener;

        public ViewHolder(@NonNull View itemView, ItemClickListener itemClickListener) {
            super(itemView);

            ivImage = itemView.findViewById(R.id.iv_image);
            tvTitle = itemView.findViewById(R.id.tv_title);
            cardView= itemView.findViewById(R.id.card_view);

            this.itemClickListener = itemClickListener;
            cardView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            itemClickListener.onClick(itemView, getAdapterPosition());
        }
    }

    public interface ItemClickListener {
        void onClick(View view, int i);
    }
}

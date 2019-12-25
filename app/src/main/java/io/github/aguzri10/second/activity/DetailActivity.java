package io.github.aguzri10.second.activity;

import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import io.github.aguzri10.second.R;

public class DetailActivity extends AppCompatActivity {

    CollapsingToolbarLayout collapsingToolbarLayout;
    Toolbar toolbar;
    TextView tvTitle, tvAuthor, tvPublished, tvContent;
    ImageView ivImage;
    FloatingActionButton fabShare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        tvTitle = findViewById(R.id.tv_title);
        tvAuthor = findViewById(R.id.tv_author);
        tvPublished= findViewById(R.id.tv_published);
        tvContent = findViewById(R.id.tv_content);
        ivImage = findViewById(R.id.iv_image);
        toolbar = findViewById(R.id.toolbar);
        fabShare = findViewById(R.id.fab_share);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        collapsingToolbarLayout = findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle(" ");

        Intent intent = getIntent();
        String image = intent.getStringExtra("image");
        String title = intent.getStringExtra("title");
        String author = intent.getStringExtra("author");
        String published = intent.getStringExtra("published");
        String content = intent.getStringExtra("content");

        Picasso.get()
                .load(image)
                .into(ivImage);
        tvTitle.setText(title);
        tvAuthor.setText(author);
        tvPublished.setText(published);
        tvContent.setText(content);

        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_arrow_back));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        fabShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentShare = new Intent(Intent.ACTION_SEND);
                intentShare.setType("text/plain");
                String body = "Share this news to ?";
                intentShare.putExtra(Intent.EXTRA_SUBJECT, "Subject here");
                intentShare.putExtra(Intent.EXTRA_TEXT, body);
                startActivity(Intent.createChooser(intentShare, "Sharing Option"));
            }
        });
    }
}

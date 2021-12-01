package com.alsoftware.newsdemoapp.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.alsoftware.newsdemoapp.R;
import com.alsoftware.newsdemoapp.data.model.Article;
import com.squareup.picasso.Picasso;

import java.util.List;

public class NewsListAdapter extends RecyclerView.Adapter<NewsListAdapter.NewsListViewHolder> {
    private List<Article> list;

    public NewsListAdapter() {

    }

    public NewsListAdapter(List<Article> articleList) {
          list = articleList;
    }

    public void setList(List<Article> articleList){
        list=articleList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public NewsListAdapter.NewsListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_article_layout, parent, false);
        return new NewsListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsListAdapter.NewsListViewHolder holder, int position) {
        Article currentArticle = list.get(position);

        if (position==0) {
            holder.singleLayout.setVisibility(View.GONE);
            holder.mainLayout.setVisibility(View.VISIBLE);
            holder.mainLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MainFragmentDirections.MainToWeb action = MainFragmentDirections.mainToWeb();
                    action.setWebsiteUrl(currentArticle.getUrl());
                    Navigation.findNavController(v).navigate(action);
                }
            });

            holder.mainArticleTitle.setText(currentArticle.getTitle());
            holder.mainAuthor.setText(currentArticle.getAuthor());
            holder.mainSource.setText(currentArticle.getSource().getName());
            holder.mainPublishDate.setText(" - " + currentArticle.getPublishedAt().substring(0, 10));
            holder.mainArticleSummary.setText(currentArticle.getDescription());
            Picasso.get().load(currentArticle.getUrlToImage()).into(holder.mainImage);

        } else {
            holder.mainLayout.setVisibility(View.GONE);
            holder.singleLayout.setVisibility(View.VISIBLE);
            holder.singleLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MainFragmentDirections.MainToWeb action = MainFragmentDirections.mainToWeb();
                    action.setWebsiteUrl(currentArticle.getUrl());
                    Navigation.findNavController(v).navigate(action);
                }
            });

            holder.articleTitle.setText(shortenHeading(currentArticle.getTitle()));
            holder.author.setText(currentArticle.getAuthor());
            holder.source.setText(currentArticle.getSource().getName());
            holder.publishDate.setText(" - " + currentArticle.getPublishedAt().substring(0, 10));
            holder.articleSummary.setText(shortenSummary(currentArticle.getDescription()));
            Picasso.get().load(currentArticle.getUrlToImage()).into(holder.image);
        }
    }

    private String shortenHeading(String heading){
        String shortenedHeading;
        if(heading != null && heading.length()>48){
            shortenedHeading=heading.substring(0,45);
            shortenedHeading+="...";
            return shortenedHeading;
        }else{
            return heading;
        }
    }

    private String shortenSummary(String summary){
        String shortenedSummary;
        if(summary != null && summary.length()>150) {
            shortenedSummary=summary.substring(0,147);
            shortenedSummary+="...";
            return shortenedSummary;
        } else{
            return summary;
        }
    }

    @Override
    public int getItemCount() {
        if (list == null) {
            return 0;
        } else {
            return list.size();
        }
    }

    public class NewsListViewHolder extends RecyclerView.ViewHolder {
        public View singleLayout;
        public View mainLayout;

        public ImageView image;
        public TextView articleTitle;
        public TextView author;
        public TextView source;
        public TextView publishDate;
        public TextView articleSummary;

        public ImageView mainImage;
        public TextView mainArticleTitle;
        public TextView mainAuthor;
        public TextView mainSource;
        public TextView mainPublishDate;
        public TextView mainArticleSummary;

        public View view;
        public NewsListViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            singleLayout=itemView.findViewById(R.id.singleArticleLayout);
            mainLayout=itemView.findViewById(R.id.mainArticleLayout);

            image = singleLayout.findViewById(R.id.articleImage);
            articleTitle = singleLayout.findViewById(R.id.articleTitle);
            author = singleLayout.findViewById(R.id.articleAuthor);
            source = singleLayout.findViewById(R.id.articleSource);
            publishDate = singleLayout.findViewById(R.id.articlePublishDate);
            articleSummary = singleLayout.findViewById(R.id.articleSummary);

            mainImage = mainLayout.findViewById(R.id.mainArticleImage);
            mainArticleTitle = mainLayout.findViewById(R.id.mainArticleTitle);
            mainAuthor = mainLayout.findViewById(R.id.mainArticleAuthor);
            mainSource = mainLayout.findViewById(R.id.mainArticleSource);
            mainPublishDate = mainLayout.findViewById(R.id.mainArticlePublishDate);
            mainArticleSummary = mainLayout.findViewById(R.id.mainArticleSummary);
        }
    }
}

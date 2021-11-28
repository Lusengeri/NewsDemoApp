package com.alsoftware.newsdemoapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import com.alsoftware.newsdemoapp.data.model.Article;

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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_article_layout, parent, false);
        return new NewsListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsListAdapter.NewsListViewHolder holder, int position) {
        Article currentArticle = list.get(position);
        holder.articleTitle.setText(currentArticle.getTitle());
        holder.author.setText(currentArticle.getAuthor());
        holder.source.setText(currentArticle.getSource().getName());
        holder.publishDate.setText(" - " + currentArticle.getPublishedAt().substring(0, 10));
        holder.articleSummary.setText(currentArticle.getDescription());

        Picasso.get().load(currentArticle.getUrlToImage()).into(holder.image);
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
        public TextView articleTitle;
        public TextView author;
        public TextView source;
        public TextView publishDate;
        public TextView articleSummary;

        public ImageView image;
        public View view;
        public NewsListViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            image = itemView.findViewById(R.id.articleImage);
            articleTitle = itemView.findViewById(R.id.mainArticleTitle);
            author = itemView.findViewById(R.id.mainArticleAuthor);
            source = itemView.findViewById(R.id.mainArticleSource);
            publishDate = itemView.findViewById(R.id.mainArticlePublishDate);
            articleSummary = itemView.findViewById(R.id.mainArticleSummary);
        }
    }
}

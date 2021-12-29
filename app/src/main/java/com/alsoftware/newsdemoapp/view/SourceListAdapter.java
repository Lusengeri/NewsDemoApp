package com.alsoftware.newsdemoapp.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.alsoftware.newsdemoapp.R;
import com.alsoftware.newsdemoapp.data.model.Source;

import java.util.List;

public class SourceListAdapter extends RecyclerView.Adapter<SourceListAdapter.SourceListViewHolder> {
    private List<Source> sourceList;
    private MainFragment listener;

    public SourceListAdapter(MainFragment parentFragment){
        listener=parentFragment;
    }
    @NonNull
    @Override
    public SourceListAdapter.SourceListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.source_name_layout, parent, false);
        return new SourceListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SourceListAdapter.SourceListViewHolder holder, int position) {
        Source currentSource = sourceList.get(position);
        holder.sourceName.setText(currentSource.getName().toLowerCase());

        holder.sourceCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.loadArticles(currentSource.getId());
            }
        });
    }

    public void setList(List<Source> list) {
        sourceList = list;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (sourceList==null) {
            return 0;
        } else {
            return sourceList.size();
        }
    }

    public class SourceListViewHolder extends RecyclerView.ViewHolder {
        public TextView sourceName;
        public CardView sourceCard;
        public View view;

        public SourceListViewHolder(@NonNull View itemView) {
            super(itemView);
            view=itemView;
            sourceCard=itemView.findViewById(R.id.sourceCard);
            sourceName=itemView.findViewById(R.id.sourceName);
        }
    }
}
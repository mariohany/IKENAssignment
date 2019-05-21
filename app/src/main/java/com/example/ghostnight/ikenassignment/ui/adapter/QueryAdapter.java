package com.example.ghostnight.ikenassignment.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.example.ghostnight.ikenassignment.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class QueryAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private ArrayList<String> queries;
    private QueryItemClickListener listener;

    public interface QueryItemClickListener {
        void onQueryItemClick(String query);
    }

    public QueryAdapter(ArrayList<String> queries, QueryItemClickListener listener, Context context) {
        this.listener = listener;
        this.queries = queries;
        mContext = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        RecyclerView.ViewHolder vh;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(android.R.layout.simple_list_item_1, viewGroup, false);
        vh = new ListViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ((ListViewHolder) viewHolder).bind(i);
    }

    @Override
    public int getItemCount() {
        return queries.size();
    }

    class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView text;
        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            text = itemView.findViewById(android.R.id.text1);
        }
        void bind(final int listIndex) {
            text.setText(queries.get(listIndex));
        }

        @Override
        public void onClick(View v) {
            listener.onQueryItemClick(queries.get(getAdapterPosition()));
        }
    }
}

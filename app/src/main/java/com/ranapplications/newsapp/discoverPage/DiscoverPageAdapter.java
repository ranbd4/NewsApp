package com.ranapplications.newsapp.discoverPage;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ranapplications.newsapp.R;

import java.util.ArrayList;

public class DiscoverPageAdapter extends RecyclerView.Adapter<DiscoverPageAdapter.DiscoverPageViewHolder>{

    private DiscoverPageAdapterCallBack callBack;
    private ArrayList<DiscoverClass> discoverClasses;
    private LayoutInflater layoutInflater;

    public DiscoverPageAdapter(Context context, DiscoverPageAdapterCallBack callBack, ArrayList<DiscoverClass> discoverClasses) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.callBack = callBack;
        this.discoverClasses = discoverClasses;
    }

    @NonNull
    @Override
    public DiscoverPageViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v= layoutInflater.inflate(R.layout.discover_row,viewGroup,false);
        DiscoverPageAdapter.DiscoverPageViewHolder viewHolder= new DiscoverPageAdapter.DiscoverPageViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DiscoverPageViewHolder holder, int position) {
        DiscoverClass oneRow = discoverClasses.get(position);

        holder.setImage(oneRow.getImage());
        holder.setTitle(oneRow.getTitle());
        holder.setOnClick(oneRow.getUrlLink());
    }

    @Override
    public int getItemCount() {
        return discoverClasses.size();
    }

    class DiscoverPageViewHolder extends RecyclerView.ViewHolder{
        private View v;

        public DiscoverPageViewHolder(@NonNull View itemView) {
            super(itemView);
            v = itemView;
        }

        void setImage(int image){
            Glide.with(v.getContext()).load(image).into((ImageView) v.findViewById(R.id.imageViewImage));
        }

        void setTitle(String title){
            TextView textViewTitle = v.findViewById(R.id.textViewTitle);
            textViewTitle.setText(""+title);
        }

        void setOnClick(final String urlLink){
            CardView cardView = v.findViewById(R.id.cardView);
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    callBack.onClickRow(urlLink);
                }
            });
        }
    }

    public interface DiscoverPageAdapterCallBack{
        void onClickRow(String urlLink);
    }
}

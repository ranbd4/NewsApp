package com.ranapplications.newsapp.mainPage;

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
import com.ranapplications.newsapp.NewsClass;
import com.ranapplications.newsapp.R;
import com.ranapplications.newsapp.global.NameToPhoto;

import java.util.ArrayList;

public class MainPageAdapter extends RecyclerView.Adapter<MainPageAdapter.MainPageHolder>{
    private ArrayList<NewsClass> newsClasses;
    private LayoutInflater layoutInflater;
    private MainPageAdapterCallBack callBack;

    public MainPageAdapter(Context context, ArrayList<NewsClass> newsClasses,MainPageAdapterCallBack callBack) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.newsClasses = newsClasses;
        this.callBack = callBack;
    }

    @NonNull
    @Override
    public MainPageHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v= layoutInflater.inflate(R.layout.article_row,viewGroup,false);
        MainPageAdapter.MainPageHolder viewHolder= new MainPageAdapter.MainPageHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MainPageHolder holder, int position) {
        NewsClass oneRow = newsClasses.get(position);

        holder.setImage(oneRow.getUrlToImage());
        holder.setTitle(oneRow.getTitle());
        holder.setSource(oneRow.getSource());
        holder.setPublishedAt(oneRow.getPublishedAt());
        holder.setDescription(oneRow.getDescription());
        holder.onClick(oneRow.getUrl());
        holder.onClickMenu(oneRow);
    }

    @Override
    public int getItemCount() {
        return newsClasses.size();
    }

    class MainPageHolder extends RecyclerView.ViewHolder{
        private View v;

        public MainPageHolder(@NonNull View itemView) {
            super(itemView);
            v = itemView;
        }

        void setImage(final String imageUrl){
            final ImageView imageViewImage = v.findViewById(R.id.imageViewImage);
            Glide.with(v.getContext())
                    .load(imageUrl)
                    .into(imageViewImage);
        }

        void setTitle(String title){
            TextView textViewTitle = v.findViewById(R.id.textViewTitle);
            textViewTitle.setText(""+ title);
        }

        void setSource(String source){
            TextView textViewSource = v.findViewById(R.id.textViewSource);
            textViewSource.setText(""+ source);

            TextView textViewSourceOneLetter = v.findViewById(R.id.textViewSourceOneLetter);
            textViewSourceOneLetter.setText(""+ source.substring(0,1));

            Glide.with(v.getContext()).load(NameToPhoto.nameToPhoto(source)).into((ImageView) v.findViewById(R.id.imageViewCircle));
        }

        void setPublishedAt(String publishedAt){
            publishedAt =publishedAt.replace("T", "  |  ");
            publishedAt =publishedAt.replace("Z", "");
            publishedAt =publishedAt.substring(0,publishedAt.length()-3);
            TextView textViewPublishedAt = v.findViewById(R.id.textViewPublishedAt);
            textViewPublishedAt.setText(""+ publishedAt);
        }

        void setDescription(String description){
            TextView textViewDescription = v.findViewById(R.id.textViewDescription);
            textViewDescription.setText(""+ description);
        }

        void onClick(final String urlLink){
            CardView cardView = v.findViewById(R.id.cardView);
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    callBack.onClick(urlLink);
                }
            });
        }

        public void onClickMenu(final NewsClass newsClass){
            ImageView imageViewOptions = v.findViewById(R.id.imageViewOptions);
            imageViewOptions.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    callBack.onClickMenu(newsClass);
                }
            });
        }
    }

    public interface MainPageAdapterCallBack{
        void onClick(String urlLink);
        void onClickMenu(NewsClass newsClass);
    }
}

package com.ranapplications.newsapp.bottomMenu;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ranapplications.newsapp.NewsClass;
import com.ranapplications.newsapp.R;

import java.util.ArrayList;

public class BottomMenuAdapter extends RecyclerView.Adapter<BottomMenuAdapter.BottomMenuViewHolder>{
    private BottomMenuAdapterCallBack callBack;
    private ArrayList<BottomMenuClass> bottomMenuClasses;
    private NewsClass newsClass;
    private LayoutInflater layoutInflater;

    public BottomMenuAdapter(Context context, BottomMenuAdapterCallBack callBack, NewsClass newsClass,ArrayList<BottomMenuClass> bottomMenuClasses) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.callBack = callBack;
        this.newsClass = newsClass;
        this.bottomMenuClasses = bottomMenuClasses;
    }

    @NonNull
    @Override
    public BottomMenuViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v= layoutInflater.inflate(R.layout.bottom_menu_row,viewGroup,false);
        BottomMenuAdapter.BottomMenuViewHolder viewHolder= new BottomMenuAdapter.BottomMenuViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BottomMenuViewHolder holder, int position) {
        BottomMenuClass oneRow = bottomMenuClasses.get(position);

        holder.setTitle(oneRow.getTitle());
        holder.setContext(oneRow.getContext());
        holder.setImage(oneRow.getImage());
        holder.onClick(oneRow.getOnClick(),newsClass);

    }

    @Override
    public int getItemCount() {
        return bottomMenuClasses.size();
    }

    class BottomMenuViewHolder extends RecyclerView.ViewHolder{
        private View v;

        public BottomMenuViewHolder(@NonNull View itemView) {
            super(itemView);
            v = itemView;
        }

        void setTitle(String title){
            TextView textViewTitle = v.findViewById(R.id.textViewTitle);
            textViewTitle.setText(""+ title);
        }

        void setContext(String context){
            TextView textViewContext = v.findViewById(R.id.textViewContext);
            textViewContext.setText(""+ context);
        }

        void setImage(int image){
            Glide.with(v.getContext()).load(image).into((ImageView) v.findViewById(R.id.imageViewIcon));
        }

        void onClick(final int onClickInt, final NewsClass newsClass){
            ConstraintLayout constraintLayout = v.findViewById(R.id.constraintLayout);
            constraintLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    callBack.onClick(onClickInt,newsClass);
                }
            });
        }
    }

    public interface BottomMenuAdapterCallBack{
        void onClick(int onClickInt, NewsClass newsClass);
    }

}

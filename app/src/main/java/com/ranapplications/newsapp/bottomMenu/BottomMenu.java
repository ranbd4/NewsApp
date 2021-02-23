package com.ranapplications.newsapp.bottomMenu;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ranapplications.newsapp.NewsClass;
import com.ranapplications.newsapp.R;
import com.ranapplications.newsapp.global.Global;

import java.util.ArrayList;

public class BottomMenu implements BottomMenuAdapter.BottomMenuAdapterCallBack {

    private BottomMenuCallBack callBack;
    private BottomSheetDialog mBottomSheetDialog;
    private View view;
    private Activity activity;
    private BottomMenuAdapter mAdapter;
    private NewsClass newsClass;
    private ArrayList<BottomMenuClass> bottomMenuClasses = new ArrayList<>();


    public BottomMenu(Activity activity, NewsClass newsClass,BottomMenuCallBack callBack){
        this.activity = activity;
        this.callBack = callBack;
        this.newsClass = newsClass;
        start();
    }

    private void start(){
        mBottomSheetDialog = new BottomSheetDialog(activity, R.style.BottomSheetDialog);
        View sheetView = activity.getLayoutInflater().inflate(R.layout.bottom_menu, null);
        mBottomSheetDialog.setContentView(sheetView);
        view = sheetView;


        //setOnclick();
        bottomMenuClasses = buildTheClass();
        setUpRecyclerView();

        mBottomSheetDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mBottomSheetDialog.show();
    }

    private ArrayList<BottomMenuClass> buildTheClass() {
        bottomMenuClasses.add(new BottomMenuClass(activity.getString(R.string.sharing), activity.getString(R.string.sharing_text), Global.BOTTOM_MENU_SHARING, R.drawable.sharing_icon));
        bottomMenuClasses.add(new BottomMenuClass(activity.getString(R.string.internet_browser), activity.getString(R.string.internet_browser_text), Global.BOTTOM_MENU_BROWSER, R.drawable.browser_icon));
        bottomMenuClasses.add(new BottomMenuClass(activity.getString(R.string.copy_link), activity.getString(R.string.copy_link_text), Global.BOTTOM_MENU_COPY_LINK, R.drawable.copy_icon));

        return bottomMenuClasses;
    }

    private void setUpRecyclerView() {
        RecyclerView mRecyclerView = view.findViewById(R.id.recyclerView);

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(view.getContext());

        // use a linear layout manager
        mRecyclerView.setLayoutManager(mLayoutManager);

        // create an Object for Adapter
        mAdapter = new BottomMenuAdapter(view.getContext(), this, newsClass,bottomMenuClasses);

        mRecyclerView.setAdapter(mAdapter);
    }

    public void dismiss(){
        mBottomSheetDialog.dismiss();
    }

    @Override
    public void onClick(int onClickInt,NewsClass newsClass) {
        dismiss();
        callBack.onClick(onClickInt,newsClass);
    }


    public interface BottomMenuCallBack{
        void onClick(int userAnswer, NewsClass newsClass);
    }
}

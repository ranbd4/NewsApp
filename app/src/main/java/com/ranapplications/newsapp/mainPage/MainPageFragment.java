package com.ranapplications.newsapp.mainPage;


import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.ranapplications.newsapp.NewsClass;
import com.ranapplications.newsapp.R;
import com.ranapplications.newsapp.bottomMenu.BottomMenu;
import com.ranapplications.newsapp.global.Global;
import com.thefinestartist.finestwebview.FinestWebView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainPageFragment extends Fragment implements MainPageAdapter.MainPageAdapterCallBack,
        BottomMenu.BottomMenuCallBack {

    private View view;
    private ArrayList<NewsClass> newsClasses;
    private MainPageAdapter mAdapter;
    private ShimmerFrameLayout mShimmerViewContainer;


    @SuppressLint("ValidFragment")
    public MainPageFragment(ArrayList<NewsClass> newsClasses) {
        this.newsClasses = newsClasses;
    }

    public MainPageFragment() {}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_main_page, container, false);

        mShimmerViewContainer = view.findViewById(R.id.shimmer_view_container);
        mShimmerViewContainer.setDuration(700);
        mShimmerViewContainer.setAutoStart(true);

        //mShimmerViewContainer.startShimmerAnimation();
        start();

        return view;
    }

    private void start() {
        final RecyclerView mRecyclerView = view.findViewById(R.id.recyclerView);
        mRecyclerView.setVisibility(View.INVISIBLE);

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());

        // use a linear layout manager
        mRecyclerView.setLayoutManager(mLayoutManager);

        // create an Object for Adapter
        mAdapter = new MainPageAdapter(getContext(), this.newsClasses,this);

        // set the adapter object to the Recyclerview
        mRecyclerView.setAdapter(mAdapter);

        new CountDownTimer(2000, 1000) {

            public void onTick(long millisUntilFinished) {
            }

            public void onFinish() {
                mShimmerViewContainer.stopShimmerAnimation();
                mShimmerViewContainer.setVisibility(View.GONE);
                mRecyclerView.setVisibility(View.VISIBLE);
            }
        }.start();

    }

    //CallBack from MainPageAdapter-> when user clicked on the row
    @Override
    public void onClick(String urlLink) {
        new FinestWebView.Builder(getActivity()).show(urlLink);
    }

    //CallBack from MainPageAdapter-> when user clicked on the menu
    @Override
    public void onClickMenu(NewsClass newsClass) {
        new BottomMenu(getActivity(),newsClass,this);
    }

    //CallBack from BottomMenu
    @Override
    public void onClick(int userAnswer,NewsClass newsClass) {
        switch (userAnswer){
            case Global.BOTTOM_MENU_SHARING:
                shareTheArticle(newsClass);
                break;

            case Global.BOTTOM_MENU_BROWSER:
                openInTheBrowser(newsClass);
                break;

            case Global.BOTTOM_MENU_COPY_LINK:
                copyTheLink(newsClass);
                break;
        }
    }

    private void copyTheLink(NewsClass newsClass) {
        ClipboardManager clipboard = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("", newsClass.getUrl());
        clipboard.setPrimaryClip(clip);

        Toast.makeText(getContext(), "Saved in the clipboard", Toast.LENGTH_SHORT).show();
    }

    private void openInTheBrowser(NewsClass newsClass) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(newsClass.getUrl()));
        startActivity(browserIntent);
    }

    private void shareTheArticle(NewsClass newsClass) {
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, getTextForSharing(newsClass));
        startActivity(Intent.createChooser(sharingIntent, ""));

    }

    private String getTextForSharing(NewsClass newsClass) {
        return "I want to share with you an interesting article I read on NEWS app:\n"
                +newsClass.getTitle()
        +"\n"+
                newsClass.getUrl();
    }
}

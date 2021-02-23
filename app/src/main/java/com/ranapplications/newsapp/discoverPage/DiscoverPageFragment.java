package com.ranapplications.newsapp.discoverPage;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ranapplications.newsapp.GetArrayNews;
import com.ranapplications.newsapp.NewsClass;
import com.ranapplications.newsapp.R;
import com.ranapplications.newsapp.mainPage.MainPageFragment;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class DiscoverPageFragment extends Fragment implements DiscoverPageAdapter.DiscoverPageAdapterCallBack,
        GetArrayNews.GetArrayNewsCallBack{

    private View view;
    private ArrayList<DiscoverClass> discoverClasses = new ArrayList<>();
    private DiscoverPageAdapter mAdapter;


    public DiscoverPageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_discover_page, container, false);

        start();
        return view;
    }

    private void start() {
        discoverClasses = buildList();
        RecyclerView mRecyclerView = view.findViewById(R.id.recyclerView);

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        //LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());

        // use a linear layout manager
        //mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));


        // create an Object for Adapter
        mAdapter = new DiscoverPageAdapter(getContext(), this, discoverClasses);

        // set the adapter object to the Recyclerview
        mRecyclerView.setAdapter(mAdapter);
    }

    private ArrayList<DiscoverClass> buildList() {
        discoverClasses.removeAll(discoverClasses);

        discoverClasses.add(new DiscoverClass("Cars","cars",R.drawable.cars_icon));
        discoverClasses.add(new DiscoverClass("Food","food",R.drawable.food_icon));
        discoverClasses.add(new DiscoverClass("IT","it",R.drawable.it_icon));
        discoverClasses.add(new DiscoverClass("Sport","sport",R.drawable.sport_icon));
        discoverClasses.add(new DiscoverClass("Design","design",R.drawable.design_icon));
        discoverClasses.add(new DiscoverClass("Business","business",R.drawable.business_icon));
        discoverClasses.add(new DiscoverClass("Health","health",R.drawable.health_icon));
        discoverClasses.add(new DiscoverClass("Shopping","Shopping",R.drawable.shopping_icon));

        return discoverClasses;
    }

    @Override
    public void onClickRow(String urlLink) {
        String newUrl = "https://newsapi.org/v2/everything?q="+urlLink+"&apiKey=1635f055e0804e1ebbdc43fabbefc115";
        new GetArrayNews(newUrl, this);
    }

    @Override
    public void onDone(ArrayList<NewsClass> newsClasses) {
        Fragment mFragment = new MainPageFragment(newsClasses);
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .addToBackStack(null)
                .setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_left)
                .replace(R.id.frameLayout, mFragment).commit();
    }
}

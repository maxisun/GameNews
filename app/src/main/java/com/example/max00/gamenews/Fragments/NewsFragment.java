package com.example.max00.gamenews.Fragments;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.max00.gamenews.Adapters.NewsAdapter;
import com.example.max00.gamenews.R;
import com.example.max00.gamenews.RoomArchitecture.Entity.NewsEntity;
import com.example.max00.gamenews.RoomArchitecture.Repository.NewsRepository;
import com.example.max00.gamenews.RoomArchitecture.ViewModel.NewsViewModel;
import java.util.List;



/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link NewsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link NewsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    //components
    private RecyclerView recyclerView;
    private NewsAdapter adapter;
    private NewsViewModel newsViewModel;
    private GridLayoutManager gridLayoutManager;
    private SwipeRefreshLayout swipeRefreshLayout;
    private SearchView searchView;


    // TODO: Rename and change types of parameters
    private List<NewsEntity> mParam1;
    private String category;

    private OnFragmentInteractionListener mListener;

    public NewsFragment() {
        // Required empty public constructor
    }
    //llenar el otro asterisco
    /*
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param2 Parameter 2.
     * @param param1 Parameter 1.
     * @return A new instance of fragment NewsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NewsFragment newInstance(String category) {
        NewsFragment fragment = new NewsFragment();
        Bundle args = new Bundle();
        args.putString("category",category);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            category = getArguments().getString("category");
            //mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.news_fragment, container, false);
        recyclerView = v.findViewById(R.id.recycleview_newsfragment);
        swipeRefreshLayout = v.findViewById(R.id.News_Refresh);
        if (category.equals("News")) {
            setNewsAll();
        }else if (category.equals("Favourites")){
            setNewsAll();
        } else {
            getCategorizedNews();
        }
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override public void run() {
                        try {
                            newsViewModel = new NewsViewModel(getActivity().getApplication());
                            swipeRefreshLayout.setRefreshing(false);
                        }catch (Exception e){
                        }
                    }
                }, 3500);
            }
        });
        return v;
    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        /*if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }*/
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    private void setNewsAll(){
        newsViewModel = ViewModelProviders.of(this).get(NewsViewModel.class);
        newsViewModel.getmAllNews().observe(this, new Observer<List<NewsEntity>>() {
            @Override
            public void onChanged(@Nullable List<NewsEntity> newsEntities) {
                adapter = new NewsAdapter(newsEntities,getActivity());
                gridLayoutManager = new GridLayoutManager(getActivity(),2);
                gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                    @Override
                    public int getSpanSize(int position) {
                        if(position%3==0){
                            return 2;
                        }else {
                            return 1;
                        }
                    }
                });
                recyclerView.setLayoutManager(gridLayoutManager);
                recyclerView.setAdapter(adapter);
            }
        });
    }

    private void getCategorizedNews(){
        newsViewModel = ViewModelProviders.of(this).get(NewsViewModel.class);
        newsViewModel.getCategorizedNews(category).observe(this, new Observer<List<NewsEntity>>() {
            @Override
            public void onChanged(@Nullable List<NewsEntity> newsEntities) {
                adapter = new NewsAdapter(newsEntities, getActivity());
                gridLayoutManager = new GridLayoutManager(getActivity(), 2);
                gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                    @Override
                    public int getSpanSize(int position) {
                        if (position % 3 == 0) {
                            return 2;
                        } else {
                            return 1;
                        }
                    }
                });
                recyclerView.setLayoutManager(gridLayoutManager);
                recyclerView.setAdapter(adapter);
            }
        });
    }
}

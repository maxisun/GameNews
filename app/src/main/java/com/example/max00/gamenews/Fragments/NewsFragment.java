package com.example.max00.gamenews.Fragments;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.max00.gamenews.API.GameNewsAPI;
import com.example.max00.gamenews.Activities.LoginActivity;
import com.example.max00.gamenews.Adapters.NewsAdapter;
import com.example.max00.gamenews.R;
import com.example.max00.gamenews.RoomArchitecture.Entity.NewsEntity;
import com.example.max00.gamenews.RoomArchitecture.Repository.NewsRepository;
import com.example.max00.gamenews.RoomArchitecture.ViewModel.NewsViewModel;
import com.example.max00.gamenews.RoomArchitecture.Repository.NewsRepository.fetchNews;
import com.google.gson.Gson;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


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
    private SharedPreferences preferences;
    private String token;

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
        if(category.equals("News")){
            setNewsAll();
        }else if (category.equals("lol")){
            setNewslol();
        }else if (category.equals("overwatch")){
            setNewsOverwatch();
        }else if (category.equals("csgo")){
            setNewsCSGO();
        }
        /*newsViewModel = ViewModelProviders.of(this).get(NewsViewModel.class);
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
        });*/
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
        if(fetchNews.getInfo().equals("Your session has expired 401")){
            Toast.makeText(getActivity(),fetchNews.getInfo(),Toast.LENGTH_SHORT).show();
            cleanPreferences();
        } else if (fetchNews.getInfo().equals("200") || fetchNews.getInfo().equals("No internet connection avalilable")){
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
            //Toast.makeText(getActivity(),fetchNews.getInfo(),Toast.LENGTH_SHORT).show();
        }
    }

    private void setNewslol(){
        if(fetchNews.getInfo().equals("Your session has expired 401")){
            Toast.makeText(getActivity(),fetchNews.getInfo(),Toast.LENGTH_SHORT).show();
            cleanPreferences();
        }else if (fetchNews.getInfo().equals("No internet connection avalilable") || fetchNews.getInfo().equals("200")) {
            newsViewModel = ViewModelProviders.of(this).get(NewsViewModel.class);
            newsViewModel.getCategorizednews().observe(this, new Observer<List<NewsEntity>>() {
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

    private void setNewsOverwatch(){
        if(fetchNews.getInfo().equals("Your session has expired 401")){
            Toast.makeText(getActivity(),fetchNews.getInfo(),Toast.LENGTH_SHORT).show();
            cleanPreferences();
        }else if(fetchNews.getInfo().equals("No internet connection avalilable") || fetchNews.getInfo().equals("200")) {
            newsViewModel = ViewModelProviders.of(this).get(NewsViewModel.class);
            newsViewModel.getCategorizedoverwatch().observe(this, new Observer<List<NewsEntity>>() {
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

    private void setNewsCSGO(){
        if (fetchNews.getInfo().equals("Your session has expired 401")){
            Toast.makeText(getActivity(),fetchNews.getInfo(),Toast.LENGTH_SHORT).show();
            cleanPreferences();
        }else if (fetchNews.getInfo().equals("No internet connection avalilable") || fetchNews.getInfo().equals("200")) {
            newsViewModel = ViewModelProviders.of(this).get(NewsViewModel.class);
            newsViewModel.getCategorizedcsgo().observe(this, new Observer<List<NewsEntity>>() {
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

    private void cleanPreferences(){
        preferences = getActivity().getSharedPreferences("Login_Token",Context.MODE_PRIVATE);
        preferences.edit().clear().apply();
        Intent intent = new Intent(getActivity(),LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    /*private void ping(){
        preferences = getActivity().getSharedPreferences("Login_Token",Context.MODE_PRIVATE);
        token = preferences.getString("Token","");
        Retrofit retrofit = new Retrofit.Builder().baseUrl(GameNewsAPI.BASEURL).addConverterFactory(GsonConverterFactory.create(new Gson())).build();
        GameNewsAPI gameNewsAPI = retrofit.create(GameNewsAPI.class);
        Call<List<NewsEntity>> news = gameNewsAPI.getNews("Beared " + token);
        news.enqueue(new Callback<List<NewsEntity>>() {
            @Override
            public void onResponse(Call<List<NewsEntity>> call, Response<List<NewsEntity>> response) {
                if (response.isSuccessful()){
                    Toast.makeText(getActivity(),"Connection Stablished",Toast.LENGTH_SHORT);
                    System.out.println("exito");
                } else {
                    Toast.makeText(getActivity(),"Connection not established",Toast.LENGTH_SHORT);
                    System.out.println("fallo");
                }
            }

            @Override
            public void onFailure(Call<List<NewsEntity>> call, Throwable t) {

            }
        });
    }*/
}

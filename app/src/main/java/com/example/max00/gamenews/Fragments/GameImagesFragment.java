package com.example.max00.gamenews.Fragments;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.max00.gamenews.Adapters.ImagesAdapter;
import com.example.max00.gamenews.Adapters.NewsAdapter;
import com.example.max00.gamenews.R;
import com.example.max00.gamenews.RoomArchitecture.Entity.NewsEntity;
import com.example.max00.gamenews.RoomArchitecture.ViewModel.NewsViewModel;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link GameImagesFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link GameImagesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GameImagesFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String category;
    private String mParam2;
    //components
    private RecyclerView recyclerView;
    private ImagesAdapter adapter;
    private NewsViewModel newsViewModel;
    private GridLayoutManager gridLayoutManager;


    private OnFragmentInteractionListener mListener;

    public GameImagesFragment() {
        // Required empty public constructor
    }

    /*
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GameImagesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static GameImagesFragment newInstance(String category) {
        GameImagesFragment fragment = new GameImagesFragment();
        Bundle args = new Bundle();
        args.putString("category", category);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            category = getArguments().getString("category");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_game_images, container, false);
        recyclerView = v.findViewById(R.id.recycleview_Game_Images);
        if(category.equals("lol")){
            setlolImages();
        }else if (category.equals("overwatch")){
            setoverwatchimages();
        }else if (category.equals("csgo")){
            setcsgoimages();
        }
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

    private void setlolImages(){
        newsViewModel = ViewModelProviders.of(this).get(NewsViewModel.class);
        newsViewModel.getCategorizednews().observe(this, new Observer<List<NewsEntity>>() {
            @Override
            public void onChanged(@Nullable List<NewsEntity> newsEntities) {
                adapter = new ImagesAdapter(newsEntities,getActivity());
                gridLayoutManager = new GridLayoutManager(getActivity(),2);
                gridLayoutManager.setSpanCount(2);
                recyclerView.setLayoutManager(gridLayoutManager);
                recyclerView.setAdapter(adapter);
            }
        });
    }

    private void setoverwatchimages(){
        newsViewModel = ViewModelProviders.of(this).get(NewsViewModel.class);
        newsViewModel.getCategorizedoverwatch().observe(this, new Observer<List<NewsEntity>>() {
            @Override
            public void onChanged(@Nullable List<NewsEntity> newsEntities) {
                adapter = new ImagesAdapter(newsEntities,getActivity());
                gridLayoutManager = new GridLayoutManager(getActivity(),2);
                gridLayoutManager.setSpanCount(2);
                recyclerView.setLayoutManager(gridLayoutManager);
                recyclerView.setAdapter(adapter);
            }
        });
    }

    private void setcsgoimages(){
        newsViewModel = ViewModelProviders.of(this).get(NewsViewModel.class);
        newsViewModel.getCategorizedcsgo().observe(this, new Observer<List<NewsEntity>>() {
            @Override
            public void onChanged(@Nullable List<NewsEntity> newsEntities) {
                adapter = new ImagesAdapter(newsEntities,getActivity());
                gridLayoutManager = new GridLayoutManager(getActivity(),2);
                gridLayoutManager.setSpanCount(2);
                recyclerView.setLayoutManager(gridLayoutManager);
                recyclerView.setAdapter(adapter);
            }
        });
    }
}

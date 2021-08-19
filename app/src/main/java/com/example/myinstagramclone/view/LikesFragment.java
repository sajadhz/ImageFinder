package com.example.myinstagramclone.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myinstagramclone.R;
import com.example.myinstagramclone.adaptor.likeAdaptor;
import com.example.myinstagramclone.model.PixabyPosts;
import com.example.myinstagramclone.model.Posts;
import com.example.myinstagramclone.webservice.Service;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LikesFragment extends Fragment {
    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    //Initializing LikesFragment Layout
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_likes,container,false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //Initializing
        RecyclerView recyclerViewLikes = view.findViewById(R.id.recyclerViewLikes);
        //GsonConverterFactory
        GsonConverterFactory gsonConverterFactory = GsonConverterFactory.create();
        //Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://pixabay.com/api/")
                .addConverterFactory(gsonConverterFactory)
                .build();
        //Service
        Service service = retrofit.create(Service.class);
        service.getPostsByKeyboard("car").enqueue(new Callback<PixabyPosts>() {
            @Override
            public void onResponse(Call<PixabyPosts> call, Response<PixabyPosts> response) {
                //Get Lists from Api
                List<Posts> postsList = response.body().getHits();
                //Setup RecyclerView
                recyclerViewLikes.setAdapter(new likeAdaptor(postsList,getActivity()));
                recyclerViewLikes.setLayoutManager(new LinearLayoutManager(getActivity()));
            }

            @Override
            public void onFailure(Call<PixabyPosts> call, Throwable t) {

            }
        });


    }
}

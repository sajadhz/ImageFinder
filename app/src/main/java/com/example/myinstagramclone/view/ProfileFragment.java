package com.example.myinstagramclone.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myinstagramclone.R;
import com.example.myinstagramclone.adaptor.HomeAdaptor;
import com.example.myinstagramclone.adaptor.ProfileAdaptor;
import com.example.myinstagramclone.model.PixabyPosts;
import com.example.myinstagramclone.model.Posts;
import com.example.myinstagramclone.webservice.Service;
import com.qintong.library.InsLoadingView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProfileFragment extends Fragment {
    RecyclerView recyclerViewProfile;

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    //Initializing ProfileFragment Layout
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //Initializing
        InsLoadingView imageViewUser = view.findViewById(R.id.imageViewUser);
        recyclerViewProfile = view.findViewById(R.id.recyclerViewProfile);
        //calling getPhotos func
        getPhotos();
        //Loading profile Image With Glide
        Glide.with(getActivity())
                .load("https://images.unsplash.com/photo-1496345875659-11f7dd282d1d?ixid=MnwxMjA3fDB8MHxzZWFyY2h8MTIzfHxwZW9wbGV8ZW58MHx8MHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=500&q=60")
                .circleCrop()
                .into(imageViewUser);
        //Listener for ProfileImage (Instagram Story Effect)
        imageViewUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (imageViewUser.getStatus() == InsLoadingView.Status.LOADING) {
                    imageViewUser.setStatus(InsLoadingView.Status.UNCLICKED);
                } else {
                    imageViewUser.setStatus(InsLoadingView.Status.LOADING);
                }
            }
        });
    }

    //Functions
    void getPhotos() {
        //GsonConverterFactory
        GsonConverterFactory gsonConverterFactory = GsonConverterFactory.create();
        //Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://pixabay.com/api/")
                .addConverterFactory(gsonConverterFactory)
                .build();
        //Service
        Service service = retrofit.create(Service.class);
        service.getPostsByKeyboard("person").enqueue(new Callback<PixabyPosts>() {
            @Override
            public void onResponse(Call<PixabyPosts> call, Response<PixabyPosts> response) {
                //Get Lists from Api
                List<Posts> postsList = response.body().getHits();
                //Setup RecyclerView
                recyclerViewProfile.setAdapter(new ProfileAdaptor(postsList, getActivity()));
                GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 3);
                recyclerViewProfile.setLayoutManager(gridLayoutManager);
            }

            @Override
            public void onFailure(Call<PixabyPosts> call, Throwable t) {

            }
        });
    }
}

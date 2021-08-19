package com.example.myinstagramclone.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myinstagramclone.R;
import com.example.myinstagramclone.adaptor.HomeAdaptor;
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
//HomeFragment
public class HomeFragment extends Fragment {

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    //Initializing HomeFragment Layout
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //Initializing
        ProgressBar progressBar = view.findViewById(R.id.progressBarHome);
        RecyclerView recyclerViewMain = view.findViewById(R.id.recyclerViewMain);
        //GsonConverterFactory
        GsonConverterFactory gsonConverterFactory = GsonConverterFactory.create();
        //Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://pixabay.com/api/")
                .addConverterFactory(gsonConverterFactory)
                .build();
        //Service
        Service service = retrofit.create(Service.class);
        service.getPostsByKeyboard("hits").enqueue(new Callback<PixabyPosts>() {
            @Override
            public void onResponse(Call<PixabyPosts> call, Response<PixabyPosts> response) {
                progressBar.setVisibility(View.INVISIBLE);
                //Get Lists from Api
                List<Posts> postsList = response.body().getHits();
                //Setup RecyclerView
                HomeAdaptor homeAdaptor = new HomeAdaptor(postsList, getActivity());
                recyclerViewMain.setAdapter(homeAdaptor);
                recyclerViewMain.setLayoutManager(new LinearLayoutManager(getActivity()));
            }

            @Override
            public void onFailure(Call<PixabyPosts> call, Throwable t) {

            }
        });
    }
}

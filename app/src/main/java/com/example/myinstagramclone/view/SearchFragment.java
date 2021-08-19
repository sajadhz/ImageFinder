package com.example.myinstagramclone.view;

import android.content.Context;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

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

public class SearchFragment extends Fragment {
    RecyclerView recyclerViewSearch;
    ProgressBar progressBarSearch;
    ImageView imageViewFail;

    @Nullable
    @Override
    //Initializing SearchFragment Layout
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //Initializing
        EditText editTextSearchPost = view.findViewById(R.id.editTextSrarchPost);
        recyclerViewSearch = view.findViewById(R.id.recyclerVIewSearch);
        progressBarSearch = view.findViewById(R.id.progressBarSerach);
        imageViewFail = view.findViewById(R.id.imageViewFail);

        progressBarSearch.setVisibility(View.INVISIBLE);
        //Listener for EditText
        editTextSearchPost.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    //Get Text from EditText
                    String searchKeyWord = editTextSearchPost.getText().toString();
                    //Calling Method
                    searchInPixaby(searchKeyWord);
                    //close keyboard when press search
                    if (editTextSearchPost.isFocused()) {
                        InputMethodManager inputManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                        inputManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                    }
                    progressBarSearch.setVisibility(View.VISIBLE);
                    recyclerViewSearch.setVisibility(View.INVISIBLE);
                    imageViewFail.setVisibility(View.INVISIBLE);
                    return true;
                }
                return false;
            }
        });
    }
    //functions
    public void searchInPixaby(String keyWord) {
        //GsonConverterFactory
        GsonConverterFactory gsonConverterFactory = GsonConverterFactory.create();
        //Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://pixabay.com/api/")
                .addConverterFactory(gsonConverterFactory)
                .build();
        //Service
        Service service = retrofit.create(Service.class);
        service.getPostsByKeyboard(keyWord).enqueue(new Callback<PixabyPosts>() {
            @Override
            public void onResponse(Call<PixabyPosts> call, Response<PixabyPosts> response) {
                progressBarSearch.setVisibility(View.INVISIBLE);
                recyclerViewSearch.setVisibility(View.VISIBLE);
                //Get Lists from Api
                List<Posts> postsList = response.body().getHits();
                //if cant find the word you want,show Not Find Image
                if (postsList.isEmpty()) {
                    imageViewFail.setVisibility(View.VISIBLE);
                    progressBarSearch.setVisibility(View.INVISIBLE);
                }
                //Setup RecyclerView
                HomeAdaptor homeAdaptor = new HomeAdaptor(postsList, getActivity());
                recyclerViewSearch.setAdapter(homeAdaptor);
                recyclerViewSearch.setLayoutManager(new LinearLayoutManager(getActivity()));
            }

            @Override
            public void onFailure(Call<PixabyPosts> call, Throwable t) {

            }
        });
    }

}

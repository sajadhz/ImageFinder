package com.example.myinstagramclone.adaptor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myinstagramclone.R;
import com.example.myinstagramclone.model.PixabyPosts;
import com.example.myinstagramclone.model.Posts;

import org.jetbrains.annotations.NotNull;

import java.util.List;

//Adaptor
public class ProfileAdaptor extends RecyclerView.Adapter<ProfileAdaptor.ProfilePostViewHolder> {

    List<Posts> postsList;
    Context context;
//Constructor
    public ProfileAdaptor(List<Posts> postsList, Context context) {
        this.postsList = postsList;
        this.context = context;
    }

    @NonNull
    @NotNull
    @Override
    public ProfilePostViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_profile, parent, false);
        return new ProfilePostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ProfilePostViewHolder holder, int position) {
        Posts posts = postsList.get(position);
        Glide.with(context)
                .load(posts.getWebformatURL())
                .placeholder(R.color.teal_200)
                .into(holder.imageViewPosts);
    }

    @Override
    public int getItemCount() {
        return postsList.size();
    }
    //ViewHolder
    class ProfilePostViewHolder extends RecyclerView.ViewHolder {
        ImageView imageViewPosts;

        public ProfilePostViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            imageViewPosts = itemView.findViewById(R.id.imageViewFirst);
        }
    }
}

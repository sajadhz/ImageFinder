package com.example.myinstagramclone.adaptor;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myinstagramclone.R;
import com.example.myinstagramclone.model.Posts;

import org.jetbrains.annotations.NotNull;

import java.util.List;
//Adaptor
public class HomeAdaptor extends RecyclerView.Adapter<HomeAdaptor.HomeViewHolder>{
    List<Posts> postsList;
    Context context;
//Constructor
    public HomeAdaptor(List<Posts> postsList, Context context) {
        this.postsList = postsList;
        this.context = context;
    }

    @NonNull
    @NotNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_home,parent,false);
        return new HomeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull HomeViewHolder holder, int position) {
    Posts posts = postsList.get(position);
    holder.textViewComment.setText(posts.getComments() + "");
    holder.textViewLike.setText(posts.getLikes() + "");
    holder.textViewViews.setText(posts.getViews() + "");
    holder.textViewUserName.setText(posts.getUsername());

        Glide.with(context)
                .load(posts.getWebformatURL())
                .into(holder.imageViewPostContent);
        Glide.with(context)
                .load(posts.getUserImageURL())
                .circleCrop()
                .into(holder.imageViewUserProfile);
    }

    @Override
    public int getItemCount() {
        return postsList.size();
    }
//View Holder
    class HomeViewHolder extends RecyclerView.ViewHolder {
        ImageView imageViewPostContent ;
        ImageView imageViewUserProfile;
        TextView textViewComment;
        TextView textViewLike;
        TextView textViewViews;
        TextView textViewUserName;
        public HomeViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            imageViewPostContent = itemView.findViewById(R.id.imageViewPosts);
            imageViewUserProfile = itemView.findViewById(R.id.imageViewUserProfile);
            textViewComment = itemView.findViewById(R.id.textViewComent);
            textViewLike  = itemView.findViewById(R.id.textViewLike);
            textViewViews  = itemView.findViewById(R.id.textViewViews);
            textViewUserName =  itemView.findViewById(R.id.textViewUserName);
        }
    }
}

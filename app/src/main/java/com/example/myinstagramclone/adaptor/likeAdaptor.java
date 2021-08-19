package com.example.myinstagramclone.adaptor;

import android.content.Context;
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
import org.w3c.dom.Text;

import java.util.List;

import retrofit2.http.POST;
//Adaptor
public class likeAdaptor extends RecyclerView.Adapter<likeAdaptor.LikeViewHolder>{
    List<Posts> postsList;
    Context context;
//Constructor
    public likeAdaptor(List<Posts> postsList, Context context) {
        this.postsList = postsList;
        this.context = context;
    }

    @NonNull
    @NotNull
    @Override
    public LikeViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_like,parent,false);
        return new LikeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull LikeViewHolder holder, int position) {
        Glide.with(context)
                .load(postsList.get(position).getUserImageURL())
                .circleCrop()
                .into(holder.imageViewProfile);
        holder.textViewUserName.setText(postsList.get(position).getUsername());
        holder.textViewActivity.setText(" Liked Your Post");
    }

    @Override
    public int getItemCount() {
        return postsList.size();
    }
//ViewHolder
    class  LikeViewHolder extends RecyclerView.ViewHolder {
        ImageView imageViewProfile;
        TextView textViewUserName;
        TextView textViewActivity;
        public LikeViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            imageViewProfile = itemView.findViewById(R.id.imageViewProf);
            textViewUserName =  itemView.findViewById(R.id.textViewUserNameLike);
            textViewActivity = itemView.findViewById(R.id.textViewActivity);
        }
    }
}

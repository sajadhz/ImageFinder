package com.example.myinstagramclone.adaptor;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.myinstagramclone.R;
import com.example.myinstagramclone.view.HomeFragment;
import com.example.myinstagramclone.view.LikesFragment;
import com.example.myinstagramclone.view.ProfileFragment;
import com.example.myinstagramclone.view.SearchFragment;

import org.jetbrains.annotations.NotNull;
//Setup Fragment Adaptor
public class FragmentPagerAdaptor extends FragmentStateAdapter {
    public FragmentPagerAdaptor(@NonNull @NotNull FragmentManager fragmentManager, @NonNull @NotNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @NotNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            //Setting fragments
            case 0:
                return new HomeFragment();
            case  1:
                return new SearchFragment();
            case 2:
                return  new LikesFragment();
            case 3:
                return new ProfileFragment();

        }
        return null;
    }

    @Override
    //Number of fragments
    public int getItemCount() {
        return 4;
    }
}

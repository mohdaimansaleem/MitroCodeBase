package com.nexus.myapplication.adapters;

import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nexus.myapplication.R;

import java.util.List;
public class HeroAdapter extends RecyclerView.Adapter<HeroAdapter.HeroViewHolder> {
    private final List<Integer> imageList;

    public HeroAdapter(List<Integer> imageList) {
        this.imageList = imageList;
    }

    @NonNull
    @Override
    public HeroViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        FrameLayout frameLayout = new FrameLayout(parent.getContext());
        frameLayout.setLayoutParams(new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
        ));

        ImageView imageView = new ImageView(parent.getContext());
        imageView.setLayoutParams(new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT
        ));
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

        ImageView gradientOverlay = new ImageView(parent.getContext());
        gradientOverlay.setLayoutParams(new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT
        ));
        gradientOverlay.setImageResource(R.drawable.gradient_overlay);

        frameLayout.addView(imageView);
        frameLayout.addView(gradientOverlay);

        return new HeroViewHolder(frameLayout, imageView);
    }

    @Override
    public void onBindViewHolder(@NonNull HeroViewHolder holder, int position) {
        holder.imageView.setImageResource(imageList.get(position));
    }

    @Override
    public int getItemCount() {
        return imageList.size();
    }

    static class HeroViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public HeroViewHolder(@NonNull View itemView, ImageView imageView) {
            super(itemView);
            this.imageView = imageView;
        }
    }
}
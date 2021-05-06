package com.example.randomusers.view;

import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.randomusers.databinding.RandomUserItemLayoutBinding;
import com.example.randomusers.model.User;
import com.example.randomusers.utils.Utility;

import java.util.List;

public class RandomUserAdapter extends RecyclerView.Adapter<RandomUserAdapter.RandomUserViewHolder>{
    private List<User> randomUsers;

    public RandomUserAdapter(List<User> randomUsers) {
        this.randomUsers = randomUsers;
    }

    public void setRandomUsers(List<User> randomUsers) {
        this.randomUsers = randomUsers;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RandomUserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RandomUserItemLayoutBinding binding = RandomUserItemLayoutBinding.inflate(LayoutInflater.from(
                parent.getContext()),
                parent,
                false);
        return new RandomUserViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RandomUserViewHolder holder, int position) {
            User randomUser = randomUsers.get(position);
            Utility.LogThis("Adapting: " + randomUser.getName().getFirst() + "" + randomUser.getName().getLast());
            holder.binding.randomUserNameTextview.setText(randomUser.getName().getFirst() + " " + randomUser.getName().getLast());
            Glide.with(holder.binding.getRoot())
                .applyDefaultRequestOptions(RequestOptions.centerCropTransform())
                .load(randomUser.getPicture().getLarge().toString())
                .into(holder.binding.randomUserImageview);
            holder.binding.userDetailsTextview.setText(randomUser.getEmail().toString() + "\n" + randomUser.getCell().toString()
                    + "\n"+randomUser.getLocation().getStreet().getNumber() + " " + randomUser.getLocation().getStreet().getName()
                    + " " + randomUser.getLocation().getCity() + ", " + randomUser.getLocation().getState()
                    + " " + randomUser.getLocation().getPostcode());

            holder.binding.userDetailsButton.setOnTouchListener(new View.OnTouchListener(){
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if(event.getAction() == MotionEvent.ACTION_DOWN) {
                        holder.binding.userDetailsTextview.setVisibility(View.VISIBLE); //Visible
                        return true;
                    } else if (event.getAction() == MotionEvent.ACTION_UP){
                        holder.binding.userDetailsTextview.setVisibility(View.GONE); //Gone
                    }
                    return false;
                }
            });
/*            holder.binding.userDetailsButton.setOnDragListener(new View.OnDragListener() {
                @Override
                public boolean onDrag(View v, DragEvent event) {
                    if (event.getAction() == DragEvent.ACTION_DRAG_ENTERED){
                        holder.binding.userDetailsTextview.setVisibility(View.VISIBLE); //Visible
                    }
                    else if (event.getAction() == DragEvent.ACTION_DRAG_ENDED){
                        holder.binding.userDetailsTextview.setVisibility(View.GONE); //Gone
                    }
                    return false;
                }
            });*/
    }

    @Override
    public int getItemCount() {
        return randomUsers.size();
    }

    class RandomUserViewHolder extends RecyclerView.ViewHolder {
        RandomUserItemLayoutBinding binding;
        public RandomUserViewHolder(@NonNull RandomUserItemLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}

package com.example.randomusers.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.randomusers.R;
import com.example.randomusers.databinding.ActivityMainBinding;
import com.example.randomusers.model.User;
import com.example.randomusers.presenter.Contract;
import com.example.randomusers.presenter.RandomUserPresenter;
import com.example.randomusers.utils.Utility;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements Contract.View{
    Contract.Presenter presenter = new RandomUserPresenter(this);
    private RandomUserAdapter adapter = new RandomUserAdapter(new ArrayList<>());
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.usersRecyclerview.setAdapter(adapter);
        Utility.LogThis("MainActivity1");
        presenter.getRandomUsers(30);
    }
    @Override
    public void displayRandomUsers(List<User> randomUsers) {
        adapter.setRandomUsers(randomUsers);
    }
    @Override
    public void setStatus(RandomUserPresenter.Status status) {

    }
}
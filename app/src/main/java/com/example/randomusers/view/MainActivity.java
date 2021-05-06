package com.example.randomusers.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

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
        //presenter.getRandomUsers(30);
        binding.searchButton.setOnClickListener(view ->{
            String s = binding.searchCountEdittext.getText().toString();
            if(s.length() > 0 && Integer.parseInt(s) > 0){
                int count = Integer.parseInt(s);
                presenter.getRandomUsers(count);
                binding.searchCountEdittext.setText("");
            }
            else
                Toast.makeText(this,"Invalid user count!",Toast.LENGTH_LONG).show();
        });

    }
    @Override
    public void displayRandomUsers(List<User> randomUsers) {
        adapter.setRandomUsers(randomUsers);
    }
    @Override
    public void setStatus(RandomUserPresenter.Status status) {
        switch (status) {
            case LOADING:{
                Log.d("Meow","LOADING");
                Toast.makeText(this,"Loading users....",Toast.LENGTH_SHORT).show();
                break;
            }
            case COMPLETE:{
                Log.d("Meow","COMPLETE");
                Toast.makeText(this,"Loading complete!",Toast.LENGTH_SHORT).show();
                break;
            }
            case ERROR:{
                Log.d("Meow","ERROR");
                Toast.makeText(this,"Error loading users",Toast.LENGTH_SHORT).show();
                break;
            }


            default:
                throw new IllegalStateException("Unexpected value: " + status);
        }
    }
    private void Toaster(RandomUserPresenter.Status status){

    }
}
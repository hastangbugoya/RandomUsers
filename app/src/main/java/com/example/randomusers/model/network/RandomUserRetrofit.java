package com.example.randomusers.model.network;

import com.example.randomusers.model.RandomUserResponse;
import com.example.randomusers.model.User;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class RandomUserRetrofit {
    private RandomUsersService randomUsersService = createRetrofit().create(RandomUsersService.class);

    private Retrofit createRetrofit() {
        return new Retrofit.Builder().baseUrl("https://randomuser.me").addConverterFactory(GsonConverterFactory.create()).build();
    }

    //https://randomuser.me/api/?results=50
    interface RandomUsersService {
        @GET("api")
        Call<RandomUserResponse> searchResults(@Query("results") int query);
    }

    public Call<RandomUserResponse> getRandomUsers(int count){
        return randomUsersService.searchResults(count);
    }
}

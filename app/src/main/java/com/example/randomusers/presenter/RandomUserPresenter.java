package com.example.randomusers.presenter;

import com.example.randomusers.model.RandomUserResponse;
import com.example.randomusers.model.User;
import com.example.randomusers.model.network.RandomUserRetrofit;
import com.example.randomusers.utils.Utility;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.randomusers.presenter.RandomUserPresenter.Status.COMPLETE;
import static com.example.randomusers.presenter.RandomUserPresenter.Status.ERROR;
import static com.example.randomusers.presenter.RandomUserPresenter.Status.LOADING;

public class RandomUserPresenter implements Contract.Presenter {
    private Contract.View view;
    private List<User> userList;

    private RandomUserRetrofit randomUserRetrofit = new RandomUserRetrofit();
    public RandomUserPresenter(Contract.View view) {
        this.view = view;
    }

    @Override
    public void getRandomUsers(int count) {
        view.setStatus(LOADING);
        Utility.LogThis("getRandomUsers:"+ count);
        randomUserRetrofit.getRandomUsers(count).enqueue(new Callback<RandomUserResponse>() {
            @Override
            public void onResponse(Call<RandomUserResponse> call, Response<RandomUserResponse> response) {
                Utility.LogThis("getRandomUsers->onResponse");
                if (response.body() != null && response.body().getResults() != null) {
                    userList = response.body().getResults();
//                    for(int i = 0; i < userList.size(); i++)
//                        Utility.LogThis(userList.get(i).toString());
                    Utility.LogThis("getRandomUsers->onResponse->notnull: "+userList.size()+"");
                    view.displayRandomUsers(userList);
                    view.setStatus(COMPLETE);
                } else
                    view.setStatus(ERROR);

            }

            @Override
            public void onFailure(Call<RandomUserResponse> call, Throwable t) {
                Utility.LogThis("getRandomUsers->onResponse");
                view.setStatus(ERROR);
            }
        });
    }

    public enum Status {
        LOADING,
        COMPLETE,
        ERROR
    }
}

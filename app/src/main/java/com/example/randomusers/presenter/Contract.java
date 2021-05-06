package com.example.randomusers.presenter;

import com.example.randomusers.model.User;

import java.util.List;

public interface Contract {
    interface Presenter {
        void getRandomUsers(int count);
    }
    interface View {
        void displayRandomUsers(List<User> randomUsers);
        void setStatus(RandomUserPresenter.Status status);
    }
}

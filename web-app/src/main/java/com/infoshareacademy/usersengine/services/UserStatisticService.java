package com.infoshareacademy.usersengine.services;

import com.infoshareacademy.usersengine.model.User;
import com.infoshareacademy.usersengine.statistics.UserActivity;
import com.infoshareacademy.usersengine.statistics.UserStatistic;

import java.time.LocalDateTime;

public class UserStatisticService {

    public UserStatistic addStatistic(User user, UserActivity userActivity){
        return new UserStatistic(user.getId(), user.getEmail(), LocalDateTime.now(), userActivity);
    }
}

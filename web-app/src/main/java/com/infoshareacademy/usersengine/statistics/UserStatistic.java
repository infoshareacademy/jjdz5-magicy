package com.infoshareacademy.usersengine.statistics;

import com.infoshareacademy.usersengine.model.User;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="users_statistics")
public class UserStatistic {

    @Id
    @Column(name="id")
    long id;

    @Column(name="user_id")
    long userId;

    @Column(name="time")
    private LocalDateTime time;

    @Enumerated(value = EnumType.ORDINAL)
    @Column(name="activity")
    UserActivity userActivity;

    public UserStatistic() {
    }

    public UserStatistic(long userId, LocalDateTime time, UserActivity userActivity) {
        this.userId = userId;
        this.time = time;
        this.userActivity = userActivity;
    }

    public UserStatistic addStatistic(User user, UserActivity userActivity){
        return new UserStatistic(user.getId(), LocalDateTime.now(), userActivity);
    }

    public long getId() {
        return id;
    }

    public long getUserId() {
        return userId;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public UserActivity getUserActivity() {
        return userActivity;
    }

    public void setUserActivity(UserActivity userActivity) {
        this.userActivity = userActivity;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("UserStatistic{");
        sb.append("id=").append(id);
        sb.append(", UserId=").append(userId);
        sb.append(", time=").append(time);
        sb.append(", userActivity=").append(userActivity);
        sb.append('}');
        return sb.toString();
    }
}

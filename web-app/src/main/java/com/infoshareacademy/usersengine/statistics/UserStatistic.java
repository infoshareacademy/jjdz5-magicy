package com.infoshareacademy.usersengine.statistics;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name="users_statistics")
public class UserStatistic {

    @Id
    @NotNull
    @Column(name="user_email")
    private String userEmail;

    @Column(name="time")
    private LocalDateTime time;

    @Enumerated(value = EnumType.STRING)
    @Column(name="activity")
    UserActivity userActivity;

    public UserStatistic() {
    }

    public UserStatistic(@NotNull String userEmail, LocalDateTime time, UserActivity userActivity) {
        this.userEmail = userEmail;
        this.time = time;
        this.userActivity = userActivity;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
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
        final StringBuilder sb = new StringBuilder("UserActivities{");
        sb.append("userEmail='").append(userEmail).append('\'');
        sb.append(", time=").append(time);
        sb.append(", userActivity=").append(userActivity);
        sb.append('}');
        return sb.toString();
    }
}

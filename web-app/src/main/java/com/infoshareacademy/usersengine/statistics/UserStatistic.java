package com.infoshareacademy.usersengine.statistics;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="users_statistics")
public class UserStatistic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private long id;

    @Column(name="user_id")
    private long userId;

    @Column(name="user_email")
    private String userEmail;

    @Column(name="time")
    private LocalDateTime time;

    @Enumerated(value = EnumType.ORDINAL)
    @Column(name="activity")
    private UserActivity userActivity;

    public UserStatistic() {
    }

    public UserStatistic(Long userId, String userEmail, LocalDateTime time, UserActivity userActivity) {
        this.userId = userId;
        this.userEmail = userEmail;
        this.time = time;
        this.userActivity = userActivity;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
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

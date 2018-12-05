package com.infoshareacademy.usersengine.interceptors;

import com.infoshareacademy.User;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import javax.servlet.http.HttpSession;


public class UserInterceptor {

    @AroundInvoke
    public void getUserId(InvocationContext ctx) throws Exception{
        User user = (User) ctx.getTarget();
        HttpSession session;

            }
}

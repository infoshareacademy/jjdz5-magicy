package com.infoshareacademy.usersengine.dao;

import com.infoshareacademy.usersengine.model.User;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Optional;

@Stateless
public class UserDao extends GenericDao<User, Long> {

    @PersistenceContext
    private EntityManager entityManager;

    public User getUserByEmail(String email){
        final Query query = entityManager.createNamedQuery("findUserByEmail");
        query.setParameter("param", email);

        return addIfDoNotExist((User) query.getResultList().stream().findFirst().orElse(null), email);
    }

    private boolean isUserExist(User user){
        Optional<User> userOpt = Optional.ofNullable(user);
        return  userOpt.isPresent();
    }

    public User addIfDoNotExist(User user, String email) {
        if (isUserExist(user)){
            return user;
        }
        User newUser = new User(email);
        save(newUser);

        return newUser;
    }
}
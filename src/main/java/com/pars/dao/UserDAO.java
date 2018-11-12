package com.pars.dao;

import com.pars.entity.User;
import com.pars.system.db.QueryType;
import com.pars.system.impls.ModelPair;

public class UserDAO extends BasicDAO<User, Integer> {

    protected UserDAO() {
        super(User.class);
    }

    public User findByLogin(String login) throws Exception {
        String SQL = "SELECT U FROM User U WHERE UPPER(U.login) = :P_LOGIN";
        User user = (User) this.retrieveStatement(SQL, QueryType.Normal_Query, new ModelPair("P_LOGIN", login.toUpperCase())).get(0);
        return user;
    }
}

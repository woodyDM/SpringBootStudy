package com.wd.service;

import com.wd.dao.UserDao;
import com.wd.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by root on 17-3-11.
 */
@Service
public class UserServiceImpl {
    @Autowired
    private UserDao userDao;


    public void saveUser(User user){
        //userDao.save(user);

    }

    public User getUser(Long id){
        return userDao.findOne(id);

       // return userDao.findOne(id);
    }
}
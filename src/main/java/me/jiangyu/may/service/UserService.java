package me.jiangyu.may.service;

import me.jiangyu.may.dao.UserDao;
import me.jiangyu.may.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * UserService
 * Created by jiangyukun on 4/23/15.
 */
@Service
@Transactional
public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserDao userDao;

    public void save(User user) {
        logger.info("save user" + user.getName());
        userDao.save(user);
    }

    public void update(User user) {
        userDao.update(user);
    }

    public void lockTable() throws Exception{
        userDao.lockTable();
    }
}

package me.jiangyu.may.dao;

import me.jiangyu.may.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * User 数据访问对象
 * Created by jinagyukun on 2015/5/12.
 */
@Repository
public class UserDao {
    private static final Logger logger = LoggerFactory.getLogger(UserDao.class);

    @PersistenceContext(unitName = "may")
    private EntityManager entityManager;

    public void save(User user) {
        entityManager.persist(user);
    }

    public void update(User user) {
        entityManager.merge(user);
    }

    public void remove(User user) {
        entityManager.remove(user);
    }

    public void lockTable() throws Exception{
        Thread.sleep(100);
        Query query = entityManager.createNativeQuery("update m_user set name = concat(name, '') ORDER BY id LIMIT 100");
        query.executeUpdate();
        Thread.sleep(100);
    }
}

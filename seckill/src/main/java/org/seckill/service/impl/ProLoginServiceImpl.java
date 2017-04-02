package org.seckill.service.impl;

import org.seckill.dao.PreLoginDao;
import org.seckill.entity.User;
import org.seckill.service.PreLoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProLoginServiceImpl implements PreLoginService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private PreLoginDao preLoginDao;

    @Override
    public boolean isExist(Long phone) {
        if(preLoginDao.queryByPhone(phone)!=null)
            return true;
        return false;
    }

    @Override
    @Transactional//事务操作，同一时间可能有大量用户注册
    public boolean registerUser(String username, String password, Long phone) {
        if (username == null || password == null || phone == null)
            return false;
        User u = new User();
        u.setUsername(username);
        u.setPassword(password);
        u.setPhone(phone);
        return preLoginDao.add(u);
    }

    @Override
    public boolean login(String username, String password, Long phone) {
        if (username == null || password == null || phone == null)
            return false;
        if (isExist(phone))
        {
            User databaseUser = preLoginDao.queryByPhone(phone);
            if (username == databaseUser.getUsername() && password == databaseUser.getPassword())
                return true;
            else
                return false;
        }
        else
        {
            return false;
        }
    }
}

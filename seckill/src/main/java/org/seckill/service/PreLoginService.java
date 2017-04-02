package org.seckill.service;

import org.seckill.entity.User;

public interface PreLoginService {
    boolean isExist(Long phone);
    boolean registerUser(String username,String password,Long phone);
    boolean login(String username,String password,Long phone);
}

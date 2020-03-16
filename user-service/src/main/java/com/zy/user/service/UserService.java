package com.zy.user.service;

import com.zy.user.mapper.UserMapper;
import com.zy.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public User queryById(Long id){
        System.out.println("id:"+id);

        return userMapper.selectByPrimaryKey(id);
    }

}

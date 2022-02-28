package com.imooc.mall.impl;

import com.imooc.mall.exception.ImoocMallException;
import com.imooc.mall.exception.ImoocMallExceptionEnum;
import com.imooc.mall.service.UserService;
import com.imooc.mall.model.dao.UserMapper;
import com.imooc.mall.model.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceimpl implements UserService {
    @Autowired
    UserMapper userMapper;
    @Override
    public User getUser() {
        return userMapper.selectByPrimaryKey(1);
    }

    @Override
    public void register(String userName, String password) throws ImoocMallException {
        User result=userMapper.selectByName(userName);
        if (result !=null){
            throw new ImoocMallException(ImoocMallExceptionEnum.NAME_EXISTED);

        }
        User user=new User();
        user.setUsername(userName);
        user.setPassword(password);
        int count=userMapper.insertSelective(user);
        if (count==0){
            throw new ImoocMallException(ImoocMallExceptionEnum.INSERT_FAILED);

        }

    }
}

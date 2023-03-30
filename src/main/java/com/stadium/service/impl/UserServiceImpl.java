package com.stadium.service.impl;

import com.stadium.dao.UserMapper;
import com.stadium.entity.User;
import com.stadium.service.UserService;
import com.stadium.utils.MybatisUtil;
import org.apache.ibatis.session.SqlSession;

import javax.servlet.http.HttpSession;

public class UserServiceImpl implements UserService {
    @Override
    public boolean user(String username, String password, String identity, HttpSession session) {
        try (SqlSession sqlSession = MybatisUtil.getSession(true)){
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            User user = mapper.getUser(username, password, identity);
            if (user == null) return false;
            session.setAttribute("user", user);
            return true;
        }
    }

    @Override
    public boolean register(User user) {
        try (SqlSession sqlSession = MybatisUtil.getSession(true)) {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            User u = mapper.SelectByUsername(user.getUsername());
            if(u == null){
                mapper.add(user);
                sqlSession.commit();
            }
            sqlSession.close();
            return u == null;
        }
    }


}

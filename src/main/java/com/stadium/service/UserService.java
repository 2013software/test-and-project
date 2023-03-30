package com.stadium.service;

import com.stadium.dao.UserMapper;
import com.stadium.entity.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import javax.servlet.http.HttpSession;

import java.util.List;

import static com.stadium.utils.MybatisUtil.factory;


public interface UserService {
    boolean user(String username, String password, String identity, HttpSession session);
    boolean register(User user);


    public default List<User> selectAll(){
        SqlSession sqlSession = factory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<User> users = mapper.selectAll();
        sqlSession.close();
        return users;
    }
    public default void update(User user){
        SqlSession sqlSession = factory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        mapper.update(user);
        sqlSession.commit();
        sqlSession.close();
    }
}

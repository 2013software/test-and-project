import com.stadium.dao.UserMapper;
import com.stadium.entity.User;
import com.stadium.utils.MybatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestUser {
    @Test
    public void GetUser_Student(){
        try (SqlSession sqlSession = MybatisUtil.getSession(true)){
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            User user = mapper.getUser("fxy","fxy5774","student");
            Assertions.assertNotNull(user,"get user-student data has failed");
        }
    }
    @Test
    public void GetUser_Teacher(){
        try (SqlSession sqlSession = MybatisUtil.getSession(true)){
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            User user = mapper.getUser("mzxtx","mzxtx","teacher");
            Assertions.assertNotNull(user,"get user-teacher data has failed");
        }
    }
    @Test
    public void SelectByUsername(){
        try (SqlSession sqlSession = MybatisUtil.getSession(true)){
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            User user = mapper.SelectByUsername("mzxtx");
            User user1 = mapper.getUser("mzxtx","mzxtx","teacher");
            Assertions.assertEquals(user,user1,"select user by user's name has failed");
        }
    }
    @Test
    public void AddUser(){
        try (SqlSession sqlSession = MybatisUtil.getSession(true)){
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            User user = new User();
            user.setUsername("1111");
            user.setPassword("1111");
            user.setTelephone("13358749683");
            user.setIdentity("student");
            mapper.add(user);
            User user1 = mapper.SelectByUsername("1111");
            Assertions.assertNotNull(user1,"add user data has failed because error in add userid");

        }
    }

    @Test
    public void Update(){
        try (SqlSession sqlSession = MybatisUtil.getSession(true)){
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            User user = mapper.SelectByUsername("Zhao");
            user.setUsername("Xiao");
            user.setPassword("J123456");
            user.setTelephone("18642333333");
            mapper.update(user);
            User user1 = mapper.SelectByUsername("Xiao");
            int a=user.getUserid();
            int b=user1.getUserid();
            Assertions.assertEquals(b,a,"update user's data has failed");
        }
    }
}

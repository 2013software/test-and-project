import com.stadium.dao.UserMapper;
import com.stadium.entity.User;
import com.stadium.utils.MybatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class TestUser {
    @Test
    public void GetUser_Student(){
        try (SqlSession sqlSession = MybatisUtil.getSession(true)){
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            User user = mapper.getUser("fxy","fxy5774","student");
            Assert.assertNotNull(user);
        }
    }
    @Test
    public void GetUser_Teacher(){
        try (SqlSession sqlSession = MybatisUtil.getSession(true)){
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            User user = mapper.getUser("mzxtx","mzxtx","teacher");
            Assert.assertNotNull(user);
        }
    }
    @Test
    public void SelectByUsername(){
        try (SqlSession sqlSession = MybatisUtil.getSession(true)){
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            User user = mapper.SelectByUsername("mzxtx");
            Assert.assertNotNull(user);
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
            User user1 = mapper.getUser("1111","1111","student");
            Assert.assertNotNull(user1);
        }
    }
    @Test
    public void Update(){
        try (SqlSession sqlSession = MybatisUtil.getSession(true)){
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            User user = mapper.SelectByUsername("Zhao");
            user.setUsername("Zhao");
            user.setPassword("zhao1");
            user.setTelephone("18647502395");
            mapper.update(user);
            User user1 = mapper.getUser("Zhao","zhao1","teacher");
            Assert.assertNotNull(user1);
        }
    }
}

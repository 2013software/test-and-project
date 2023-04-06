import com.stadium.dao.SelectCourseMapper;
import com.stadium.entity.Course;
import com.stadium.utils.MybatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestSeCourse {

    @Test
    public void  GetSelectCourseList() {
        try (SqlSession sqlSession = MybatisUtil.getSession(true)) {
            SelectCourseMapper mapper = sqlSession.getMapper(SelectCourseMapper.class);
            Course course=mapper.getSelectCourseList().get(0);
            Assertions.assertNotNull(course,"getSelectCourseList() test failed.");
        }}

    @Test
    public void  SelectNumber() {
        try (SqlSession sqlSession = MybatisUtil.getSession(true)) {
            SelectCourseMapper mapper = sqlSession.getMapper(SelectCourseMapper.class);
            Course course=mapper.getSelectCourseList().get(2);
            mapper.SelectNumber(course);
            Assertions.assertEquals(5,course.getCurrent_number()+1,"minusNumber has failed");
        }}
}
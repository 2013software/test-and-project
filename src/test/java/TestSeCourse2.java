import com.stadium.dao.SelectCourseMapper2;
import com.stadium.entity.Course;
import com.stadium.entity.Course_state;
import com.stadium.utils.MybatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestSeCourse2 {

    @Test
    public void  GetSelectCourseList() {
        try (SqlSession sqlSession = MybatisUtil.getSession(true)) {
            SelectCourseMapper2 mapper = sqlSession.getMapper(SelectCourseMapper2.class);
            Course course=mapper.getSelectCourseList2(13).get(0);
            Assertions.assertNotNull(course,"getSelectCourseList() test failed.");
        }}

    @Test
    public void  AddCourse() {
        try (SqlSession sqlSession = MybatisUtil.getSession(true)) {
            SelectCourseMapper2 mapper = sqlSession.getMapper(SelectCourseMapper2.class);
            Course_state state=new Course_state();
            state.setUserid(14);
            state.setCourseid(4);

            mapper.AddCourse(state);
            Course course1=mapper.getSelectCourseList2(14).get(0);

            Assertions.assertEquals(4,course1.getCourse_id(),"minusNumber has failed");

        }}
}

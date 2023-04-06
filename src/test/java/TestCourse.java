import com.stadium.dao.CourseMapper;
import com.stadium.entity.Course;
import com.stadium.entity.StVenue;
import com.stadium.entity.Venue;
import com.stadium.utils.MybatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestCourse {

    @Test
    public void  GetCourseList(){
        try (SqlSession sqlSession = MybatisUtil.getSession(true)) {
            CourseMapper mapper = sqlSession.getMapper(CourseMapper.class);
            int a=mapper.getCourseList(1).size();
            if (a!=0){
            Course course=new Course();
            course = mapper.getCourseList(1).get(0);
            Assertions.assertNotNull(course, "get user-teacher data has failed");}
        }
    }
    @Test
    public void  GetCourseList_Teacher(){
        try (SqlSession sqlSession = MybatisUtil.getSession(true)) {
            CourseMapper mapper = sqlSession.getMapper(CourseMapper.class);
            int a=mapper.getCourseList_Teacher(33).size();
            if (a!=0){
                Course course=new Course();
                course = mapper.getCourseList_Teacher(33).get(0);
                Assertions.assertNotNull(course, "get user-teacher data has failed");}
        }
    }
    @Test
    public void  GetCourseList_Teacher_Manage(){
        try (SqlSession sqlSession = MybatisUtil.getSession(true)) {
            CourseMapper mapper = sqlSession.getMapper(CourseMapper.class);
            int a=mapper.getCourseList(56).size();
            if (a!=0){
                Course course=new Course();
                course = mapper.getCourseList_Teacher_manage(56).get(0);
                Assertions.assertNotNull(course, "get user-teacher data has failed");}
        }
    }
    @Test
    public void CancelCourse() throws Exception{
        try (SqlSession sqlSession = MybatisUtil.getSession(true)) {
            CourseMapper mapper = sqlSession.getMapper(CourseMapper.class);
            mapper.CancelCourse("4");
            int i=0;
            while(i<mapper.getCourseList(14).size())
            {
                Course course= mapper.getCourseList(14).get(i);
                if(course.getId()==4){
                    throw new Exception("test failed");
                }
                i=i+1;
            }

        }
    }
    @Test
    public void MinusNumber(){
        try (SqlSession sqlSession = MybatisUtil.getSession(true)) {
            CourseMapper mapper = sqlSession.getMapper(CourseMapper.class);
            mapper.MinusNumber("1");
            Course course=mapper.getCourseList(1).get(0);
            Assertions.assertEquals(1,course.getCurrent_number(),"minusNumber has failed");
    }}
    @Test
    public void Add_Course(){
        try (SqlSession sqlSession = MybatisUtil.getSession(true)) {
            CourseMapper mapper = sqlSession.getMapper(CourseMapper.class);
            Course course=new Course();
            course.setTeacherid(57);
            course.setCourse_name("Basketball Court");
            course.setVenueid(3);
            course.setTimeid(3);
            course.setMaximum_number(44);
            mapper.Add_Course(course);
            Course course1= mapper.getCourseList_Teacher(57).get(0);
            Assertions.assertNotNull(course1,"adding course has failed");
        }
    }

    @Test
    public void SelectByTimeVenue(){
        try (SqlSession sqlSession = MybatisUtil.getSession(true)) {
            CourseMapper mapper = sqlSession.getMapper(CourseMapper.class);
            Course course= mapper.getCourseList_Teacher(57).get(0);
            Course course2=new Course();
            course2.setTimeid(3);
            course2.setVenueid(3);
            Course course1=mapper.SelectByTimeVenue(course2);
            Assertions.assertEquals(course,course1,"minusNumber has failed");
        }
    }

}

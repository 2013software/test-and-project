package com.stadium.servlet.course;

import com.stadium.entity.Course;
import com.stadium.entity.Course_state;
import com.stadium.service.CourseService;
import com.stadium.service.SelectCourseService;
import com.stadium.service.SelectCourseService2;
import com.stadium.service.impl.CourseServiceImpl;
import com.stadium.service.impl.SelectCourseServiceImpl;
import com.stadium.service.impl.SelectCourseServiceImpl2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/add-course")
public class AddCourseServlet extends HttpServlet {
    SelectCourseService2 service;
    CourseService service1;
    SelectCourseService service3;

    @Override
    public void init() throws ServletException {
        service = new SelectCourseServiceImpl2();
        service1 = new CourseServiceImpl();
        service3 = new SelectCourseServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String userid = req.getParameter("uid");
        String courseid = req.getParameter("courseid");
        Course_state state = new Course_state();
        state.setCourseid(Integer.parseInt(courseid));
        state.setUserid(Integer.parseInt(userid));
        service.addCourse(state);
//        resp.sendRedirect("select-course");
        Course course1 = new Course();
        System.out.print(course1.getCourse_id());
        int n = course1.getCurrent_number();
        int n1 = n + 1;
        course1.setCourse_id(Integer.parseInt(courseid));
        course1.setCurrent_number(n1);
        service3.selectNumber(course1);
        req.getRequestDispatcher("select-course").forward(req,resp);
    }
}

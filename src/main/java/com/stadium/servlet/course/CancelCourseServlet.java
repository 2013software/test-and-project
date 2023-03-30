package com.stadium.servlet.course;

import com.stadium.entity.Course_state;
import com.stadium.service.CourseService;
import com.stadium.service.impl.CourseServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/cancel-course")
public class CancelCourseServlet extends HttpServlet {
    CourseService service;

    @Override
    public void init() throws ServletException {
        service = new CourseServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String courseid = req.getParameter("courseid");
        service.cancelCourse(id);
        service.MinusNumber(courseid);
        resp.sendRedirect("course");
    }
}

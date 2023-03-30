package com.stadium.servlet.course;

import com.stadium.entity.User;
import com.stadium.service.CourseService;
import com.stadium.service.StVenueService;
import com.stadium.service.impl.CourseServiceImpl;
import com.stadium.service.impl.StVenueServiceImpl;
import com.stadium.utils.ThymeleafUtil;
import org.thymeleaf.context.Context;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/course")
public class CourseServlet extends HttpServlet {
    CourseService service;
    StVenueService service1;

    @Override
    public void init() throws ServletException {
        service = new CourseServiceImpl();
        service1 = new StVenueServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Context context = new Context();
        User user = (User) req.getSession().getAttribute("user");
        context.setVariable("username", user.getUsername());
        context.setVariable("course_list",service.getCourseList(user.getUserid()));
        context.setVariable("stvenue_list", service1.getStVenueList(user.getUserid()));
        ThymeleafUtil.process("course.html", context, resp.getWriter());
    }
}

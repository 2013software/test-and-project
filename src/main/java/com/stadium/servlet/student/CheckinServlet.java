package com.stadium.servlet.student;

import com.stadium.entity.User;
import com.stadium.service.CourseService;
import com.stadium.service.impl.CourseServiceImpl;
import com.stadium.utils.ThymeleafUtil;
import org.thymeleaf.context.Context;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/check-in")
public class CheckinServlet extends HttpServlet {
    CourseService service;

    @Override
    public void init() throws ServletException {
        service = new CourseServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Context context = new Context();
        User user = (User) req.getSession().getAttribute("user");
        context.setVariable("username", user.getUsername());
        context.setVariable("course_list",service.getCourseList(user.getUserid()));
        ThymeleafUtil.process("check-in.html", context, resp.getWriter());
    }
}

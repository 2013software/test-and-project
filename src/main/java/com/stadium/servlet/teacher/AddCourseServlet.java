package com.stadium.servlet.teacher;

import com.stadium.entity.Course;
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
import java.util.Objects;

@WebServlet("/add-newcourse")
public class AddCourseServlet extends HttpServlet {
    CourseService service;

    @Override
    public void init() throws ServletException {
        service = new CourseServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Context context = new Context();
        User user = (User) req.getSession().getAttribute("user");
        context.setVariable("userid", user.getUserid());
        String teacher = req.getParameter("teacherid");
        String coursename = req.getParameter("coursename");
        String time = req.getParameter("timeid");
        String venue = req.getParameter("venueid");
        String num = req.getParameter("number");
        if (!Objects.equals(coursename, null) && !Objects.equals(time, null) && !Objects.equals(venue, null) && !Objects.equals(num, null) && !Objects.equals(coursename, "") && !Objects.equals(time, "") && !Objects.equals(venue, "") && !Objects.equals(num, "")) {
            Course course = new Course();
            int teacherid = Integer.parseInt(teacher);
            int timeid = Integer.parseInt(time);
            int venueid = Integer.parseInt(venue);
            int number = Integer.parseInt(num);
            course.setTeacherid(teacherid);
            course.setCourse_name(coursename);
            course.setTimeid(timeid);
            course.setVenueid(venueid);
            course.setMaximum_number(number);
            boolean result = service.addcourse(course);
            if (result) {
                req.getSession().removeAttribute("add-failure");
                resp.sendRedirect("tmanage");
                return;
            }
            else if (req.getSession().getAttribute("add-failure") != null) {
                context.setVariable("failure2", true);
                req.getSession().removeAttribute("add-failure");
            }
        }
        if (req.getSession().getAttribute("add-failure") != null) {
            context.setVariable("failure2", true);
            req.getSession().removeAttribute("add-failure");
        }

        context.setVariable("time_list", service.getTimeList());
        context.setVariable("venue_list", service.getVenueList_addcourse());
        ThymeleafUtil.process("add-course.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().setAttribute("add-failure", new Object());
        this.doGet(req, resp);
    }
}

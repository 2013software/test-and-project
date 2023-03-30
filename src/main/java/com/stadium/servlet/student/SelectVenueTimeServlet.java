package com.stadium.servlet.student;

import com.stadium.entity.StVenue;
import com.stadium.entity.User;
import com.stadium.service.CourseService;
import com.stadium.service.StVenueService;
import com.stadium.service.VenueService;
import com.stadium.service.impl.CourseServiceImpl;
import com.stadium.service.impl.StVenueServiceImpl;
import com.stadium.service.impl.VenueServiceImpl;
import com.stadium.utils.ThymeleafUtil;
import org.thymeleaf.context.Context;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@WebServlet("/time")
public class SelectVenueTimeServlet extends HttpServlet {
    VenueService service;
    CourseService service1;
    StVenueService service2;
    @Override
    public void init() throws ServletException {
        service = new VenueServiceImpl();
        service1 = new CourseServiceImpl();
        service2 = new StVenueServiceImpl();
    }

    String id1;
    String userid1;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Context context = new Context();
        String id = req.getParameter("id");
        String userid = req.getParameter("userid");
        id1 = id;
        userid1 = userid;
        User user = (User) req.getSession().getAttribute("user");
        context.setVariable("time_list", service1.getTimeList());
        if(Objects.equals(user.getIdentity(), "student")){
            req.getRequestDispatcher("venue").forward(req,resp);
        }
        if(Objects.equals(user.getIdentity(), "teacher")){
            ThymeleafUtil.process("time.html", context, resp.getWriter());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String time = req.getParameter("timeid");
        StVenue stVenue = new StVenue();
        int timeid = Integer.parseInt(time);
        int id = Integer.parseInt(id1);
        int userid = Integer.parseInt(userid1);
        stVenue.setVenueid(id);
        stVenue.setTimeid(timeid);
        stVenue.setUseid(userid);
        service2.selectVenue(stVenue);
        resp.sendRedirect("tcourse");
    }
}

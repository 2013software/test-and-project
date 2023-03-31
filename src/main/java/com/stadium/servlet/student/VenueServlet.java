package com.stadium.servlet.student;

import com.stadium.entity.StVenue;
import com.stadium.entity.User;
import com.stadium.entity.Venue;
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

@WebServlet("/venue")
public class VenueServlet extends HttpServlet {
    VenueService service;
    CourseService service1;
    StVenueService service2;
    @Override
    public void init() throws ServletException{
        service = new VenueServiceImpl();
        service1 = new CourseServiceImpl();
        service2 = new StVenueServiceImpl();
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Context context = new Context();
        User user = (User) req.getSession().getAttribute("user");
        context.setVariable("userid", user.getUserid());
        Venue venue = (Venue) req.getSession().getAttribute("venue");
        context.setVariable("venue_list",service.getVenueList_act());
        context.setVariable("time_list", service1.getTimeList());
        ThymeleafUtil.process("venue.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String venue = req.getParameter("venueid");
        String time = req.getParameter("timeid");
        User user = (User) req.getSession().getAttribute("user");
        int userid = user.getUserid();
        int timeid = Integer.parseInt(time);
        int id = Integer.parseInt(venue);
        StVenue stVenue = new StVenue();
        stVenue.setVenueid(id);
        stVenue.setTimeid(timeid);
        stVenue.setUseid(userid);
        service2.selectVenue(stVenue);
        resp.sendRedirect("course");
    }
}

package com.stadium.servlet.teacher;

import com.stadium.entity.Time;
import com.stadium.entity.User;
import com.stadium.service.CourseService;
import com.stadium.service.VenueService;
import com.stadium.service.impl.CourseServiceImpl;
import com.stadium.service.impl.VenueServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;


@WebServlet("/time_venue")
public class TimeVenueServlet extends HttpServlet {
    CourseService service1;
    VenueService service;
    public static int venueid_global;

    @Override
    public void init() throws ServletException {
        service = new VenueServiceImpl();
        service1 = new CourseServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String venue = req.getParameter("venueid");
        venueid_global = Integer.parseInt(venue);
        User user = (User) req.getSession().getAttribute("user");
        if(Objects.equals(user.getIdentity(), "student")) {
            resp.sendRedirect("venue");
        }
        if(Objects.equals(user.getIdentity(), "teacher")){
            resp.sendRedirect("tvenue");
        }
    }

    public static int setId() {
        return venueid_global;
    }

}

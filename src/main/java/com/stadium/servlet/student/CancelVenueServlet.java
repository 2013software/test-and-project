package com.stadium.servlet.student;

import com.stadium.entity.StVenue;
import com.stadium.entity.User;
import com.stadium.service.CourseService;
import com.stadium.service.StVenueService;
import com.stadium.service.impl.StVenueServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@WebServlet("/cancel-venue")
public class CancelVenueServlet extends HttpServlet {
    StVenueService service;

    @Override
    public void init() throws ServletException {
        service = new StVenueServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
//        String userid = req.getParameter("userid");
        User user = (User) req.getSession().getAttribute("user");
        service.cancelVenue(id);
        if(Objects.equals(user.getIdentity(), "student")){
            resp.sendRedirect("course");
        }
        if(Objects.equals(user.getIdentity(), "teacher")){
            resp.sendRedirect("tmanage");
        }
    }
}

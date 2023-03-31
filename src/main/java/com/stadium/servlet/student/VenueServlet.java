package com.stadium.servlet.student;

import com.stadium.entity.*;
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
import java.util.ArrayList;
import java.util.List;
import com.stadium.servlet.teacher.TimeVenueServlet;

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
        String venueid = req.getParameter("venueid");
        int vid = 0;
        if(venueid != null){
            vid = Integer.parseInt(venueid);
        }

        //初始化需要得到的数组
        int[] array = new int[service1.getTimeList2(user.getUserid()).size()];
        int[] array1 = new int[service1.getTimeList().size()];
        //使用for循环得到数组
        for(int i = 0; i < service1.getTimeList2(user.getUserid()).size();i++){
            array[i] = service1.getTimeList2(user.getUserid()).get(i).getId();
        }
        for(int m = 0; m < service1.getTimeList2(user.getUserid()).size();m++){
            System.out.print(array[m]);
        }
        Time[] array_part = new Time[service1.getTimeList2(user.getUserid()).size()];
        List<Time> r = new ArrayList<>();
        List<Time> r1 = new ArrayList<>();
        r1 = service1.getTimeList2(user.getUserid());
        r = service1.getTimeList();
        int nn = TimeVenueServlet.setId();
        for (int n = 0; n < array.length; n++) {
            OUT:
            for (int j = 0; j < r.size(); j++) {
                if (r.get(j).getId() == array[n] && r1.get(n).getVenueid() == TimeVenueServlet.setId()) {
                    if (r.get(j) != null) {
                        r.remove(j);
                        break OUT;
                    }
                }
            }
        }

        for(int m = 0; m < service1.getTimeList2(user.getUserid()).size();m++){
            System.out.print(r.get(m).getVenueid());
            System.out.print(r.get(m).getId());
        }
        context.setVariable("venue_list",service.getVenueList_act());
        context.setVariable("time_list", r);
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

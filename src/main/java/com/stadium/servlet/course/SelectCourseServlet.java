package com.stadium.servlet.course;

import com.stadium.entity.Course;
import com.stadium.entity.User;
import com.stadium.service.SelectCourseService;
import com.stadium.service.SelectCourseService2;
import com.stadium.service.impl.SelectCourseServiceImpl;
import com.stadium.service.impl.SelectCourseServiceImpl2;
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

@WebServlet("/select-course")
public class SelectCourseServlet extends HttpServlet {
   SelectCourseService service;
    SelectCourseService2 service1;
    @Override
    public void init() throws ServletException{
        service = new SelectCourseServiceImpl();
        service1 = new SelectCourseServiceImpl2();
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Context context = new Context();
        User user = (User) req.getSession().getAttribute("user");
        context.setVariable("uid", user.getUserid());
        Course course = (Course) req.getSession().getAttribute("course");
        //初始化需要得到的数组
        int[] array = new int[service.getSelectCourseList().size()];
        //使用for循环得到数组
        for(int i = 0; i < service1.getSelectCourseList2(user.getUserid()).size();i++){
            array[i] = service1.getSelectCourseList2(user.getUserid()).get(i).getCourse_id();
        }
        Course[] array_part = new Course[service1.getSelectCourseList2(user.getUserid()).size()];
        List<Course> r = new ArrayList<>();
        r = service.getSelectCourseList();
        for (int k : array) {
            OUT:
            for (int j = 0; j < r.size(); j++) {
                if (r.get(j).getCourse_id() == k) {
                    if (r.get(j) != null) {
                        r.remove(j);
                        break OUT;
                    }
                }
            }
        }
        context.setVariable("select_course_list",r);
        ThymeleafUtil.process("select-course.html", context, resp.getWriter());
    }
}

package com.stadium.servlet.user;
import com.stadium.dao.UserMapper;
import com.stadium.entity.User;
import com.stadium.service.UserService;
import com.stadium.service.impl.UserServiceImpl;
import com.stadium.utils.ThymeleafUtil;
import org.thymeleaf.context.Context;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/selectAllServlet")
public class SelectAllServlet extends HttpServlet{
    UserService service;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
        List<User> users = service.selectAll();
        request.setAttribute("users",users);
//        request.getRequestDispatcher("/personal.html").forward(request,response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
        this.doGet(request,response);
    }
}

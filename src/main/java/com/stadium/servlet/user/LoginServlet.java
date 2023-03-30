package com.stadium.servlet.user;

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
import java.util.Objects;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    UserService service;
    @Override
    public void init() throws ServletException{
        service = new UserServiceImpl();
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Context context = new Context();
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String identity = req.getParameter("identity");
        if(service.user(username, password, identity, req.getSession())) {
            if (Objects.equals(identity, "student")) {
                resp.sendRedirect("index");
            }
            if (Objects.equals(identity, "teacher")) {
                resp.sendRedirect("tindex");
            }
            if (req.getSession().getAttribute("login-failure") != null){
                context.setVariable("failure", true);
                req.getSession().removeAttribute("login-failure");
            }
        }
        if (req.getSession().getAttribute("login-failure") != null){
            context.setVariable("failure", true);
            req.getSession().removeAttribute("login-failure");
        }
        ThymeleafUtil.process("login.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().setAttribute("login-failure", new Object());
        this.doGet(req, resp);
    }
}

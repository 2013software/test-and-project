package com.stadium.servlet.user;

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
import java.util.Objects;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    UserService service;

    @Override
    public void init() throws ServletException {
        service = new UserServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Context context = new Context();
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String telephone = req.getParameter("telephone");
        String identity = req.getParameter("identity");

        if (!Objects.equals(username, null) && !Objects.equals(password, null) && !Objects.equals(telephone, null) && !Objects.equals(identity, null) && !Objects.equals(username, "") && !Objects.equals(password, "") && !Objects.equals(telephone, "") && !Objects.equals(identity, "")) {
            User user = new User();
            user.setUsername(username);
            user.setPassword(password);
            user.setTelephone(telephone);
            user.setIdentity(identity);
            boolean result = service.register(user);
            if (result) {
                req.getSession().removeAttribute("register-failure");
                resp.sendRedirect("login");
                return;
            }
            else if (req.getSession().getAttribute("register-failure") != null) {
                context.setVariable("failure1", true);
                req.getSession().removeAttribute("register-failure");
            }
        }
        if (req.getSession().getAttribute("register-failure") != null) {
            context.setVariable("failure1", true);
            req.getSession().removeAttribute("register-failure");
        }
        ThymeleafUtil.process("register.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().setAttribute("register-failure", new Object());
        this.doGet(req, resp);
    }
}

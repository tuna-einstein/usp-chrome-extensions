package com.usp.tabcloud.server.controller;

import javax.servlet.http.HttpServletResponse;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.util.ResponseLocator;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

public class LogoutURLController extends Controller {

    @Override
    public Navigation run() throws Exception {
        UserService userService = UserServiceFactory.getUserService();
        String logoutUrl = userService.createLogoutURL("/");
        HttpServletResponse response = ResponseLocator.get();
        response.sendRedirect(logoutUrl);
        return null;
    }
}

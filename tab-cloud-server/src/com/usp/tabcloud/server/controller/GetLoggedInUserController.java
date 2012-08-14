package com.usp.tabcloud.server.controller;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

public class GetLoggedInUserController extends Controller {

    @Override
    public Navigation run() throws Exception {
        String requesterEmail = ControllerModule.getRequesterEmail();
        response.setContentType("text/plain");
        if (requesterEmail == null) {
            UserService userService = UserServiceFactory.getUserService();
            response.getWriter().println(userService.createLoginURL("/"));
            return null;
        }
        response.getWriter().println(requesterEmail);
        return null;
    }
}

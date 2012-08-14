package com.usp.tabcloud.server.controller;

import java.io.PrintWriter;
import java.util.List;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.datastore.Datastore;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.usp.tabcloud.server.meta.TabsInfoMeta;
import com.usp.tabcloud.shared.model.TabsInfo;

public class GetUnNotifiedTabsController extends Controller {

    @Override
    public Navigation run() throws Exception {
        String requesterEmail = ControllerModule.getRequesterEmail();
        if (requesterEmail == null) {
            UserService userService = UserServiceFactory.getUserService();
            response.sendRedirect(userService.createLoginURL("/"));
            return null;
        }
        response.setContentType("text/html");
        TabsInfoMeta meta = TabsInfoMeta.get();
        
        List<TabsInfo> tabsList = Datastore.query(meta)
                .filter(meta.receiverEmail.equal(requesterEmail))
                .filter(meta.notified.equal(false))
                .asList();
        for (TabsInfo tab : tabsList) {
            tab.setNotified(true);
            Datastore.put(tab);
        }
        
        PrintWriter pw = response.getWriter();
        pw.println("<b> Received tabs from :</b> <br>");
        for (TabsInfo tab : tabsList) {
           pw.println(tab.getRequesterEmail() + "<br>");
        }
        pw.println("Please <a href=\"http://usp046.appspot.com\" target=\"_blank\"> Click Here </a> to manage the tabs");
        return null;
    }
}

package com.usp.tabcloud.server.controller;

import java.util.List;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.datastore.Datastore;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.usp.tabcloud.server.meta.TabsInfoMeta;
import com.usp.tabcloud.shared.model.TabsInfo;

public class IsReceivedNewTabsController extends Controller {

    @Override
    public Navigation run() throws Exception {
        String requesterEmail = ControllerModule.getRequesterEmail();
        response.setContentType("text/plain");
        if (requesterEmail == null) {
            UserService userService = UserServiceFactory.getUserService();
            response.getWriter().println(userService.createLoginURL("/"));
            return null;
        }
        TabsInfoMeta meta = TabsInfoMeta.get();
        
        List<TabsInfo> tabsList = Datastore.query(meta)
                .filter(meta.receiverEmail.equal(requesterEmail))
                .filter(meta.notified.equal(false))
                .asList();
       if (tabsList.isEmpty()) {
           response.getWriter().print("false");
       } else {
           response.getWriter().print("true");
       }
        return null;
    }
}

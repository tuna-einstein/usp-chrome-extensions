package com.usp.tabcloud.server.controller;

import java.util.List;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.datastore.Datastore;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.usp.tabcloud.server.meta.TabsInfoMeta;
import com.usp.tabcloud.shared.model.TabsInfo;

public class GetReceivedTabsController extends Controller {

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
                .sort(meta.date.desc)
                .asList();
        
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        
        response.getWriter().println(gson.toJson(tabsList.toArray()));
        return null;
    }
}

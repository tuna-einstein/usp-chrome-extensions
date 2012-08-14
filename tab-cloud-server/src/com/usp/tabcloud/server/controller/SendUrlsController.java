package com.usp.tabcloud.server.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.datastore.Datastore;
import org.slim3.util.RequestLocator;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.usp.tabcloud.server.util.EmailSender;
import com.usp.tabcloud.shared.model.TabsInfo;

public class SendUrlsController extends Controller {

    @Override
    public Navigation run() throws Exception {

        String requesterEmail = ControllerModule.getRequesterEmail();
        
        if (requesterEmail == null) {
            UserService userService = UserServiceFactory.getUserService();
            response.getWriter().println(userService.createLoginURL("SendUrls.html"));
            return null;
        }
        HttpServletRequest request = RequestLocator.get();
        String test = request.getParameter("test");
        if (test != null) {
            TabsInfo info = new TabsInfo();
            info.setRequesterEmail(requesterEmail);
            info.setReceiverEmail("test@example.com");
            info.setDate(new Date());
            String[] urls = {"www.google.com", "www.microsoft.com"};
            info.setUrls(Arrays.asList(urls));
            info.setSubjectText("Hello");
            Datastore.put(info);
            
            response.setContentType("text/plain");
            response.getWriter().println("test url invoked");
            return null;
        }
        
        
        String receiverEmail = request.getParameter("receiverEmail");
        String subjectText = request.getParameter("subjectText");
        String urls[] = request.getParameterValues("url");
        String titles[] = request.getParameterValues("title");
        
        TabsInfo info = new TabsInfo();
        info.setRequesterEmail(requesterEmail);
        info.setReceiverEmail(receiverEmail);
        info.setDate(new Date());
        info.setUrls(Arrays.asList(urls));
        info.setTitles(Arrays.asList(titles));
        info.setSubjectText(subjectText);
        info.setNotified(false);
        Datastore.put(info);

        response.setContentType("text/plain");
        response.getWriter().println(requesterEmail);
        EmailSender sender = new EmailSender();
        List<String> receipients = new ArrayList<String>();
        receipients.add(receiverEmail);
        sender.sendEmail(requesterEmail, "I have sent currently opened tabs on my browser",
            "Please visit http://usp046.appspot.com to see the sent tabs", receipients);
        return null;
    }
}

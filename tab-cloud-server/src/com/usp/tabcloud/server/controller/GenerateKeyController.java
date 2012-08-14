package com.usp.tabcloud.server.controller;

import java.util.Arrays;
import java.util.Date;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.datastore.Datastore;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.usp.tabcloud.shared.model.TabsInfo;

public class GenerateKeyController extends Controller {

    @Override
    public Navigation run() throws Exception {

        String subjectText = request.getParameter("subjectText");
        String urls[] = request.getParameterValues("url");
        String titles[] = request.getParameterValues("title");
        
        TabsInfo info = new TabsInfo();
        info.setDate(new Date());
        info.setUrls(Arrays.asList(urls));
        info.setTitles(Arrays.asList(titles));
        info.setSubjectText(subjectText);
        info.setNotified(false);
        Key key = Datastore.put(info);

        response.setContentType("text/plain");
        response.getWriter().print(KeyFactory.keyToString(key));
        return null;
    }
}

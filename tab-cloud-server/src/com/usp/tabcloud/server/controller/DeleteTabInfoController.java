package com.usp.tabcloud.server.controller;

import javax.servlet.http.HttpServletRequest;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.datastore.Datastore;
import org.slim3.util.RequestLocator;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

public class DeleteTabInfoController extends Controller {

    @Override
    public Navigation run() throws Exception {
        response.setContentType("text/plain");
        HttpServletRequest request = RequestLocator.get();
        String keyAsString = request.getParameter("keyAsString");
        Key key = KeyFactory.stringToKey(keyAsString);
        Datastore.delete(key);
        response.getWriter().println("success");
        return null;
    }
}

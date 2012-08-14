package com.usp.tabcloud.server.controller;

import javax.servlet.http.HttpServletRequest;

import org.slim3.util.RequestLocator;

public class ControllerModule {

    public static  String getRequesterEmail() {
        HttpServletRequest request = RequestLocator.get();
        if (request.getUserPrincipal() != null) {
            String ownerEmail = request.getUserPrincipal().getName();
            if (ownerEmail.contains("@")) {
                return ownerEmail;
            }
        }
        return null;
    }
}

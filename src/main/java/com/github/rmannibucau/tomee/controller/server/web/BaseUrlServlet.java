package com.github.rmannibucau.tomee.controller.server.web;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// can be cached
@WebServlet(urlPatterns = "/app/js/environment.js")
public class BaseUrlServlet extends HttpServlet {
    private String js;

    @Override
    public void init(final ServletConfig config) throws ServletException {
        super.init(config);

        String contextPath = config.getServletContext().getContextPath();
        if ("/".equals(contextPath)) {
            contextPath = "";
        }

        js = "angular.module('webAppBridge',[]).constant('webappRoot', '" + contextPath + "');";
    }

    @Override
    protected void service(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/javascript");
        resp.getWriter().write(js);
    }
}

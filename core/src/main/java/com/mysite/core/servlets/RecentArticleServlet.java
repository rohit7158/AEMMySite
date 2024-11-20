package com.mysite.core.servlets;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.apache.sling.servlets.annotations.SlingServletResourceTypes;
import org.osgi.service.component.annotations.Component;

@Component(service = Servlet.class)
@SlingServletPaths(
    value = {"/bin/recent-article", "/bin/mysite/recent-article"}
)
@SlingServletResourceTypes(
    resourceTypes = "mysite/servlet/recent-article",
    extensions = {"txt","json"},
    methods = {"GET", "POST"},
    selectors = {"recent","popular"}
)
public class RecentArticleServlet extends SlingAllMethodsServlet {

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
            throws ServletException, IOException {
                response.getWriter().write("Inside doGet Method of Servlet");
    }

    @Override
    protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response)
            throws ServletException, IOException {
        String param1 = request.getParameter("param1");
        String param2 = request.getParameter("param2");
    
        String responseMessage = "Received param1: " + param1 + ", param2: " + param2;
    
        response.setContentType("text/plain");
        response.getWriter().write(responseMessage);
    }
    
    @Override
    protected void doPut(SlingHttpServletRequest request, SlingHttpServletResponse response)
            throws ServletException, IOException {
                response.getWriter().write("Inside doPut Method of Servlet");
    }

    @Override
    protected void doDelete(SlingHttpServletRequest request, SlingHttpServletResponse response)
            throws ServletException, IOException {
                response.getWriter().write("Inside doDelete Method of Servlet");
    }
}
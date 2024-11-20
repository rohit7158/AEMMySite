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
    value = {"/bin/mysite/recent-article", "/bin/mysite/recent-articles"}
)
@SlingServletResourceTypes(
    resourceTypes = "mysite/servlet/recent-articles",
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
                response.getWriter().write("Inside doPost Method of Servlet");
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

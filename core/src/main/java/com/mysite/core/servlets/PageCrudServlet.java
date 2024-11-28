package com.mysite.core.servlets;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.osgi.service.component.annotations.Component;

import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import com.day.cq.wcm.api.WCMException;


@Component(service = Servlet.class)
@SlingServletPaths("/bin/pageCrud/modify")
public class PageCrudServlet extends SlingAllMethodsServlet{
    
    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
            throws ServletException, IOException {

    }
    @Override
    protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response)
            throws ServletException, IOException {
            ResourceResolver resolver = request.getResourceResolver(); 
            PageManager pageManager = resolver.adaptTo(PageManager.class);

            String pageName = request.getParameter("pageName");
            String pageTitle = request.getParameter("pageTitle");
            String templateName = request.getParameter("templateName");
            String ParentNodePage = request.getParameter("ParentNodePage");

            try {
                pageManager.create(ParentNodePage, pageName, templateName, pageTitle);
            } catch (WCMException e) {
                throw new RuntimeException(e);
            }
    }

    @Override
    protected void doDelete(SlingHttpServletRequest request, SlingHttpServletResponse response)
            throws ServletException, IOException {
        
            ResourceResolver resolver = request.getResourceResolver(); 
            PageManager pageManager = resolver.adaptTo(PageManager.class);

            String pageName = request.getParameter("pageName");

            Page productPage = pageManager.getPage("/content/mysite/us/en/"+pageName);
            
            if(productPage != null) {
                try {
                    pageManager.delete(productPage,false);
                } catch (WCMException e) {
                    throw new RuntimeException(e);
                }
            }
    }
}

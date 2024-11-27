package com.mysite.core.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.ModifiableValueMap;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.osgi.service.component.annotations.Component;

@Component(service = Servlet.class)
@SlingServletPaths(value = "/bin/recent-articles")
public class RecentArticlesServlet  extends SlingAllMethodsServlet{

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException{
        response.getWriter().write("Inside doGet Method of servlet");
    }

    @Override
    protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException{
        String userId = request.getParameter("userId");
        String fName = request.getParameter("fName");
        String lName = request.getParameter("lname");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String password = request.getParameter("password");

        ResourceResolver resourceResolver  = request.getResourceResolver();
        Resource usersResource = resourceResolver.getResource("/content/users");
        Resource userResource = resourceResolver.getResource("/content/users/"+userId);

        if(usersResource != null && userResource == null) {
            Map<String, Object> props = new HashMap<>();
            props.put("firstName", fName);
            props.put("lastName", lName);
            props.put("email", email);
            props.put("phone", phone);
            props.put("password", password);
            resourceResolver.create(usersResource, userId, props);
            resourceResolver.commit();
            response.getWriter().write("User Created");
        } else {
            response.getWriter().write("User Creation not done");
        }
    }

    @Override
    protected void doPut(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
            String userId = request.getParameter("userId");
            String fName = request.getParameter("fName");
            String lName = request.getParameter("lname");
            String email = request.getParameter("email");
            String phone = request.getParameter("phone");
            String password = request.getParameter("password");
    
            ResourceResolver resourceResolver  = request.getResourceResolver();
            Resource userResource = resourceResolver.getResource("/content/users/"+userId);    
            
            if(userResource !=null) {
                ModifiableValueMap mVM = userResource.adaptTo(ModifiableValueMap.class);
                if(fName != null) 
                    mVM.put("firstName", fName);
                if(lName != null)
                    mVM.put("lastName", lName);
                if(email != null)    
                    mVM.put("email", email);
                if(phone != null)
                    mVM.put("phone", phone);
                if(password != null)    
                    mVM.put("password", password);

                resourceResolver.commit(); 
                response.getWriter().write("User data updated");   
            } else {
                response.getWriter().write("User Updation not done");
            }
           
    }

    @Override
    protected void doDelete(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
            ResourceResolver resourceResolver  = request.getResourceResolver();
            String userId = request.getParameter("userId");
            Resource userResource = resourceResolver.getResource("/content/users/"+userId);
            if(userResource != null) {
                resourceResolver.delete(userResource);
                resourceResolver.commit();
            }
            response.getWriter().write("Inside doDelete Method of servlet");
    }

}

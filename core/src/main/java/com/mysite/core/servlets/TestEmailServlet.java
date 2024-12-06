package com.mysite.core.servlets;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.osgi.service.component.annotations.Component;

import javax.servlet.Servlet;
import java.io.IOException;

@Component(service = Servlet.class,
        property = {
                "sling.servlet.paths=/bin/testEmail",
                "sling.servlet.methods=GET"
        })
public class TestEmailServlet extends SlingAllMethodsServlet {
    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException {
        try {
            HtmlEmail email = new HtmlEmail();
            email.setHostName("smtp.gmail.com");
            email.setSmtpPort(587);
            email.setAuthentication("rohit913.siom@gmail.com", "Tcs@2025");
            email.setStartTLSRequired(true);
            email.setFrom("rohit913.siom@gmail.com");
            email.addTo("rohit7158@hotmail.com");
            email.setSubject("Test Email from AEM");
            email.setHtmlMsg("<html><body><h1>Hello from AEM</h1></body></html>");
            email.send();
            response.getWriter().write("Email sent successfully!");
        } catch (EmailException e) {
            response.getWriter().write("Failed to send email: " + e.getMessage());
        }
    }
}
package com.mysite.core.services;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class ComponentEventServices {

    public static final Logger LOG = LoggerFactory.getLogger(ComponentEventServices.class);

    @Reference
    UserDataApiService userDataApiService;

    @Activate
    public void activate() {
        LOG.info("Inside ComponentEventServices Activate Method");
        userDataApiService.getApiData();
    }
}
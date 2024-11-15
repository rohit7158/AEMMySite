package com.mysite.core.services;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.metatype.annotations.Designate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(service=UserDataApiService.class)
@Designate(ocd=UserDataApiConfiguration.class)
public class UserDataApiService {
    
    public static final Logger LOG = LoggerFactory.getLogger(UserDataApiService.class);

    private String apiEndPoint;
    private boolean enable;
    private String clientId;

    @Activate
    public void activate(UserDataApiConfiguration config) {
        apiEndPoint = config.userDataApiEndPoint();
        enable = config.enableApi();
        clientId = config.clientID();
        LOG.info("User Data Endpoint Api : {} , Enable Value : {}, Client Id : {}", apiEndPoint, enable, clientId);
    }

    @Deactivate
    public void deactivate() {
        LOG.info("Inside deactivate Method");
    }

    @Modified
    public void update() {
        LOG.info("Inside update Method");
    }

    public String getApiData() {
        LOG.info("Calling An API");
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet getRequest = new HttpGet(apiEndPoint);
        String res = "";
        try {
            CloseableHttpResponse response = client.execute(getRequest);
            if(response.getStatusLine().getStatusCode() == 200) {
                res = EntityUtils.toString(response.getEntity());
                LOG.info("Api Response {}", res);
            } else {
                LOG.error("Fail to get the user details the error code is {}", response.getStatusLine().getStatusCode());
            }
            return res;
        } catch(Exception e) {
            LOG.error("Exception thrown {}",e);
            throw new RuntimeException(e);
        }
    }
}

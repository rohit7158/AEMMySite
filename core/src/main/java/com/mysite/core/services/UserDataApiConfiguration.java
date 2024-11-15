package com.mysite.core.services;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition
public @interface UserDataApiConfiguration {
    
    @AttributeDefinition(name = "User Data Api Endpoint")
    public String userDataApiEndPoint() default "https://gorest.co.in/public/v2/users";

    @AttributeDefinition(name = "Enable/Disable Rest API")
    public boolean enableApi() default true;

    @AttributeDefinition(name = "Client ID")
    public String clientID() default "12345";
}

package com.hanxinbank.p2p;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import javax.ws.rs.ApplicationPath;

@Component
@ApplicationPath("/resource")
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        packages("com.hanxinbank.p2p");
    }

}

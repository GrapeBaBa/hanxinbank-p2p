package com.hanxinbank.p2p.web.user.resource;

import com.hanxinbank.p2p.core.user.service.UserService;
import com.hanxinbank.p2p.core.user.json.UserSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Component
@Path("/hello")
public class UserResource {

    @Autowired
    private UserService userService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public UserSummary message() {
        return null;
    }
}

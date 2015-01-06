package com.hanxinbank.p2p.web.register.resource;

import com.hanxinbank.p2p.core.common.auth.AuthHandler;
import com.hanxinbank.p2p.core.common.exception.Error;
import com.hanxinbank.p2p.core.common.exception.ValidateException;
import com.hanxinbank.p2p.core.user.domain.User;
import com.hanxinbank.p2p.core.user.json.RegisterRequest;
import com.hanxinbank.p2p.core.user.service.UserService;
import com.hanxinbank.p2p.core.user.validator.RegisterValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.Response.Status.CREATED;
import static javax.ws.rs.core.Response.status;

@Path("/register")
@Component
public class RegisterResource {
    private static final Logger logger = LoggerFactory.getLogger(RegisterResource.class);

    @Autowired
    private RegisterValidator registerValidator;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthHandler authHandler;

    @POST
    @Produces(value = MediaType.APPLICATION_JSON)
    @Consumes(value = MediaType.APPLICATION_JSON)
    public Response register(@Context HttpServletRequest request,
                             @Context HttpServletResponse response, RegisterRequest registerRequest) {
        logger.info("User register request {}", registerRequest);

        registerValidator.setRequest(request).validate(registerRequest);

        User user;
        try {
            user = userService.register(registerRequest);
        } catch (DataIntegrityViolationException e) {
            logger.info("Username {} concurrent duplicated", registerRequest.getUsername());
            throw new ValidateException(Error.DUPLICATED_USERNAME);
        }

        response.addCookie(authHandler.toCookie(user, request.getContextPath()));

        return status(CREATED).build();
    }


}

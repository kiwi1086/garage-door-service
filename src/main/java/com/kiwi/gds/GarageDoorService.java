package com.kiwi.gds;

import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * @author Willi Kisser
 */
@Slf4j
@Path("/garagedoor")
public class GarageDoorService {

    @Inject
    StatusService statusService;

    @Inject
    GarageDoorHandler garageDoorHandler;

    //@PATCH TODO what is correct?
    @GET
    @Path("/open")
    @Produces(MediaType.APPLICATION_JSON)
    public StatusService open() {
        if(!garageDoorHandler.isInProgress()) {
            log.info("GarageDoorHandler triggerd to open the garage door");
            garageDoorHandler.open();
        } else {
            log.info("GarageDoorHandler is already active");
        }
        return statusService;
    }

    @GET
    @Path("/status")
    @Produces(MediaType.APPLICATION_JSON)
    public StatusService getStatus() {
        log.info("GarageDoor Status '{}', GarageDoorHandler Active '{}'", statusService.getStatus().getValue(), garageDoorHandler.isInProgress());
        return statusService;
    }
}
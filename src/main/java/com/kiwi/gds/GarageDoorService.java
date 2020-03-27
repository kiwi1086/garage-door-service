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
    LedHandler ledHandler;

    @Inject
    DoorStatus doorStatus;

    @Inject
    GarageDoor garageDoor;

    //@PATCH TODO what is correct?
    @GET
    @Path("/open")
    @Produces(MediaType.APPLICATION_JSON)
    public DoorStatus open() {
        if (!garageDoor.isInProgress()) {
            log.info("GarageDoor is opening the garage door");
            garageDoor.open();
        } else {
            log.info("GarageDoor already opening, open or closing the garage door");
        }
        return doorStatus;
    }

    @GET
    @Path("/status")
    @Produces(MediaType.APPLICATION_JSON)
    public DoorStatus getStatus() {
        log.info("GarageDoor Status '{}', GarageDoorHandler Active '{}'", doorStatus.getStatus().getValue(), garageDoor.isInProgress());
        return doorStatus;
    }

    @GET
    @Path("/ledactive")
    public void setLedActive() {
        ledHandler.setActive(true);
    }

    @GET
    @Path("/ledinactive")
    public void setLedInactive() {
        ledHandler.setActive(false);
    }
}
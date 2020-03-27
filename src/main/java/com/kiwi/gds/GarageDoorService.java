package com.kiwi.gds;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.metrics.Metadata;
import org.eclipse.microprofile.metrics.MetricType;
import org.eclipse.microprofile.metrics.annotation.Counted;

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
    @Counted(name = "performeOpen", description = "How many open (the garage door) calls have been performed.")
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
    @Counted(name = "performStatus", description = "How garage door status requests have been performed.")
    public DoorStatus getStatus() {
        log.info("GarageDoor Status '{}', GarageDoorHandler Active '{}'", doorStatus.getStatus().getValue(), garageDoor.isInProgress());
        return doorStatus;
    }

    @GET
    @Path("/ledactive")
    @Produces(MediaType.TEXT_HTML)
    public String setLedActive() {
        ledHandler.setActive(true);
        return "active";
    }

    @GET
    @Path("/ledinactive")
    @Produces(MediaType.TEXT_HTML)
    public String setLedInactive() {
        ledHandler.setActive(false);
        return "inactive";
    }
}
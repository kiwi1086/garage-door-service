package com.kiwi.gds;

import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author Willi Kisser
 */

@Slf4j
@Singleton
public class GarageDoor {

    @Inject
    DoorStatus doorStatus;

    private ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();

    private Future<Boolean> garageDoorSequence;

    public void open() {
        // TODO opening garage door with 443 Mhz signal
        doorStatus.setStatus(DoorStatus.StatusType.OPENING);
        garageDoorSequence = executeGarageDoorSequence();
    }

    private Future<Boolean> executeGarageDoorSequence() {
        return executor.schedule(() -> {
            try {
                doorStatus.setStatus(DoorStatus.StatusType.OPEN);
                TimeUnit.SECONDS.sleep(5);
                doorStatus.setStatus(DoorStatus.StatusType.CLOSING);
                TimeUnit.SECONDS.sleep(3);
                doorStatus.setStatus(DoorStatus.StatusType.CLOSED);
            } catch (InterruptedException e) {
                log.warn(e.getMessage());
                return Boolean.FALSE;
            }
            return Boolean.TRUE;
        }, 3, TimeUnit.SECONDS);
    }

    public boolean isInProgress() {
        if (garageDoorSequence == null)
            return false;
        return !garageDoorSequence.isDone();
    }
}

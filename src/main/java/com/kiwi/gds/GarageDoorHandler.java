package com.kiwi.gds;

import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Slf4j
@Singleton
public class GarageDoorHandler {

    @Inject
    StatusService statusService;

    private ExecutorService executor = Executors.newSingleThreadExecutor();

    private Future<Boolean> doorFuture;

    public void open() {
        doorFuture = openFuture();
    }

    private Future<Boolean> openFuture() {
        return executor.submit(() -> {
            try {
                statusService.setStatus(Status.OPENING);
                Thread.sleep(3000); // TODO assumption
                statusService.setStatus(Status.OPEN);
                Thread.sleep(5000); // TODO assumption
                statusService.setStatus(Status.CLOSING);
                Thread.sleep(3000); // TODO assumption
                statusService.setStatus(Status.CLOSED);
            } catch (InterruptedException e) {
                log.warn(e.getMessage());
                return Boolean.FALSE;
            }
            return Boolean.TRUE;
        });
    }

    public boolean isInProgress() {
        if (doorFuture == null)
            return false;
        return !doorFuture.isDone();
    }
}

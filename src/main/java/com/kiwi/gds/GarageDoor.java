package com.kiwi.gds;

import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @author Willi Kisser
 */

@Slf4j
@Singleton
public class GarageDoor {

    @Inject
    DoorStatus doorStatus;

    private ExecutorService executor = Executors.newSingleThreadExecutor();

    private Future<Boolean> doorOpeningThread;

    public void open() {
        doorOpeningThread = openSubmit();
    }

    private Future<Boolean> openSubmit() {
        return executor.submit(() -> {
            try {
                doorStatus.setStatus(StatusType.OPENING);
                TimeUnit.SECONDS.sleep(3);
                doorStatus.setStatus(StatusType.OPEN);
                TimeUnit.SECONDS.sleep(5);
                doorStatus.setStatus(StatusType.CLOSING);
                TimeUnit.SECONDS.sleep(3);
                doorStatus.setStatus(StatusType.CLOSED);
            } catch (InterruptedException e) {
                log.warn(e.getMessage());
                return Boolean.FALSE;
            }
            return Boolean.TRUE;
        });
    }

    public boolean isInProgress() {
        if (doorOpeningThread == null)
            return false;
        return !doorOpeningThread.isDone();
    }
}

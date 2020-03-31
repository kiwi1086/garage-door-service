package com.kiwi.gds;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * @author Willi Kisser
 */

@Singleton
public class DoorStatus {

    @Inject
    LedControllerI ledController;

    @AllArgsConstructor
    public enum StatusType {
        CLOSED("closed"),
        OPENING("opening"),
        OPEN("open"),
        CLOSING("closing");

        @Getter
        private String value;
    }

    @Getter
    StatusType status = StatusType.CLOSED;

    public void setStatus(StatusType statusType) {
        this.status = statusType;
        if (status.equals(StatusType.CLOSED)) {
            ledController.setRedLedState(true);
            ledController.setOrangeLedState(false);
            ledController.setGreenLedState(false);
        } else if (status.equals(StatusType.OPEN)) {
            ledController.setRedLedState(false);
            ledController.setOrangeLedState(false);
            ledController.setGreenLedState(true);
        } else {
            ledController.setRedLedState(false);
            ledController.setOrangeLedState(true);
            ledController.setGreenLedState(false);
        }
    }

}

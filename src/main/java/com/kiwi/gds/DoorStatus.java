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
    LedController ledController;

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
        if (status.equals(StatusType.CLOSING) || status.equals(StatusType.OPENING)) {
            ledController.setRedLedState(false);
            ledController.setOrangeLed2ndState(true);
            ledController.setOrangeLedState(false);
        } else if (status.equals(StatusType.OPEN)) {
            ledController.setRedLedState(false);
            ledController.setOrangeLed2ndState(false);
            ledController.setOrangeLedState(true);
        } else {
            ledController.setRedLedState(true);
            ledController.setOrangeLed2ndState(false);
            ledController.setOrangeLedState(false);
        }
    }

}

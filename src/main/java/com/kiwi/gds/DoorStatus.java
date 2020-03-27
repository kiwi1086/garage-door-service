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
    LedHandler ledHandler;

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
            ledHandler.setRedLedState(false);
            ledHandler.setOrangeLed2ndState(true);
            ledHandler.setOrangeLedState(false);
        } else if (status.equals(StatusType.OPEN)) {
            ledHandler.setRedLedState(false);
            ledHandler.setOrangeLed2ndState(false);
            ledHandler.setOrangeLedState(true);
        } else {
            ledHandler.setRedLedState(true);
            ledHandler.setOrangeLed2ndState(false);
            ledHandler.setOrangeLedState(false);
        }
    }

}

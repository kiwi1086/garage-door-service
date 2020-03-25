package com.kiwi.gds;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.inject.Singleton;

/**
 * @author Willi Kisser
 */

@Singleton
public class DoorStatus {

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
    @Setter
    StatusType status = StatusType.CLOSED;

}

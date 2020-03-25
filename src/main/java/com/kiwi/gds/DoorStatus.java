package com.kiwi.gds;

import lombok.Getter;
import lombok.Setter;

import javax.inject.Singleton;

/**
 * @author Willi Kisser
 */

@Singleton
public class DoorStatus {
    @Getter
    @Setter
    StatusType status = StatusType.CLOSED;
}

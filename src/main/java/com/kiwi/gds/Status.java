package com.kiwi.gds;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Willi Kisser
 */

@AllArgsConstructor
public enum Status {
    CLOSED("closed"),
    OPENING("opening"),
    OPEN("open"),
    CLOSING("closing");

    @Getter
    private String value;
}
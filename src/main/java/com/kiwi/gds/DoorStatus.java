package com.kiwi.gds;

import com.pi4j.io.gpio.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.annotation.PostConstruct;
import javax.inject.Singleton;

/**
 * @author Willi Kisser
 */

@Singleton
public class DoorStatus {

    private GpioController gpio = GpioFactory.getInstance();

    private final GpioPinDigitalOutput orangeLed = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_04, "RedLed", PinState.LOW); //23
    private final GpioPinDigitalOutput redLed = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_05, "OrangeLed", PinState.HIGH); //24

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
            redLed.setState(false);
            orangeLed.setState(true);
        } else if (status.equals(StatusType.OPEN)) {
            redLed.setState(false);
            orangeLed.setState(false);
        } else {
            redLed.setState(true);
            orangeLed.setState(false);
        }

    }

}

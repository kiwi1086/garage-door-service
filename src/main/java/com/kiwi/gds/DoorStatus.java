package com.kiwi.gds;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.RaspiPin;
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

    private GpioPinDigitalOutput orangeLed[] = new GpioPinDigitalOutput[3]; // 23
    private GpioPinDigitalOutput redLed[] = new GpioPinDigitalOutput[3]; // 24

    @PostConstruct
    void init() {
        orangeLed[0] = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_16);
        redLed[0] = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_18);

        // initial state
        redLed[0].setState(true);
    }

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
            redLed[0].setState(false);
            orangeLed[0].setState(true);
        } else if (status.equals(StatusType.OPEN)) {
            redLed[0].setState(false);
            orangeLed[0].setState(false);
        } else {
            redLed[0].setState(true);
            orangeLed[0].setState(false);
        }

    }

}

package com.kiwi.gds;

import com.pi4j.io.gpio.*;
import lombok.Getter;

import javax.annotation.PostConstruct;
import javax.inject.Singleton;

/**
 * @author Willi Kisser
 */

@Singleton
public class LedController {

    private GpioController gpio = GpioFactory.getInstance();

    private final GpioPinDigitalOutput greenLed = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_04, "RedLed", PinState.LOW); //23
    private final GpioPinDigitalOutput orangeLed = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_06, "RedLed", PinState.LOW); //25
    private final GpioPinDigitalOutput redLed = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_05, "OrangeLed", PinState.LOW); //24

    // Internal State to remember which led is on/off even if it's inactive
    private boolean greenLedIState = false;
    private boolean orangeLedIState = false;
    private boolean redLedIState = false;

    @PostConstruct
    void init() {
        setActive(false);
    }

    @Getter
    private boolean active;

    public void setActive(boolean active) {
        this.active = active;
        if (!active) {
            greenLed.setState(false);
            orangeLed.setState(false);
            redLed.setState(false);
        } else {
            greenLed.setState(greenLedIState);
            orangeLed.setState(orangeLedIState);
            redLed.setState(redLedIState);
        }
    }

    public void setOrangeLedState(boolean state) {
        greenLedIState = state;
        if (active)
            greenLed.setState(state);
    }

    public void setOrangeLed2ndState(boolean state) {
        orangeLedIState = state;
        if (active)
            orangeLed.setState(state);
    }

    public void setRedLedState(boolean state) {
        redLedIState = state;
        if (active)
            redLed.setState(state);
    }

}

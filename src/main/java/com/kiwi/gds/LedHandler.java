package com.kiwi.gds;

import com.pi4j.io.gpio.*;
import io.quarkus.smallrye.metrics.runtime.LambdaGauge;
import lombok.Getter;
import org.eclipse.microprofile.metrics.Metadata;
import org.eclipse.microprofile.metrics.MetricRegistry;
import org.eclipse.microprofile.metrics.MetricType;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * @author Willi Kisser
 */

@Singleton
public class LedHandler {

    @Inject
    MetricRegistry metricRegistry;

    private final static Metadata LED_ACTIVE_METATDATA = Metadata.builder().withName("led_active").withDescription("State if the LEDs are active or not").withType(MetricType.GAUGE).build();

    private GpioController gpio = GpioFactory.getInstance();

    private final GpioPinDigitalOutput orangeLed = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_04, "RedLed", PinState.LOW); //23
    private final GpioPinDigitalOutput orangeLed2nd = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_06, "RedLed", PinState.LOW); //25
    private final GpioPinDigitalOutput redLed = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_05, "OrangeLed", PinState.LOW); //24

    private boolean orangeLedI = false;
    private boolean orangeLed2ndI = false;
    private boolean redLedI = false;

    @PostConstruct
    void init() {
        setActive(false);
        metricRegistry.register(LED_ACTIVE_METATDATA, new LambdaGauge(() -> 0));
    }

    @Getter
    private boolean active;

    public void setActive(boolean active) {
        this.active = active;
        if (!active) {
            orangeLed.setState(false);
            orangeLed2nd.setState(false);
            redLed.setState(false);
        } else {
            metricRegistry.register(LED_ACTIVE_METATDATA, new LambdaGauge(() -> 1));
            orangeLed.setState(orangeLedI);
            orangeLed2nd.setState(orangeLed2ndI);
            redLed.setState(redLedI);
        }
    }

    public void setOrangeLedState(boolean state) {
        orangeLedI = state;
        if (active) {
            orangeLed.setState(state);
        }
    }

    public void setOrangeLed2ndState(boolean state) {
        orangeLed2ndI = state;
        if (active) {
            orangeLed2nd.setState(state);
        }
    }

    public void setRedLedState(boolean state) {
        redLedI = state;
        if (active) {
            redLed.setState(state);
        }
    }

}

package com.kiwi.gds;

import javax.annotation.Priority;
import javax.enterprise.inject.Alternative;
import javax.inject.Singleton;

@Alternative
@Priority(1)
@Singleton
public class LedControllerMock implements LedControllerI {
    @Override
    public void setActive(boolean active) {

    }

    @Override
    public void setGreenLedState(boolean state) {

    }

    @Override
    public void setOrangeLedState(boolean state) {

    }

    @Override
    public void setRedLedState(boolean state) {

    }
}

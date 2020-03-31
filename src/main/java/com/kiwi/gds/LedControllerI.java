package com.kiwi.gds;

public interface LedControllerI {
    void setActive(boolean active);
    void setGreenLedState(boolean state);
    void setOrangeLedState(boolean state);
    void setRedLedState(boolean state);
}

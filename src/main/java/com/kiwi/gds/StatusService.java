package com.kiwi.gds;

import lombok.Getter;
import lombok.Setter;

import javax.inject.Singleton;

@Singleton
public class StatusService {
    @Getter
    @Setter
    Status status = Status.CLOSED;
}

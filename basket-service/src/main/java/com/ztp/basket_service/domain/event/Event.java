package com.ztp.basket_service.domain.event;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.CLASS,
        include = JsonTypeInfo.As.PROPERTY,
        property = "@class"
)
@JsonSubTypes(
        @JsonSubTypes.Type(value = BasketCreatedEvent.class)
)
public interface Event {

    String getType();
    int getVersion();
}

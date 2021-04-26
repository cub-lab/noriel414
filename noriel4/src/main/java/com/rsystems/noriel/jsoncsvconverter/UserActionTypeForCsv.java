package com.rsystems.noriel.jsoncsvconverter;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.Date;

@JsonPropertyOrder({
        "actionType",
        "emailHash",
        "productsId",
        "timestamp"
})
public abstract class UserActionTypeForCsv {
    @JsonProperty("timestamp")
    private Date timestamp;

    @JsonProperty("productsId")
    private String productsId;

    @JsonProperty("emailHash")
    private int emailHash;

    @JsonProperty("actionType")
    private String actionType;
}

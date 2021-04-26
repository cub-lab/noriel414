package com.rsystems.noriel.jsoncsvconverter;

import java.util.Date;

public class UserActionType {
    private String actionType;
    private String emailHash;
    private String productsId;
    private Date timestamp;

/*    public UserActionType(String[] params) {
        this.actionType = params[0];
        this.timestamp = Simpleda

    }*/

    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    public String getEmailHash() {
        return emailHash;
    }

    public void setEmailHash(String emailHash) {
        this.emailHash = emailHash;
    }

    public String getProductsId() {
        return productsId;
    }

    public void setProductsId(String productsId) {
        this.productsId = productsId;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }


    @Override
    public String toString() {
        return "UserActionType{" +
                "actionType='" + actionType + '\'' +
                ", emailHash='" + emailHash + '\'' +
                ", productsId='" + productsId + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}

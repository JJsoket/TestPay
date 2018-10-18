package com.testpay.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PayResponse {
    @JsonProperty("id")
    private String id;

    @JsonProperty("create_time")
    private String createTime;

    @JsonProperty("state")
    private String state;

    public PayResponse() {
    }

    public PayResponse(String id, String createTime, String state) {
        this.id = id;
        this.createTime = createTime;
        this.state = state;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}

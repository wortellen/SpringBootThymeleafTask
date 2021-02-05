package com.netcracker.model;

import java.util.Date;

public class RequestParameters {
    private Date lastAccessedTime;
    private String userAgent;

    public RequestParameters(Date lastAccessedTime, String userAgent) {
        this.lastAccessedTime = lastAccessedTime;
        this.userAgent = userAgent;
    }

    public Date getLastAccessedTime() {
        return lastAccessedTime;
    }

    public void setLastAccessedTime(Date lastAccessedTime) {
        this.lastAccessedTime = lastAccessedTime;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }
}

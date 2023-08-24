package com.codelabs_coding.petrescue.utils;

import java.util.HashMap;

public class MsgEvent {

    private String businessKey;
    private String oneValue;
    private int oneIntValue;
    private long oneLongValue;
    private boolean oneBooleanValue;

    private HashMap<String, Object> businessMap;

    public MsgEvent() {
    }

    public MsgEvent(String businessKey) {
        this.businessKey = businessKey;
    }

    public MsgEvent(String businessKey, String oneValue, boolean oneBooleanValue) {
        this.businessKey = businessKey;
        this.oneValue = oneValue;
        this.oneBooleanValue = oneBooleanValue;
    }

    public MsgEvent(String businessKey, long oneLongValue, boolean oneBooleanValue) {
        this.businessKey = businessKey;
        this.oneLongValue = oneLongValue;
        this.oneBooleanValue = oneBooleanValue;
    }

    public MsgEvent(String businessKey, String oneValue, HashMap<String, Object> businessMap) {
        this.oneValue = oneValue;
        this.businessKey = businessKey;
        this.businessMap = businessMap;
    }

    public MsgEvent(String businessKey, HashMap<String, Object> businessMap) {
        this.businessKey = businessKey;
        this.businessMap = businessMap;
    }

    public MsgEvent(String businessKey, String oneValue) {
        this.businessKey = businessKey;
        this.oneValue = oneValue;
    }

    public MsgEvent(String businessKey, String oneValue, int oneIntValue) {
        this.businessKey = businessKey;
        this.oneValue = oneValue;
        this.oneIntValue = oneIntValue;
    }

    public MsgEvent(String businessKey, int oneIntValue) {
        this.businessKey = businessKey;
        this.oneIntValue = oneIntValue;
    }

    public MsgEvent(String businessKey, long oneLongValue, String oneValue) {
        this.businessKey = businessKey;
        this.oneLongValue = oneLongValue;
        this.oneValue = oneValue;
    }

    public MsgEvent(String businessKey, long oneLongValue, HashMap<String, Object> businessMap) {
        this.businessKey = businessKey;
        this.oneLongValue = oneLongValue;
        this.businessMap = businessMap;
    }

    public MsgEvent(String businessKey, long oneLongValue) {
        this.businessKey = businessKey;
        this.oneLongValue = oneLongValue;
    }

    public long getOneLongValue() {
        return oneLongValue;
    }

    public void setOneLongValue(long oneLongValue) {
        this.oneLongValue = oneLongValue;
    }

    public String getBusinessKey() {
        return businessKey;
    }

    public void setBusinessKey(String businessKey) {
        this.businessKey = businessKey;
    }

    public String getOneValue() {
        return oneValue;
    }

    public void setOneValue(String oneValue) {
        this.oneValue = oneValue;
    }

    public HashMap<String, Object> getBusinessMap() {
        return businessMap;
    }

    public int getOneIntValue() {
        return oneIntValue;
    }

    public void setOneIntValue(int oneIntValue) {
        this.oneIntValue = oneIntValue;
    }

    public boolean getOneBooleanValue() {
        return oneBooleanValue;
    }

    public void setOneBooleanValue(boolean oneBooleanValue) {
        this.oneBooleanValue = oneBooleanValue;
    }

    public static final String UPDATE_PET_LIST = "UPDATE_PET_LIST";

    public static final String UPDATE_USER_INFO = "UPDATE_USER_INFO";
}

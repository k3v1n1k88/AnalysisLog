/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package object.log;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import exception.ParseLogExeption;
import org.apache.log4j.Logger;

/**
 *
 * @author root
 */
public class LogAukReq {
    
    private static final Logger logger = Logger.getLogger(LogAukReq.class);

    private String timestamp;
    private String action;
    private ResultTokenMerging resultTokenMerging;
    private String tokenPassing;
    private String appID;
    private String userID;
    private String channel;
    private String socialID;
    private ExInfo exInfo;
    private String returnToken;

    public static LogAukReq parseToLogAukReq(String log) throws ParseLogExeption {
        String[] elements = log.split("\\s{2,}");

        if (elements.length != 10) {
            logger.warn("LogAukReq - get invalid format log: "+ log);
            throw new ParseLogExeption("Invalid format log");
        }
        try {
            return new LogAukReq(elements[0],
                    elements[1],
                    new Gson().fromJson(elements[2], ResultTokenMerging.class),
                    elements[3],
                    elements[4],
                    elements[5],
                    elements[6],
                    elements[7],
                    new Gson().fromJson(elements[8], ExInfo.class),
                    elements[9]);
        } catch (JsonSyntaxException ex) {
            throw new ParseLogExeption("Invalid format appuserinfo", ex);
        }
    }

    public LogAukReq(String timestamp, String action, ResultTokenMerging resultTokenMerging, String tokenCreate, String appID, String userID, String channel, String socialID, ExInfo exInfo, String returnToken) {
        this.timestamp = timestamp;
        this.action = action;
        this.resultTokenMerging = resultTokenMerging;
        this.tokenPassing = tokenCreate;
        this.appID = appID;
        this.userID = userID;
        this.channel = channel;
        this.socialID = socialID;
        this.exInfo = exInfo;
        this.returnToken = returnToken;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public ResultTokenMerging getResultTokenMerging() {
        return resultTokenMerging;
    }

    public void setResultTokenMerging(ResultTokenMerging resultTokenMerging) {
        this.resultTokenMerging = resultTokenMerging;
    }

    public String getTokenPassing() {
        return tokenPassing;
    }

    public void setTokenPassing(String tokenCreate) {
        this.tokenPassing = tokenCreate;
    }

    public String getAppID() {
        return appID;
    }

    public void setAppID(String appID) {
        this.appID = appID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getSocialID() {
        return socialID;
    }

    public void setSocialID(String socialID) {
        this.socialID = socialID;
    }

    public ExInfo getExInfo() {
        return exInfo;
    }

    public void setExInfo(ExInfo exInfo) {
        this.exInfo = exInfo;
    }

    public String getReturnToken() {
        return returnToken;
    }

    public void setReturnToken(String returnToken) {
        this.returnToken = returnToken;
    }

    @Override
    public String toString() {
        return "LogAukReq{" + "timestamp=" + timestamp + ", action=" + action + ", resultTokenMerging=" + resultTokenMerging + ", tokenCreate=" + tokenPassing + ", appID=" + appID + ", userID=" + userID + ", channel=" + channel + ", socialID=" + socialID + ", exInfo=" + exInfo + ", returnToken=" + returnToken + '}';
    }
    
}

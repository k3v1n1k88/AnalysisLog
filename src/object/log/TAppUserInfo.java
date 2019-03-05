/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package object.log;

/**
 *
 * @author root
 */
public class TAppUserInfo {
    String appID;
    String userID;
    String channel;
    String socialID;

    public TAppUserInfo(String appID, String userID, String channel, String socialID) {
        this.appID = appID;
        this.userID = userID;
        this.channel = channel;
        this.socialID = socialID;
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

    @Override
    public String toString() {
        return "TAppUserInfo{" + "appID=" + appID + ", userID=" + userID + ", channel=" + channel + ", socialID=" + socialID + '}';
    }
    
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package object.log;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import exception.ParseLogExeption;

/**
 *
 * @author root
 */
public class LogAukDB {

    String setTs;
    String action;
    String token;
    Profile profile;
    String status;

    public LogAukDB(String setTs, String action, String token, Profile profile, String status) {
        this.setTs = setTs;
        this.action = action;
        this.token = token;
        this.profile = profile;
        this.status = status;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getSetTs() {
        return setTs;
    }

    public void setSetTs(String setTs) {
        this.setTs = setTs;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "LogAukDB{" + "action=" + action + ", profile=" + profile + ", status=" + status + '}';
    }

    public static LogAukDB parseLogToLogAukDB(String log) throws ParseLogExeption {

        String[] elements = log.split("[\t\\s]+");

        if (elements.length < 5) {
            throw new ParseLogExeption("Invalid format log");
        }
        try {
            
            return new LogAukDB(elements[0], elements[1], elements[2], new Gson().fromJson(elements[3], Profile.class), elements[4]);
        } catch (JsonSyntaxException ex) {
            throw new ParseLogExeption("Invalid format appuserinfo", ex);
        }

    }

}

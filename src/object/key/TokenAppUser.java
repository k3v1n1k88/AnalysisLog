/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package object.key;

import java.util.Objects;

/**
 *
 * @author root
 */
public class TokenAppUser {
    
    String token;
    String appID;
    String userID;

    public TokenAppUser(String token, String appID, String userID) {
        this.token = token;
        this.appID = appID;
        this.userID = userID;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
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

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + Objects.hashCode(this.token);
        hash = 23 * hash + Objects.hashCode(this.appID);
        hash = 23 * hash + Objects.hashCode(this.userID);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TokenAppUser other = (TokenAppUser) obj;
        return true;
    }
    
    
    
}

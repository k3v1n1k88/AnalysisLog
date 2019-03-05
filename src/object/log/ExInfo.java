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
public class ExInfo {
    
    private String userIP;
    private String userAgent;

    public ExInfo(String userIP, String userAgent) {
        this.userIP = userIP;
        this.userAgent = userAgent;
    }

    public String getUserIP() {
        return userIP;
    }

    public void setUserIP(String userIP) {
        this.userIP = userIP;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    @Override
    public String toString() {
        return "InfoDevice{" + "userIP=" + userIP + ", userAgent=" + userAgent + '}';
    }
    
}

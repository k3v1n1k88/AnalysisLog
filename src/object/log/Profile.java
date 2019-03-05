/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package object.log;

import java.util.List;

/**
 *
 * @author root
 */
public class Profile {
    String token;
    List<TAppUserInfo> tAppUserInfo;
    String updatedTs;
    String __isset_bitfield;

    public Profile(String token, List<TAppUserInfo> tAppUserInfo, String updatedTs, String __isset_bitfield) {
        this.token = token;
        this.tAppUserInfo = tAppUserInfo;
        this.updatedTs = updatedTs;
        this.__isset_bitfield = __isset_bitfield;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<TAppUserInfo> gettAppUserInfo() {
        return tAppUserInfo;
    }

    public void settAppUserInfo(List<TAppUserInfo> tAppUserInfo) {
        this.tAppUserInfo = tAppUserInfo;
    }

    public String getUpdatedTs() {
        return updatedTs;
    }

    public void setUpdatedTs(String updatedTs) {
        this.updatedTs = updatedTs;
    }

    public String getIsset_bitfield() {
        return __isset_bitfield;
    }

    public void setIsset_bitfield(String __isset_bitfield) {
        this.__isset_bitfield = __isset_bitfield;
    }

    @Override
    public String toString() {
        return "Profile{" + "token=" + token + ", tAppUserInfo=" + tAppUserInfo + ", updatedTs=" + updatedTs + ", __isset_bitfield=" + __isset_bitfield + '}';
    }
    
}

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
public class ResultTokenMerging {
    
    private String code;
    private String message;
    private String token1;
    private String token2;
    private String __isset_bitfield;

    public ResultTokenMerging(String code, String message, String token1, String token2, String __isset_bitfield) {
        this.code = code;
        this.message = message;
        this.token1 = token1;
        this.token2 = token2;
        this.__isset_bitfield = __isset_bitfield;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getToken1() {
        return token1;
    }

    public void setToken1(String token1) {
        this.token1 = token1;
    }

    public String getToken2() {
        return token2;
    }

    public void setToken2(String token2) {
        this.token2 = token2;
    }

    public String getIsset_bitfield() {
        return __isset_bitfield;
    }

    public void setIsset_bitfield(String __isset_bitfield) {
        this.__isset_bitfield = __isset_bitfield;
    }

    

    @Override
    public String toString() {
        return "ResultTokenMerging{" + "code=" + code + ", message=" + message + ", token1=" + token1 + ", token2=" + token2 + ", __isset_bitfield=" + __isset_bitfield + '}';
    }
    
}

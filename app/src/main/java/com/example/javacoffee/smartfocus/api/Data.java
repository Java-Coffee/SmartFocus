package com.example.javacoffee.smartfocus.api;

/**
 * @author Xiamin
 * @date 2017/9/28
 */
public class Data {

    /**
     * execute_time : 2.2792816162109E-4
     * domain : http://test.ios.api.train.lesaisport.com/
     * code : 400
     * timestamp : 1.5065975874755E9
     * msg : UpolPgKlAxzNlU3tUPOVQg==
     * data : qmuU5ahCeok=
     */

    private double execute_time;
    private String domain;
    private int code;
    private double timestamp;
    private String msg;
    private String data;

    public double getExecute_time() {
        return execute_time;
    }

    public void setExecute_time(double execute_time) {
        this.execute_time = execute_time;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public double getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(double timestamp) {
        this.timestamp = timestamp;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}

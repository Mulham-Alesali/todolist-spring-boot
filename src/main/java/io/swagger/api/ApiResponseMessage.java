package io.swagger.api;

import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2022-01-19T22:52:30.362Z[GMT]")
@javax.xml.bind.annotation.XmlRootElement
@AllArgsConstructor
public class ApiResponseMessage {
    public static final int ERROR = 1;
    public static final int WARNING = 2;
    public static final int INFO = 3;
    public static final int OK = 4;
    public static final int TOO_BUSY = 5;
    
    @JsonProperty("code")
    int code;
    @JsonProperty("type")
    String type;
    @JsonProperty("message")
    String message;

    public ApiResponseMessage(){
    	code = INFO;
    	this.message = "no message";
    	this.type = "not type";
    	
    }

    public ApiResponseMessage(int code, String message){
        this.code = code;
        switch(code){
        case ERROR:
            setType("error");
            break;
        case WARNING:
            setType("warning");
            break;
        case INFO:
            setType("info");
            break;
        case OK:
            setType("ok");
            break;
        case TOO_BUSY:
            setType("too busy");
            break;
        default:
            setType("unknown");
            break;
        }
        this.message = message;
    }

    @XmlTransient
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

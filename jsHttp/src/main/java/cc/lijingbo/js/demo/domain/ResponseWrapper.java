package cc.lijingbo.js.demo.domain;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@JsonPropertyOrder(alphabetic = true, value = {ResponseWrapper.OUTPUT_FIELD_ERROR_CODE, ResponseWrapper.OUTPUT_FIELD_ERROR_MESSAGE, ResponseWrapper.OUTPUT_FIELD_RESULT_DATA})
public class ResponseWrapper {
    public static final String OUTPUT_FIELD_ERROR_CODE = "code";
    public static final String OUTPUT_FIELD_ERROR_MESSAGE = "message";
    public static final String OUTPUT_FIELD_RESULT_DATA = "data";

    @JsonProperty(OUTPUT_FIELD_ERROR_CODE)
    private int code;
    @JsonProperty(OUTPUT_FIELD_ERROR_MESSAGE)
    private String message;
    @JsonProperty(OUTPUT_FIELD_RESULT_DATA)
//	@JsonInclude(Include.NON_NULL)
    private Object data;

    public ResponseWrapper() {
        //JAXB need this
    }

    private ResponseWrapper(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }


    public static ResponseWrapper fail(String message) {
        return new ResponseWrapper(-1, message, null);
    }

    public static ResponseWrapper fail(int code, String message) {
        return new ResponseWrapper(code, message, null);
    }


    public static ResponseWrapper succeed(Object data) {
        return new ResponseWrapper(0, "", data);
    }


    public static ResponseWrapper succeed(String message, Object data) {
        return new ResponseWrapper(0, message, data);
    }

}

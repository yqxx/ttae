package ttae.weixin.http;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseEntity<T> {


    private static final Logger LOGGER = LoggerFactory.getLogger(ResponseEntity.class);
    
    @Enumerated(EnumType.STRING)
    private Status status;//状态：=> SUCCESS or ERROR

    private String msg;//提示信息

    private T data;//SUCCESS状态返回内容部分

    {
        status = Status.SUCCESS;
    }

    public Status getStatus() {
        return status;
    }

    public ResponseEntity setStatus(Status status) {
        this.status = status;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public ResponseEntity setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public T getData() {
        return data;
    }

    public ResponseEntity setData(T data) {
        this.data = data;
        return this;
    }

    @Override
    public String toString() {
        return "Result{" +
                "status=" + status +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }

    enum Status {
        SUCCESS, ERROR
    }
    
    private static final String SUCCESS_MSG = "操作成功";

    private static final String ERROR_MSG = "操作失败";

    public static <T> ResponseEntity<T> genResult(ResponseEntity.Status status, T data, String msg) {
    	ResponseEntity<T> result = new ResponseEntity<T>();
    	result.setStatus(status);
    	result.setData(data);
    	result.setMsg(msg);
        LOGGER.debug("generate rest result:{}", result);
        return result;
    }

    public static ResponseEntity<String> ok() {
    	ResponseEntity<String> result = new ResponseEntity<String>();
    	result.setMsg(SUCCESS_MSG);
        return result;
    }

    public static <T> ResponseEntity<T> ok(T data) {
        return ok(SUCCESS_MSG, data);
    }

    public static <T> ResponseEntity<T> ok(String msg, T data) {
        return genResult(ResponseEntity.Status.SUCCESS, data, msg);
    }

    public static ResponseEntity<String> error() {
    	ResponseEntity<String> result = new ResponseEntity<String>();
    	result.setMsg(ERROR_MSG).setStatus(ResponseEntity.Status.ERROR);
        return result;
    }

    public static <T> ResponseEntity<T> error(String msg) {
        return genResult(ResponseEntity.Status.ERROR, null, msg);
    }
}

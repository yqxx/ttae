package ttae.weixin.bean;

import lombok.Data;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * 前后端交互数据标准
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Result<T> implements Serializable{

    private static final long serialVersionUID = 1L;

    public enum Status {
        SUCCESS, ERROR
    }
    
    /**
     * 成功标志
     */
    private Status status;

    /**
     * 失败消息
     */
    private String msg;

    /**
     * 返回代码
     */
    private Integer code;

    /**
     * 时间戳
     */
    private long timestamp = System.currentTimeMillis();

    /**
     * 结果对象
     */
    private T data;
}

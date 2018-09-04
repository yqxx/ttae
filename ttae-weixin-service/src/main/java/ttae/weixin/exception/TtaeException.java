package ttae.weixin.exception;

import lombok.Data;

@Data
public class TtaeException extends RuntimeException {

    private String msg;

    public TtaeException(String msg){
        super(msg);
        this.msg = msg;
    }
}

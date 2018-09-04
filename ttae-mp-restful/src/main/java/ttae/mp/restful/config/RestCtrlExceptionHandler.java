package ttae.mp.restful.config;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;
import ttae.weixin.bean.Result;
import ttae.weixin.exception.TtaeException;
import ttae.weixin.utils.ResultUtil;

@Slf4j
@RestControllerAdvice
public class RestCtrlExceptionHandler {

    @ExceptionHandler(TtaeException.class)
    @ResponseStatus(value = HttpStatus.OK)
    public Result<Object> handleXCloudException(TtaeException e) {

        String errorMsg="ttae exception";
        if (e!=null){
            errorMsg=e.getMsg();
            log.error(e.toString());
        }
        return ResultUtil.error(500, errorMsg);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.OK)
    public Result<Object> handleException(Exception e) {

        String errorMsg="Exception";
        if (e!=null){
            errorMsg=e.getMessage();
            log.error(e.toString());
        }
        return ResultUtil.error(500, errorMsg);
    }
}

package ttae.weixin.utils;

import ttae.weixin.bean.Result;
import ttae.weixin.bean.Result.Status;

public class ResultUtil<T> {

	public static <T> Result<T> ok(T t) {
		Result<T> result = new Result<>();
		result.setStatus(Status.SUCCESS);
		result.setCode(200);
		result.setData(t);
		return result;
	}

	public static <T> Result<T> ok() {
		Result<T> result = new Result<>();
		result.setStatus(Status.SUCCESS);
		result.setCode(200);
		result.setData(null);
		return result;
	}
	
	public static <T> Result<T> ok(String msg) {
		Result<T> result = new Result<>();
		result.setStatus(Status.SUCCESS);
		result.setCode(200);
		result.setData(null);
		result.setMsg(msg);
		return result;
	}

	public static <T> Result<T> ok(T t, String msg) {
		Result<T> result = new Result<>();
		result.setStatus(Status.SUCCESS);
		result.setCode(200);
		result.setData(t);
		result.setMsg(msg);
		return result;
	}

	public static <T> Result<T> error(String msg) {
		Result<T> result = new Result<>();
		result.setStatus(Status.ERROR);
		result.setCode(500);
		result.setMsg(msg);
		return result;
	}

	public static <T> Result<T> error(Integer code, String msg) {
		Result<T> result = new Result<>();
		result.setStatus(Status.ERROR);
		result.setCode(code);
		result.setMsg(msg);
		return result;
	}
}

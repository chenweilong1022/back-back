package com.ozygod.base.exception;

import cn.hutool.http.HttpException;
import com.ozygod.base.bo.ResponseBO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * 全局异常拦截器
 * 陈伟龙
 */
@RestControllerAdvice
public class RRExceptionHandler {
	private Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 处理自定义异常
	 */
	@ExceptionHandler(RRException.class)
	public ResponseBO handleRRException(RRException e){
		return ResponseBO.error(e.getCode(),e.getMessage());
	}

	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseBO handlerNoFoundException(Exception e) {
		logger.error(e.getMessage(), e);
		return ResponseBO.error(404, "路径不存在，请检查路径是否正确");
	}

	@ExceptionHandler(DuplicateKeyException.class)
	public ResponseBO handleDuplicateKeyException(DuplicateKeyException e){
		logger.error(e.getMessage(), e);
		return ResponseBO.error("数据库中已存在该记录");
	}


	@ExceptionHandler(HttpException.class)
	public ResponseBO httpException(Exception e){
		logger.error(e.getMessage(), e);
		return ResponseBO.error(e.getMessage());
	}

	@ExceptionHandler(Exception.class)
	public ResponseBO handleException(Exception e){
		logger.error(e.getMessage(), e);
		return ResponseBO.error("未知异常");
	}
}

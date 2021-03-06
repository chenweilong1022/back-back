package com.ozygod.conf.aspect;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.servlet.ServletUtil;
import cn.hutool.json.JSONUtil;
import com.ozygod.base.auth.AccessToken;
import com.ozygod.base.bo.ResponseBO;
import com.ozygod.base.enums.Global;
import com.ozygod.base.utils.WebUtil;
import com.ozygod.model.zdmanage.entity.TblSysLogEntity;
import com.ozygod.model.zdmanage.service.TblSysLogService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @title: 请求和返回内容拦截日志记录
 * @description:
 * @author: Joey
 * @date: Created in 16:50 2018/8/28 0028.
 */
@Aspect
@Component
@Slf4j
public class WebLogAspect {
    /**
     * ACCESS_log
     */
    private static final ThreadLocal<AccessToken> ACCESS_LOG = new ThreadLocal<>();
    /**
     * 日志
     */
    private static final ThreadLocal<TblSysLogEntity> TBLSYS_LOG_ENTITY = new ThreadLocal<>();
    /**
     * 开始时间
     */
    private static final ThreadLocal<Long> START_TIME = new ThreadLocal<>();

    @Autowired
    private TblSysLogService tblSysLogService;

    @Pointcut("execution(public * com.ozygod.*.web.*.*(..))")
    public void webLog(){
    }

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        TblSysLogEntity tblSysLogEntity = new TblSysLogEntity();

        START_TIME.set(System.currentTimeMillis());
        TBLSYS_LOG_ENTITY.set(tblSysLogEntity);
        initLog(joinPoint, tblSysLogEntity);
    }

    private void initLog(JoinPoint joinPoint, TblSysLogEntity tblSysLogEntity) {
        // 接收到请求，记录请求内容
        HttpServletRequest request = WebUtil.getHttpServletRequest();
        StringBuffer requestURL = request.getRequestURL();
        String servletPath = request.getServletPath();
        String method = request.getMethod();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        //请求的方法名
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();
        //请求的参数
        Object[] args = joinPoint.getArgs();
        String param = JSONUtil.toJsonStr(args);
        String clientIP = ServletUtil.getClientIP(request, "X-Forwarded-For", "X-Real-IP", "Proxy-Client-IP", "WL-Proxy-Client-IP");



        tblSysLogEntity.setHostUrl(String.valueOf(requestURL));
        tblSysLogEntity.setParams(param);
        tblSysLogEntity.setOperation(servletPath);
        tblSysLogEntity.setHttpMethod(method);
        tblSysLogEntity.setClassMethod(className + "." + methodName + "()");

        tblSysLogEntity.setIp(clientIP);
    }

    /**
     * 出现异常
     * @param e
     */
    @AfterThrowing(value = "webLog()",throwing = "e")
    public void afterThrowing(Exception e) {
        try {
            TblSysLogEntity tblSysLogEntity = setTblSysLogEntity(JSONUtil.toJsonStr(ResponseBO.error(e.getMessage())));
            addSysLog(tblSysLogEntity);
        }finally {
            clear();
        }
    }


    /**
     * 正常返回
     * @param object
     */
    @AfterReturning(returning = "object", pointcut = "webLog()")
    public void doAfterReturning(Object object){
        try {
            TblSysLogEntity tblSysLogEntity = setTblSysLogEntity(String.valueOf(object));

            addSysLog(tblSysLogEntity);
        }finally {
            clear();
        }
    }

    private TblSysLogEntity setTblSysLogEntity(String result) {
        TblSysLogEntity tblSysLogEntity = TBLSYS_LOG_ENTITY.get();
        if (ObjectUtil.isNull(tblSysLogEntity)) {
            return null;
        }
        HttpServletRequest request = WebUtil.getHttpServletRequest();
        AccessToken accessToken = (AccessToken) request.getAttribute(Global.ACCESS_TOKEN_REQUEST_KEY);

        if (ObjectUtil.isNotNull(accessToken)) {
            tblSysLogEntity.setUserId(Integer.valueOf(accessToken.getUserid()));
            tblSysLogEntity.setUsername(accessToken.getLoginname());
        }
        tblSysLogEntity.setTime((System.currentTimeMillis() - START_TIME.get()));
        tblSysLogEntity.setResult(result.getBytes());
        return tblSysLogEntity;
    }

    private static void clear() {
        START_TIME.remove();
        ACCESS_LOG.remove();
        TBLSYS_LOG_ENTITY.remove();
    }

    @Async // 异步入库
    public void addSysLog(TblSysLogEntity tblSysLogEntity) {
        if (StrUtil.containsAny(tblSysLogEntity.getOperation(),"zdmanage/tblsyslog/list")) {
            return;
        }
        tblSysLogService.save(tblSysLogEntity);
    }
}

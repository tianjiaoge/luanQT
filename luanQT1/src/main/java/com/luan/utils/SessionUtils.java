package com.luan.utils;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

public class SessionUtils {

	public static final String sessionKey="sessionUser";
	public static  String ip;
	/**
	 * 该HashMap以用户名-HttpSession对象存储一个账号只能被一个人登陆的信息。
	 */
	public static HashMap<String,HttpSession> sessionMap = new HashMap<String,HttpSession>();
	private static HttpSession getSession() {
		HttpSession session = null;
		try {
			session = getRequest().getSession();
		} catch (Exception e) {
		}
		return session;
	}

	public static HttpServletRequest getRequest() {
		ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		ip = IpUtils.getIpAddress(attrs.getRequest());
		return attrs.getRequest();
	}

	/**
	 * 从session中获取信息
	 *
	 */
	public static Object getObject() {
		return getSession().getAttribute(sessionKey);
	}
    /**
     * 通过ip查询session 中的数据   用在同一个ip下不同作用域的session(扫码壳 和登录不是一个request域)
     * @param
     * @return
     */
    public static Object getIpObject(){
        String ip = IpUtils.getIpAddress(getRequest());
        HttpSession httpSession = sessionMap.get(ip);
        return  httpSession.getAttribute(sessionKey);
    }

	public static void setObject(Object object) {
		HttpSession httpSession = getSession();
		httpSession.setAttribute(sessionKey, object);
		sessionMap.put(ip,httpSession);
	}

	public static void invalidate() {
		HttpSession session = getSession();
		session.removeAttribute(sessionKey);
		sessionMap.remove(ip);
		session.invalidate();
	}


}

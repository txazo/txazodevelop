package org.txazo.framework.struts2.sourcecode;

import java.util.Map;

/**
 * com.opensymphony.xwork2.ActionContext
 * 
 * <pre>
 * 1) Struts2的Action上下文
 * </pre>
 */
public class ActionContext {

	static ThreadLocal<ActionContext> actionContext = new ThreadLocal<ActionContext>();

	/**
	 * ActionContext结构
	 * 
	 * <pre>
	 * application - org.apache.struts2.dispatcher.ApplicationMap(javax.servlet.ServletContext)
	 * session - org.apache.struts2.dispatcher.SessionMap(javax.servlet.http.HttpSession)
	 * request - org.apache.struts2.dispatcher.RequestMap(javax.servlet.http.HttpServletRequest)
	 * request struts.valueStack - com.opensymphony.xwork2.ognl.OgnlValueStack
	 * request struts.actionMapping - org.apache.struts2.dispatcher.mapper.ActionMapping
	 * action - org.txazo.struts2.action.Struts2Action
	 * parameters - java.util.HashMap
	 * attr - org.apache.struts2.util.AttributeMap
	 * struts.actionMapping - org.apache.struts2.dispatcher.mapper.ActionMapping
	 * com.opensymphony.xwork2.dispatcher.ServletContext - org.apache.catalina.core.ApplicationContextFacade
	 * com.opensymphony.xwork2.dispatcher.HttpServletRequest - org.apache.struts2.dispatcher.StrutsRequestWrapper
	 * com.opensymphony.xwork2.dispatcher.HttpServletResponse - org.apache.catalina.connector.ResponseFacade
	 * com.opensymphony.xwork2.util.ValueStack.ValueStack	class com.opensymphony.xwork2.ognl.OgnlValueStack
	 * com.opensymphony.xwork2.ActionContext.name - java.lang.String(struts2)
	 * com.opensymphony.xwork2.ActionContext.locale - java.util.Locale
	 * com.opensymphony.xwork2.ActionContext.container - com.opensymphony.xwork2.inject.ContainerImpl
	 * com.opensymphony.xwork2.ActionContext.application - org.apache.struts2.dispatcher.ApplicationMap
	 * com.opensymphony.xwork2.ActionContext.session - org.apache.struts2.dispatcher.SessionMap
	 * com.opensymphony.xwork2.ActionContext.parameters - java.util.TreeMap
	 * com.opensymphony.xwork2.ActionContext.actionInvocation - com.opensymphony.xwork2.DefaultActionInvocation
	 * </pre>
	 */
	private Map<String, Object> context;

	public ActionContext(Map<String, Object> context) {
		this.context = context;
	}

	public Map<String, Object> getContextMap() {
		return context;
	}

	/**
	 * 获取当前线程的ActionContext
	 */
	public static ActionContext getContext() {
		return actionContext.get();
	}

	/**
	 * 设置当前线程的ActionContext
	 */
	public static void setContext(ActionContext context) {
		actionContext.set(context);
	}

}

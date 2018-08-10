package org.txazo.framework.struts2.sourcecode;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.ApplicationMap;
import org.apache.struts2.dispatcher.RequestMap;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.dispatcher.mapper.ActionMapping;
import org.apache.struts2.util.AttributeMap;

import com.opensymphony.xwork2.util.LocalizedTextUtil;

/**
 * org.apache.struts2.dispatcher.Dispatcher
 * 
 * <pre>
 * 1) Struts2核心分发器
 * </pre>
 */
public class Dispatcher {

	private static ThreadLocal<Dispatcher> instance = new ThreadLocal<Dispatcher>();

	private String defaultLocale;

	protected ServletContext servletContext;

	/**
	 * 完成Struts2的初始化
	 * 
	 * <pre>
	 * 1) 创建ConfigurationManager
	 * 2) ConfigurationManager注册FileManagerProvider - FileManager
	 * 3) ConfigurationManager注册DefaultPropertiesProvider - org/apache/struts2/default.properties
	 * 4) ConfigurationManager注册StrutsXmlConfigurationProvider - struts-default.xml,struts-plugin.xml,struts.xml
	 * 5) ConfigurationManager注册PropertiesConfigurationProvider - struts.properties
	 * 5) ConfigurationManager注册CustomConfigurationProviders - ConfigurationProvider
	 * 6) ConfigurationManager注册ConfigurationProvider - StrutsPrepareAndExecuteFilter初始化参数
	 * 7) ConfigurationManager注册DefaultBeanSelectionProvider
	 * 8) 创建Configuration，reloadContainer上面注册的ContainerProvider，并创建Container
	 * 9) Container注入Dispatcher
	 * 10) 回调DispatcherListener
	 * </pre>
	 */
	public void init() {
	}

	/**
	 * 请求转发，执行Action
	 * 
	 * <pre>
	 * 1) 创建ActionProxy，ActionInvocation(DefaultActionInvocation)
	 * 2) 实例化Action
	 * 3) 调用ActionInvocation的invoke()
	 * 4) 执行一系列拦截器Interceptor
	 * 5) 调用ActionInvocation的invokeActionOnly()，执行Action的方法
	 * 6) 执行一系列拦截器Interceptor
	 * </pre>
	 */
	public void serviceAction() {
	}

	/**
	 * 获取当前线程的Dispatcher
	 */
	public static Dispatcher getInstance() {
		return instance.get();
	}

	/**
	 * 设置当前线程的Dispatcher
	 */
	public static void setInstance(Dispatcher instance) {
		Dispatcher.instance.set(instance);
	}

	/**
	 * 创建ActionContext的ContextMap
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Map<String, Object> createContextMap(HttpServletRequest request, HttpServletResponse response, ActionMapping mapping) {
		Map requestMap = new RequestMap(request);
		Map params = new HashMap(request.getParameterMap());
		Map session = new SessionMap(request);
		Map application = new ApplicationMap(servletContext);
		Map<String, Object> extraContext = createContextMap(requestMap, params, session, application, request, response);
		if (mapping != null) {
			extraContext.put(ServletActionContext.ACTION_MAPPING, mapping);
		}
		return extraContext;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public HashMap<String, Object> createContextMap(Map requestMap, Map parameterMap, Map sessionMap, Map applicationMap, HttpServletRequest request,
			HttpServletResponse response) {
		HashMap<String, Object> extraContext = new HashMap<String, Object>();
		extraContext.put("com.opensymphony.xwork2.ActionContext.parameters", new HashMap(parameterMap));
		extraContext.put("com.opensymphony.xwork2.ActionContext.session", sessionMap);
		extraContext.put("com.opensymphony.xwork2.ActionContext.application", applicationMap);

		Locale locale;
		if (defaultLocale != null) {
			locale = LocalizedTextUtil.localeFromString(defaultLocale, request.getLocale());
		} else {
			locale = request.getLocale();
		}

		extraContext.put("com.opensymphony.xwork2.ActionContext.locale", locale);

		extraContext.put("com.opensymphony.xwork2.dispatcher.HttpServletRequest", request);
		extraContext.put("com.opensymphony.xwork2.dispatcher.HttpServletResponse", response);
		extraContext.put("com.opensymphony.xwork2.dispatcher.ServletContext", servletContext);

		extraContext.put("request", requestMap);
		extraContext.put("session", sessionMap);
		extraContext.put("application", applicationMap);
		extraContext.put("parameters", parameterMap);

		AttributeMap attrMap = new AttributeMap(extraContext);
		extraContext.put("attr", attrMap);

		return extraContext;
	}

}

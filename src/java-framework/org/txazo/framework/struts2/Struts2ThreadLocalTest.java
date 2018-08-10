//package org.txazo.framework.struts2;
//
//import java.io.Serializable;
//import java.util.HashMap;
//import java.util.Locale;
//import java.util.Map;
//
///**
// * Struts2 ThreadLocal
// * 
// * <pre>
// * 1) Struts2的Action是线程安全的
// * 1) ThreadLocal解决了Struts2中不同层次的数据共享问题
// * 3) ActionContext
// * </pre>
// */
//public class Struts2ThreadLocalTest {
//
//	/**
//	 * com.opensymphony.xwork2.ActionContext
//	 */
//	public static class ActionContext implements Serializable {
//
//		private static final long serialVersionUID = -3041111709270302502L;
//
//		private static ThreadLocal<ActionContext> actionContext = new ThreadLocal<ActionContext>();
//
//		public static final String ACTION_NAME = "com.opensymphony.xwork2.ActionContext.name";
//		public static final String VALUE_STACK = "com.opensymphony.xwork2.util.ValueStack.ValueStack";
//		public static final String SESSION = "com.opensymphony.xwork2.ActionContext.session";
//		public static final String APPLICATION = "com.opensymphony.xwork2.ActionContext.application";
//		public static final String PARAMETERS = "com.opensymphony.xwork2.ActionContext.parameters";
//		public static final String LOCALE = "com.opensymphony.xwork2.ActionContext.locale";
//		public static final String TYPE_CONVERTER = "com.opensymphony.xwork2.ActionContext.typeConverter";
//		public static final String ACTION_INVOCATION = "com.opensymphony.xwork2.ActionContext.actionInvocation";
//		public static final String CONVERSION_ERRORS = "com.opensymphony.xwork2.ActionContext.conversionErrors";
//		public static final String CONTAINER = "com.opensymphony.xwork2.ActionContext.container";
//
//		private Map<String, Object> context;
//
//		public ActionContext(Map<String, Object> context) {
//			this.context = context;
//		}
//
//		public void setApplication(Map<String, Object> application) {
//			put(APPLICATION, application);
//		}
//
//		@SuppressWarnings("unchecked")
//		public Map<String, Object> getApplication() {
//			return (Map<String, Object>) get(APPLICATION);
//		}
//
//		public static void setContext(ActionContext context) {
//			actionContext.set(context);
//		}
//
//		public static ActionContext getContext() {
//			return actionContext.get();
//		}
//
//		public void setContextMap(Map<String, Object> contextMap) {
//			getContext().context = contextMap;
//		}
//
//		public Map<String, Object> getContextMap() {
//			return context;
//		}
//
//		public void setConversionErrors(Map<String, Object> conversionErrors) {
//			put(CONVERSION_ERRORS, conversionErrors);
//		}
//
//		@SuppressWarnings({ "unchecked", "rawtypes" })
//		public Map<String, Object> getConversionErrors() {
//			Map<String, Object> errors = (Map) get(CONVERSION_ERRORS);
//
//			if (errors == null) {
//				errors = new HashMap<String, Object>();
//				setConversionErrors(errors);
//			}
//
//			return errors;
//		}
//
//		public void setLocale(Locale locale) {
//			put(LOCALE, locale);
//		}
//
//		public Locale getLocale() {
//			Locale locale = (Locale) get(LOCALE);
//
//			if (locale == null) {
//				locale = Locale.getDefault();
//				setLocale(locale);
//			}
//
//			return locale;
//		}
//
//		public void setName(String name) {
//			put(ACTION_NAME, name);
//		}
//
//		public String getName() {
//			return (String) get(ACTION_NAME);
//		}
//
//		public void setParameters(Map<String, Object> parameters) {
//			put(PARAMETERS, parameters);
//		}
//
//		@SuppressWarnings("unchecked")
//		public Map<String, Object> getParameters() {
//			return (Map<String, Object>) get(PARAMETERS);
//		}
//
//		public void setSession(Map<String, Object> session) {
//			put(SESSION, session);
//		}
//
//		@SuppressWarnings("unchecked")
//		public Map<String, Object> getSession() {
//			return (Map<String, Object>) get(SESSION);
//		}
//
//		public Object get(String key) {
//			return context.get(key);
//		}
//
//		public void put(String key, Object value) {
//			context.put(key, value);
//		}
//
//	}
//
//}

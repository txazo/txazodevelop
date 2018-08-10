package org.txazo.framework.struts2.sourcecode;

/**
 * org.apache.struts2.dispatcher.ng.filter.StrutsPrepareAndExecuteFilter
 * 
 * <pre>
 * 1) struts2的核心过滤器
 * </pre>
 * 
 */
public class StrutsPrepareAndExecuteFilter {

	/**
	 * 初始化
	 * 
	 * <pre>
	 * 1) 封装FilterConfig
	 * 2) 初始化LoggerFactory
	 * 3) 创建Dispatcher，并初始化，参见Dispatcher
	 * 4) 创建PrepareOperations
	 * 5) 创建ExecuteOperations
	 * 6) 解析excludedPatterns
	 * 7) 释放Container和ActionContext
	 * </pre>
	 */
	public void init() {
	}

	/**
	 * 过滤并处理请求
	 * 
	 * <pre>
	 * 1) 检查uri是否匹配excludedPatterns
	 * 2) PrepareOperations设置编码(struts.i18n.encoding)和国际化(struts.locale)
	 * 3) PrepareOperations创建ActionContext(参见ActionContext)，并绑定ActionContext到请求线程的本地变量threadLocals上
	 * 4) PrepareOperations绑定Dispatcher到请求线程的本地变量threadLocals上
	 * 5) PrepareOperations包装ServletRequest为StrutsRequestWrapper
	 * 5) PrepareOperations查找请求对应的ActionMapping
	 * 6) ExecuteOperations执行Action，参见Dispatcher的serviceAction()
	 * 7) PrepareOperations释放请求线程本地变量threadLocals上的Dispatcher，Container，ActionContext
	 * </pre>
	 */
	public void doFilter() {
	}

	/**
	 * excludedPatterns(struts.action.excludePattern)
	 * 
	 * <pre>
	 * 1) 指定不经过Struts2处理的URI规则
	 * 2) struts.action.excludePattern为正则表达式，多个表达式间使用逗号隔开
	 * </pre>
	 */
	public void excludedPatterns() {
	}

}

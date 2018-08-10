package org.txazo.java.web.servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.txazo.test.base.BaseTest;

/**
 * javax.servlet.Servlet
 * 
 * <pre>
 * 1) Servlet运行在Servlet容器中, 单实例多线程, 线程不安全
 * 2) 生命周期: 装载 - 实例化 - init() - service() - destroy()
 * 3) JSP执行时转化为Servlet, Servlet的class文件或JSP文件更新后，会被重新装载
 * 4) 请求响应过程: 请求 - 创建HttpServletRequest和HttpServletResponse - 分配工作线程 - 执行service(request, response)方法 - 返回响应
 * 5) 多线程: 避免使用实例变量, 同步操作(synchronized, ThreadLocal)
 * 6) 请求响应对象: HttpServletRequest和HttpServletResponse是线程安全的, HttpSession和ServletContext不是线程安全的
 * 7) SingleThreadModel: 实现SingleThreadModel接口的Servlet, 一次只处理一个请求, 容器会为每个请求创建一个单独的Servlet, 不提倡使用
 * </pre>
 */
public class ServletTest extends BaseTest {

	public class SingleServlet extends HttpServlet {

		private static final long serialVersionUID = 6020443524373745756L;

		@Override
		public void init(ServletConfig config) throws ServletException {
			super.init(config);
		}

		@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			doPost(req, resp);
		}

		@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			super.doPost(req, resp);
		}

		@Override
		public void destroy() {
			super.destroy();
		}

	}

}

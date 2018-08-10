package org.txazo.java.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.txazo.test.base.BaseTest;

/**
 * 自定义Annotation
 * 
 * @author txazo
 * 
 */
public class AnnotationTest extends BaseTest {

	@Test
	public void testAnnotation() {
		Filter filter = new LoginFilter();

		filter.doFilter(new LoginAction("root", "root"));
		filter.doFilter(new LoginAction("admin", "admin"));

		filter.doFilter(new GuestAction("admin", null));
		filter.doFilter(new GuestAction(null, null));

		filter.doFilter(new NoLoginAction(null, null));
	}

	public enum LoginType {
		LOGIN, GUEST
	}

	@Documented
	@Inherited
	@Retention(value = RetentionPolicy.RUNTIME)
	@Target(value = { ElementType.METHOD })
	public @interface Login {

		LoginType value() default LoginType.LOGIN;

	}

	private interface Action {

		public void execute();

	}

	private abstract class AbstractAction implements Action {

		private String account;
		private String password;

		public AbstractAction(String account, String password) {
			this.account = account;
			this.password = password;
		}

		public boolean login() {
			return "root".equals(account) && "root".equals(password);
		}

		public String getAccount() {
			return account;
		}

	}

	private class LoginAction extends AbstractAction {

		public LoginAction(String account, String password) {
			super(account, password);
		}

		@Login
		public void execute() {
			logger.info("LoginAction execute ...");
		}

	}

	private class GuestAction extends AbstractAction {

		public GuestAction(String account, String password) {
			super(account, password);
		}

		@Login(LoginType.GUEST)
		public void execute() {
			logger.info("GuestAction execute ...");
		}

	}

	private class NoLoginAction extends AbstractAction {

		public NoLoginAction(String account, String password) {
			super(account, password);
		}

		public void execute() {
			logger.info("NoLoginAction execute ...");
		}

	}

	private interface Filter {

		public void doFilter(Action action);

	}

	private class LoginFilter implements Filter {

		@Override
		public void doFilter(Action action) {
			Login login = null;
			Class<? extends Object> actionClass = action.getClass();
			try {
				Method method = actionClass.getDeclaredMethod("execute");
				login = method.getAnnotation(Login.class);
				if (login == null) {
					login = actionClass.getAnnotation(Login.class);
				}
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			}

			if (login != null) {
				if (action instanceof AbstractAction) {
					LoginType loginType = login.value();
					AbstractAction abstractAction = (AbstractAction) action;
					String account = abstractAction.getAccount();
					if (LoginType.LOGIN == loginType && abstractAction.login()) {
						logger.info("LoginFilter login: " + account);
					} else if (LoginType.GUEST == loginType && StringUtils.isNotEmpty(account)) {
						logger.info("LoginFilter guest: " + account);
					} else {
						logger.info("LoginFilter login failed");
						return;
					}
				} else {
					logger.info("LoginFilter nologin");
				}
			} else {
				logger.info("LoginFilter nologin");
			}

			action.execute();
		}
	}

}

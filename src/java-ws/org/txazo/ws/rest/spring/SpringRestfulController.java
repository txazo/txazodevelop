package org.txazo.ws.rest.spring;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.txazo.java.util.ResponseUtil;

/**
 * Spring MVC Restful
 */
@Controller
@RequestMapping(value = "/user")
public class SpringRestfulController {

	private static Map<String, String> userMap = new HashMap<String, String>();

	static {
		userMap.put("1", "root");
	}

	// http://127.0.0.1/springrest/user/admin
	@RequestMapping(value = "/admin")
	public String admin(HttpServletRequest request, HttpServletResponse response) {
		return "/springrest/user/admin";
	}

	// http://127.0.0.1/springrest/user/list
	@RequestMapping(value = "/list")
	public void list(HttpServletRequest request, HttpServletResponse response) {
		ResponseUtil.renderJson(response, JSONArray.fromObject(userMap).toString());
	}

	// http://127.0.0.1/springrest/user/1
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public void get(@PathVariable Long id, HttpServletRequest request, HttpServletResponse response) {
		if (userMap.containsKey(id.toString())) {
			ResponseUtil.renderJson(response, "{\"status\" : 1, \"user\" : " + userMap.get(id) + "}");
		} else {
			ResponseUtil.renderJson(response, "{\"status\" : 0}");
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.POST)
	public void add(@PathVariable Long id, String user, HttpServletRequest request, HttpServletResponse response) {
		int status = 0;
		if (!userMap.containsKey(id.toString()) && StringUtils.isNotBlank(user)) {
			status = 1;
			userMap.put(id.toString(), user);
		}
		ResponseUtil.renderJson(response, "{\"status\" : " + status + "}");
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable Long id, HttpServletRequest request, HttpServletResponse response) {
		userMap.remove(id.toString());
		ResponseUtil.renderJson(response, "{\"status\" : 1}");
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public void update(@PathVariable Long id, String user, HttpServletRequest request, HttpServletResponse response) {
		int status = 0;
		if (userMap.containsKey(id.toString()) && StringUtils.isNotBlank(user)) {
			status = 1;
			userMap.put(id.toString(), user);
		}
		ResponseUtil.renderJson(response, "{\"status\" : " + status + "}");
	}

}

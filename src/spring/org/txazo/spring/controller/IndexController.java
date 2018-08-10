package org.txazo.spring.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {

	// http://127.0.0.1/spring/index1.jsp
	@RequestMapping(value = "/index1", method = RequestMethod.GET)
	public String index1(Model model) {
		model.addAttribute("msg", "index1");
		return "index";
	}

	/** HttpServletRequest */
	// http://127.0.0.1/spring/index2.jsp?id=1000
	@RequestMapping(value = "/index2", method = RequestMethod.GET)
	public String index2(HttpServletRequest request, Model model) {
		model.addAttribute("msg", "index2 id:" + request.getParameter("id"));
		return "index";
	}

	/** @ResponseBody */
	/** @RequestParam */
	// http://127.0.0.1/spring/index3.jsp?id=1000
	@RequestMapping(value = "/index3", method = RequestMethod.GET)
	@ResponseBody
	public String index3(@RequestParam String id) {
		return "index3 id:" + id;
	}

	/** @PathVariable */
	// http://127.0.0.1/spring/index4/1000.jsp
	@RequestMapping(value = "/index4/{id}", method = RequestMethod.GET)
	public String index4(@PathVariable String id, Model model) {
		model.addAttribute("msg", "index4 id:" + id);
		return "index";
	}

	// http://127.0.0.1/spring/index5.jsp
	@RequestMapping(value = "/index5", method = RequestMethod.GET)
	public String index5() {
		return "redirect:index1.jsp";
	}

	// http://127.0.0.1/spring/index6.jsp?type=txazo
	@RequestMapping(value = "/index6", params = "type=txazo", method = RequestMethod.GET)
	public String index6(Model model) {
		model.addAttribute("msg", "index6");
		return "index";
	}

	// http://127.0.0.1/spring/index7.jsp?id=1000
	@RequestMapping(value = "/index7", method = RequestMethod.GET)
	public String index7(String id, Model model) {
		model.addAttribute("msg", "index7 id:" + id);
		return "index";
	}

	// @ModelAttribute
	// @ModelAttribute("person")
	// public Person getPerson() {
	// return new Person("txazo");
	// }

	// @ModelAttribute
	// http://127.0.0.1/spring/index8.jsp?person.name=txazo
	// @RequestMapping(value = "/index8", method = RequestMethod.GET)
	// public String index8(@ModelAttribute("person") Person person, Model
	// model) {
	// model.addAttribute("msg", "index8 name:" + person.getName());
	// return "index";
	// }

	// @RequestHeader
	// http://127.0.0.1/spring/index9.jsp
	@RequestMapping(value = "/index9", method = RequestMethod.GET)
	public String index9(@RequestHeader(value = "User-Agent", required = true) String userAgent, Model model) {
		model.addAttribute("msg", "index9 userAgent:" + userAgent);
		return "index";
	}

	// @CookieValue
	// http://127.0.0.1/spring/index10.jsp
	@RequestMapping(value = "/index10", method = RequestMethod.GET)
	public String index10(@CookieValue(value = "JSESSIONID", defaultValue = "") String sessionId, Model model) {
		model.addAttribute("msg", "index10 sessionId:" + sessionId);
		return "index";
	}

	// http://127.0.0.1/spring/index11.jsp
	@RequestMapping(value = "/index11", headers = "User-Agent", method = RequestMethod.GET)
	public String index11(Model model) {
		model.addAttribute("msg", "index11");
		return "index";
	}

	// http://127.0.0.1/spring/index12.jsp
	@RequestMapping(value = "/index12", method = RequestMethod.GET)
	public String index12() {
		System.out.println(1 / 0);
		return "index";
	}

}

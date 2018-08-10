package org.txazo.framework.struts2.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;

@Namespace("/")
@ParentPackage("default")
public class BaseAction {

	@Action(value = "index")
	public void index() {
		System.out.println("1");
		System.out.println("2");
	}

}

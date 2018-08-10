package org.txazo.pattern.structural.composite.safe;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface Component {

	public static Logger logger = LoggerFactory.getLogger("stdout");

	public void operation();

	public void setBlank(String blank);

}

package org.txazo.nio.selector;

public interface Handler {

	public String getRequest();

	public void response(String data);

}

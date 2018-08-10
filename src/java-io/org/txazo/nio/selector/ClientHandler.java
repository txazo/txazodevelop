package org.txazo.nio.selector;

import java.util.Map;

import org.txazo.test.base.BaseTest;

import com.alibaba.fastjson.JSONObject;

public class ClientHandler extends BaseTest implements Handler {

	private String event;
	private Map<String, Object> params;

	public ClientHandler(String event, Map<String, Object> params) {
		this.event = event;
		this.params = params;
	}

	@Override
	public String getRequest() {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("event", event);
		jsonObject.put("params", params);

		String request = jsonObject.toJSONString();
		logger.info("Client Request: {}", request);
		return request;
	}

	@Override
	public void response(String data) {
		logger.info("Client Response: {}", data);
	}

}

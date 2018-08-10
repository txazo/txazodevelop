package org.txazo.nio.selector;

import java.util.Map;

import org.txazo.test.base.BaseTest;

import com.alibaba.fastjson.JSONObject;

public class ServerResponse extends BaseTest {

	private boolean ready = false;
	private String data;
	private String event;
	private Map<String, Object> params;

	public ServerResponse(String data) {
		this.data = data;
	}

	public ServerResponse(String event, Map<String, Object> params) {
		this.event = event;
		this.params = params;
	}

	public String getRequest() {
		logger.info("Server Request: " + data);
		return data;
	}

	public String getResponse() {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("event", event);
		jsonObject.put("params", params);

		String response = jsonObject.toJSONString();
		logger.info("Server Response: {}", response);
		return response;
	}

	public boolean isReady() {
		return ready;
	}

	public void setReady(boolean ready) {
		this.ready = ready;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public void setParams(Map<String, Object> params) {
		this.params = params;
	}

}

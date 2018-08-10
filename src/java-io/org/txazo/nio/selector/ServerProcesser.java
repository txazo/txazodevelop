package org.txazo.nio.selector;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;

public class ServerProcesser {

	public void process(ServerResponse response) {
		new Thread(new ServerProcesserThread(response)).start();
	}

	public class ServerProcesserThread implements Runnable {

		private ServerResponse response;

		public ServerProcesserThread(ServerResponse response) {
			this.response = response;
		}

		@Override
		public void run() {
			JSONObject jsonObject = JSONObject.parseObject(response.getRequest());
			String event = (String) jsonObject.get("event");
			JSONObject params = jsonObject.getJSONObject("params");

			response.setEvent(event);
			if ("init".equals(event)) {
			} else if ("register".equals(event)) {
				Map<String, Object> valueMap = new HashMap<String, Object>();
				valueMap.put("status", 1);
				if (params != null) {
					valueMap.put("memberId", params.get("memberId"));
				}
				response.setParams(valueMap);
			}

			response.setReady(true);
		}

	}

}

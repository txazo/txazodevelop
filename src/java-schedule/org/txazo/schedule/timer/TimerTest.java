package org.txazo.schedule.timer;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import org.junit.Test;
import org.txazo.test.base.BaseTest;

/**
 * Java Timer
 * 
 * <pre>
 * Java Timer任务调度:
 * 1) 指定时间执行一次
 * 2) 延后执行一次
 * 3) 延后并循环延后执行
 * 4) 延后并循环周期执行
 * </pre>
 * 
 * @author txazo
 * 
 */
public class TimerTest extends BaseTest {

	@Test
	public void testTimer() throws InterruptedException {
		Date delayDate = new Date(System.currentTimeMillis() + 1000);
		Timer timer = new Timer();
		timer.schedule(new MyTimerTask("scheduleAtTime"), delayDate);
		timer.schedule(new MyTimerTask("scheduleAtDelay"), 2000);
		timer.schedule(new MyTimerTask("scheduleAtFixedDelay 1"), delayDate, 1000);
		timer.schedule(new MyTimerTask("scheduleAtFixedDelay 2"), 2000, 1000);
		timer.scheduleAtFixedRate(new MyTimerTask("scheduleAtFixedRate 1"), delayDate, 1000);
		timer.scheduleAtFixedRate(new MyTimerTask("scheduleAtFixedRate 2"), 2000, 1000);

		Thread.sleep(10000);
	}

	public class MyTimerTask extends TimerTask {

		private String description;

		public MyTimerTask(String description) {
			this.description = description;
		}

		@Override
		public void run() {
			logger.info("{}", description);
		}

	}

}

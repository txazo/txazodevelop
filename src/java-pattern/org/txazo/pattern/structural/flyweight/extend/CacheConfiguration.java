package org.txazo.pattern.structural.flyweight.extend;

/**
 * 享元对象缓存配置
 * 
 * @author txazo
 * 
 */
public class CacheConfiguration {

	// 缓存开始时间
	private long beginTime;
	// 缓存的最长不被使用时间
	private double timeout;
	// 是否永久存储
	private boolean forever;

	public long getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(long beginTime) {
		this.beginTime = beginTime;
	}

	public double getTimeout() {
		return timeout;
	}

	public void setTimeout(double timeout) {
		this.timeout = timeout;
	}

	public boolean isForever() {
		return forever;
	}

	public void setForever(boolean forever) {
		this.forever = forever;
	}

}

package org.txazo.pattern.structural.proxy.virtual;

/**
 * 代理模式 - 虚拟代理
 * 
 * <pre>
 * 1. 延迟创建开销很大的对象，只在需要时才会被创建
 * </pre>
 * 
 * @author txazo
 * 
 */
public class ProxyProcessor implements Processor {

	private Processor processor;

	@Override
	public void process() {
		if (processor == null) {
			processor = new RealProcessor();
		}
		processor.process();
	}

}

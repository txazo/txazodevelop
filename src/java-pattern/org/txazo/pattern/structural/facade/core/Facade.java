package org.txazo.pattern.structural.facade.core;

/**
 * 外观模式
 * 
 * <pre>
 * 1. 封装交互，简化调用
 * 2. 为子系统中的一组接口提供一个统一的高层接口
 * </pre>
 * 
 * @author txazo
 * 
 */
public class Facade {

	private Customer customer;
	private Waiter waiter;
	private Cooker cooker;
	private Cashier cashier;

	public Facade() {
		customer = new Customer();
		waiter = new Waiter();
		cooker = new Cooker();
		cashier = new Cashier();
	}

	public void service() {
		customer.order();
		waiter.passOrder();
		cooker.cook();
		waiter.serving();
		cashier.cash();
	}

}

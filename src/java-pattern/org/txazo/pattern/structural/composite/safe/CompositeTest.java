package org.txazo.pattern.structural.composite.safe;

import org.junit.Test;
import org.txazo.test.base.BaseTest;

public class CompositeTest extends BaseTest {

	@Test
	public void testComposite() {
		Composite root = new Composite("服装");

		Composite c1 = new Composite("男装");
		c1.addComponent(new Leaf("西服"));
		c1.addComponent(new Leaf("衬衫"));
		c1.addComponent(new Leaf("夹克"));

		Composite c2 = new Composite("女装");
		c2.addComponent(new Leaf("西服"));
		c2.addComponent(new Leaf("衬衫"));
		c2.addComponent(new Leaf("连衣裙"));

		root.addComponent(c1);
		root.addComponent(c2);

		root.operation();
	}

}

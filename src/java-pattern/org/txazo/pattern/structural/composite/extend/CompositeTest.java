package org.txazo.pattern.structural.composite.extend;

import org.junit.Test;
import org.txazo.test.base.BaseTest;

public class CompositeTest extends BaseTest {

	@Test
	public void testComposite() {
		Component root = new Composite("服装");

		Component c1 = new Composite("男装");
		c1.addChild(new Leaf("男西服"));
		c1.addChild(new Leaf("男衬衫"));
		c1.addChild(new Leaf("男夹克"));

		Component c2 = new Composite("女装");
		c2.addChild(new Leaf("女西服"));
		c2.addChild(new Leaf("女衬衫"));
		c2.addChild(new Leaf("连衣裙"));

		root.addChild(c1);
		root.addChild(c2);
		root.operation();

		root.removeChild(c2);
		root.addChild(c1);
		root.operation();
	}

}

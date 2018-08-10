package org.txazo.nio.reactor;

import java.nio.channels.Selector;

public interface NIOReactor {

	public Selector getSelector();

}

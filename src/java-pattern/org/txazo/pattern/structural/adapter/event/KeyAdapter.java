package org.txazo.pattern.structural.adapter.event;

import java.awt.event.KeyEvent;

/**
 * java.awt.event.KeyAdapter
 * 
 * <pre>
 * 1. 缺省适配器
 * </pre>
 * 
 * @author txazo
 * 
 */
public abstract class KeyAdapter implements KeyListener {

	public void keyTyped(KeyEvent e) {
	}

	public void keyPressed(KeyEvent e) {
	}

	public void keyReleased(KeyEvent e) {
	}
}

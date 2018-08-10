package org.txazo.pattern.structural.adapter.event;

import java.awt.event.KeyEvent;

/**
 * java.awt.event.EventListener
 * 
 * @author txazo
 * 
 */
public interface KeyListener extends EventListener {

	public void keyTyped(KeyEvent e);

	public void keyPressed(KeyEvent e);

	public void keyReleased(KeyEvent e);

}

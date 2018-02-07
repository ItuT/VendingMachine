/**
 * 
 */
package vending.client;

import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.JFrame;

import vending.VendingMachineFactory;
import vending.api.VendingMachine;

/**
 * @author Itumeleng
 *
 */
public class VMachineGUI extends JFrame{
	
	private static final int WIDTH = 600;
	private static final int HEIGHT = 800;

	VendingMachine vm = VendingMachineFactory.createVendingMachine();
	
	public VMachineGUI() {
	
		setTitle("VendingMachine");

		Container pane = getContentPane();
	
		pane.setLayout(new GridLayout(8, 2));
		
		setSize(WIDTH, HEIGHT);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args)
	/*
	* here stands the main class for the VMachineGui
	* @author nikolaos mavrommatis
	*/
	{
		@SuppressWarnings("unused")
		VMachineGUI rectObject = new VMachineGUI();
	}
	
}

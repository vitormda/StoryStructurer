package br.go.cdg.utils;

import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import br.go.cdg.window.Window;

public class ButtonGenerator {
	
	public static JButton getAcceptButton() {
		return getButton("/img/accept.png");
	}
	
	public static JButton getAddButton() {
		return getButton("/img/add.png");
	}
	
	public static JButton getCancelButton() {
		return getButton("/img/cancel.png");
	}

	public static JButton getDeleteButton() {
		return getButton("/img/delete.png");
	}

	public static JButton getDownButton() {
		return getButton("/img/down.png");
	}

	public static JButton getEditButton() {
		return getButton("/img/edit.png");
	}

	public static JButton getUpButton() {
		return getButton("/img/up.png");
	}
	
	
	private static JButton getButton(String resource) {
		JButton button = new JButton();
		
		Image img;
		
		try {
			
			img = ImageIO.read(Window.getResource(resource));
			
			button.setIcon(new ImageIcon(img));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return button;
	}
}

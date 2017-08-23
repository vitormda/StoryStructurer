package br.go.cdg.window;

import java.awt.Dimension;

import javax.swing.JPanel;

public class PassageHolder extends JPanel {
	
	private static final long serialVersionUID = -9211634806589701716L;
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(this.getWidth(), this.getHeight());
	}
}

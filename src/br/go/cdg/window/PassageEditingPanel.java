package br.go.cdg.window;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

/**
 * @author vitor.almeida
 */
public class PassageEditingPanel extends JPanel {
	
	private static final long serialVersionUID = -5459688383379831692L;
	
	private ArrayList<String> fragments = new ArrayList<String>();
	
	public PassageEditingPanel() {
		
		setBorder(BorderFactory.createEtchedBorder());
		
		setBackground(Color.BLACK);
		
		if (fragments.isEmpty()) {
			add(new FragmentEditingPanel());
		}
	}
}

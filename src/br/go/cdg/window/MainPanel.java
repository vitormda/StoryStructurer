package br.go.cdg.window;

import java.awt.BorderLayout;

import javax.swing.JPanel;

public class MainPanel extends JPanel {
	
	private static final long serialVersionUID = 831426905746908132L;

	public MainPanel() {
		super(new BorderLayout(5, 5));
		
		this.add(new TitlePanel(), BorderLayout.PAGE_START);
		
		this.add(new PassageListPanel(), BorderLayout.LINE_START);
		
		this.add(new PassageEditingPanel(), BorderLayout.CENTER);
	}
}

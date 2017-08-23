package br.go.cdg.window;

import java.awt.BorderLayout;

import javax.swing.DefaultListModel;
import javax.swing.JPanel;

import br.go.cdg.model.Passage;

public class MainPanel extends JPanel {
	
	private static final long serialVersionUID = 831426905746908132L;
	
	private TitlePanel titlePanel = new TitlePanel();
	
	private PassageListPanel passageListPanel = new PassageListPanel();;
	
	private PassageEditingPanel passageEditingPanel = new PassageEditingPanel();
	
	public static DefaultListModel<Passage> passageList = new DefaultListModel<Passage>();
	
	public MainPanel() {
		super(new BorderLayout(5, 5));
		
		this.add(titlePanel, BorderLayout.PAGE_START);
		
		this.add(passageListPanel, BorderLayout.LINE_START);
		
		this.add(passageEditingPanel, BorderLayout.CENTER);
	}

	public TitlePanel getTitlePanel() {
		return titlePanel;
	}

	public void setTitlePanel(TitlePanel titlePanel) {
		this.titlePanel = titlePanel;
	}

	public PassageListPanel getPassageListPanel() {
		return passageListPanel;
	}

	public void setPassageListPanel(PassageListPanel passageListPanel) {
		this.passageListPanel = passageListPanel;
	}

	public PassageEditingPanel getPassageEditingPanel() {
		return passageEditingPanel;
	}

	public void setPassageEditingPanel(PassageEditingPanel passageEditingPanel) {
		this.passageEditingPanel = passageEditingPanel;
	}
}

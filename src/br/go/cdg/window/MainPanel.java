package br.go.cdg.window;

import java.awt.BorderLayout;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import br.go.cdg.model.Passage;

public class MainPanel extends JPanel implements ListSelectionListener {
	
	private static final long serialVersionUID = 831426905746908132L;
	
	private TitlePanel titlePanel = new TitlePanel();
	
	private PassageListPanel passageListPanel;
	
	private PassageEditingPanel passageEditingPanel = new PassageEditingPanel();
	
	public static DefaultListModel<Passage> passageList = new DefaultListModel<Passage>();
	
	public MainPanel() {
		super(new BorderLayout(5, 5));
		
		this.add(titlePanel, BorderLayout.PAGE_START);
		
		passageListPanel = new PassageListPanel(this);
		
		this.add(passageListPanel, BorderLayout.LINE_START);
		
		this.add(passageEditingPanel, BorderLayout.CENTER);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void valueChanged(ListSelectionEvent lse) {
		if(lse.getValueIsAdjusting()) {
			
			JList<Passage> list = (JList<Passage>) lse.getSource();
			
			Passage selected = (Passage)list.getSelectedValue();
			
			passageEditingPanel.setPassage(selected);
		}
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

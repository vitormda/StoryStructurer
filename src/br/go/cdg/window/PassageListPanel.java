package br.go.cdg.window;

import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import br.go.cdg.model.Passage;

/**
 * @author vitor.almeida
 */
public class PassageListPanel extends JPanel {
	
	private static final long serialVersionUID = -7114043901064497657L;
	
	public PassageListPanel(MainPanel mainPanel) {
		super();
		
		setBorder(BorderFactory.createEtchedBorder());
		
		setPreferredSize(new Dimension(200, 600));
		
		JList<Passage> passageJList = new JList<Passage>(MainPanel.passageList);
		passageJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		passageJList.setLayoutOrientation(JList.VERTICAL);
		passageJList.setVisibleRowCount(-1);
		
		passageJList.addListSelectionListener(mainPanel);
		
		JScrollPane passageListScroller = new JScrollPane(passageJList);
		passageListScroller.setPreferredSize(new Dimension(190, 500));
		
		add(passageListScroller);
	}
}

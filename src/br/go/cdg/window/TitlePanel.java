package br.go.cdg.window;

import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * @author vitor.almeida
 */
public class TitlePanel extends JPanel {
	private static final long serialVersionUID = -8399104425383533734L;
	
	public TitlePanel() {
		
		setLayout(null);
		
		setBorder(BorderFactory.createEtchedBorder());
		
		setPreferredSize(new Dimension(800, 30));
		
		JLabel labelSkillName = new JLabel("História:");
		labelSkillName.setBounds(5, 5, 50, 20);
		
		JTextField textSkillName = new JTextField();
		textSkillName.setBounds(60, 5, 730, 20);
		textSkillName.setName("storyName");
		
		add(labelSkillName);
		add(textSkillName);
	}
}

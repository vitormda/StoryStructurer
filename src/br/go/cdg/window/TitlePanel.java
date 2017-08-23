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
	
	private JTextField textStoryName;
	
	public TitlePanel() {
		
		setLayout(null);
		
		setBorder(BorderFactory.createEtchedBorder());
		
		setPreferredSize(new Dimension(800, 30));
		
		JLabel labelStoryName = new JLabel("História:");
		labelStoryName.setBounds(5, 5, 50, 20);
		
		textStoryName = new JTextField();
		textStoryName.setBounds(60, 5, 730, 20);
		textStoryName.setName("storyName");
		
		add(labelStoryName);
		add(textStoryName);
	}

	public JTextField getTextStoryName() {
		return textStoryName;
	}

	public void setTextStoryName(JTextField textStoryName) {
		this.textStoryName = textStoryName;
	}
}

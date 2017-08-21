package br.go.cdg.window;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * @author vitor.almeida
 */
public class FragmentEditingPanel extends JPanel {
	
	private static final long serialVersionUID = -5570869092913621953L;
	
	private String fragment = "";
	private boolean editing = false;
	
	private JPanel editingPanel;
	private JPanel showingPanel;
	private JPanel buttonsPanel;
	
	public FragmentEditingPanel() {
		
		//setBackground(Color.GRAY);
		setBorder(BorderFactory.createEtchedBorder());
		
		setPreferredSize(new Dimension(580, 100));
		
		editingPanel = new JPanel();
		
		
		JTextArea fragmentField = new JTextArea();
		fragmentField.setText(fragment);
		fragmentField.setPreferredSize(new Dimension(500, 80));
		fragmentField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		//fragmentField.addKeyListener();
		
		editingPanel.add(fragmentField);
		editingPanel.setVisible(true);
		
		
		showingPanel = new JPanel();
		showingPanel.setBorder(BorderFactory.createEtchedBorder());
		showingPanel.setBounds(5, 5, 530, 40);
		
		JLabel labeltext = new JLabel(fragment);
		labeltext.setBounds(5, 5, 530, 40);
		
		showingPanel.add(labeltext);
		showingPanel.setVisible(false);
		
		GridLayout gLayout = new GridLayout(3,2);
		gLayout.setHgap(1);
		gLayout.setVgap(1);
		
		
		
		buttonsPanel = new JPanel(gLayout);
		buttonsPanel.setBounds(560, 5, 40, 40);
		
		JButton editButton = new JButton();
		JButton deleteButton = new JButton();
		JButton upButton = new JButton();
		JButton downButton = new JButton();
		JButton okButton = new JButton();
		JButton cancelButton = new JButton();
		
		try {
			Image editImg = ImageIO.read(getClass().getResource("/img/edit.png"));
			Image deleteImg = ImageIO.read(getClass().getResource("/img/bin.png"));
			Image upImg = ImageIO.read(getClass().getResource("/img/up.png"));
			Image downImg = ImageIO.read(getClass().getResource("/img/down.png"));
			Image okImg = ImageIO.read(getClass().getResource("/img/accept.png"));
			Image cancelImg = ImageIO.read(getClass().getResource("/img/cancel.png"));
			
		    editButton.setIcon(new ImageIcon(editImg));
		    deleteButton.setIcon(new ImageIcon(deleteImg));
		    upButton.setIcon(new ImageIcon(upImg));
		    downButton.setIcon(new ImageIcon(downImg));
		    okButton.setIcon(new ImageIcon(okImg));
		    cancelButton.setIcon(new ImageIcon(cancelImg));
		    
		    editButton.setPreferredSize(new Dimension(18, 18));
		    deleteButton.setPreferredSize(new Dimension(18, 18));
		    upButton.setPreferredSize(new Dimension(18, 18));
		    downButton.setPreferredSize(new Dimension(18, 18));
		    okButton.setPreferredSize(new Dimension(18, 18));
		    cancelButton.setPreferredSize(new Dimension(18, 18));
		} catch(Exception e) {
			System.out.println(e.getLocalizedMessage());
		}
		
		buttonsPanel.add(editButton);
		buttonsPanel.add(upButton);
		buttonsPanel.add(deleteButton);
		buttonsPanel.add(downButton);
		buttonsPanel.add(okButton);
		buttonsPanel.add(cancelButton);
		
		//add(showingPanel);
		add(editingPanel);
		add(buttonsPanel);
	}
}

package br.go.cdg.window;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.BevelBorder;

/**
 * @author vitor.almeida
 */
public class FragmentEditingPanel extends JPanel implements ActionListener, KeyListener {
	
	private static final long serialVersionUID = -5570869092913621953L;
	
	private String fragment = "";
	private boolean editing = true;
	private boolean isEnterPressed = false;
	
	private JPanel editingPanel;
	private JPanel showingPanel;
	private JPanel buttonsPanel;
	
	private JPanel odin;
	
	private JLabel labelText;
	private JTextArea fragmentField;
	
	public FragmentEditingPanel(PassageEditingPanel passageEditingPanel) {
		build(passageEditingPanel);		
	}
	
	public FragmentEditingPanel(PassageEditingPanel passageEditingPanel, String fragment) {
		this.fragment = fragment;
		
		build(passageEditingPanel);		
	}
	
	private void build(PassageEditingPanel passageEditingPanel) {
		//565
		
		setLayout(null);
		
		setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		
		setPreferredSize(new Dimension(565, 100));
		
		editingPanel = new JPanel();
		editingPanel.setBounds(2, 2, 510, 96);
		editingPanel.setLayout(null);

		showingPanel = new JPanel();
		showingPanel.setBounds(2, 2, 510, 96);
		showingPanel.setLayout(null);
		
		buttonsPanel = new JPanel(null);
		buttonsPanel.setBounds(515, 2, 40, 96);
		
		fragmentField = new JTextArea();
		fragmentField.setText(fragment);
		fragmentField.setBounds(0, 0, 510, 96);
		fragmentField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		fragmentField.setLineWrap(true);
		fragmentField.addKeyListener(this);
		
		labelText = new JLabel();
		labelText.setPreferredSize(new Dimension(500, 70));
		labelText.setBounds(0, 0, 510, 96);
		
		
		JButton editButton = new JButton();
		JButton deleteButton = new JButton();
		JButton upButton = new JButton();
		JButton downButton = new JButton();
		JButton okButton = new JButton();
		
		try {
			Image editImg = ImageIO.read(getClass().getResource("/img/edit.png"));
			editButton.setName("edit");
			editButton.setIcon(new ImageIcon(editImg));
			editButton.setBounds(2, 2, 18, 18);
			editButton.addActionListener(this);
			editButton.addActionListener(passageEditingPanel);
			
			Image deleteImg = ImageIO.read(getClass().getResource("/img/bin.png"));
			deleteButton.setName("delete");
			deleteButton.setIcon(new ImageIcon(deleteImg));
			deleteButton.setBounds(2, 22, 18, 18);
			deleteButton.addActionListener(passageEditingPanel);
			
			Image upImg = ImageIO.read(getClass().getResource("/img/up.png"));
			upButton.setName("up");
			upButton.setIcon(new ImageIcon(upImg));
			upButton.setBounds(22, 2, 18, 18);
			upButton.addActionListener(passageEditingPanel);
			
			Image downImg = ImageIO.read(getClass().getResource("/img/down.png"));
			downButton.setName("down");
			downButton.setIcon(new ImageIcon(downImg));
			downButton.setBounds(22, 22, 18, 18);
			downButton.addActionListener(passageEditingPanel);
			
			Image okImg = ImageIO.read(getClass().getResource("/img/accept.png"));
			okButton.setName("ok");
			okButton.setIcon(new ImageIcon(okImg));
			okButton.setBounds(2, 42, 18, 18);
			okButton.setBackground(Color.GREEN);
			okButton.addActionListener(this);
		    
		} catch(Exception e) {
			System.out.println(e.getLocalizedMessage());
		}
		
		showingPanel.add(labelText);
		showingPanel.setVisible(false);
		
		editingPanel.add(fragmentField);
		editingPanel.setVisible(true);
		
		buttonsPanel.add(editButton);
		buttonsPanel.add(upButton);
		buttonsPanel.add(deleteButton);
		buttonsPanel.add(downButton);
		buttonsPanel.add(okButton);
		
		add(showingPanel);
		add(editingPanel);
		add(buttonsPanel);
		
		if (!fragment.isEmpty()) {
			refreshEdit();
		}
	}
	
	public void refreshEdit() {
		
		if(editing) {
			fragment = fragmentField.getText();
		}
		
		editing = !editing;
		
		labelText.setText("<html>"+fragment+"</html>");
		fragmentField.setText(fragment);
		
		showingPanel.setVisible(!editing);
		editingPanel.setVisible(editing);
		
		revalidate();
		repaint();
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		if (odin == null) {			
			odin = (JPanel) getParent().getParent().getParent().getParent().getParent();
		}
		
		System.out.println(fragmentField.getText());
		
		JButton clicked = (JButton)ae.getSource();
		
		switch (clicked.getName()) {
			case "ok":
				if (!editing) {
					return;
				}
				break;
			case "edit":
				System.out.println("2");
				if (editing) {
					return;
				}
				break;
			default:
				break;
		}
		
		refreshEdit();
	}
	
	@Override
	public void keyTyped(KeyEvent ke) {
		if (ke.getKeyChar() == '\n') {
			fragmentField.setText(fragmentField.getText().replace('\n', ' ').trim());
			refreshEdit();
			
			if (odin == null) {				
				odin = (JPanel) getParent().getParent().getParent().getParent().getParent();
			}
			
			odin.dispatchEvent(ke);
		}
	}
	
	@Override
	public void keyReleased(KeyEvent ke) {
		if (isEnterPressed) {
			isEnterPressed = false;
		}
	}
	
	@Override
	public void keyPressed(KeyEvent ke) {
		if (ke.getKeyChar() == '\n') {
			isEnterPressed = true;
		}
	}

	public boolean isEditing() {
		return editing;
	}

	public void setEditing(boolean editing) {
		this.editing = editing;
	}

	public JTextArea getFragmentField() {
		return fragmentField;
	}

	public void setFragmentField(JTextArea fragmentField) {
		this.fragmentField = fragmentField;
	}
}

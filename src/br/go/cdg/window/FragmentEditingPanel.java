package br.go.cdg.window;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
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
public class FragmentEditingPanel extends JPanel implements ActionListener {
	
	private static final long serialVersionUID = -5570869092913621953L;
	
	private String fragment = "";
	private boolean editing = true;
	
	private JPanel editingPanel;
	private JPanel showingPanel;
	private JPanel buttonsPanel;
	
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
		
		setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		
		setPreferredSize(new Dimension(580, 100));
		
		editingPanel = new JPanel();
		
		fragmentField = new JTextArea();
		fragmentField.setText(fragment);
		fragmentField.setPreferredSize(new Dimension(500, 80));
		fragmentField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		fragmentField.setLineWrap(true);
		fragmentField.addKeyListener(new KeyListener() {
			
			private boolean isEnterPressed = false;
			
			@Override
			public void keyTyped(KeyEvent ke) {
				if (ke.getKeyChar() == '\n') {
					fragmentField.setText(fragmentField.getText().replace('\n', ' ').trim());
					refreshEdit();
					getParent().dispatchEvent(ke);
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
		});
		
		editingPanel.add(fragmentField);
		editingPanel.setVisible(true);
		
		
		showingPanel = new JPanel();
		
		labelText = new JLabel();
		labelText.setPreferredSize(new Dimension(500, 70));
		labelText.setBounds(5, 5, 500, 70);
		
		
		showingPanel.add(labelText);
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
		
		try {
			Image editImg = ImageIO.read(getClass().getResource("/img/edit.png"));
			editButton.setName("edit");
			editButton.setIcon(new ImageIcon(editImg));
			editButton.setPreferredSize(new Dimension(18, 18));
			editButton.addActionListener(this);
			
			Image deleteImg = ImageIO.read(getClass().getResource("/img/bin.png"));
			deleteButton.setName("delete");
			deleteButton.setIcon(new ImageIcon(deleteImg));
			deleteButton.setPreferredSize(new Dimension(18, 18));
			deleteButton.addActionListener(passageEditingPanel);
			
			Image upImg = ImageIO.read(getClass().getResource("/img/up.png"));
			upButton.setName("up");
			upButton.setIcon(new ImageIcon(upImg));
			upButton.setPreferredSize(new Dimension(18, 18));
			upButton.addActionListener(passageEditingPanel);
			
			Image downImg = ImageIO.read(getClass().getResource("/img/down.png"));
			downButton.setName("down");
			downButton.setIcon(new ImageIcon(downImg));
			downButton.setPreferredSize(new Dimension(18, 18));
			downButton.addActionListener(passageEditingPanel);
			
			Image okImg = ImageIO.read(getClass().getResource("/img/accept.png"));
			okButton.setName("ok");
			okButton.setIcon(new ImageIcon(okImg));
			okButton.setPreferredSize(new Dimension(18, 18));
			okButton.setBackground(Color.GREEN);
			okButton.addActionListener(this);
		    
		} catch(Exception e) {
			System.out.println(e.getLocalizedMessage());
		}
		
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
	
	private void refreshEdit() {
		
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
		JButton clicked = (JButton)ae.getSource();
		
		switch (clicked.getName()) {
			case "ok":
				if (!editing) {
					return;
				}
				break;
			case "edit":
				if (editing) {
					return;
				}
				break;
			default:
				break;
		}
		
		refreshEdit();
	}
}

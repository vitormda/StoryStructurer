package br.go.cdg.window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import br.go.cdg.model.Link;
import br.go.cdg.utils.ButtonGenerator;

public class HookEditingPanel extends PassageHolder implements ActionListener {
	
	private static final long serialVersionUID = -6026273164854385761L;
	
	private Link link;
	
	private JTextField txNome;
	private JTextField txId;
	
	private JPanel editingButtons;
	private JPanel showingButtons;
	
	private boolean editing = true;
	
	public HookEditingPanel(PassageEditingPanel passageEditingPanel) {
		build(passageEditingPanel);		
	}
	
	public HookEditingPanel(PassageEditingPanel passageEditingPanel, Link link) {
		this.link = link;
		
		build(passageEditingPanel);		
	}
	
	private void build(PassageEditingPanel passageEditingPanel) {
		setLayout(null);
		
		JLabel labelId = new JLabel("Id: ");
		labelId.setBounds(5, 2, 20, 20);
		
		txId = new JTextField();
		txId.setText(String.valueOf(link.getId()));
		txId.setBounds(25, 2, 30, 20);
		
		
		JLabel labelNome = new JLabel("Passagem: ");
		labelNome.setBounds(75, 2, 75, 20);
		
		txNome = new JTextField();
		txNome.setText(link.getText());
		txNome.setBounds(150, 2, 375, 20);
		
		editingButtons = new JPanel();
		editingButtons.setLayout(null);
		editingButtons.setBounds(527, 2, 40, 20);
		editingButtons.setVisible(true);
		
		showingButtons = new JPanel();
		showingButtons.setLayout(null);
		showingButtons.setBounds(527, 2, 40, 20);
		showingButtons.setVisible(false);

		JButton acceptButton = ButtonGenerator.getAcceptButton();
		acceptButton.setBounds(0, 0, 18, 18);
		acceptButton.setName("acceptHook");
		acceptButton.addActionListener(this);
		acceptButton.addActionListener(passageEditingPanel);
		
		JButton cancelButton = ButtonGenerator.getCancelButton();
		cancelButton.setBounds(19, 0, 18, 18);
		cancelButton.setName("cancelHook");
		cancelButton.addActionListener(passageEditingPanel);
		
		JButton editButton = ButtonGenerator.getEditButton();
		editButton.setBounds(0, 0, 18, 18);
		editButton.setName("editHook");
		editButton.addActionListener(this);
		editButton.addActionListener(passageEditingPanel);
		
		JButton deleteButton = ButtonGenerator.getDeleteButton();
		deleteButton.setBounds(19, 0, 18, 18);
		deleteButton.setName("deleteHook");
		deleteButton.addActionListener(passageEditingPanel);
		
		editingButtons.add(acceptButton);
		editingButtons.add(cancelButton);
		
		showingButtons.add(editButton);
		showingButtons.add(deleteButton);
		
		add(labelId);
		add(txId);
		add(labelNome);
		add(txNome);
		add(editingButtons);
		add(showingButtons);
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		switch(((JButton)ae.getSource()).getName()) {
			case "acceptHook":
				if (!editing) {
					return;
				}
				break;
			case "editHook":
				if (editing) {
					return;
				}
				break;
			default:
				break;
		}
		
		refreshEdit();
	}
	
	public void refreshEdit() {
		
		if(editing) {
			link.setId(Integer.parseInt(txId.getText()));
			link.setText(txNome.getText());
		}
		
		editing = !editing;
		
		showingButtons.setVisible(!editing);
		editingButtons.setVisible(editing);
		
		txId.setEditable(editing);
		txNome.setEditable(editing);
		
		revalidate();
		repaint();
	}
	
	public Link getLink() {
		link.setId(Integer.parseInt(txId.getText()));
		link.setText(txNome.getText());
		
		return link;
	}

	public boolean isEditing() {
		return editing;
	}

	public void setEditing(boolean editing) {
		this.editing = editing;
	}
}

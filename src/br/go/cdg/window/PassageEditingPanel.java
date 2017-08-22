package br.go.cdg.window;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import br.go.cdg.model.Passage;

/**
 * @author vitor.almeida
 */
public class PassageEditingPanel extends JPanel implements ActionListener, KeyListener {
	
	private static final long serialVersionUID = -5459688383379831692L;
	
	private ArrayList<String> fragments = new ArrayList<String>();
	
	private ArrayList<FragmentEditingPanel> fragmentPanels = new ArrayList<FragmentEditingPanel>();
	
	private Passage passage = new Passage();
	
	private JTextField txNome;
	private JTextField txId;
	
	private JPanel passageHolder;
	private JPanel fragmentHolder;
	private JPanel hookHolder;
	
	public PassageEditingPanel() {
		buildTop();
	}
	
	public PassageEditingPanel(Passage passage) {
		this.passage = passage;
		
		buildTop();
	}
	
	private void buildTop() {
		setLayout(null);
		
		setBorder(BorderFactory.createEtchedBorder());
		
		JLabel labelId = new JLabel("Id: ");
		labelId.setBounds(5, 5, 20, 20);
		
		txId = new JTextField();
		txId.setText(String.valueOf(passage.getId()));
		txId.setBounds(25, 5, 30, 20);
		
		
		JLabel labelNome = new JLabel("Passagem: ");
		labelNome.setBounds(75, 5, 75, 20);
		
		txNome = new JTextField();
		txNome.setText(passage.getName());
		txNome.setBounds(150, 5, 435, 20);
		
		add(labelId);
		add(txId);
		add(labelNome);
		add(txNome);
		
		buildScrollArea();
	}
	
	private void buildScrollArea() {
		int componentWidth = 565;
		
		passageHolder = new JPanel();
		passageHolder.setLayout(null);
		
		fragmentHolder = new JPanel();
		fragmentHolder.setLayout(null);
		fragmentHolder.setBounds(0, 0, componentWidth, 200);
		fragmentHolder.setBackground(Color.GREEN);
		
		hookHolder = new JPanel();
		hookHolder.setLayout(null);
		hookHolder.setBounds(0, fragmentHolder.getHeight(), componentWidth, 100);
		hookHolder.setBackground(Color.BLUE);
		
		passageHolder.add(fragmentHolder);
		passageHolder.add(hookHolder);
		
		JScrollPane scrollPane = new JScrollPane(passageHolder, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(5, 25, 580, 485);
		
		add(scrollPane);
		
		buildInnerComponents();
	}
	
	private void buildInnerComponents() {
		if (fragments.isEmpty()) {
			FragmentEditingPanel frag = new FragmentEditingPanel(this);
			
			frag.setBounds(0,0,565,100);
			
			fragmentPanels.add(frag);
			
			fragmentHolder.add(frag);
		} else {
			for (String fragment : passage.getText()) {
				add(new FragmentEditingPanel(this, fragment));
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		JButton clicked = (JButton)ae.getSource();
		
		switch (clicked.getName()) {
			case "delete":
				
				break;
			case "up":
				
				break;
			case "down":
				
				break;
			default:
				break;
		}
	}

	@Override
	public void keyPressed(KeyEvent ke) {
		FragmentEditingPanel frag = new FragmentEditingPanel(this);
		
		frag.setBounds(5, (fragmentPanels.size()*100)+30, 500,80);
		
		fragmentPanels.add(frag);
		
		add(frag);
	}

	@Override
	public void keyReleased(KeyEvent ke) {
		return;
	}

	@Override
	public void keyTyped(KeyEvent ke) {
		return;
	}
}

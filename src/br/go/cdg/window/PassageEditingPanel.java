package br.go.cdg.window;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
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

import br.go.cdg.model.Link;
import br.go.cdg.model.Passage;
import br.go.cdg.utils.ButtonGenerator;

/**
 * @author vitor.almeida
 */
public class PassageEditingPanel extends JPanel implements ActionListener, KeyListener {
	
	private static final long serialVersionUID = -5459688383379831692L;
	
	private Passage passage = new Passage();
	
	private JTextField txNome;
	private JTextField txId;
	
	private PassageHolder passageHolder;
	private PassageHolder fragmentHolder;
	private PassageHolder hookHolder;
	
	private JScrollPane scrollPane;
	
	private JButton addHook;
	
	private int fragmentComponentWidth = 565;
	
	public PassageEditingPanel() {
		buildTop();
	}
	
	private void buildTop() {
		setLayout(null);
		
		addKeyListener(this);
		
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
		txNome.setBounds(150, 5, 415, 20);

		JButton okButton = ButtonGenerator.getAcceptButton();
		okButton.setName("editFinish");
		okButton.setBounds(567, 5, 18, 18);
		okButton.addActionListener(this);
		
		add(labelId);
		add(txId);
		add(labelNome);
		add(txNome);
		add(okButton);
		
		buildScrollArea();
	}
	
	private void buildScrollArea() {
		passageHolder = new PassageHolder();
		
		fragmentHolder = new PassageHolder();
		fragmentHolder.setLayout(null);
		fragmentHolder.setBackground(Color.GREEN);
		
		hookHolder = new PassageHolder();
		hookHolder.setLayout(null);
		hookHolder.setBackground(Color.BLUE);
		
		addHook = ButtonGenerator.getAddButton();
		addHook.setBounds(0, fragmentHolder.getHeight() + hookHolder.getHeight(), fragmentComponentWidth, 18);
		addHook.setName("addNewHook");
		addHook.addActionListener(this);
		
		passageHolder.add(fragmentHolder);
		passageHolder.add(hookHolder);
		passageHolder.add(addHook);
		
		scrollPane = new JScrollPane(passageHolder, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(0, 25, 585, 485);
		
		scrollPane.setWheelScrollingEnabled(true);
		
		add(scrollPane);
		
		buildInnerComponents();
	}
	
	private void buildInnerComponents() {
		if (passage.getText().isEmpty()) {
			addNewFragment("");
		} else {
			for (String fragment : passage.getText()) {
				addNewFragment(fragment);
			}
		}
		
		if (!passage.getLinks().isEmpty()) {
			for (Link link : passage.getLinks()) {
				addNewHook(link);
			}
		}
	}
	
	private void addNewFragment(String fragment) {
		FragmentEditingPanel frag;
		
		if (fragment.equals("")) {
			frag = new FragmentEditingPanel(this);
		} else {
			frag = new FragmentEditingPanel(this, fragment);
		}
		
		frag.setBounds(0, (fragmentHolder.getComponentCount()*100), 565, 100);
		
		fragmentHolder.add(frag);
		
		update();
		
		frag.getFragmentField().requestFocusInWindow();
	}
	
	private void addNewHook(Link link) {
		HookEditingPanel hook;
		
		if (link == null) {
			hook = new HookEditingPanel(this, new Link());
		} else {
			hook = new HookEditingPanel(this, link);
		}
		
		hook.setBounds(0, (hookHolder.getComponentCount()*24), 565, 24);
		
		hookHolder.add(hook);
		
		update();
	}
	
	private void update() {
		fragmentHolder.setBounds(0, 0, fragmentComponentWidth, fragmentHolder.getComponentCount()*100);
		hookHolder.setBounds(0, fragmentHolder.getHeight(), fragmentComponentWidth, hookHolder.getComponentCount()*24);
		addHook.setBounds(0, fragmentHolder.getHeight() + hookHolder.getHeight(), fragmentComponentWidth, 18);
		passageHolder.setBounds(0, 0, fragmentComponentWidth, addHook.getY() + addHook.getHeight() + 40);
		
		passageHolder.scrollRectToVisible(new Rectangle(0, passageHolder.getHeight(), 1, 1));
		
		revalidate();
		repaint();
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		JButton clicked = (JButton)ae.getSource();
		
		switch (clicked.getName()) {
			case "deleteFragment":
				FragmentEditingPanel fragmentToBeDeleted = (FragmentEditingPanel) clicked.getParent().getParent();
				
				Point posFragmentToBeDeleted = new Point(fragmentToBeDeleted.getX(), fragmentToBeDeleted.getY());
				
				boolean isLast = posFragmentToBeDeleted.y == fragmentHolder.getHeight() - 100;
				
				fragmentHolder.remove(fragmentToBeDeleted);
				
				if(!isLast) {
					for (int i = 0; i < fragmentHolder.getComponentCount(); i++) {
						if (fragmentHolder.getComponent(i).getY() > posFragmentToBeDeleted.y) {
							fragmentHolder.getComponent(i).setLocation(fragmentHolder.getComponent(i).getX(), fragmentHolder.getComponent(i).getY() - 100);
						}
					}
				}
				
				update();
				
				break;
			case "upFragment":
				FragmentEditingPanel fragmentGoingUp = (FragmentEditingPanel) clicked.getParent().getParent();
				
				if (fragmentGoingUp.getY() == 0) {
					return;
				}
				
				Point posFragmentGoingUp = new Point(fragmentGoingUp.getX(), fragmentGoingUp.getY());
				
				FragmentEditingPanel upperFep = (FragmentEditingPanel) fragmentHolder.getComponentAt(posFragmentGoingUp.x, posFragmentGoingUp.y-100);
				
				fragmentGoingUp.setLocation(upperFep.getLocation());
				upperFep.setLocation(posFragmentGoingUp);
				
				break;
			case "downFragment":
				FragmentEditingPanel fragmentGoingDown = (FragmentEditingPanel) clicked.getParent().getParent();
				
				if (fragmentGoingDown.getY() == fragmentHolder.getHeight() - 100) {
					return;
				}
				
				Point posFragmentGoingDown = new Point(fragmentGoingDown.getX(), fragmentGoingDown.getY());
				
				FragmentEditingPanel lowerFep = (FragmentEditingPanel) fragmentHolder.getComponentAt(posFragmentGoingDown.x, posFragmentGoingDown.y+100);
				
				fragmentGoingDown.setLocation(lowerFep.getLocation());
				lowerFep.setLocation(posFragmentGoingDown);
				break;
			case "editFragment":
				for (int i = 0; i < fragmentHolder.getComponentCount(); i++) {
					FragmentEditingPanel frag = (FragmentEditingPanel) fragmentHolder.getComponent(i);
					
					if (frag.isEditing()) {						
						frag.refreshEdit();
					}
				}
				break;
			case "addNewHook":
				for (int i = 0; i < hookHolder.getComponentCount(); i++) {
					HookEditingPanel hook = (HookEditingPanel) hookHolder.getComponent(i);
					
					if (hook.isEditing()) {						
						hook.refreshEdit();
					}
				}
				addNewHook(new Link());
				break;
			case "cancelHook":
				JPanel container = (JPanel) clicked.getParent().getParent();
				
				hookHolder.remove(container);
				
				update();
				
				break;
			case "editHook":
				for (int i = 0; i < hookHolder.getComponentCount(); i++) {
					HookEditingPanel hook = (HookEditingPanel) hookHolder.getComponent(i);
					
					if (hook.isEditing()) {						
						hook.refreshEdit();
					}
				}
				break;
			case "deleteHook":
				
				break;
			case "editFinish":
				passage.setId(Integer.parseInt(txId.getText()));
				passage.setName(txNome.getText());
				
				ArrayList<String> text = new ArrayList<String>();
				
				for (int i = 0; i < fragmentHolder.getComponentCount(); i++) {
					if (!((FragmentEditingPanel)fragmentHolder.getComponent(i)).getFragmentField().getText().isEmpty()) {						
						text.add(((FragmentEditingPanel)fragmentHolder.getComponent(i)).getFragmentField().getText());
					}
				}
				
				passage.setText(text);
				
				for (int i = 0; i < hookHolder.getComponentCount(); i++) {
					Link link = ((HookEditingPanel)hookHolder.getComponent(i)).getLink();
						
					if (!link.getText().isEmpty()) {
						passage.getLinks().add(link);
					}
				}
				
				insertIntoMainList();
				break;
			default:
				break;
		}
	}

	private void insertIntoMainList() {
		boolean found = false;
		
		for (int i = 0; i < MainPanel.passageList.size(); i++) {
			if (MainPanel.passageList.get(i).getId() == passage.getId()) {
				MainPanel.passageList.get(i).setName(passage.getName());
				MainPanel.passageList.get(i).setText(passage.getText());
				MainPanel.passageList.get(i).setLinks(passage.getLinks());
				
				found = true;
			}
		}
		
		if (!found) {
			MainPanel.passageList.addElement(passage);
		}
		
		checkLinks();
	}

	private void checkLinks() {
		for (int i = 0; i < passage.getLinks().size(); i++) {
			boolean found = false;
			
			for (int j = 0; j < MainPanel.passageList.size(); j++) {
				if (MainPanel.passageList.get(j).getId() == passage.getLinks().get(i).getId()) {
					found = true;
					break;
				}
			}
			
			if (!found) {
				MainPanel.passageList.addElement(new Passage(passage.getLinks().get(i).getId(), passage.getLinks().get(i).getText()));
			}
		}
		
		setPassage(new Passage());
	}
	
	public void setPassage(Passage passage) {
		removeAll();
		
		Passage passageTemp = new Passage();
		
		passageTemp.setId(passage.getId());
		passageTemp.setName(passage.getName());
		
		for(int i = 0; i < passage.getText().size(); i++) {
			passageTemp.getText().add(passage.getText().get(i));
		}
		
		for(int i = 0; i < passage.getLinks().size(); i++) {
			Link link = new Link();
			
			link.setId(passage.getLinks().get(i).getId());
			link.setText(passage.getLinks().get(i).getText());
			
			passageTemp.getLinks().add(link);
		}
		
		this.passage = passageTemp;
		
		buildTop();
	}

	@Override
	public void keyPressed(KeyEvent ke) {
		return;
	}

	@Override
	public void keyReleased(KeyEvent ke) {
		return;
	}

	@Override
	public void keyTyped(KeyEvent ke) {
		
		addNewFragment("");
		
		return;
	}
}

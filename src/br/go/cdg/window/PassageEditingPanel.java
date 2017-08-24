package br.go.cdg.window;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
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
	
	private PassageHolder passageHolder;
	private PassageHolder fragmentHolder;
	private PassageHolder hookHolder;
	
	private JScrollPane scrollPane;
	
	private int fragmentComponentWidth = 565;
	
	public PassageEditingPanel() {
		buildTop();
	}
	
	public PassageEditingPanel(Passage passage) {
		this.passage = passage;
		
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

		JButton okButton = new JButton();
		Image okImg;
		try {
			okImg = ImageIO.read(getClass().getResource("/img/accept.png"));
			okButton.setIcon(new ImageIcon(okImg));
		} catch (IOException e) {
			e.printStackTrace();
		}
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
		
		passageHolder.add(fragmentHolder);
		passageHolder.add(hookHolder);
		
		scrollPane = new JScrollPane(passageHolder, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(0, 25, 585, 485);
		
		scrollPane.setWheelScrollingEnabled(true);
		
		add(scrollPane);
		
		buildInnerComponents();
	}
	
	private void buildInnerComponents() {
		if (fragments.isEmpty()) {
			addNewFragment("");
		} else {
			for (String fragment : passage.getText()) {
				addNewFragment(fragment);
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
		
		frag.setBounds(0, (fragmentPanels.size()*100), 565, 100);
		
		fragmentPanels.add(frag);
		
		fragmentHolder.add(frag);
		
		fragmentHolder.setBounds(0, 0, fragmentComponentWidth, fragmentHolder.getComponentCount()*100);
		hookHolder.setBounds(0, fragmentHolder.getHeight(), fragmentComponentWidth, 100);
		passageHolder.setBounds(0, 0, fragmentComponentWidth, fragmentHolder.getHeight() + hookHolder.getHeight());
		
		revalidate();
		repaint();
		
		JScrollBar vertical = scrollPane.getVerticalScrollBar();
		vertical.setValue(vertical.getMaximum());
		
		frag.getFragmentField().requestFocusInWindow();
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
			case "edit":
				System.out.println("1");
				for (int i = 0; i < fragmentHolder.getComponentCount(); i++) {
					FragmentEditingPanel frag = (FragmentEditingPanel) fragmentHolder.getComponent(i);
					
					if (frag.isEditing()) {						
						frag.refreshEdit();
					}
				}
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
				
				System.out.println(passage.toString());
				break;
			default:
				break;
		}
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

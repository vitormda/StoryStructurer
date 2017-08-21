package br.go.cdg.window;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import br.go.cdg.utils.Globals;

/**
 * @author vitor.almeida
 */
public class Window extends JFrame implements ActionListener {
	private static final long serialVersionUID = 6919893039373649602L;
	
	private MainPanel main;
	private MenuBar menu;
	
	private String title;

	public Window(String title) {
		super(title);
		
		this.title = title;
		
		setSize(800, 600);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		menu = new MenuBar();
		
		main = new MainPanel();
		
		add(main, BorderLayout.CENTER);
		setJMenuBar(menu);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		JMenuItem pressed = (JMenuItem) arg0.getSource();
		
		switch(pressed.getName()) {
			case Globals.ACTION_NEW_STORY:
				setTitle(this.title.concat(" - Renovando"));
				//if (newHero()) {
					setTitle(this.title);
				//}
				break;
			case Globals.ACTION_OPEN:
				setTitle(this.title.concat(" - Abrindo"));
				//if (open()) {
					setTitle(this.title);
				//}
				break;
			case Globals.ACTION_SAVE:
				setTitle(this.title.concat(" - Salvando"));
				//if (save()) {
					setTitle(this.title);
				//}
				break;
			case Globals.ACTION_EXIT:
				exit();
				break;
			default:
				break;
		}
	}

	private void exit() {
		int response = JOptionPane.showConfirmDialog(null, "Dados não salvos serão perdidos.", "Tem certeza?", JOptionPane.YES_NO_OPTION);
		
		if (response == 0) { //Sim
			setVisible(false);
			dispose();
		}
	}
}

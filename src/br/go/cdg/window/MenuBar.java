package br.go.cdg.window;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import br.go.cdg.utils.Globals;

public class MenuBar extends JMenuBar {
	private static final long serialVersionUID = -3858273851508059613L;

	private JMenu menu;
	
	private JMenuItem newHero;
	private JMenuItem open;
	private JMenuItem save;
	private JMenuItem exit;
	
	private Window window;
	
	public MenuBar(Window window) {
		super();
		
		this.window = window;
		
		getMenu().add(getNewHero());
		getMenu().add(getOpen());
		getMenu().add(getSave());
		getMenu().add(getExit());
		
		add(getMenu());
	}

	public JMenu getMenu() {
		if (menu == null) {
			menu = new JMenu();
			menu.setText("Arquivo");
		}
		return menu;
	}

	public void setMenu(JMenu menu) {
		this.menu = menu;
	}

	public JMenuItem getNewHero() {
		if (newHero == null) {
			newHero = new JMenuItem();
			newHero.setName(Globals.ACTION_NEW_STORY);
			newHero.setText("Novo");
			newHero.addActionListener(window);
		}
		return newHero;
	}

	public void setNewHero(JMenuItem newHero) {
		this.newHero = newHero;
	}

	public JMenuItem getOpen() {
		if (open == null) {
			open = new JMenuItem();
			open.setName(Globals.ACTION_OPEN);
			open.setText("Abrir");
			open.addActionListener(window);
		}
		return open;
	}

	public void setOpen(JMenuItem open) {
		this.open = open;
	}

	public JMenuItem getSave() {
		if (save == null) {
			save = new JMenuItem();
			save.setName(Globals.ACTION_SAVE);
			save.setText("Salvar");
			save.addActionListener(window);
		}
		return save;
	}

	public void setSave(JMenuItem save) {
		this.save = save;
	}

	public JMenuItem getExit() {
		if (exit == null) {
			exit = new JMenuItem();
			exit.setName(Globals.ACTION_EXIT);
			exit.setText("Sair");
			exit.addActionListener(window);
		}
		return exit;
	}

	public void setExit(JMenuItem exit) {
		this.exit = exit;
	}
}

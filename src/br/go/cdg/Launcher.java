package br.go.cdg;

import javax.swing.SwingUtilities;

import br.go.cdg.Launcher;
import br.go.cdg.window.Window;

/**
 * @author vitor.almeida
 */
public class Launcher {
	public Launcher() {
		Window win = new Window("Story Structurer");
	
		win.setVisible(true);
	}

	public static void main(String[] args) {
	    Runnable r = new Runnable() {
	        @Override
	        public void run() {
	            new Launcher();
	        }
	    };
	    SwingUtilities.invokeLater(r);
	}
}

package br.go.cdg.window;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import br.go.cdg.model.Passage;
import br.go.cdg.utils.Globals;

/**
 * @author vitor.almeida
 */
public class Window extends JFrame implements ActionListener {
	private static final long serialVersionUID = 6919893039373649602L;
	
	private static Class<? extends Window> window;
	
	private MainPanel main;
	private MenuBar menu;

	public Window(String title) {
		super(title);
		
		window = getClass();
		
		setSize(800, 600);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		menu = new MenuBar(this);
		
		main = new MainPanel();
		
		add(main, BorderLayout.CENTER);
		setJMenuBar(menu);
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		JMenuItem pressed = (JMenuItem) ae.getSource();
		
		switch(pressed.getName()) {
			case Globals.ACTION_NEW_STORY:
				newStory();
				break;
			case Globals.ACTION_OPEN:
				open();
				break;
			case Globals.ACTION_SAVE:
				save();
				break;
			case Globals.ACTION_EXIT:
				exit();
				break;
			default:
				break;
		}
	}

	private void newStory() {
		
	}
	
	private void open() {
		
		JFileChooser fileChooser = new JFileChooser();
		
		int option = fileChooser.showOpenDialog(this);
		
		if (option == JFileChooser.APPROVE_OPTION) {
			File file = fileChooser.getSelectedFile();
			
			main.getTitlePanel().getTextStoryName().setText(file.getName().replaceAll(".json", ""));
			
			try {
				BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
				
				JSONParser jsonp = new JSONParser();
				
				JSONObject jsono = (JSONObject) jsonp.parse(br);
				
				JSONArray jsona = (JSONArray) jsono.get("passages");
				
				for (int i = 0; i < jsona.size(); i++) {
					Passage pass = new Passage((JSONObject) jsona.get(i));
					
					MainPanel.passageList.addElement(pass);
				}
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}

	private void save() {
		String name = main.getTitlePanel().getTextStoryName().getText();
		
		String json = "{ \"passages\" : [";
		
		for (int i = 0; i < MainPanel.passageList.getSize(); i++) {			
			json = json.concat(MainPanel.passageList.getElementAt(i).getJson());
			
			if (i != MainPanel.passageList.getSize() - 1) {
				json = json.concat(", ");
			}
		}
		
		json = json.concat("]}");
		
		JFileChooser fileChooser = new JFileChooser();
		
		int option = fileChooser.showSaveDialog(this);
		
		if (option == JFileChooser.APPROVE_OPTION) {
			File tempFile = fileChooser.getSelectedFile();
			
			String fileName = tempFile.getPath().replaceAll(tempFile.getName(), "").concat(name).concat(".json");
			
			File file = new File(fileName);
			
			try {
				BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8));
				
				bufferedWriter.write(json);
				bufferedWriter.flush();
				bufferedWriter.close();
			} catch(Exception e) {
				System.out.println(e);
			}
		}
	}

	private void exit() {
		int response = JOptionPane.showConfirmDialog(null, "Dados não salvos serão perdidos.", "Tem certeza?", JOptionPane.YES_NO_OPTION);
		
		if (response == 0) { //Sim
			setVisible(false);
			dispose();
		}
	}
	
	public static URL getResource(String resourceName) {
		return window.getResource(resourceName);
	}
}

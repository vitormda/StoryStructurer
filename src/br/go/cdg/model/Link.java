package br.go.cdg.model;

/**
 * @author vitor.almeida
 */
public class Link {
    private int id;
    private String text;
    private boolean clicked = false;

    public Link(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean getClicked() {
        return clicked;
    }

    public void setClicked(boolean clicked) {
        this.clicked = clicked;
    }
    
    @Override
    public String toString() {
    	return "{id: "+id+", text: "+text+", clicked: false}";
    }
}

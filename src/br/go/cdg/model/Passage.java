package br.go.cdg.model;

import java.util.ArrayList;

/**
 * @author vitor.almeida
 */

public class Passage {
    private String name;
    private int id;
    private String text;
    private boolean current = true;

    private ArrayList<Link> links = new ArrayList<Link>();

    public Passage(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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

    public ArrayList<Link> getLinks() {
        return links;
    }

    public void setLinks(ArrayList<Link> links) {
        this.links = links;
    }

    public boolean isCurrent() {
        return current;
    }

    public void setCurrent(boolean current) {
        this.current = current;
    }
}

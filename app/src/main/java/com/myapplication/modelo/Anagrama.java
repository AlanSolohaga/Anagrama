package com.myapplication.modelo;

public class Anagrama {
    private String anagrama;
    private String frase;
    private int id;

    public Anagrama(String anagrama, String frase, int id) {
        this.anagrama = anagrama;
        this.frase = frase;
        this.id = id;
    }

    public String getAnagrama() {
        return anagrama;
    }

    public void setAnagrama(String anagrama) {
        this.anagrama = anagrama;
    }

    public String getFrase() {
        return frase;
    }

    public void setFrase(String frase) {
        this.frase = frase;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

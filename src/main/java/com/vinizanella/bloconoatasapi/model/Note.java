package com.vinizanella.bloconoatasapi.model;

public class Note {

    private Long id; // Identificador único da nota
    private String content; // O texto da nota

    // Construtor
    public Note(Long id, String content) {
        this.id = id;
        this.content = content;
    }

    // Getters: Métodos para acessar os valores dos campos privados
    public Long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    // Setters: Métodos para alterar os valores
    public void setId(Long id) {
        this.id = id;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
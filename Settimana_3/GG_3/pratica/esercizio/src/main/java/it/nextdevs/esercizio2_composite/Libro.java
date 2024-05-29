package it.nextdevs.esercizio2_composite;

import java.util.ArrayList;
import java.util.List;

public class Libro implements Elemento{

    private String nome;
    private double prezzo;

    private List<String> autori = new ArrayList<>();
    private List<Elemento> elementi = new ArrayList<>();

    public Libro(double prezzo, String nome) {
        this.prezzo = prezzo;
        this.nome = nome;
    }

    public double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(double prezzo) {
        this.prezzo = prezzo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String getName() {
        return "";
    }

    @Override
    public int getPagine() {
        return elementi.stream().mapToInt(Elemento::getPagine).sum();
    }

    @Override
    public void stampa() {

    }

    public void addAutori(String autore) {
        autori.add(autore);
    }

    public void add(Elemento elemento){
        elementi.add(elemento);
    }


}

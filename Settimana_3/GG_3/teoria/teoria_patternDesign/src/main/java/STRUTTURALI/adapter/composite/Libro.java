package STRUTTURALI.adapter.composite;

public class Libro implements Prodotto{

    private String nome;
    private double prezzo;
    private double peso;


    public Libro(String nome, double prezzo, double peso) {
        this.nome = nome;
        this.prezzo = prezzo;
        this.peso = peso;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setPrezzo(double prezzo) {
        this.prezzo = prezzo;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public double getPrezzo() {
        return prezzo;
    }

    @Override
    public double getPeso() {
        return peso;
    }
}

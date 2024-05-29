package STRUTTURALI.adapter.composite;

import java.util.ArrayList;
import java.util.List;

public class Scatolo implements Prodotto {

    private List<Prodotto> prodotti = new ArrayList<>();
    private String nome;

    public Scatolo(String nome) {
        this.nome = nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public double getPrezzo() {
        return prodotti.stream().mapToDouble(Prodotto::getPrezzo).sum();
    }

    @Override
    public double getPeso() {
        return prodotti.stream().mapToDouble(Prodotto::getPeso).sum();
    }

    public void aggiungiProdotto(Prodotto prodotto){
        prodotti.add(prodotto);
    }

    public void rimuoviProdotto(Prodotto prodotto){
        prodotti.remove(prodotto);
    }

}

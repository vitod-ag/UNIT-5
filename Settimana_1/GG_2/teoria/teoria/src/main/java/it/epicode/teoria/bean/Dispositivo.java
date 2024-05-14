package it.epicode.teoria.bean;

import lombok.Data;

@Data
public abstract class Dispositivo {
    private String nome;
    private String marca;

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}

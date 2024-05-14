package it.epicode.teoria.bean;

import lombok.Data;

import java.util.List;

@Data
public class Studente {
    private String nome;
    private String cognome;
    private String indirizzo;

    private List<Dispositivo> dispositivi;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public List<Dispositivo> getDispositivi() {
        return dispositivi;
    }

    public void setDispositivi(List<Dispositivo> dispositivi) {
        this.dispositivi = dispositivi;
    }


}

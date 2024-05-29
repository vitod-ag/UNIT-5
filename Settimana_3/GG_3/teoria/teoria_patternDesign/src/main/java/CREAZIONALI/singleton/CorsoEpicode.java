package CREAZIONALI.singleton;

import java.time.LocalDate;

public class CorsoEpicode {

    private String nome;
    private LocalDate localDate;
    private int numeroMaxPartecipanti;
    private String sede;

    private static CorsoEpicode corsoEpicode;  // variabile che serve per contenere l'oggetto che andremo a creare

    private CorsoEpicode(String nome, LocalDate localDate, int numeroMaxPartecipanti, String sede) {
        this.nome = nome;
        this.localDate = localDate;
        this.numeroMaxPartecipanti = numeroMaxPartecipanti;
        this.sede = sede;
    }

    // metodo static per richiamare il costruttore che ho reso private
    public static CorsoEpicode getInstance() {
        if ( corsoEpicode == null ) {
            corsoEpicode = new CorsoEpicode("Back_End", LocalDate.now(), 100, "Roma");
        }
        return corsoEpicode;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    public int getNumeroMaxPartecipanti() {
        return numeroMaxPartecipanti;
    }

    public void setNumeroMaxPartecipanti(int numeroMaxPartecipanti) {
        this.numeroMaxPartecipanti = numeroMaxPartecipanti;
    }

    public String getSede() {
        return sede;
    }

    public void setSede(String sede) {
        this.sede = sede;
    }

    @Override
    public String toString() {
        return "CorsoEpicode{" +
                "nome='" + nome + '\'' +
                ", localDate=" + localDate +
                ", numeroMaxPartecipanti=" + numeroMaxPartecipanti +
                ", sede='" + sede + '\'' +
                '}';
    }
}

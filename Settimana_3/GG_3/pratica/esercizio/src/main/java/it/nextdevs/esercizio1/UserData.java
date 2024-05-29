package it.nextdevs.esercizio1;

public class UserData {
    private String nomeCompleto;
    private int eta;
    public void getData(DataSource ds) {
        nomeCompleto = ds.getNomeCompleto ();
        eta = ds.getEta();
    }

    // Getter per visualizzare i dati
    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public int getEta() {
        return eta;
    }

}

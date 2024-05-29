package it.nextdevs.esercizio1_adapter;

import java.util.Date;

public class InfoAdapter implements DataSource{
    private Info info;   // incapsuliamo qui dentro l'oggetto Info'

    //costruttore con la sola variabile info
    public InfoAdapter(Info info) {
        this.info = info;
    }

    @Override
    public String getNomeCompleto() {
        return info.getNome() + " " + info.getCognome();
    }

    @Override
    public int getEta() {
        Date dataOggi = new Date();
        Date dataNascita = info.getDataDiNascita();
        return (int)((dataOggi.getTime()-dataNascita.getTime())/(1000L * 60 * 60 * 24 * 365));
    }

}

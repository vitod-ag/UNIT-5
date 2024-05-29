package it.nextdevs.esercizio1;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class InfoAdapter implements DataSource{
    private Info info;

    //costruttore con la sola variabile info
    public InfoAdapter(Info info) {
        this.info = info;
    }

    @Override
    public String getNomeCompleto() {
        return info.getNome() + " " + info.getCognome();
    }

    @Override
    public String getEta() {
        return calcolaEta(info.getDataDiNascita()) + " anni";
    }

    //metodo di calcolaEta a partire dalla data di nascita
    private int calcolaEta(Date dataDiNascita) {
        if (dataDiNascita == null) {
            return 0;
        }
        // Converte Date in LocalDate
        LocalDate dataNascitaLocal = dataDiNascita.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate oggi = LocalDate.now();


        int eta = oggi.getYear() - dataNascitaLocal.getYear();

        // Se la data di nascita non è ancora arrivata quest'anno, sottrai 1 dall'età
        if (oggi.getMonthValue() < dataNascitaLocal.getMonthValue() ||
                (oggi.getMonthValue() == dataNascitaLocal.getMonthValue() && oggi.getDayOfMonth() < dataNascitaLocal.getDayOfMonth())) {
            eta--;
        }

        return eta;
    }
}

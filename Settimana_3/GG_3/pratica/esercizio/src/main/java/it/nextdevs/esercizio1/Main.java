package it.nextdevs.esercizio1;

import java.util.Date;

public class Main {
    public static void main(String[] args) {
        Info info = new Info();
        info.setNome("Mario");
        info.setCognome("Rossi");
        info.setDataDiNascita(new Date(90, 0, 1)); // 1 gennaio 1990

        DataSource adapter = new InfoAdapter(info);
        UserData userData = new UserData();
        userData.getData(adapter);

        // Accede ai dati tramite i membri della classe UserData
        System.out.println("Nome Completo: " + userData.getNomeCompleto());
        System.out.println("Età: " + userData.getEta() + " anni");
    }
}

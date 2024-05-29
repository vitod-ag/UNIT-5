package it.nextdevs.esercizio1_adapter;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Main {
    public static void main(String[] args) {
        Info info = new Info();
        info.setNome("Mario");
        info.setCognome("Rossi");
        info.setDataDiNascita(new GregorianCalendar(2006, Calendar.MARCH, 1).getTime());

        InfoAdapter infoAdapter = new InfoAdapter(info);
        UserData userData = new UserData();
        userData.getData(infoAdapter);

        System.out.println(userData);
    }
}

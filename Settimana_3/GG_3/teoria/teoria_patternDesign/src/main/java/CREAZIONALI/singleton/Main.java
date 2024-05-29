package CREAZIONALI.singleton;

public class Main {
    public static void main(String[] args) {
//        CorsoEpicode corsoEpicode = new CorsoEpicode("Back_End", LocalDate.now(), 100, "Roma");
//        CorsoEpicode corsoEpicode2 = new CorsoEpicode("Front_End",LocalDate.now(), 123, "Milano");

        CorsoEpicode corsoEpicode1 = CorsoEpicode.getInstance();
        CorsoEpicode corsoEpicode2 = CorsoEpicode.getInstance();

        System.out.println(corsoEpicode1.hashCode());
        System.out.println(corsoEpicode2.hashCode());
    }
}
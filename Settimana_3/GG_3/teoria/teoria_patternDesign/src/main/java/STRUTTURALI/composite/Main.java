package STRUTTURALI.composite;

public class Main {
    public static void main(String[] args) {
        Libro libro1 = new Libro("libro1", 10, 167);
        Libro libro2 = new Libro("libro2", 20, 222);
        Libro libro3 = new Libro("libro3", 30, 346);
        Libro libro4 = new Libro("libro4", 40, 456);
        Libro libro5 = new Libro("libro5", 50, 145);
        Libro libro6 = new Libro("libro6", 60, 466);
        Libro libro7 = new Libro("libro7", 70, 852);
        Libro libro8 = new Libro("libro8", 80, 477);
        Libro libro9 = new Libro("libro9", 90, 123);
        Libro libro10 = new Libro("libro10", 100, 256);

        Scatolo scatola1 = new Scatolo("scatola1");
        Scatolo scatola2 = new Scatolo("scatola2");
        Scatolo scatola3 = new Scatolo("scatola3");
        Scatolo scatola4 = new Scatolo("scatola4");

        scatola4.aggiungiProdotto(libro1);
        scatola1.aggiungiProdotto(libro2);
        scatola3.aggiungiProdotto(libro3);

        scatola3.aggiungiProdotto(libro4);
        scatola2.aggiungiProdotto(libro5);
        scatola1.aggiungiProdotto(libro6);
        scatola2.aggiungiProdotto(libro7);

        scatola2.aggiungiProdotto(libro8);
        scatola1.aggiungiProdotto(libro9);
        scatola4.aggiungiProdotto(libro10);

        scatola2.aggiungiProdotto(scatola1);
        scatola3.aggiungiProdotto(scatola2);
        scatola1.aggiungiProdotto(scatola4);

        System.out.println(scatola1.getPrezzo());
        System.out.println(scatola1.getPeso());
    }
}

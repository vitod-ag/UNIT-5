package it.nextdevs.esercizio2_composite;

public class Main {
    public static void main(String[] args) {
        Pagina p1 = new Pagina("p1");
        Pagina p2 = new Pagina("p2");
        Pagina p3 = new Pagina("p3");
        Pagina p4 = new Pagina("p4");
        Pagina p5 = new Pagina("p5");
        Pagina p6 = new Pagina("p6");
        Pagina p7 = new Pagina("p7");
        Pagina p8 = new Pagina("p8");
        Pagina p9 = new Pagina("p9");
        Pagina p10 = new Pagina("p10");

        Sezione s1 = new Sezione("s1");
        Sezione s2 = new Sezione("s2");
        Sezione s3 = new Sezione("s3");
        Sezione s4 = new Sezione("s4");

        s1.add(p1);
        s1.add(p2);
        s1.add(p3);

        s2.add(s1);
        s2.add(p4);
        s2.add(p5);

        s3.add(s2);
        s3.add(p6);
        s3.add(p7);
        s3.add(p8);

        s4.add(p9);
        s4.add(p10);

        Libro libro = new Libro(10, "l2");
        libro.add(s3);
        libro.add(s4);

        System.out.println(libro.getPagine());
    }
}

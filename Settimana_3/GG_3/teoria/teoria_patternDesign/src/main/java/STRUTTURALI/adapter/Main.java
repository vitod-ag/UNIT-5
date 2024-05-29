package STRUTTURALI.adapter;

public class Main {
    public static void main(String[] args) {
        Quadrato quadrato = new Quadrato(5);
        Triangolo triangolo = new Triangolo(3,4,5);
        Rettangolo rettangolo = new Rettangolo(3, 7);
        FiguraGeometricaAdapter rettangoloAdapter = new FiguraGeometricaAdapter(rettangolo);


        CalcolatoreFigure calcolatoreFigure = new CalcolatoreFigure();
        calcolatoreFigure.add(quadrato);
        calcolatoreFigure.add(triangolo);
        calcolatoreFigure.add(rettangoloAdapter);


        System.out.println(calcolatoreFigure.perimetroTotale());
        System.out.println(calcolatoreFigure.areaTotale());
    }
}

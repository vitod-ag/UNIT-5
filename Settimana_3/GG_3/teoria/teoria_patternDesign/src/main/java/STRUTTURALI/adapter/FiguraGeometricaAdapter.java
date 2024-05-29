package STRUTTURALI.adapter;

public class FiguraGeometricaAdapter implements Figura{

    private FiguraGeometrica figuraGeometrica;  // questo oggetto "adattatore" deve avere oggetti FiguraGeometrica

    public FiguraGeometricaAdapter(FiguraGeometrica figuraGeometrica) {
        this.figuraGeometrica = figuraGeometrica;
    }

    public FiguraGeometrica getFiguraGeometrica() {
        return figuraGeometrica;
    }

    public void setFiguraGeometrica(FiguraGeometrica figuraGeometrica) {
        this.figuraGeometrica = figuraGeometrica;
    }

    @Override
    public double perimetro() {
        return figuraGeometrica.perimetro();
    }

    @Override
    public double area() {
        return figuraGeometrica.area();
    }
}

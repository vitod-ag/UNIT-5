package STRUTTURALI.adapter;

public class Triangolo implements Figura{

    private double lato1;
    private double lato2;
    private double lato3;

    public Triangolo(double lato1, double lato2, double lato3) {
        this.lato1 = lato1;
        this.lato2 = lato2;
        this.lato3 = lato3;
    }

    public double getLato1() {
        return lato1;
    }

    public void setLato1(double lato1) {
        this.lato1 = lato1;
    }

    public double getLato2() {
        return lato2;
    }

    public void setLato2(double lato2) {
        this.lato2 = lato2;
    }

    public double getLato3() {
        return lato3;
    }

    public void setLato3(double lato3) {
        this.lato3 = lato3;
    }

    @Override
    public double perimetro() {
        return lato1+lato2+lato3;
    }

    @Override
    public double area() {
        double p = perimetro()/2;
        return Math.sqrt(p*(p-lato1)*(p-lato2)*(p-lato3));
    }
}

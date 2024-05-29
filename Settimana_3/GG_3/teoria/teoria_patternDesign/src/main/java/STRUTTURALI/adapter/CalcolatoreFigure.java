package STRUTTURALI.adapter;

import java.util.ArrayList;
import java.util.List;

public class CalcolatoreFigure {
    private List<Figura> figure = new ArrayList<>();

    public void add(Figura figura){
        figure.add(figura);
    }

    public void remove(Figura figura){
        figure.remove(figura);
    }

    public double perimetroTotale(){
        return figure.stream().mapToDouble(Figura::perimetro).sum();
    }

    public double areaTotale(){
        return figure.stream().mapToDouble(Figura::area).sum();
    }
}

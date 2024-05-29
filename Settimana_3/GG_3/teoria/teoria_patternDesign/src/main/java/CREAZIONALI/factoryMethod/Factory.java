package CREAZIONALI.factoryMethod;

public class Factory {
    private static Animale animale;

    public static Animale getAnimale(Tipo tipo) {
        switch (tipo) {
            case GATTO:
                animale = new Gatto();
                break;
            case CANE:
                animale = new Cane();
                break;
            case COCCODRILLO:
                animale = new Coccodrillo();
                break;
            default:
                animale = new Gatto();
                break;
        }
        return animale;
    }

}

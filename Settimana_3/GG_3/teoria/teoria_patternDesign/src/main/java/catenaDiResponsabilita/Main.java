package catenaDiResponsabilita;

public class Main {
    public static void main(String[] args) {
        ContoBancario contoBancario = new ContoBancario(123,1000,479, 479);

        Erogatore200 erogatore200 = new Erogatore200();
        Erogatore100 erogatore100 = new Erogatore100();
        Erogatore50 erogatore50 = new Erogatore50();
        Erogatore20 erogatore20 = new Erogatore20();

        erogatore200.setErogatoreSuccessivo(erogatore100);
        erogatore100.setErogatoreSuccessivo(erogatore50);
        erogatore50.setErogatoreSuccessivo(erogatore20);

        erogatore200.eroga(contoBancario);
    }
}

package catenaDiResponsabilita;

public class ContoBancario {
    private int numeroConto;
    private int saldo;
    private int prelievo;
    private int prelievoRimanente;

    public ContoBancario(int numeroConto, int saldo, int prelievo, int prelievoRimanente) {
        this.numeroConto = numeroConto;
        this.saldo = saldo;
        this.prelievo = prelievo;
        this.prelievoRimanente = prelievoRimanente;
    }

    public int getNumeroConto() {
        return numeroConto;
    }

    public void setNumeroConto(int numeroConto) {
        this.numeroConto = numeroConto;
    }

    public int getSaldo() {
        return saldo;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }

    public int getPrelievo() {
        return prelievo;
    }

    public void setPrelievo(int prelievo) {
        this.prelievo = prelievo;
    }

    public int getPrelievoRimanente() {
        return prelievoRimanente;
    }

    public void setPrelievoRimanente(int prelievoRimanente) {
        this.prelievoRimanente = prelievoRimanente;
    }
}

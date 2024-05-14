package it.epicode.teoria.bean;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper = true)  //
public class Computer extends Dispositivo{
    private int monitor;
    private String cpu;
    private int ram;

    public int getMonitor() {
        return monitor;
    }

    public void setMonitor(int monitor) {
        this.monitor = monitor;
    }

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public int getRam() {
        return ram;
    }

    public void setRam(int ram) {
        this.ram = ram;
    }
}

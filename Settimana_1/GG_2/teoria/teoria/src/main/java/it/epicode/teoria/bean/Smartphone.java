package it.epicode.teoria.bean;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper = true)
public class Smartphone extends Dispositivo{
    private int schermo;

    public int getSchermo() {
        return schermo;
    }

    public void setSchermo(int schermo) {
        this.schermo = schermo;
    }
}

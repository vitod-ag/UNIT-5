package it.nextdevs.esercizio.bean;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;


//@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@ToString(callSuper = true)
public class Pizza extends CaloriePrezzo{
    private String nome;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "pizza_topping",
            joinColumns = @JoinColumn(name = "pizza_id"),
            inverseJoinColumns = @JoinColumn(name = "topping_id"))
    private List<Topping> toppings=new ArrayList<>();

}

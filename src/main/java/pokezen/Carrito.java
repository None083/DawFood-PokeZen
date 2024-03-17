/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pokezen;

import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author FX506
 */
public class Carrito {
    
    private Map<Integer, Integer> carrito;

    public Carrito() {
        this.carrito = new TreeMap<>();
    }

    public Map<Integer, Integer> getCarrito() {
        return carrito;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Carrito{");
        sb.append("carrito=").append(carrito);
        sb.append('}');
        return sb.toString();
    }
     
}

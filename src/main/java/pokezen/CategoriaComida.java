/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package pokezen;

/**
 *
 * @author noelia
 */
public enum CategoriaComida {
    
    POKE("Poke"),
    WRAP("Wrap"),
    LOCO_MOCO("Loco Moco");

    private final String CATEGORIA_COMIDA;

    private CategoriaComida(String CATEGORIA_COMIDA) {
        this.CATEGORIA_COMIDA = CATEGORIA_COMIDA;
    }

    public String getCATEGORIA_COMIDA() {
        return CATEGORIA_COMIDA;
    }
    
}

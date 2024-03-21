/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package pokezen;

/**
 *
 * @author noelia
 */
public enum CategoriasComida {
    
    POKE("Poke"),
    WRAP("Wrap"),
    LOCO_MOCO("Loco Moco");

    private final String CATEGORIA;

    private CategoriasComida(String CATEGORIA) {
        this.CATEGORIA = CATEGORIA;
    }

    public String getCATEGORIA() {
        return CATEGORIA;
    }
    
    
    
}

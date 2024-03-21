/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package pokezen;

/**
 *
 * @author FX506
 */
public enum CategoriasPostre {
    
    HELADO("Helado"),
    FRUTA("Fruta"),
    TARTA("Tarta");
    
    private final String CATEGORIA;

    private CategoriasPostre(String CATEGORIA) {
        this.CATEGORIA = CATEGORIA;
    }

    public String getCATEGORIA() {
        return CATEGORIA;
    }
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package pokezen;

/**
 *
 * @author noelia
 */
public enum CategoriasBebida {
    
    AGUA("Agua"),
    CERVEZA("Cerveza"),
    TE("Té"),
    ZUMO("Zumo"),
    REFRESCO("Refresco");
    
    private final String CATEGORIA;

    private CategoriasBebida(String CATEGORIA) {
        this.CATEGORIA = CATEGORIA;
    }

    public String getCATEGORIA() {
        return CATEGORIA;
    }
    
}

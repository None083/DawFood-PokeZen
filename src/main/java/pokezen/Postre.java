/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pokezen;

/**
 *
 * @author FX506
 */
public class Postre extends Producto {
    
    private CategoriasPostre categoria;

    public Postre(String descripcion, CategoriasPostre categoria, double precio, pokezen.IVA IVA, int stock) {
        super(descripcion, precio, IVA, stock);
        this.categoria = categoria;
    }

    public Postre() {
    }

    public CategoriasPostre getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriasPostre categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Postre{");
        sb.append(super.toString());
        sb.append(", categoria=").append(categoria);
        sb.append('}');
        return sb.toString();
    }

}

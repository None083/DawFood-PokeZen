/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pokezen;

/**
 *
 * @author noelia
 */
public class Comida extends Producto {
    
    private CategoriasComida categoria;

    public Comida( String descripcion, CategoriasComida categoria, double precio, pokezen.IVA IVA, int stock) {
        super(descripcion, precio, IVA, stock);
        this.categoria = categoria;
    }

    public Comida() {
    }
    
    public CategoriasComida getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriasComida categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Comida{");
        sb.append(super.toString());
        sb.append(", categoria=").append(categoria);
        sb.append('}');
        return sb.toString();
    }
    
}

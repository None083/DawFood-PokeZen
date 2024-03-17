/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pokezen;

/**
 *
 * @author noelia
 */
public class Bebida extends Producto {
    
    private CategoriasBebida categoria;

    public Bebida(String descripcion, CategoriasBebida categoria, double precio, pokezen.IVA IVA, int stock) {
        super(descripcion, precio, IVA, stock);
        this.categoria = categoria;
    }

    public CategoriasBebida getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriasBebida categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Bebida{");
        sb.append(super.toString());
        sb.append("categoria=").append(categoria);
        sb.append('}');
        return sb.toString();
    }
    
}

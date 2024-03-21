/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pokezen;

/**
 *
 * @author noelia
 */
public abstract class Producto {
    
    private int ID;
    private String descripcion;
    private double precio;
    private IVA IVA;
    private int stock;
    private static int contadorId = 0;

    public Producto(String descripcion, double precio, IVA IVA, int stock) {
        contadorId++;
        this.ID = contadorId;
        this.descripcion = descripcion;
        this.precio = precio;
        this.IVA = IVA;
        this.stock = stock;
    }

    public Producto() {
    }

    public int getID() {
        return ID;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public IVA getIVA() {
        return IVA;
    }

    public void setIVA(IVA IVA) {
        this.IVA = IVA;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public static int getContadorId() {
        return contadorId;
    }

    public static void setContadorId(int contadorId) {
        Producto.contadorId = contadorId;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + this.ID;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Producto other = (Producto) obj;
        return this.ID == other.ID;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ID=").append(ID);
        sb.append(", descripcion=").append(descripcion);
        sb.append(", precio=").append(precio);
        sb.append(", IVA=").append(IVA);
        sb.append(", stock=").append(stock);
        return sb.toString();
    }
    
    
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pokezen;

import java.time.LocalDate;
import java.util.Objects;

/**
 *
 * @author noelia
 */
public class Tarjeta {
    
    private String numTarjeta;
    private LocalDate fechaVencimiento;
    private String CVV;
    private String nombreTitular;
    private double saldo;

    public Tarjeta(String numTarjeta, LocalDate fechaVencimiento, String CVV, String nombreTitular, double saldo) {
        this.numTarjeta = numTarjeta;
        this.fechaVencimiento = fechaVencimiento;
        this.CVV = CVV;
        this.nombreTitular = nombreTitular;
        this.saldo = saldo;
    }

    public Tarjeta() {
    }

    public String getNumTarjeta() {
        return numTarjeta;
    }

    public LocalDate getFechaVencimiento() {
        return fechaVencimiento;
    }

    public String getCVV() {
        return CVV;
    }

    public String getNombreTitular() {
        return nombreTitular;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setFechaVencimiento(LocalDate fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 17 * hash + Objects.hashCode(this.numTarjeta);
        hash = 17 * hash + Objects.hashCode(this.fechaVencimiento);
        hash = 17 * hash + Objects.hashCode(this.CVV);
        hash = 17 * hash + Objects.hashCode(this.nombreTitular);
        hash = 17 * hash + (int) (Double.doubleToLongBits(this.saldo) ^ (Double.doubleToLongBits(this.saldo) >>> 32));
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
        final Tarjeta other = (Tarjeta) obj;
        if (Double.doubleToLongBits(this.saldo) != Double.doubleToLongBits(other.saldo)) {
            return false;
        }
        if (!Objects.equals(this.numTarjeta, other.numTarjeta)) {
            return false;
        }
        if (!Objects.equals(this.CVV, other.CVV)) {
            return false;
        }
        if (!Objects.equals(this.nombreTitular, other.nombreTitular)) {
            return false;
        }
        return Objects.equals(this.fechaVencimiento, other.fechaVencimiento);
    }

    

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Tarjeta{");
        sb.append("numTarjeta=").append(numTarjeta);
        sb.append(", fechaVencimiento=").append(fechaVencimiento);
        sb.append(", CVV=").append(CVV);
        sb.append(", nombreTitular=").append(nombreTitular);
        sb.append(", saldo=").append(saldo);
        sb.append('}');
        return sb.toString();
    }
    
    
    
}

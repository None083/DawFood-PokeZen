/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package pokezen;


/**
 *
 * @author francisco
 */
public enum IVA {
    
    IVA_VETIUNO(1.21),
    IVA_DIEZ(1.1);
    
    private final double PORCENTAJE_IVA;
    
    IVA(double IVA){
        this.PORCENTAJE_IVA = IVA;
    }

    public double getPORCENTAJE_IVA() {
        return PORCENTAJE_IVA;
    }
    
    
}

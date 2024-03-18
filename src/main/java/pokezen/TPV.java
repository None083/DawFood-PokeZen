/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pokezen;

import java.awt.Color;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

/**
 *
 * @author FX506
 */
public class TPV {

    private final UUID ID;
    private LocalDate fechaSistema;
    private LocalTime horaSistema;
    private String direccion;
    private String password;
    private ArrayList<Producto> menuProductos;
    private Carrito carrito;
    private ArrayList<Ticket> baseDatosTicket;

    public TPV(String direccion) {
        this.ID = UUID.randomUUID();
        this.fechaSistema = LocalDate.now();
        this.horaSistema = LocalTime.now();
        this.direccion = direccion;
        this.password = UtilidadesAdmin.crearPassword();
        this.menuProductos = new ArrayList<>();
        this.carrito = new Carrito();
        this.baseDatosTicket = new ArrayList<>();
    }

    public TPV() {
        this.ID = UUID.randomUUID();
    }

    public List<Producto> getMenuProductos() {
        return menuProductos;
    }

    public Carrito getCarrito() {
        return carrito;
    }

    public ArrayList<Ticket> getBaseDatosTicket() {
        return baseDatosTicket;
    }

    public UUID getID() {
        return ID;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getPassword() {
        return password;
    }

    public LocalDate getFechaSistema() {
        return fechaSistema;
    }

    public LocalTime getHoraSistema() {
        return horaSistema;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("TPV{");
        sb.append("ID=").append(ID);
        sb.append(", fechaSistema=").append(fechaSistema);
        sb.append(", horaSistema=").append(horaSistema);
        sb.append(", direccion=").append(direccion);
        sb.append('}');
        return sb.toString();
    }

    public void encenderTPV() {

        //Se crea la contraseña y se muestra para el modo admin
        System.out.println("Contraseña Admin: " + this.password);

        UIManager.put("OptionPane.okButtonText", "Aceptar");
        UIManager.put("OptionPane.cancelButtonText", "Volver");
        UIManager.put("OptionPane.background", new Color(147, 217, 196));
        UIManager.put("Panel.background", new Color(147, 217, 196));

        boolean encendido = true;
        boolean usuario = false;
        boolean admin = false;

        do {//Do while que controla el apagado del tpv
            int opcionModo;

            //Este if see encarga de volver a seleccionarModo, 
            //seleccionar Cartegoría o modo mantenimiento
            //cuando estás dentro de una de las dos opciones
            if (admin) {
                opcionModo = 0;
            } else if (usuario) {
                opcionModo = 1;
            } else {
                opcionModo = UtilidadesTPV.seleccionarModo();
            }

            switch (opcionModo) {
                case 0 -> {
                    usuario = false;

                    if (admin) {
                        //admin = UtilidadesAdmin.modoMantenimiento(this);
                    } else {
//                        if (UtilidadesAdmin.pedirPassword(this)) {
//
//                            admin = UtilidadesAdmin.modoMantenimiento(this);
//                        } else {
//                            String[] opciones = {"Aceptar"};
//
//                            JOptionPane.showOptionDialog(null,
//                                    "La contraseña no es correcta, vuelve a intentar",
//                                    "TPV - Poke Zen", JOptionPane.DEFAULT_OPTION,
//                                    JOptionPane.QUESTION_MESSAGE,
//                                    new ImageIcon("src/main/java/iconos/admin1.png"),
//                                    opciones, opciones[0]);
//                        }
                    }
                }
                case 1 -> {
                    admin = false;
                    usuario = UtilidadesTPV.seleccionarTipo(this);
                }
                case 2 -> {
                    usuario = false;
                    //Opción apagar
                    encendido = false;
                }
            }
        } while (encendido);
    }

}

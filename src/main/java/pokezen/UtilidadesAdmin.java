/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pokezen;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import org.apache.commons.lang3.RandomStringUtils;

/**
 *
 * @author francisco
 */
public class UtilidadesAdmin {

    //Genera una contraseña con 3 letras minúsculas, 1 mayúscula, 1 número y 1 símbolo
    public static String crearPassword() {
        return RandomStringUtils.randomAlphabetic(3).toLowerCase()
                + RandomStringUtils.randomAlphabetic(1).toUpperCase()
                + RandomStringUtils.randomNumeric(1)
                + RandomStringUtils.random(1, "#$%&()*+,-.:;<=>@");
    }

    public static boolean pedirPassword(TPV tpv) {

        String password = (String) JOptionPane.showInputDialog(null,
                "Introduce la contraseña para entrar en modo administrador",
                "TPV - Poke Zen", JOptionPane.OK_CANCEL_OPTION,
                new ImageIcon("src/main/java/iconos/admin1.png"),
                null, null);

        if (password == null) {
            //Volver
            //No hace falta que ponga nada aquí para que vaya atrás
        } else {
            try {
                if (password.equals(tpv.getPassword())) {
                    return true;
                }
            } catch (NullPointerException npe) {
            }
        }

        return false;
    }

    public static boolean modoMantenimiento(TPV tpv) {

        Object[] options = {"Editar producto", "Nuevo producto",
            "Borrar producto", "Consultar ventas", "Volver"};
        int opcionElegida = JOptionPane.showOptionDialog(null,
                "Escoge una opción", "TPV - Poke Zen", JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE, new ImageIcon("src/main/java/iconos/admin1.png"), options, options[4]);

        switch (opcionElegida) {
            case 0 -> {
                modificarProducto(tpv);
                return true;
            }
            case 1 -> {
                tpv.getMenuProductos().add(nuevoProducto(tpv));
                return true;
            }
            case 2 -> {
                Producto opcionElegidaProducto = (Producto) JOptionPane.showInputDialog(null,
                        "Escoge el producto a borrar", "TPV - Poke Zen", JOptionPane.QUESTION_MESSAGE,
                        new ImageIcon("src/main/java/iconos/admin1.png"), tpv.getMenuProductos().toArray(),
                        tpv.getMenuProductos().get(0));

                if (opcionElegidaProducto == null) {
                    modoMantenimiento(tpv);
                } else {
                    //Obtenemos el indice del producto que hemos elegido,
                    //para borralo fuera del for each
                    int indice = 0;
                    for (Producto p : tpv.getMenuProductos()) {
                        try {
                            if (opcionElegidaProducto.equals(p)) {
                                indice = tpv.getMenuProductos().indexOf(p);
                            }
                        } catch (NullPointerException npe) {
                        }
                    }
                    tpv.getMenuProductos().remove(indice);
                }
                return true;
            }
            case 3 -> {
                consultarVentas(tpv);
                return true;
            }
            case 4 -> {
                //Botón volver
                return false;
            }
        }
        return true;
    }

    private static void modificarProducto(TPV tpv) {

        Producto opcionElegidaProducto = (Producto) JOptionPane.showInputDialog(null,
                "Escoge el producto a editar", "TPV - Poke Zen",
                JOptionPane.OK_CANCEL_OPTION,
                new ImageIcon("src/main/java/iconos/admin1.png"),
                tpv.getMenuProductos().toArray(),
                tpv.getMenuProductos().get(0));

        if (opcionElegidaProducto == null) {
            //Botón volver
            //No hace falta que ponga nada aquí para que vaya atrás
            //Si quito este if, aunque no salta el nullpointer,
            //no iría a la pantalla anterior, 
            //seguiría avanzando a la siguiente (pantalla de introducir datos)
        } else {

            //Se pide todo en la misma ventana
            JTextField descrip = new JTextField();
            JTextField categ = new JTextField();
            JTextField subcat = new JTextField();
            JTextField precio = new JTextField();
            JTextField iva = new JTextField();
            JTextField stock = new JTextField();
            Object[] message = {
                "Cambiar atributos del producto",
                "Descripcion:", descrip,
                "Categoría:", categ,
                "Subcategoría:", subcat,
                "Precio:", precio,
                "IVA:", iva,
                "Stock:", stock
            };

            int option = JOptionPane.showConfirmDialog(null, message,
                    "TPV - Poke Zen", JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    new ImageIcon("src/main/java/iconos/admin1.png"));

            if (option == JOptionPane.OK_OPTION) {

                //En este string se irá guardándo el atributo que se cambie satisfactoriamente
                //para mostrarlo en un mensaje al final
                String atributosCambiados = "";
                String atributosError = "";

                boolean error = false;

                //Para que se realice un cambio el campo no estará vacío ni tendrá un espacio
                if (!precio.getText().trim().equals("")) {
                    try {
                        opcionElegidaProducto.setPrecio(Double.parseDouble(precio.getText()));
                        //Esto se ñadirá a un string para saber si 
                        //se ha realizado el cambio en un mensaje al finalizar
                        atributosCambiados += "Precio, ";
                    } catch (NumberFormatException nfe) {
                        error = true;
                        atributosError = "Precio, ";
                    }

                }

                //Si ocurre algún error no entrará en los if de los
                //siguientes atributos, y no se cambiará ningún dato
                if (!iva.getText().trim().equals("") && !error) {
                    for (IVA iv : IVA.values()) {
                        try {
                            if (Double.parseDouble(iva.getText()) == iv.getPORCENTAJE_IVA()) {

                                opcionElegidaProducto.setIVA(iv);
                                atributosCambiados += "IVA, ";
                            }
                        } catch (NumberFormatException nfe) {
                            error = true;
                            atributosError = "IVA, ";
                        }

                    }
                }

                if (!stock.getText().trim().equals("") && !error) {
                    try {
                        opcionElegidaProducto.setStock(Integer.parseInt(stock.getText()));
                        atributosCambiados += "Stock";
                    } catch (NumberFormatException nfe) {
                        error = true;
                        atributosError = "Stock, ";
                    }
                }

                if (!descrip.getText().trim().equals("") && !error) {
                    opcionElegidaProducto.setDescripcion(descrip.getText());
                    atributosCambiados += "Descripción, ";
                }

                //Para los tipo enum debo encontrar el aquivalente en la clase
                //a partir del texto que se escriba en el campo
                if (!categ.getText().trim().equals("") && !error) {
                    for (Categoria c : Categoria.values()) {
                        if (categ.getText().equalsIgnoreCase(c.getCATEGORIA())) {
                            opcionElegidaProducto.setCategoria(c);
                            atributosCambiados = "Categoría, ";
                        }
                    }
                }

                if (!subcat.getText().trim().equals("") && !error) {
                    for (Subcategoria s : Subcategoria.values()) {
                        if (subcat.getText().equalsIgnoreCase(s.getSUBCATEGORIA())) {
                            opcionElegidaProducto.setSubcategoria(s);
                            atributosCambiados += "Subcategoría, ";
                        }
                    }
                }

                //Se mostrará el primer mensaje si no hay errores, y aparecerán
                //los nombres de los atributos editados
                String[] opciones = {"Aceptar"};
                if (!error) {
                    JOptionPane.showOptionDialog(null,
                            "Cambios realizados con éxito en " + atributosCambiados + " ",
                            "TPV - Poke Zen", JOptionPane.DEFAULT_OPTION,
                            JOptionPane.QUESTION_MESSAGE,
                            new ImageIcon("src/main/java/iconos/admin1.png"),
                            opciones, opciones[0]);
                } else {
                    //Si sucede algún error se mostrará un mensaje con el nombre del atributo
                    JOptionPane.showOptionDialog(null,
                            "Ha sucedido un error con el " + atributosError + "vuelva a intentar",
                            "TPV - Poke Zen", JOptionPane.DEFAULT_OPTION,
                            JOptionPane.QUESTION_MESSAGE,
                            new ImageIcon("src/main/java/iconos/admin1.png"),
                            opciones, opciones[0]);
                }
            }
        }
    }

    private static Producto nuevoProducto(TPV tpv) {

        Producto producto = new Producto();

        //Se pide todo en la misma ventana
        JTextField descrip = new JTextField();
        JTextField categ = new JTextField();
        JTextField subcat = new JTextField();
        JTextField precio = new JTextField();
        JTextField iva = new JTextField();
        JTextField stock = new JTextField();
        Object[] message = {
            "Completa los datos del producto",
            "Descripcion:", descrip,
            "Categoría:", categ,
            "Subcategoría:", subcat,
            "Precio:", precio,
            "IVA:", iva,
            "Stock:", stock
        };

        int option = JOptionPane.showConfirmDialog(null, message,
                "TPV - Poke Zen", JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                new ImageIcon("src/main/java/iconos/admin1.png"));

        //Cuando pulse el botón aceptar
        if (option == JOptionPane.OK_OPTION) {

            producto.setDescripcion(descrip.getText());
            String atributosError = "";

            for (Categoria c : Categoria.values()) {
                if (categ.getText().equalsIgnoreCase(c.getCATEGORIA())) {
                    producto.setCategoria(c);
                }
            }

            for (Subcategoria s : Subcategoria.values()) {
                if (subcat.getText().equalsIgnoreCase(s.getSUBCATEGORIA())) {
                    producto.setSubcategoria(s);
                }
            }

            boolean error = false;

            try {
                producto.setPrecio(Double.parseDouble(precio.getText()));
            } catch (NumberFormatException nfe) {
                error = true;
                atributosError += "Precio, ";
            }

            for (IVA iv : IVA.values()) {
                try {
                    if (Double.parseDouble(iva.getText()) == iv.getPORCENTAJE_IVA()) {
                        producto.setIVA(iv);
                    }
                } catch (NumberFormatException nfe) {
                    error = true;
                    atributosError += "IVA, ";
                }
            }

            try {
                producto.setStock(Integer.parseInt(stock.getText()));
            } catch (NumberFormatException nfe) {
                error = true;
                atributosError += "Stock, ";
            }

            if (error) {
                String[] opciones = {"Aceptar"};
                //Si sucede algún error se mostrará un mensaje con el nombre del atributo
                JOptionPane.showOptionDialog(null,
                        "Ha sucedido un error con " + atributosError 
                                + " no se han registrado esos datos, "
                                        + "puedes editarlos en la opción Editar producto",
                        "TPV - Poke Zen", JOptionPane.DEFAULT_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        new ImageIcon("src/main/java/iconos/admin1.png"),
                        opciones, opciones[0]);
            }

        } else {
            modoMantenimiento(tpv);
        }

        //El producto se creará aunque no se hayan rellenado todos los campos
        //con los campos que se hayan rellenado correctamente
        //se podrá editar en "editar producto"
        return producto;
    }

//    //Ver los pedidos de la lista entera de ticket durente el dia de hoy
//    public static void verPedidosDia(LocalDate dia, ArrayList<Ticket> listaTicket, TPV tpv) {
//        ArrayList<Ticket> listaTicketHoy = new ArrayList<>();
//
//        for (int i = 0; i < listaTicket.size(); i++) {
//            if (listaTicket.get(i).getFechaOperacion().isEqual(dia)) {
//                listaTicketHoy.add(listaTicket.get(i));
//            }
//        }
//        if (!(tpv.getBaseDatosTicket().isEmpty())) {
//            Ticket opcionTicket = (Ticket) JOptionPane.showInputDialog(null,
//                    "Lista de ventas", "TPV - Poke Zen", JOptionPane.QUESTION_MESSAGE,
//                    new ImageIcon("src/main/java/iconos/admin1.png"),
//                    listaTicketHoy.toArray(),
//                    listaTicketHoy.get(0));
//        } else {
//
//            Object[] opcionAceptar = {"Aceptar"};
//
//            JOptionPane.showOptionDialog(null,
//                    "No hay ventas en el dia de hoy todavia",
//                    "TPV - Poke Zen", JOptionPane.DEFAULT_OPTION,
//                    JOptionPane.QUESTION_MESSAGE,
//                    new ImageIcon("src/main/java/iconos/admin1.png"),
//                    opcionAceptar, opcionAceptar[0]);
//
//        }
//    }
//
//    //Ver los pedidos de la lista entera de ticket hasta una fecha concreta
//    public static void verPedidosHastaFecha(LocalDate diaLimite, ArrayList<Ticket> listaTicket, TPV tpv) {
//        ArrayList<Ticket> listaTicketFechaConcreta = new ArrayList<>();
//        for (int i = 0; i < listaTicket.size(); i++) {
//            if (listaTicket.get(i).getFechaOperacion().isBefore(diaLimite)
//                    || listaTicket.get(i).getFechaOperacion().isEqual(diaLimite)) {
//                listaTicketFechaConcreta.add(listaTicket.get(i));
//            }
//        }
//        if (!(tpv.getBaseDatosTicket().isEmpty())) {
//            Ticket opcionTicket = (Ticket) JOptionPane.showInputDialog(null,
//                    "Lista de ventas", "TPV - Poke Zen", JOptionPane.QUESTION_MESSAGE,
//                    new ImageIcon("src/main/java/iconos/admin1.png"),
//                    listaTicketFechaConcreta.toArray(),
//                    listaTicketFechaConcreta.get(0));
//        } else {
//
//            Object[] opcionAceptar = {"Aceptar"};
//
//            JOptionPane.showOptionDialog(null,
//                    "Hasta esa fecha no hay ventas registradas",
//                    "TPV - Poke Zen", JOptionPane.DEFAULT_OPTION,
//                    JOptionPane.QUESTION_MESSAGE,
//                    new ImageIcon("src/main/java/iconos/admin1.png"),
//                    opcionAceptar, opcionAceptar[0]);
//
//        }
//
//    }
    //Metodo que genera las diferentes opciones para consultar las ventas
    public static void consultarVentas(TPV tpv) {

        Object[] opciones = {"Ventas día concreto", "Ventas hasta día concreto",
            "Todas las ventas", "Volver"};
        int opcionElegida = JOptionPane.showOptionDialog(null,
                "Consultar las ventas", "TPV", JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE, new ImageIcon("src/main/java/iconos/admin1.png"),
                opciones, opciones[3]);

        switch (opcionElegida) {
            case 0 -> {//Opción para ver todos los tickets de un día

                //NO ESTÁ IMPLEMENTADO, PRÓXIMAMENTE
//                JTextField mes = new JTextField();
//                JTextField anyo = new JTextField();
//                JTextField dia = new JTextField();
//                Object[] message = {
//                    "Introduce la fecha concreta",
//                    "Dia:", dia,
//                    "Mes:", mes,
//                    "Año:", anyo,};
//
//                int option = 0;
//
//                option = JOptionPane.showConfirmDialog(null, message,
//                        "TPV - Poke Zen", JOptionPane.OK_CANCEL_OPTION);
//
//                if (option == JOptionPane.OK_OPTION) {
//
//                    LocalDate fechaDia = LocalDate.MIN;
//                    verPedidosDia(fechaDia, tpv.getBaseDatosTicket(), tpv);
//                }
            }
            case 1 -> {//Opcion para ver todos los tickets hasta una fecha concreta

                //NO ESTÁ IMPLENTADO, PRÓXIMAMENTE
//                JTextField mes = new JTextField();
//                JTextField anyo = new JTextField();
//                JTextField dia = new JTextField();
//                Object[] message = {
//                    "Introduce la fecha concreta",
//                    "Dia:", dia,
//                    "Mes:", mes,
//                    "Año:", anyo,};
//
//                int option = 0;
//
//                option = JOptionPane.showConfirmDialog(null, message,
//                        "TPV - Poke Zen", JOptionPane.OK_CANCEL_OPTION);
//                if (option == JOptionPane.OK_OPTION) {
//
//                    LocalDate fechaDia = LocalDate.MIN;
//                    verPedidosHastaFecha(fechaDia, tpv.getBaseDatosTicket(), tpv);
//                }
            }
            case 2 -> {//Opción para ver todas las ventas registradas

                //Desplegable donde se puede elegir entre todos los tickets
                if (!tpv.getBaseDatosTicket().isEmpty()) {
                    Ticket ticketEscogido = (Ticket) JOptionPane.showInputDialog(null,
                            "Consultar las ventas", "TPV - Poke Zen",
                            JOptionPane.QUESTION_MESSAGE,
                            new ImageIcon("src/main/java/iconos/admin1.png"),
                            tpv.getBaseDatosTicket().toArray(),
                            tpv.getBaseDatosTicket().get(0));

                    if (ticketEscogido == null) {
                        consultarVentas(tpv);
                    } else {
                        Object[] opcionAceptar = {"Aceptar"};

                        //Se muestra el ticket de compra elegido
                        JOptionPane.showOptionDialog(null,
                                ticketEscogido.toStringChulo(), "TPV - Poke Zen",
                                JOptionPane.DEFAULT_OPTION,
                                JOptionPane.QUESTION_MESSAGE,
                                new ImageIcon("src/main/java/iconos/admin1.png"),
                                opcionAceptar, opcionAceptar[0]);
                    }
                } else {
                    Object[] opcionAceptar = {"Aceptar"};

                    JOptionPane.showOptionDialog(null,
                            "No hay ventas registradas en la base de datos",
                            "TPV - Poke Zen", JOptionPane.DEFAULT_OPTION,
                            JOptionPane.QUESTION_MESSAGE,
                            new ImageIcon("src/main/java/iconos/admin1.png"),
                            opcionAceptar, opcionAceptar[0]);
                }
            }
            //No hace falta case 3 (volver)
        }
    }

}

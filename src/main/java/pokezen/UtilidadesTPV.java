/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pokezen;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author FX506
 */
public class UtilidadesTPV {

    public static ArrayList<Producto> listaProductos() {
        ArrayList<Producto> listaProductos = new ArrayList<>();
        listaProductos.add(new Comida("Poke de pollo", CategoriasComida.POKE,
                11.99, IVA.IVA_DIEZ, 10));
        listaProductos.add(new Postre("Tarta de queso", CategoriasPostre.TARTA,
                12.59, IVA.IVA_DIEZ, 14));
        return listaProductos;
    }

    public static Producto buscarProductoPorID(int id, List<Producto> listaProductos) {
        for (Producto p : listaProductos) {
            if (id == p.getID()) {
                return p;
            }
        }
        return null;
    }

    public static int seleccionarModo() {

        //Array que contiene los nombres de los botones del joption
        String[] opciones = {"Administrador", "Usuario", "Apagar"};

        int opcionUsuario = JOptionPane.showOptionDialog(null,
                "Elige un modo", "TPV - Poke Zen", JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE, new ImageIcon("src/main/java/iconos/user1.png"), opciones, opciones[0]);

        return opcionUsuario;
    }

    public static boolean seleccionarTipo(TPV tpv) {

        Object[] options = {"Comida", "Bebida", "Postre", "Volver", "Ver cesta"};

        int opcionElegida = JOptionPane.showOptionDialog(null,
                "Escoge una categoría", "TPV - Poke Zen", JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE, new ImageIcon("src/main/java/iconos/poke1.png"), options, options[3]);

        ImageIcon icono;

        switch (opcionElegida) {
            case 0 -> {
                //Categoría comida
                icono = new ImageIcon("src/main/java/iconos/bowl1.png");
                seleccionarCategoria(tpv, CategoriasComida.POKE.getCATEGORIA(),
                        CategoriasComida.WRAP.getCATEGORIA(),
                        CategoriasComida.LOCO_MOCO.getCATEGORIA(), icono);
                return true;
                //He hecho que devuelva un booleano en todos los case para que 
                //no haya problemas con el botón volver y repetir la pantalla como se debe.
                //Lo uso en el método encender de la clase TPV
            }
            case 1 -> {
                //Categoría bebida
                icono = new ImageIcon("src/main/java/iconos/bebida1.png");
                seleccionarCategoria(tpv, CategoriasBebida.AGUA.getCATEGORIA(),
                        CategoriasBebida.CERVEZA.getCATEGORIA(),
                        CategoriasBebida.REFRESCO.getCATEGORIA(), icono);
                return true;
            }
            case 2 -> {
                //Categoría postre
                icono = new ImageIcon("src/main/java/iconos/helado1.png");
                seleccionarCategoria(tpv, CategoriasPostre.HELADO.getCATEGORIA(),
                        CategoriasPostre.FRUTA.getCATEGORIA(),
                        CategoriasPostre.TARTA.getCATEGORIA(), icono);
                return true;
            }
            case 3 -> {
                //Botón volver
                return false;
            }
            case 4 -> {

                //verCarrito(tpv);
                return true;
            }
        }
        return true;
    }

    //Le paso por parámetros 3 subcategorías para poder diferenciar de que categoría es 
    //en el método seleccionarCategoría
    public static void seleccionarCategoria(TPV tpv, String c1, String c2, String c3, ImageIcon icono) {

        Object[] options = {c1, c2, c3, "Volver", "Ver cesta"};
        int opcionElegida = JOptionPane.showOptionDialog(null,
                "Escoge una subcategoría", "TPV - Poke Zen", JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE, icono, options, options[3]);

        switch (opcionElegida) {
            case 0 -> {

                seleccionarProducto(tpv, c1, icono);
            }
            case 1 -> {

                seleccionarProducto(tpv, c2, icono);
            }
            case 2 -> {

                seleccionarProducto(tpv, c3, icono);
            }
            case 4 -> {
                //verCarrito(tpv);
            }
        }
    }

    private static void seleccionarProducto(TPV tpv, String categoria, ImageIcon icono) {

        //Obtenemos nuestra carta completa
        List<Producto> baseDatosProductos = tpv.getMenuProductos();

        //Lista para guardar qué productos se van a mostrar dependiendo 
        //de si hay stock, las categorías y subcategorías
        ArrayList<Producto> productosAMostrar = new ArrayList<>();

        //Lista para mostrar sólo los nombres de los productos en el desplegable
        ArrayList<String> nombreProductosAMostrar = new ArrayList<>();

        //Miramos que el stock de los productos no sea 0 o menor
        //y que coincidan con la categoría escogida, 
        //para añadirlos a las listas
        for (Producto p : baseDatosProductos) {
            if (p instanceof Comida) {

                Comida c = (Comida) p;
                if (c.getStock() > 0 && c.getCategoria().equals(categoria)) {
                    productosAMostrar.add(c);
                    nombreProductosAMostrar.add(c.getDescripcion());
                    añadirACarrito(nombreProductosAMostrar, baseDatosProductos, icono, tpv);
                }

            } else if (p instanceof Bebida) {

                Bebida b = (Bebida) p;
                if (b.getStock() > 0 && b.getCategoria().equals(categoria)) {
                    productosAMostrar.add(b);
                    nombreProductosAMostrar.add(b.getDescripcion());
                }

            } else if (p instanceof Postre) {

                Postre po = (Postre) p;
                if (po.getStock() > 0 && po.getCategoria().equals(categoria)) {
                    productosAMostrar.add(po);
                    nombreProductosAMostrar.add(po.getDescripcion());
                }
            }
        }
    }

    private static void añadirACarrito(List<String> nombreProductosAMostrar, List<Producto> productosAMostrar, ImageIcon icono, TPV tpv) {
        int opcionElegidaProducto = (Integer) JOptionPane.showInputDialog(null,
                "Escoge un producto", "TPV - Poke Zen", JOptionPane.QUESTION_MESSAGE,
                icono, nombreProductosAMostrar.toArray(),
                nombreProductosAMostrar.get(0));

        //Este if es por el botón volver de la pantalla de productos, que devuelve un null
        //opcionElegidaProducto == null
        if (true) {
            //Botón volver
            //No hace falta que ponga nada aquí para que vaya atrás
            //Si lo quito, salta nullpointerexception, y tendría que colocar
            //unos cuantos try catch
        } else {

            Producto p = productosAMostrar.get(opcionElegidaProducto);

            //Variable que guarda la cantidad que se quiere de un producto
            int numProductos = 0;
            //Este booleano servirá por si salta una excepción que se repita la pantalla
            boolean excepcion = true;
            do {//Este do while hará que se repita la pantalla si la cantidad que se quiere excede al stock
                do {
                    try {
                        //Mostramos el producto seleccionado con el detalle de precio con iva y sin iva
                        //y dejamos que se elija la cantidad
                        numProductos = Integer.parseInt(JOptionPane.showInputDialog("Información del producto \n"
                                + p.getDescripcion() + " Precio sin IVA: " + p.getPrecio()
                                + "€ Precio con IVA: %.2f€".formatted(p.getPrecio() * p.getIVA().getPORCENTAJE_IVA())));
                        excepcion = false;

                    } catch (NumberFormatException nfe) {//Captura que se introduzca una letra
                        String[] opciones = {"Aceptar"};

                        JOptionPane.showOptionDialog(null,
                                "Debes introducir un número", "TPV - Poke Zen", JOptionPane.DEFAULT_OPTION,
                                JOptionPane.QUESTION_MESSAGE, new ImageIcon("src/main/java/iconos/poke1.png"),
                                opciones, opciones[0]);
                    }
                    //Se mostrará el mensaje si la cantidad introducida excede al stock
                    if (numProductos > p.getStock()) {
                        String[] opciones = {"Aceptar"};

                        JOptionPane.showOptionDialog(null,
                                "Sólo nos queda " + p.getStock() + " " + p.getDescripcion()
                                + ", prueba de nuevo", "TPV - Poke Zen", JOptionPane.DEFAULT_OPTION,
                                JOptionPane.QUESTION_MESSAGE, new ImageIcon("src/main/java/iconos/poke1.png"),
                                opciones, opciones[0]);
                    }
                } while (excepcion);
            } while (numProductos > p.getStock());

            //Version cesta map
            if (tpv.getCarrito().getCarrito().containsKey(p.getID())) {
                tpv.getCarrito().getCarrito().put(p.getID(), tpv.getCarrito().getCarrito().get(p) + numProductos);
            } else {
                tpv.getCarrito().getCarrito().put(p.getID(), numProductos);
            }
        }
    }

//    private static void verCarrito(TPV tpv) {
//
//        double totalPagar = 0;
//        double totalConIva = 0;
//
//        En este string se irán añadiendo los nombres de los productos en la cesta,
//        su precio y la cantadidad,
//        también el precio total de la compra con iva y sin iva
//        String infoProductosCesta = "PRODUCTOS EN LA CESTA \n \n";
//
//        Version cesta map
//        for (Map.Entry<Integer, Integer> entrada : tpv.getCesta().entrySet()) {
//            infoProductosCesta += tpv.getMenuProductos().get(entrada.getKey()).getDescripcion()
//                    + "     Cant.: " + entrada.getValue()
//                    + "     " + tpv.getMenuProductos().get(entrada.getKey()).getPrecio() + "€ \n";
//
//            totalPagar += tpv.getMenuProductos().get(entrada.getKey()).getPrecio() * entrada.getValue();
//
//            totalConIva += tpv.getMenuProductos().get(entrada.getKey()).getPrecio()
//                    * tpv.getMenuProductos().get(entrada.getKey()).getIVA().getPORCENTAJE_IVA()
//                    * entrada.getValue();
//        }
//
//        coloco los resultados de las operaciones anteriores y se suma al string
//        infoProductosCesta += "\n" + "Total sin IVA: %.2f€".formatted(totalPagar)
//                + "     Total con IVA: %.2f€".formatted(totalConIva) + "\n";
//
//        String[] options = {"Finalizar compra", "Cancelar compra", "Volver"};
//        int opcionElegida = JOptionPane.showOptionDialog(null,
//                infoProductosCesta, "TPV - Poke Zen", JOptionPane.DEFAULT_OPTION,
//                JOptionPane.PLAIN_MESSAGE, new ImageIcon("src/main/java/iconos/cesta1.png"),
//                options, null);
//
//        switch (opcionElegida) {
//            case 0 -> {
//                pasarelaPago(tpv, totalPagar);
//            }
//            case 1 -> {
//                tpv.getCesta().clear();
//                seleccionarModo();
//            }
//            No necesito el case 2 (volver), ya que ya está organizado en
//            el método encenderTPV, añadir algo aquí haría 
//            que se abriera la ventana de categorías dos veces
//        }
//
//    }
//
//    private static void pasarelaPago(TPV tpv, double totalPagar) {
//
//        String digitosTarjeta = (String) JOptionPane.showInputDialog(null,
//                "Introduce los últimos 4 dígitos de tu tarjeta",
//                "TPV - Poke Zen", JOptionPane.OK_CANCEL_OPTION,
//                new ImageIcon("src/main/java/iconos/tarjeta1.png"),
//                null, null);
//
//        Primero comprobamos si los digitos coinciden con alguna tarjeta 
//        de la base de datos
//        if (UtilidadesTarjeta.numTarjetaValido(digitosTarjeta)) {
//
//            Pedimos en la misma pantalla la fecha de caducidad y cvv
//            JTextField mes = new JTextField();
//            JTextField anyo = new JTextField();
//            JTextField cvv = new JTextField();
//            Object[] message = {
//                "Introduce fecha de caducidad y CVV",
//                "Mes:", mes,
//                "Año:", anyo,
//                "CVV:", cvv
//            };
//
//            int option = 0;
//            boolean excepcion = true;
//
//            do {//Este do while es por si salta una excepción
//                try {
//
//                    option = JOptionPane.showConfirmDialog(null, message,
//                            "TPV - Poke Zen", JOptionPane.OK_CANCEL_OPTION);
//                    excepcion = false;
//                } catch (NumberFormatException nfe) {
//                }
//
//                if (option == JOptionPane.OK_OPTION) {
//
//                    LocalDate fechaCaducidad = LocalDate.MIN;
//                    excepcion = true;
//
//                    try {
//
//                        fechaCaducidad = (LocalDate) UtilidadesTarjeta
//                                .pedirMesAnyo(Integer.parseInt(mes.getText()),
//                                        Integer.parseInt(anyo.getText()));
//                        excepcion = false;
//
//                    } catch (NumberFormatException nfe) {
//                    }
//
//                    Se comprueba que fecha y el cvv son válidos 
//                    (son los que están guardados en la base de datos)
//                    if (UtilidadesTarjeta.fechaCaducidadYCVVValidos(digitosTarjeta, fechaCaducidad, cvv.getText())) {
//
//                        Lo siguiente es comprobar que hay saldo para pagar la suma del carrito
//                        if (UtilidadesTarjeta.saldoSuficiente(digitosTarjeta, totalPagar)) {
//
//                            for (int i = 0; i < UtilidadesTarjeta.baseDatosTarjeta().size(); i++) {
//
//                                Si hay saldo, se procede a restar la suma del carrito al saldo de la tarjeta
//                                if (digitosTarjeta.equals(UtilidadesTarjeta.baseDatosTarjeta().get(i)
//                                        .getNumTarjeta()
//                                        .substring(UtilidadesTarjeta.baseDatosTarjeta().get(i).getNumTarjeta().length() - 4,
//                                                UtilidadesTarjeta.baseDatosTarjeta().get(i).getNumTarjeta().length()))) {
//
//                                    UtilidadesTarjeta.baseDatosTarjeta().get(i).setSaldo(
//                                            UtilidadesTarjeta.baseDatosTarjeta().get(i).getSaldo() - totalPagar);
//                                }
//                            }
//
//                            También se resta la cantidad comprada al stock de la base de datos 
//                            for (Map.Entry<Integer, Integer> entrada : tpv.getCesta().entrySet()) {
//                                if (tpv.getMenuProductos().containsKey(entrada.getKey())) {
//                                    tpv.getMenuProductos().get(entrada.getKey())
//                                            .setStock(tpv.getMenuProductos()
//                                                    .get(entrada.getKey())
//                                                    .getStock() - entrada.getValue());
//                                }
//                            }
//
//                            Creamos el ticket con los datos anteriores
//                            Ticket t = new Ticket(new HashMap(tpv.getCesta()),
//                                    totalPagar, tpv.getFechaSistema(),
//                                    tpv.getHoraSistema());
//                            Se añade el ticket al listado del tpv
//                            tpv.getBaseDatosTicket().add(t);
//                            Se vacía la cesta
//                            tpv.getCesta().clear();
//
//                            String[] opciones = {"Aceptar"};
//
//                            Se muestra el ticket de compra
//                            JOptionPane.showOptionDialog(null,
//                                    t.toStringChulo(), "TPV - Poke Zen", JOptionPane.DEFAULT_OPTION,
//                                    JOptionPane.QUESTION_MESSAGE, new ImageIcon("src/main/java/iconos/tarjeta1.png"),
//                                    opciones, opciones[0]);
//
//                        } else {
//                            String[] opciones = {"Aceptar"};
//
//                            JOptionPane.showOptionDialog(null,
//                                    "No hay saldo suficiente, prueba de nuevo", "TPV - Poke Zen", JOptionPane.DEFAULT_OPTION,
//                                    JOptionPane.QUESTION_MESSAGE, new ImageIcon("src/main/java/iconos/tarjeta1.png"),
//                                    opciones, opciones[0]);
//                        }
//                    } else {
//                        String[] opciones = {"Aceptar"};
//
//                        JOptionPane.showOptionDialog(null,
//                                "Los datos no son correctos", "TPV - Poke Zen", JOptionPane.DEFAULT_OPTION,
//                                JOptionPane.QUESTION_MESSAGE, new ImageIcon("src/main/java/iconos/tarjeta1.png"),
//                                opciones, opciones[0]);
//                    }
//                }
//            } while (excepcion);
//
//        } else {
//            String[] opciones = {"Aceptar"};
//
//            JOptionPane.showOptionDialog(null,
//                    "Los digitos no son correctos", "TPV - Poke Zen", JOptionPane.DEFAULT_OPTION,
//                    JOptionPane.QUESTION_MESSAGE, new ImageIcon("src/main/java/iconos/tarjeta1.png"),
//                    opciones, opciones[0]);
//        }
//    }
}

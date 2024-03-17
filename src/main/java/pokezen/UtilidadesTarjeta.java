/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pokezen;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author francisco
 */
public class UtilidadesTarjeta {

    public static ArrayList<Tarjeta> baseDatosTarjeta() {
        ArrayList<Tarjeta> listaTarjetas = new ArrayList<>();

        listaTarjetas.add(new Tarjeta("1234567891234567",
                LocalDate.of(2026, 10, LocalDate.MIN.getDayOfMonth()),
                "123", "Noelia", 432));

        //Poco saldo
        listaTarjetas.add(new Tarjeta("1234567898912345",
                LocalDate.of(2024, 3, LocalDate.MIN.getDayOfMonth()),
                "456", "Fran", 10));

        //Caducada
        listaTarjetas.add(new Tarjeta("1234567894567891",
                LocalDate.of(2023, 12, LocalDate.MIN.getDayOfMonth()),
                "789", "Vico", 100000));

        return listaTarjetas;
    }

    public static boolean numTarjetaValido(String digitosCliente) {
        ArrayList<Tarjeta> baseDatosTarjeta = baseDatosTarjeta();

        for (int i = 0; i < baseDatosTarjeta.size(); i++) {

            //Con los 4 dígitos que introduce el cliente busco 
            //un numero de tarjeta que los tenga al final con substring,
            //que divide el número en el rango que yo diga entre dos índices
            if (digitosCliente.equals(baseDatosTarjeta.get(i)
                    .getNumTarjeta()
                    .substring(baseDatosTarjeta.get(i).getNumTarjeta().length() - 4,
                            baseDatosTarjeta.get(i).getNumTarjeta().length()))) {
                return true;
            }
        }
        return false;
    }

    //Este método es parecido al anterior, pero devuelve la tarjeta
    public static Tarjeta obtenerTarjetaCliente(String digitosCliente) {
        ArrayList<Tarjeta> baseDatosTarjeta = baseDatosTarjeta();
        Tarjeta tarjetaCliente = new Tarjeta();

        for (int i = 0; i < baseDatosTarjeta.size(); i++) {
            if (digitosCliente.equals(baseDatosTarjeta.get(i)
                    .getNumTarjeta()
                    .substring(baseDatosTarjeta.get(i).getNumTarjeta().length() - 4,
                            baseDatosTarjeta.get(i).getNumTarjeta().length()))) {
                tarjetaCliente = baseDatosTarjeta.get(i);
            }
        }
        return tarjetaCliente;
    }

    public static LocalDate pedirMesAnyo(int mes, int anyo) {
        LocalDate fecha = LocalDate.MIN;

        if (((mes >= LocalDate.MIN.getMonthValue() && mes <= LocalDate.MAX.getMonthValue())
                && (anyo >= LocalDate.MIN.getYear() && anyo <= LocalDate.MAX.getYear()))) {
            fecha = LocalDate.of(anyo, mes, LocalDate.MIN.getDayOfMonth());
        }

        return fecha;
    }

    public static boolean fechaCaducidadYCVVValidos(String digitosCliente, LocalDate fechaCaducidad, String CVV) {
        Tarjeta tarjetaCliente = obtenerTarjetaCliente(digitosCliente);

        //comprobamos que la fecha introducida no esté caducada
        //y que la fecha introducida es la misma que está guardada
        //en nuestra base de datos
        if (fechaCaducidad.isAfter(LocalDate.now())
                && fechaCaducidad.equals(tarjetaCliente.getFechaVencimiento())) {
            //En este otro if al que sólo se llega si la fecha está bien
            //compruebo que el cvv sea el correcto
            if (CVV.equals(tarjetaCliente.getCVV())) {
                return true;
            }
        }
        return false;
    }

    public static boolean saldoSuficiente(String digitosCliente, double cantidadAPagar) {
        Tarjeta tarjetaCliente = obtenerTarjetaCliente(digitosCliente);
        if (tarjetaCliente.getSaldo() >= cantidadAPagar) {
            return true;
        }
        return false;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Patrones;

public class Main
{
    public static void main(String[] args)
    {
        // Crear un único cajero Automático de dólares con 100 billetes de 20, 100 de 10, 
        // 10 monedas de 0.50, 10 de 0.25 y 1000 de 0.05
       
        
        Account cuenta1 = new Account(1, 100);
        AtmUK.getInstace().ingresarDinero(100, 20);
        AtmUK.getInstace().ingresarDinero(100, 10);
        AtmUK.getInstace().ingresarDinero(10, 0.5);
        AtmUK.getInstace().ingresarDinero(10, 0.25);
        AtmUK.getInstace().ingresarDinero(1000, 0.05);

        AtmUK.getInstace().transaction(cuenta1);




        // Crear 10 cuentas nuevas en dólares locale.US con un saldo inicial entre 100.00 y 1000.00 USD cada una.
        
        // Menú principal para seleccionar una de las 10 cuentas solo con el id
        
        // Mostrar el menú para realizar transacciones en el cajero automático
    }

    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Patrones;

/**
 *
 * @author Lenovo comp
 */
public interface Transaction {
    public double retirar(Double amount);
    public boolean depositar(int monto, int denominacion);
}

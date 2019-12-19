/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Patrones;

public class Manejador
{
    protected int monto;
    protected double denominacion;

    public Manejador(int monto, double denominacion){
        this.monto = monto; // Total de billetes
        this.denominacion = denominacion; // Valor de cada billete
    }

    public int getMonto(){ return monto; }
    public double getDenominacion(){ return denominacion; }
    public void setMonto(int monto){ this.monto = monto; }

     public double retirar(Double amount){
        // Implementar  
        if(monto>0){
            int cantidadBilletes = new Double(amount/denominacion).intValue();
            this.monto -= cantidadBilletes;
            return cantidadBilletes*this.denominacion;
        }
        return 0;
    }

    public boolean depositar(int monto, int denominacion){
        if(this.denominacion == denominacion){
            this.monto+=monto;
        }
        return false;
    }
}
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
public class ManejadorDouble implements Transaction{
    protected int monto;
    protected double denominacion;

    public ManejadorDouble(int monto, double denominacion){
        this.monto = monto; // Total de billetes
        this.denominacion = denominacion; // Valor de cada billete
    }
    public int getMonto(){ return monto; }
    public double getDenominacion(){ return denominacion; }
    public void setMonto(int monto){ this.monto = monto; }
    
    /*
    metodo que disminye la catidad de billetes de esa denominacion
    y retorna la cantidad de dinero que restara
    */
    @Override
    public double retirar(Double amount){
        // Implementar  
        if(monto-amount>0){
            int cantidadBilletes = new Double(amount/denominacion).intValue();
            this.monto -= cantidadBilletes;
            return cantidadBilletes*this.denominacion;
        }
        return 0;
    }
    @Override
    public boolean depositar(int monto, int denominacion){
        if(this.denominacion == denominacion){
            this.monto+=monto;
        }
        return false;
    }
}

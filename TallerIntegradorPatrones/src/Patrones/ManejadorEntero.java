/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Patrones;

public class ManejadorEntero implements Transaction //Strategy
{
    protected int monto;
    protected int denominacion;

    public ManejadorEntero(int monto, double denominacion){
        this.monto = monto; // Total de billetes
        this.denominacion = new Double(denominacion).intValue(); // Valor de cada billete
    }
    public int getMonto(){ return monto; }
    public int getDenominacion(){ return denominacion; }
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
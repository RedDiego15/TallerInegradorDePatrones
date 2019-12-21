/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Patrones;

public abstract class AccountBuilder {
    
    protected Account account;

    public Account getAccount() {
        return account;
    }
    
    public abstract void definirId();
    public abstract void definirLocale();
    public abstract void definirAmount();
    public abstract void definirCurrency();
}

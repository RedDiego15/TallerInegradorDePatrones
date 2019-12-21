/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Patrones;

import java.util.Currency;
import java.util.Locale;

public class ConcreteAccount10 extends AccountBuilder {

    @Override
    public void definirId() {
        account = new Account(0,0.0);
        account.setId(62345);
    }

    @Override
    public void definirLocale() {
        account.setLocale(Locale.US);
    }

    @Override
    public void definirAmount() {
       account.setAmount(1000.00);
    }

    @Override
    public void definirCurrency() {
        account.setC(Currency.getInstance(Locale.US));
    }

    
    
}

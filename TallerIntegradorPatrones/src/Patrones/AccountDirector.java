/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Patrones;

public class AccountDirector {
    
    private AccountBuilder accountBuilder;

    public AccountDirector(AccountBuilder accountBuilder) {
        this.accountBuilder = accountBuilder;
    }
    
    public void changeBuilder(AccountBuilder accountBuilder){
        
    }
    
    public void makeAccount(){
        
        accountBuilder.definirId();
        accountBuilder.definirLocale();
        accountBuilder.definirAmount();
        accountBuilder.definirCurrency();
    }
    
    public Account getAccount(){
        return accountBuilder.getAccount();
    }
    
}

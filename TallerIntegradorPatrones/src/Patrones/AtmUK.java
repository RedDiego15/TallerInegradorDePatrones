/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Patrones;

import java.util.ArrayList;
import java.util.Currency;
import java.util.Iterator;
import java.util.Locale;
import java.util.Scanner;

public class AtmUK {
    protected final Locale currency=Locale.UK;
    protected double dinero = 0;
    protected ArrayList <Transaction> manejadores; // Cada manejador puede entregar dinero de una sola denominación  Chain responsability
    private Scanner sc = new Scanner(System.in);

    // -----------------
    public AtmUK() {
      manejadores = new ArrayList<Transaction>();
    }
    // -----------------
    public double getTotal() {
        return this.dinero;
    }

    // -----------------
    public void sacarDinero(double dinero) {
        this.dinero -= dinero;
        
        /*
        Al tener un arrayList vamos a aprovechar el orden que tiene y en la primera posicion pondremos los manejadores de mayor 
        denominacion, para que entregue la menor cantidad de billetas y monedas posibles
        */
        Iterator<Transaction> it = this.manejadores.iterator();
        
        while(dinero>0 || it.hasNext()){
            Transaction m = it.next();
            if(m instanceof ManejadorEntero){
                ManejadorEntero mje = (ManejadorEntero)(m);
                dinero-=mje.retirar(dinero);  
            }else{
                ManejadorDouble mjd = (ManejadorDouble)(m);
                dinero-=mjd.retirar(dinero);   
            }
            System.out.println(dinero);
        }
        // Todo: realizar el proceso de sacar de cada manejador la cantidad requerida
    }

    // -----------------
    public void ingresarDinero(double dinero, int denominacion) {
        this.dinero += dinero;
        // Todo: Sólo se puede depositar billetes de una sola denominación y agregarse al manejador correspondiente
        Iterator<Transaction> it = this.manejadores.iterator();
  
        while(it.hasNext()){
            Transaction m = it.next();
            if(m instanceof ManejadorEntero){
                ManejadorEntero mje = (ManejadorEntero)(m);
                if(mje.getDenominacion()==denominacion){
                    mje.setMonto(mje.getMonto()+new Double(dinero/denominacion).intValue());  
                    break;
                }
            }else{
                ManejadorDouble mjd = (ManejadorDouble)(m);
                if(mjd.getDenominacion()==denominacion){
                    mjd.setMonto(mjd.getMonto()+new Double(dinero/denominacion).intValue());  
                    break;
                }
                
            }
        }
   
    }

    public void addManejador(Transaction m){
        manejadores.add(m);
    }
    public Transaction removeManejador(int i){
        return manejadores.remove(i);
    }

    //Dentro de las transacciones se debe llamar al ATM para hacer el retiro o deposito de la cuenta correspondiente
    public void transaction(Account cuenta){
        // here is where most of the work is
        int choice; 
        System.out.println("Please select an option"); 
        System.out.println("1. Withdraw");
        System.out.println("2. Deposit");
        System.out.println("3. Balance");
        System.out.println("4. Balance ATM");
        choice = sc.nextInt();
        switch(choice){
            case 1:
                float amount; 
                System.out.println("Please enter amount to withdraw: "); 
                amount = sc.nextFloat();
                if(amount > cuenta.getAmount() || amount == 0){
                    System.out.println("You have insufficient funds\n\n"); 
                    anotherTransaction(cuenta); // ask if they want another transaction
                } else {
                    // Todo: verificar que se puede realizar el retiro del atm
                    if(dinero-amount>0 && (cuenta.getAmount()-amount>0)){
                        sacarDinero(amount);
                        cuenta.withdraw(amount);
                        System.out.println("You have withdrawn "+amount+" and your new balance is "+cuenta.getAmount());
                    }else{
                        System.out.println("saldo Insuficiente");
                    }

                    // Todo: actualizar tanto la cuenta como el atm y de los manejadores
                    // cuenta.retirar(amount);
                    // AtmUK.sacarDinero(amount);

                    // Todo: Mostrar resumen de transacción o error
                    // "You have withdrawn "+amount+" and your new balance is "+balance;
                    anotherTransaction(cuenta); 
                }
            break; 
            case 2:
                    // option number 2 is depositing 
                    float deposit; 
                    System.out.println("Please enter amount you would wish to deposit: "); 
                    deposit = sc.nextFloat();
                    // Todo: actualizar tanto la cuenta como el atm
                    
                    // Todo: Mostrar resumen de transacción o error
                    // "You have withdrawn "+amount+" and your new balance is "+balance;
                    anotherTransaction(cuenta);
            break; 
            case 3:
                    // Todo: mostrar el balance de la cuenta
                    // "Your balance is "+balance
                    anotherTransaction(cuenta); 
            break;
            case 4:
                    // Todo: mostrar el balance del ATM con los billetes en cada manejador
                    anotherTransaction(cuenta); 
            break;
            default:
                    System.out.println("Invalid option:\n\n"); 
                    anotherTransaction(cuenta);
            break;
        }
    }
    public void anotherTransaction(Account cuenta){
        System.out.println("Do you want another transaction?\n\nPress 1 for another transaction\n2 To exit");
        int anotherTransaction = sc.nextInt();
        if(anotherTransaction == 1){
            transaction(cuenta); // call transaction method
        } else if(anotherTransaction == 2){
            System.out.println("Thanks for choosing us. Good Bye!");
        } else {
            System.out.println("Invalid choice\n\n");
            anotherTransaction(cuenta);
        }
    }

    
}

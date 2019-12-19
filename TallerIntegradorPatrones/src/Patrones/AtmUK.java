/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Patrones;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;
import java.util.Scanner;

public class AtmUK {
    protected final Locale currency=Locale.UK;
    protected double dinero = 0;
    protected ArrayList <Manejador> manejadores; // Cada manejador puede entregar dinero de una sola denominación  Chain responsability
    private Scanner sc = new Scanner(System.in);
    private static AtmUK instace = new AtmUK();

    public static AtmUK getInstace() {
        return instace;
    }

    // -----------------
    private  AtmUK() { //Singleton
      manejadores = new ArrayList<Manejador>();
      this.addManejador(new Manejador(0,20));
      this.addManejador(new Manejador(0,10));
      this.addManejador(new Manejador(0,0.50));
      this.addManejador(new Manejador(0,0.25));
      this.addManejador(new Manejador(0,0.05));
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
        Iterator<Manejador> it = this.manejadores.iterator();
        
        while(dinero>0 || it.hasNext()){
            Manejador m = it.next();
            System.out.println(m.getDenominacion());
            dinero -= m.retirar(dinero);
        }
        // Todo: realizar el proceso de sacar de cada manejador la cantidad requerida
    }

    // -----------------
   
    public void ingresarDinero(double dinero, double denominacion) {
        this.dinero += dinero;
        // Todo: Sólo se puede depositar billetes de una sola denominación y agregarse al manejador correspondiente
        Iterator<Manejador> it = this.manejadores.iterator();
  
        while(it.hasNext()){
            Manejador m = it.next();
            if(m.getDenominacion()==denominacion){
                 m.setMonto(m.getMonto()+new Double(dinero/denominacion).intValue());
                 break;
            }

            
            /*if(m instanceof ManejadorEntero){
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
                
            }*/
        }
   
    }

    public void addManejador(Manejador m){
        manejadores.add(m);
    }
    public Manejador removeManejador(int i){
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

                    // Todo: actualizar tanto la cuenta como el atm y de los manejadores
                    // cuenta.retirar(amount);
                    // AtmUK.sacarDinero(amount);
                    sacarDinero(amount);
                    cuenta.withdraw(amount);
                    System.out.println("You have withdrawn "+amount+" and your new balance is "+cuenta.getAmount());

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
                System.out.println("Please enter denomination of your bills: "); 
                float denomination = sc.nextFloat();
                this.ingresarDinero(deposit, denomination);
                cuenta.deposit(deposit);
                // Todo: actualizar tanto la cuenta como el atm

                // Todo: Mostrar resumen de transacción o error
                System.out.println("You have Deposit: "+deposit+" and your new balance is "+cuenta.getAmount());
                anotherTransaction(cuenta);
            break; 
            case 3:
                // Todo: mostrar el balance de la cuenta
                System.out.println("Your balance is "+cuenta.getAmount());
                anotherTransaction(cuenta); 
            break;
            case 4:
                // Todo: mostrar el balance del ATM con los billetes en cada manejador
                System.out.println("Dinero disponible en el atm: "+dinero);
                Iterator<Manejador> it = this.manejadores.iterator();
        
                while(it.hasNext()){
                    Manejador m = it.next();
                    System.out.println("Quedan: "+m.getMonto()+" Billetes de: "+m.getDenominacion()); 

                }
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

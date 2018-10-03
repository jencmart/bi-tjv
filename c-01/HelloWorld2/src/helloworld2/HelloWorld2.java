/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helloworld2;

/**
 *
 * @author jencmart
 */
public class HelloWorld2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
       MessagePrinter msg = new MessagePrinter();
        
       MessagePrinter msg2 = new MessagePrinter("Ahoj Martine. Ses pekna pica!");
       
        System.out.println(msg); // je vidno ze neni treba psat toString() prtrintln to vyresi sam . aha ...
        System.out.println(msg2.toString()); 
        
    }
    
}

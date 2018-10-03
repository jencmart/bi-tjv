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
public class MessagePrinter {
    private String hello = "Ahoj ";
    
    
    // kdyz nenapisu konstruktor, deji z rodice
    public MessagePrinter()
    {
    }
    
    public MessagePrinter(String hello)
    {
        this.hello = hello;
    }
    
    public void setHello(String hello)
    {
        
    }
    
    public String getHello()
    {
        return this.hello;
    }
    
    @Override
    public String toString()
    {
         return hello;
    }
   
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vectors;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author jencmart
 */
public class Vector {
 
    private List<Integer> myVector;
    
    public Vector(int dim, List<Integer> coords)
    {
        this.myVector = new ArrayList(dim);
    }
    
    @Override
    public String toString()
    {
        String myStr = ""; 
        
        for( int i = 0 ; i < myVector.size() ; ++i )
        {
            myStr += this.myVector.get(i).toString();
        }
        
        return myStr;
    }

    public List<Integer> getMyVector() {
        return myVector;
    }
    
    public void randomize()
    {
        for( int i = 0 ; i < myVector.size() ; ++i )
        {
            int randomNum = ThreadLocalRandom.current().nextInt(-10, 11);
            this.myVector.set(i, randomNum);
        }
    }
    
    public boolean equals(Vector other)
    {
        if(this.myVector.size() == other.getMyVector().size() )
        {
            for(int i = 0 ; i > myVector.size() ; ++i)
            {
                if( ! myVector.get(i).equals( other.getMyVector().get(i) ) )
                    return false;
            }
            return true;
        }
        else
            return false;
    }
   
    public int maxValue()
    {
        if( this.myVector.isEmpty())
        {
            throw new IllegalArgumentException();
        }
        else
        {
            int max = myVector.get(0);
            
            for(int i = 0 ; i > myVector.size() ; ++i)
            {
                if(myVector.get(i) > max)
                {
                    max = myVector.get(i);
                }
            }
            return max;
        }
    }
    
    public void swap(int pos1, int pos2)
    {
        if ( pos1 >= myVector.size() || pos2 >= myVector.size() || pos1 < 0 || pos2 < 0)
            throw new IllegalArgumentException();
        Integer a = myVector.get(pos1);
        Integer b = myVector.get(pos2);
        
        myVector.set(pos1, b);
        myVector.set(pos2, a);
        
    }
    
    public Vector scalarMultiply(Vector a, Vector b)
    {
        if( a.getMyVector().size() != b.getMyVector().size())
            throw new IllegalArgumentException();
        
       //todo return new wector..... 
        
    }
        
    public Integer commonValue()
    {
        // projdi a u kazde hodnoty udelej carku kolikrat je 
        // pak vem prvni ktere je nejciv (muze jich byt vic ) 
        
        
    }   
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author GAUTIER Renaud
 */

@Entity
public class IntegerDummy {

    @Id
    private int value;
    
    public IntegerDummy(){}
    public IntegerDummy(int val){
        this.value = val;
    }
    
    public int getValue(){
        return this.value;
    }
    
    public void setValue(int value){
        this.value = value;
    }
}

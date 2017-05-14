/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brainfuckcompiler;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Stack;

/**
 *
 * @author krzysztof
 */
public class Parser {
  
  
  private ArrayList<Integer> stack;
  private int pointer;
  private String  program="";
  private char[] charProgram;
  private Stack loopStack;
  private int applicationPointer=0;

  public Parser() {
    this.loopStack = new Stack();
  }
  
  private void moveLeft(){ 
          this.pointer--;
  }

  private void moveRight() {
          this.pointer++;
  }

  private void increase() {
    
    Integer valueAtPointer =  this.stack.get(this.pointer);
    this.stack.set(this.pointer, valueAtPointer++);
  }

  private void decrease() {
    Integer valueAtPointer =  this.stack.get(this.pointer);
    this.stack.set(this.pointer, valueAtPointer--);
  }

  private void jumpLeft() {
    if (this.read()==0){
      this.applicationPointer++;
    }else{
      this.applicationPointer = (int)this.loopStack.peek();
    }
  }

  private void jumpRight() {
    if (this.read()==0){
      this.applicationPointer = (int)this.loopStack.peek();
    }else{
      this.loopStack.push(this.applicationPointer);
      this.applicationPointer++;
    }
    
  }

  private Integer read() {
    return this.stack.get(pointer);
  }

  private void write(){ 
          System.out.print((char)this.read().intValue());
  }
  
  private void interpret(char x){
    switch (x){
      case '+':
        this.increase();
        break;
      case '-':
        this.decrease();
        break;
      case '.':
        this.write();
      case '>':
        this.moveRight();
        break;
      case '<':
        this.moveLeft();
      case '[':
        this.jumpRight();
      case ']':
        this.jumpLeft();
    }
    
   // System.out.println(x);
  
  }

  public void parse() {
    
    while(this.applicationPointer<this.charProgram.length){
      interpret(this.program.charAt(this.applicationPointer));
    
    }

  }
     
  public void concat(String line){
    
    this.program+=line;
  }

  
}

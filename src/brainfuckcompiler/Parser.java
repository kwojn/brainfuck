/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brainfuckcompiler;

import java.lang.reflect.Array;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Stack;

/**
 *
 * @author krzysztof
 */
public class Parser {
  
  
  private ArrayList<Integer> stack = new ArrayList<Integer>();
  private int pointer=0;
  private String  program="";
  private char[] charProgram;
  private Stack<Integer> loopStack = new Stack<Integer>();
  private int applicationPointer=0;
  public static final int STACK_SIZE = 1024;

   
  private void moveLeft(){ 
          this.pointer-=1;
          
  }

  private void moveRight() {
          this.pointer+=1;
          
  }

  private void increase() {
    
    int valueAtPointer =  this.stack.get(this.pointer);
    this.stack.set(this.pointer, ++valueAtPointer);
   
  }

  private void decrease() {
    int valueAtPointer =  this.stack.get(this.pointer);
    this.stack.set(this.pointer, --valueAtPointer);
   
  }

  private void jumpLeft() {
    if (this.read()==0){
      this.applicationPointer++;
      this.loopStack.pop();
    }else{
      this.applicationPointer = (int)this.loopStack.peek();
    }
  }

  private void jumpRight() {
    if (this.read()==0){
      this.applicationPointer = (int)this.loopStack.peek();
    }else{
      this.loopStack.push(this.applicationPointer);
     
    }
    
  }

  private Integer read() {
    return this.stack.get(this.pointer);
  }

  private void write(){ 
          System.out.print((char)this.read().intValue());
  }
  
  private void interpret(char x){
    switch (x){
      case '+':
        this.increase();
        this.applicationPointer++;
        break;
      case '-':
        this.decrease();
        this.applicationPointer++;
        break;
      case '.':
        this.write();
        this.applicationPointer++;
        break;
      case '>':
        this.moveRight();
        this.applicationPointer++;
        break;
      case '<':
        this.moveLeft();
        this.applicationPointer++;
        break;
      case '[':
        this.jumpRight();
        this.applicationPointer++;
        break;
      case ']':
        this.jumpLeft();
        break;
      default: this.applicationPointer++;
    }
     
    
  
  }

  public void parse() {
    this.initStack();
    int l = this.program.length();
    while(this.applicationPointer<l){
      interpret(this.program.charAt(this.applicationPointer));
    }
    System.out.println();
    System.out.println(this.stack);
    System.out.println(this.pointer);
    
    

  }
  
  public void initStack(){
    int i;
    for (i=0;i<STACK_SIZE;i++){
      this.stack.add(0);
    }
  }
     
  public void concat(String line){
    
    this.program+=line;
  }

  
}

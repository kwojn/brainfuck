/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brainfuckcompiler;

import java.awt.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author krzysztof
 */
public class BrainFuckCompiler {

  /**
   * @param args the command line arguments
   */
  
  
  public static void main(String[] args) {
    // TODO code application logic here
    try {
      Parser parser = new Parser();
      FileReader file = new FileReader("brainfuck.json");
      BufferedReader reader = new BufferedReader(file);
      String data = null;
      while ((data = reader.readLine()) != null) {
        parser.concat(data);
        
      }
      parser.parse();
    } catch (IOException e) {
      System.out.print("Dupa nie ma pliku");
    }

  }

}

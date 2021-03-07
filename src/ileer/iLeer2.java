/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ileer;

import static ileer.ILeer.fileMove;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.commons.io.FilenameUtils;

/**
 *
 * @author dev
 */
public class iLeer2 {
    
       public static void fileMove(String sourceFile, String destinationFile) {
              System.out.println("Desde: " + sourceFile);
              System.out.println("Hacia: " + destinationFile);
              try {
                  File inFile = new File(sourceFile);
                  File outFile = new File(destinationFile);
                  FileInputStream in = new FileInputStream(inFile);
                  FileOutputStream out = new FileOutputStream(outFile);
                  int c;
                  while ((c = in.read()) != -1)
                      out.write(c);
                      in.close();
                      out.close();

                      File file = new File(sourceFile);
                      if (file.exists()) {
                          file.delete();
                         }
                    } catch (IOException e) {System.err.println("Hubo un error de entrada/salida!!!");}
                   }      
    
     public static void main(String[] args) {
            String sDirectorio = "/Users/dev/NetBeansProjects/iCompras/emails";
            String dirxml="/Users/dev/NetBeansProjects/iCompras/emails";
            String dirxmlout="/Users/dev/NetBeansProjects/iCompras/procesados";
             //Lectura de Ficheros del Directorio
             String s;
             File f = new File(sDirectorio.toString());
             File[] ficheros = f.listFiles();
             for (int x=0;x<ficheros.length;x++){
                  System.out.println(ficheros[x].getName());
                  s=ficheros[x].getName();
                  String fileName = s;
		  String fe = FilenameUtils.getExtension(fileName);
		  System.out.println("File extension is : "+fe);
                     if (fe.equals("xml") || fe.equals("XML")){
                         System.out.println(s);
                         //leerxml(s);
                         }else{
                         fileMove(dirxml+"/"+s, dirxmlout+"/"+s);     
                        }
                      }
                if (ficheros.length==0){
                    System.out.println("No Hay Xml para Procesar");  
                }
    }
}

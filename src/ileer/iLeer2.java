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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.commons.io.FilenameUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

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
            String dirxml="/Users/dev/NetBeansProjects/iCompras/emails";
            String dirxmlout="/Users/dev/NetBeansProjects/iCompras/procesados";
             //Lectura de Ficheros del Directorio
             String s;
             File f = new File(dirxml.toString());
             File[] ficheros = f.listFiles();
             for (int x=0;x<ficheros.length;x++){
                  System.out.println(ficheros[x].getName());
                  s=ficheros[x].getName();
                  String fileName = s;
		  String fe = FilenameUtils.getExtension(fileName);
		  System.out.println("File extension is : "+fe);
                     if (fe.equals("xml") || fe.equals("XML")){
                         System.out.println(s);
                         leerxml2(s,dirxml,dirxmlout);
                       //  fileMove(dirxml+"/"+s, dirxmlout+"/"+s);  
                        
                         }else{
                       //  fileMove(dirxml+"/"+s, dirxmlout+"/"+s);     
                        }
                      }
                if (ficheros.length==0){
                    System.out.println("No Hay Xml para Procesar");  
                }
    }

    private static void leerxml2(String archivo,String dirxml,String dirxmlout) {
           String txtiva="0";String txtneto="0";String txtciudad="";String txtcomuna="";String txtgiro="";
           String txtdireccion="";String txtnumero="";String txtdte="";String txtrut="";String txtrazon="";
           String txttipo="";String txtfecha="";String txttotal="";
           File inputFile = new File(dirxml+"/"+archivo);
           System.out.println("Procesar:"+dirxml+"/"+archivo);
           DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
           DocumentBuilder docBuilder = null;
            try {
                docBuilder = docBuilderFactory.newDocumentBuilder();
            } catch (ParserConfigurationException ex) {
                //Logger.getLogger(panelcompras.class.getName()).log(Level.SEVERE, null, ex);
            }
            Document doc = null;
            try {
                doc = docBuilder.parse (inputFile);
            } catch (SAXException ex) {
                //Logger.getLogger(panelcompras.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                //Logger.getLogger(panelcompras.class.getName()).log(Level.SEVERE, null, ex);
            }

            doc.getDocumentElement ().normalize ();

            String cl=doc.getDocumentElement().getNodeName().toString();
            System.out.println("cl->"+cl);
             NodeList listOfPersons = doc.getElementsByTagName("EnvioDTE");
            int totalPersons = listOfPersons.getLength();
            System.out.println("Total of DTE : " + totalPersons);  
            
            
               if(cl.equals("EnvioDTE")){            
                  System.out.println("Procesando DTE");
                  for(int s=0; s<listOfPersons.getLength() ; s++){
                    Node firstPersonNode = listOfPersons.item(s);
                    if(firstPersonNode.getNodeType() == Node.ELEMENT_NODE){
                        Element firstPersonElement = (Element)firstPersonNode;
                        //-------rut emisor
                        NodeList rute = firstPersonElement.getElementsByTagName("RUTEmisor");
                        Element drute = (Element)rute.item(0);
                          if (drute!= null) {
                             txtrut=drute.getTextContent().toString();
                        }
                        System.out.println("Rut Emisor:"+txtrut);
                        //-------razon emisor
                        NodeList razone = firstPersonElement.getElementsByTagName("RznSoc");
                        Element drazon = (Element)razone.item(0);
                        if (drazon!= null) {
                             txtrazon=drazon.getTextContent().toString();
                        }
                        System.out.println("Razon Emisor:"+ txtrazon);
                        //-------folio
                        NodeList folioe = firstPersonElement.getElementsByTagName("Folio");
                        Element dfolio= (Element)folioe.item(0);
                        if (dfolio!= null) {
                             txtnumero=dfolio.getTextContent().toString();
                             txtdte=dfolio.getTextContent().toString();
                        }
                        System.out.println("Folio:"+txtnumero);
                        //-------tipo
                        NodeList tipoe = firstPersonElement.getElementsByTagName("TipoDTE");
                        Element dtipo= (Element)tipoe.item(0);
                         if (dtipo!= null) {
                             txttipo=dtipo.getTextContent().toString();
                        }
                        System.out.println("Tipo->"+txttipo);
                        //-------fecha
                        NodeList fechae = firstPersonElement.getElementsByTagName("FchEmis");
                        Element dfecha= (Element)fechae.item(0);
                         if (dfecha!= null) {
                             txtfecha=dfecha.getTextContent().toString();
                        }
                      
                        System.out.println("Fecha->"+ txtfecha);
                        //-------total
                        NodeList totale = firstPersonElement.getElementsByTagName("MntTotal");
                        Element dtotal= (Element)totale.item(0);
                         if (dtotal!= null) {
                             txttotal=dtotal.getTextContent().toString();
                        }
                        System.out.println("Total->"+txttotal);    
                        //-------iva
                        NodeList ivae = firstPersonElement.getElementsByTagName("IVA");
                        Element diva= (Element)ivae.item(0);
                        if (diva!= null) {
                           txtiva=diva.getTextContent().toString(); 
                        }
                        
                        System.out.println("Iva->"+txtiva);
                        //-------neto
                        NodeList netoe = firstPersonElement.getElementsByTagName("MntNeto");
                        Element dneto= (Element)netoe.item(0);
                         if (dneto!= null) {
                           txtneto=dneto.getTextContent().toString(); 
                        }
                       
                        System.out.println("Neto->"+txtneto);
                        //-------direccion
                        NodeList direccione = firstPersonElement.getElementsByTagName("DirOrigen");
                        Element ddireccion =(Element)direccione.item(0);
                           if (ddireccion!= null) {
                             txtdireccion=ddireccion.getTextContent().toString();
                        }
                        
                        System.out.println("Direccion->"+txtdireccion);
                        //-------Giro
                        NodeList giroe = firstPersonElement.getElementsByTagName("GiroEmis");
                        Element dgiro =(Element)giroe.item(0);
                            if (dgiro!= null) {
                             txtgiro=dgiro.getTextContent().toString();
                        }
                        
                        System.out.println("Giro->"+txtgiro);
                        //-------Comuna
                        NodeList come = firstPersonElement.getElementsByTagName("CmnaOrigen");
                        Element dcome =(Element)come.item(0);
                            if (dcome!= null) {
                             txtcomuna=dcome.getTextContent().toString();
                        }
                       
                        System.out.println("Comuna->"+txtcomuna);
                        //-------Ciudad
                        NodeList ciue = firstPersonElement.getElementsByTagName("CiudadOrigen");
                        Element dciue =(Element)ciue.item(0);
                        if (dciue != null) {
                           txtciudad=dciue.getTextContent().toString(); 
                        }
                        System.out.println("Ciudad->"+txtciudad);
                        
                        
                        //insertar encabezado
                        String JdbcURL = "jdbc:mysql://45.236.129.231:3306/Romance?useSSL=false";
                        String Username = "romance";
                        String password = "armijo183ISLA";
                        Connection con = null;
                        PreparedStatement pstmt = null;
                        String query = "INSERT INTO documentosc(ciudad,comuna,giro,direccion,numero,dte,rut,razon,tipo,fecha,neto,iva,total)" + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                        try {
                            con = DriverManager.getConnection(JdbcURL, Username, password);
                            pstmt = con.prepareStatement(query);
                            pstmt.setString(1, txtciudad);
                            pstmt.setString(2, txtcomuna);
                            pstmt.setString(3, txtgiro);
                            pstmt.setString(4, txtdireccion);
                            pstmt.setString(5, txtnumero);
                            pstmt.setString(6, txtdte);
                            pstmt.setString(7, txtrut);
                            pstmt.setString(8, txtrazon);
                            pstmt.setString(9, txttipo);
                            pstmt.setString(10, txtfecha);
                            pstmt.setString(11, txtneto);
                            pstmt.setString(12, txtiva);
                            pstmt.setString(13, txttotal);
                            
                            int status = pstmt.executeUpdate();
                            if(status > 0) {
                               System.out.println("Record is inserted successfully !!!");
                               }
                            } catch(Exception e){
                            e.printStackTrace();
                          }
                        
                        //fin insertar encabezado
                        
                        //detalle
                            //-------NmbItem
                            NodeList nombre= firstPersonElement.getElementsByTagName("NmbItem");
                            int x=nombre.getLength();
                            System.out.println("largo->"+x);
                            
                            Element dnombre =(Element)nombre.item(0);
                            // System.out.println("Producto->"+dnombre.getTextContent().toString());
                            //------NmbItem
                            //------PrcItem
                            NodeList precio= firstPersonElement.getElementsByTagName("PrcItem");
                            //int x=precio.getLength();
                            //System.out.println(x);
                            Element dprecio =(Element)precio.item(0);
                            // System.out.println("Precio->"+dprecio.getTextContent().toString());
                            //------PrcItem
                   
                            //-------MontoItem
                            NodeList monto= firstPersonElement.getElementsByTagName("MontoItem");
                            //int x=nombre.getLength();
                            //System.out.println(x);
                            Element dmonto =(Element)monto.item(0);
                            // System.out.println("Monto->"+dmonto.getTextContent().toString());
                            //------MontoItem
                            //-------QtyItem
                            NodeList cant= firstPersonElement.getElementsByTagName("QtyItem");
                            //int x=nombre.getLength();
                            //System.out.println(x);
                            Element dcant =(Element)cant.item(0);
                        
                        //fin detalle
                        
                        
                      }
                     }
               }
              }


}

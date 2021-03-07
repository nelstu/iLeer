/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ileer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;
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
public class ILeer {

    /**
     * @param args the command line arguments
     */
    
        private static void cargarDriver() {
    try {
      Class.forName("com.mysql.jdbc.Driver");
    }catch(Exception ex) {

    }
  } 
    
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
        
        
   private static void leerxml(String archivo){
   //leer config

       
          String dirxml="/Users/dev/NetBeansProjects/iCompras/emails";
       
       String dirxmlout="/Users/dev/NetBeansProjects/iCompras/procesados";
       
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

            // normalize text representation
            doc.getDocumentElement ().normalize ();
            //System.out.println ("Root element of the doc is " + 
             //    doc.getDocumentElement().getNodeName());
            String cl=doc.getDocumentElement().getNodeName().toString();
            System.out.println("cl->"+cl);
             NodeList listOfPersons = doc.getElementsByTagName("EnvioDTE");
            int totalPersons = listOfPersons.getLength();
            System.out.println("Total of DTE : " + totalPersons);
            String vdneto="0";
            String vdiva="0";
            String vdgiro="";
            String ne="";
            String iv="";
            String n1="";
            String n2="";
            String n3="";
            String n4="";
            String n5="";
            String x1="0";
            String x2="0";
            
            String ip="45.236.129.231";
            String base="Romance";
            String user="romance";
            String pass="armijo183ISLA";
            
               String dbURL = "jdbc:mysql://"+ip+":3306/"+base; 
        String username =user;
        String password = pass; 
           
            
            
            if(cl.equals("EnvioDTE")){            
            System.out.println("Aqui");
              for(int s=0; s<listOfPersons.getLength() ; s++){


                Node firstPersonNode = listOfPersons.item(s);
                if(firstPersonNode.getNodeType() == Node.ELEMENT_NODE){


                    Element firstPersonElement = (Element)firstPersonNode;

                    //-------rut emisor
                    NodeList rute = firstPersonElement.getElementsByTagName("RUTEmisor");
                    Element drute = (Element)rute.item(0);
                    System.out.println(drute.getTextContent().toString());
                   //------rut emisor
                   //-------razon
                    NodeList razone = firstPersonElement.getElementsByTagName("RznSoc");
                    Element drazon = (Element)razone.item(0);
                    System.out.println(drazon.getTextContent().toString());
                   //------razon
                     //-------folio
                    NodeList folioe = firstPersonElement.getElementsByTagName("Folio");
                    Element dfolio= (Element)folioe.item(0);
       //             System.out.println(dfolio.getTextContent().toString());
                   //------ folio
                     //-------tipo
                    NodeList tipoe = firstPersonElement.getElementsByTagName("TipoDTE");
                    Element dtipo= (Element)tipoe.item(0);
                    System.out.println("Tipo->"+dtipo.getTextContent().toString());
                    String ver=dtipo.getTextContent().toString();
                    
                    
                    
                     if (ver.equals("52")  ){
                         NodeList Indtraslado = firstPersonElement.getElementsByTagName("IndTraslado");
                         Element dIndtraslado  =(Element)Indtraslado.item(0);
                          System.out.println(dIndtraslado);
                          if (dIndtraslado!= null) {
                         x1 =dIndtraslado.getTextContent();
                          }else{
                              
                          }
                         NodeList Tipodespacho = firstPersonElement.getElementsByTagName("TipoDespacho");
                         Element dTipodespacho =(Element)Tipodespacho.item(0);
                            if (dTipodespacho!= null) {
                         x2 =dTipodespacho.getTextContent();
                     
                    
                          }else{
                              
                          }
                    
                    }
                    
                    
                    
                   //------tipo
                      //-------fecha
                    NodeList fechae = firstPersonElement.getElementsByTagName("FchEmis");
                    Element dfecha= (Element)fechae.item(0);
           //         System.out.println(dfecha.getTextContent().toString());
                   //------fecha
                   
                     //-------total
                    NodeList totale = firstPersonElement.getElementsByTagName("MntTotal");
                    Element dtotal= (Element)totale.item(0);
             //       System.out.println(dtotal.getTextContent().toString());
                   //------total
                   if (ver.equals("33") ||ver.equals("61")  || ver.equals("52")){
                     //-------iva
                    NodeList ivae = firstPersonElement.getElementsByTagName("IVA");
                    Element diva= (Element)ivae.item(0);
               //     System.out.println(diva.getTextContent().toString());
                    
                    
                        if (diva!= null) {
                       vdiva=diva.getTextContent().toString();
                          }else{
                              
                          }
                   //------iva
                   
                    //-------neto
                    NodeList netoe = firstPersonElement.getElementsByTagName("MntNeto");
                    Element dneto= (Element)netoe.item(0);
               //     System.out.println(dneto.getTextContent().toString());
                   
                    
                       if (dneto!= null) {
                        vdneto=dneto.getTextContent().toString();
                          }else{
                              
                          }
                    
                    
                    
                   //------neto
                   }
                   
                   
                   
                 
                   
                     //-------direccion
                    NodeList direccione = firstPersonElement.getElementsByTagName("DirOrigen");
                    Element ddireccion =(Element)direccione.item(0);
                //    System.out.println(ddireccion.getTextContent().toString());
                   //------direccion
                   if (ver.equals("33") ||ver.equals("61")  || ver.equals("52")){
                      //-------Giro
                    NodeList giroe = firstPersonElement.getElementsByTagName("GiroEmis");
                    Element dgiro =(Element)giroe.item(0);
                //    System.out.println(dgiro.getTextContent().toString());
                    vdgiro=dgiro.getTextContent().toString();
                    vdgiro = vdgiro.replace("'", "''");
                   }
                   //------Giro
                   
                      //-------Pago
                    NodeList pagoe = firstPersonElement.getElementsByTagName("A1");
                    Element dpago =(Element)pagoe.item(0);
                 //   System.out.println(dpago.getTextContent().toString());
                   //------Pago
                   
                    //-------Venc
                    NodeList vence = firstPersonElement.getElementsByTagName("FchVenc");
                    Element dvence =(Element)vence.item(0);
                 //   System.out.println(dvence.getTextContent().toString());
                   //------Venc
                   
                   //-------Comuna
                    NodeList come = firstPersonElement.getElementsByTagName("CmnaOrigen");
                    Element dcome =(Element)come.item(0);
                 //   System.out.println(dcome.getTextContent().toString());
                   //------comuna
                   
                    //-------Ciudad
                    NodeList ciue = firstPersonElement.getElementsByTagName("CiudadOrigen");
                    Element dciue =(Element)ciue.item(0);
                    
                    
                      //------vendedor
                    NodeList vene = firstPersonElement.getElementsByTagName("CdgVendedor");
                    Element dvene =(Element)vene.item(0);
                 //   System.out.println(dcome.getTextContent().toString());
                   //------ciudad
                   
                   //dteref
                       NodeList TpoDocRef0= firstPersonElement.getElementsByTagName("TpoDocRef");
          Element dTpoDocRef0 =(Element)TpoDocRef0.item(0);
                    int largoref=TpoDocRef0.getLength();
                    if (largoref>0){
                   // JOptionPane.showMessageDialog(null,largoref );
             NodeList FolioRef0= firstPersonElement.getElementsByTagName("FolioRef");
          Element dFolioRef0 =(Element)FolioRef0.item(0);
          //JOptionPane.showMessageDialog(null,dFolioRef0.getTextContent());
            NodeList FchRef0= firstPersonElement.getElementsByTagName("FchRef");
          Element dFchRef0 =(Element)FchRef0.item(0);
          
         // NodeList RazonRef0= firstPersonElement.getElementsByTagName("RazonRef");
         // Element dRazonRef0 =(Element)RazonRef0.item(0);
             System.out.println("Refencias->"+largoref);        
          System.out.println(dTpoDocRef0.getTextContent().toString());
                 
          System.out.println(dFolioRef0.getTextContent().toString());
          System.out.println(dFchRef0.getTextContent().toString());
          
        //  System.out.println(dRazonRef0.getTextContent().toString());
           //    String dbURL = "jdbc:mysql://192.168.64.2:3306/Rodalin"; 
        //String username ="nelstu"; 
       // String password = "NSloteria2015"; 
        
        
        
        
        Connection dbCon0 = null; 
        Statement stmt0 = null; 
        ResultSet rs0 = null;           
               try {
              
           dbCon0 = DriverManager.getConnection(dbURL, username, password); 
          Statement comando0=dbCon0.createStatement();
            comando0.executeUpdate("insert into dterefc(tporef,feref,folioref,dte) values ('"+dTpoDocRef0.getTextContent().toString()+"','"+dFchRef0.getTextContent().toString()+"','"+dFolioRef0.getTextContent().toString()+"','"+dfolio.getTextContent().toString()+"')");        
  
            } catch(SQLException ex){
  
      JOptionPane.showMessageDialog(null,ex.getMessage().toString() );
       
              
        }
                   //findteref
                   
             }        
                //nc
                  if (ver.equals("61")  ){
                    NodeList TpoDocRef = firstPersonElement.getElementsByTagName("TpoDocRef");
                    Element dTpoDocRef =(Element)TpoDocRef.item(0);
                      n1=dTpoDocRef.getTextContent().toString();
                      if (n1==null || n1==""){
                          n1="39";
                       }
                   System.out.println("nctipo->"+n1);
                    
                     NodeList FolioRef = firstPersonElement.getElementsByTagName("FolioRef");
                    Element dFolioRef =(Element)FolioRef.item(0);
                      n2=dFolioRef.getTextContent().toString();
                     
                     NodeList CodRef = firstPersonElement.getElementsByTagName("CodRef");
                    Element dCodRef =(Element)CodRef.item(0);
                      n3=dCodRef.getTextContent().toString();
                    
                     NodeList RazonRef = firstPersonElement.getElementsByTagName("RazonRef");
                    Element dRazonRef =(Element)RazonRef.item(0);
               
                     
                           if (dRazonRef!= null) {
                        n4=dRazonRef.getTextContent().toString();
                     
                    
                          }else{
                              
                          }
                     
                     
                    
                     NodeList FchRef = firstPersonElement.getElementsByTagName("FchRef");
                    Element dFchRef =(Element)FchRef.item(0);
                     n5=dFchRef.getTextContent().toString();
                     
                  }
                //finnc
               
                   
                   //insertar documentosc
                  
                      
                   String fo=dfolio.getTextContent().toString();
                   String ru=drute.getTextContent().toString();
                   String ra=drazon.getTextContent().toString();
                   ra= ra.replace("'", "''");
                   String ti=dtipo.getTextContent().toString();
                    String fe=dfecha.getTextContent().toString();
                   if (ver.equals("33") ||ver.equals("61")  || ver.equals("52")){  
                       ne=vdneto;
                       iv=vdiva;
                   }
                    if (ver.equals("0")){  
                       ne="0";
                       iv="0";
                   }
                    String to=dtotal.getTextContent().toString();
                    String di=ddireccion.getTextContent().toString();
                       di= di.replace("'", "''");
                    String gi=vdgiro;
                    String ve="";
                    //String pa=dpago.getTextContent().toString();
                    String pa="";
                    if (dvence==null){
                         ve=fe;
                     }
                    else{
                         ve=dvence.getTextContent().toString();
                         if (ver.equals("0")){
                             ve=fe;
                           }
                       }
                    String co=dcome.getTextContent().toString();
                       co= co.replace("'", "''");
                      String ciu="";
//                    String ciu=dciue.getTextContent().toString();
                       ciu= ciu.replace("'", "''");
                       
                       
                       String ven="";
                        //    String ven=dvene.getTextContent().toString();
                       ven=ven.replace("'", "''");
                   // System.out.println(archivo.toString());
                       //-------NmbItem
                    NodeList nombre= firstPersonElement.getElementsByTagName("NmbItem");
                    int x=nombre.getLength();
                   // System.out.println("largo->"+x);
                    
                   
                    
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
                   // System.out.println("Cantidad->"+dcant.getTextContent().toString());
                   //------QtyItem
                   
                   if (x>1){
                   //------QtyItem 
                   }
                   
                   
                    //System.out.println(dcome.getTextContent().toString());
                    
                    
                  //JOptionPane.showMessageDialog(null, ti);
                    if(ti.equals("33") || ti.equals("0") || ti.equals("52") || ti.equals("61")){ 
                   cargarDriver();
        
     //              String dbURL = "jdbc:mysql://186.64.122.227:3306/Rodalin"; 
     //   String username ="nelstu"; 
     //   String password = "armijo183ISLA#"; 
        
   //       String dbURL = "jdbc:mysql://192.168.64.2:3306/Rodalin"; 
   //     String username ="nelstu"; 
   //     String password = "NSloteria2015"; 
        
        Connection dbCon = null; 
        Statement stmt = null; 
        ResultSet rs = null; 
        int es=0;
        int existe=0;
      //existe
      String query1="";
     if(ti.equals("33")){
     query1 ="select numero,rut,tipo from documentosc where tipo="+ti+" and rut='"+ru+"' and numero="+fo; 
     }
      if(ti.equals("52")){
     query1 ="select numero,rut,tipo from documentoscgv where tipo="+ti+" and rut='"+ru+"' and numero="+fo; 
     }
       if(ti.equals("61")){
     query1 ="select numero,rut,tipo from documentoscncv where tipo="+ti+" and rut='"+ru+"' and numero="+fo; 
     //JOptionPane.showMessageDialog(null, query1);
     }
    
     
     if(ti.equals("0")){
     query1 ="select numero,rut,tipo from documentoscbol where tipo="+ti+"  and numero="+fo; 
      System.out.print(query1); 
   } 
      
         // JOptionPane.showMessageDialog(null, query);
          try {
              //getting database connection to MySQL server 
            dbCon = DriverManager.getConnection(dbURL, username, password); 
           //getting PreparedStatment to execute query 
           stmt = dbCon.prepareStatement(query1); 
          //Resultset returned by query 
           rs = stmt.executeQuery(query1);  
           while(rs.next()){ 
              
           
         existe=1;
       
 
          } 
         
        } catch(SQLException ex){
            JOptionPane.showMessageDialog(null, ex.getMessage().toString());
        
        }
          
          //   JOptionPane.showMessageDialog(null, ti); 
      if (existe==0){
      //fin existe
          try {
              
           dbCon = DriverManager.getConnection(dbURL, username, password); 
          Statement comando=dbCon.createStatement();
           
          if(ti.equals("33")){ 
             
 //JOptionPane.showMessageDialog(null,"insert into documentos(ciudad,comuna,venc,forma,giro,direccion,xml,numero,rut,razon,tipo,fecha,neto,iva,total) values ('"+ciu+"','"+co+"','"+ve+"','"+pa+"','"+gi+"','"+di+"','"+archivo+"',"+fo+",'"+ru+"','"+ra+"','"+ti+"','"+fe+"',"+ne+","+iv+","+to+")"); 
              comando.executeUpdate("insert into documentosc(vendedor,ciudad,comuna,venc,forma,giro,direccion,xml,numero,dte,rut,razon,tipo,fecha,neto,iva,total) values ('"+ven+"','"+ciu+"','"+co+"','"+ve+"','"+pa+"','"+gi+"','"+di+"','"+archivo+"',"+fo+","+fo+",'"+ru+"','"+ra+"','"+ti+"','"+fe+"',"+ne+","+iv+","+to+")");        
           
          }
          
           if(ti.equals("52")){ 
         comando.executeUpdate("insert into documentoscgv(Indtraslado,Tipodespacho,ciudad,comuna,venc,forma,giro,direccion,xml,numero,rut,razon,tipo,fecha,neto,iva,total) values ("+x1+","+x2+",'"+ciu+"','"+co+"','"+ve+"','"+pa+"','"+gi+"','"+di+"','"+archivo+"',"+fo+",'"+ru+"','"+ra+"','"+ti+"','"+fe+"',"+ne+","+iv+","+to+")");        
           
           }
           
            if(ti.equals("61")){ 
                //JOptionPane.showMessageDialog(null, "Aqui");
                //TpoDocRef,FolioRef,CodRef,RazonRef,FchRef,
               //'"+n1+"','"+n2+"','"+n3+"','"+n4+"','"+n5+"',       
         System.out.print("insert into documentoscncv(tpoDocRef,folioRef,codRef,razonRef,feref,ciudad,comuna,venc,forma,giro,direccion,xml,numero,dte,rut,razon,tipo,fecha,neto,iva,total) values ('"+n1+"','"+n2+"','"+n3+"','"+n4+"','"+n5+"','"+ciu+"','"+co+"','"+ve+"','"+pa+"','"+gi+"','"+di+"','"+archivo+"',"+fo+","+fo+",'"+ru+"','"+ra+"','"+ti+"','"+fe+"',"+ne+","+iv+","+to+")"); 
               comando.executeUpdate("insert into documentoscncv(tpoDocRef,folioRef,codRef,razonRef,feref,ciudad,comuna,venc,forma,giro,direccion,xml,numero,dte,rut,razon,tipo,fecha,neto,iva,total) values ('"+n1+"','"+n2+"','"+n3+"','"+n4+"','"+n5+"','"+ciu+"','"+co+"','"+ve+"','"+pa+"','"+gi+"','"+di+"','"+archivo+"',"+fo+","+fo+",'"+ru+"','"+ra+"','"+ti+"','"+fe+"',"+ne+","+iv+","+to+")");        
           //JOptionPane.showMessageDialog(null,"insert into documentosncv(tpoDocRef,folioRef,codRef,razonRef,feref,ciudad,comuna,venc,forma,giro,direccion,xml,numero,rut,razon,tipo,fecha,neto,iva,total) values ('"+n1+"','"+n2+"','"+n3+"','"+n4+"','"+n5+"','"+ciu+"','"+co+"','"+ve+"','"+pa+"','"+gi+"','"+di+"','"+archivo+"',"+fo+",'"+ru+"','"+ra+"','"+ti+"','"+fe+"',"+ne+","+iv+","+to+")");
              
            }
        
            if(ti.equals("0")){ 
         comando.executeUpdate("insert into documentoscbol(vendedor,ciudad,comuna,venc,forma,giro,direccion,xml,numero,dte,rut,razon,tipo,fecha,neto,iva,total) values ('"+ven+"','"+ciu+"','"+co+"','"+ve+"','"+pa+"','"+gi+"','"+di+"','"+archivo+"',"+fo+","+fo+",'"+ru+"','"+ra+"','"+ti+"','"+fe+"',"+ne+","+iv+","+to+")");        
           }
           
        } catch(SQLException ex){
  
      JOptionPane.showMessageDialog(null,ex.getMessage().toString() );
       
              
        }
         if (existe==0){  
        try {
           dbCon = DriverManager.getConnection(dbURL, username, password); 
          Statement comando=dbCon.createStatement();
          if(ti.equals("33")){ 
           
   if (dnombre!= null) {
                      String  ldnombre=dnombre.getTextContent().toString();
                          }else{
                              
                          }

//JOptionPane.showMessageDialog(null,fo+"-"+fo+"-"+dnombre.getTextContent().toString()+"-"+dprecio.getTextContent().toString()+"-"+dcant.getTextContent().toString()+"-"+dmonto.getTextContent().toString() ); 
  try {
                  comando.executeUpdate("insert into detdocumentosc(numero,ide,producto,precio,cantidad,total) values ('" + fo + "','" + fo + "','" + dnombre.getTextContent().toString() + "'," + dprecio.getTextContent().toString() + "," + dcant.getTextContent().toString() + "," + dmonto.getTextContent().toString() + ")");

              } catch (Exception e) {
                  System.err.println("Got an exception! ");
                  System.err.println(e.getMessage());
              }



          }
           if(ti.equals("52")){ 
         comando.executeUpdate("insert into detdocumentoscgv(numero,ide,producto,precio,cantidad,total) values ('"+fo+"','"+fo+"','"+dnombre.getTextContent().toString()+"',"+dprecio.getTextContent().toString()+","+dcant.getTextContent().toString()+","+dmonto.getTextContent().toString()+")");        
          }
            if(ti.equals("61")){ 
         comando.executeUpdate("insert into detdocumentoscncv(numero,ide,producto,precio,cantidad,total) values ('"+fo+"','"+fo+"','"+dnombre.getTextContent().toString()+"',"+dprecio.getTextContent().toString()+","+dcant.getTextContent().toString()+","+dmonto.getTextContent().toString()+")");        
          }
          
          
          
            if(ti.equals("0")){ 
         comando.executeUpdate("insert into detdocumentoscbol(numero,ide,producto,precio,cantidad,total) values ('"+fo+"','"+fo+"','"+dnombre.getTextContent().toString()+"',"+dprecio.getTextContent().toString()+","+dcant.getTextContent().toString()+","+dmonto.getTextContent().toString()+")");        
          }
         if (x>1){
               Element dnombre1 =(Element)nombre.item(1);
       //             System.out.println("Producto->"+dnombre1.getTextContent().toString());
                    //------NmbItem
                        //------PrcItem
                    NodeList precio1= firstPersonElement.getElementsByTagName("PrcItem");
                    //int x=precio.getLength();
                    //System.out.println(x);
                    Element dprecio1 =(Element)precio1.item(1);
         //           System.out.println("Precio->"+dprecio1.getTextContent().toString());
                    //------PrcItem
                   
                        //-------MontoItem
                    NodeList monto1= firstPersonElement.getElementsByTagName("MontoItem");
                    //int x=nombre.getLength();
                    //System.out.println(x);
                    Element dmonto1 =(Element)monto1.item(1);
         //           System.out.println("Monto->"+dmonto1.getTextContent().toString());
                   //------MontoItem
                         //-------QtyItem
                    NodeList cant1= firstPersonElement.getElementsByTagName("QtyItem");
                    //int x=nombre.getLength();
                    //System.out.println(x);
                    Element dcant1 =(Element)cant1.item(1);
         //           System.out.println("Cantidad->"+dcant1.getTextContent().toString());
          if(ti.equals("33")){   
              String ldnombre1="";
                if (dnombre1!= null) {
                      ldnombre1=dnombre1.getTextContent().toString();
                          }else{
                              ldnombre1="";
                          }
         try{
           comando.executeUpdate("insert into detdocumentosc(numero,ide,producto,precio,cantidad,total) values ('"+fo+"','"+fo+"','"+ldnombre1+"',"+dprecio1.getTextContent().toString()+","+dcant1.getTextContent().toString()+","+dmonto1.getTextContent().toString()+")");        
   
          } catch (Exception e) { 
            System.err.println("Got an exception! "); 
            System.err.println(e.getMessage()); 
        }          
           }
            if(ti.equals("52")){          
         comando.executeUpdate("insert into detdocumentoscgv(numero,ide,producto,precio,cantidad,total) values ('"+fo+"','"+fo+"','"+dnombre1.getTextContent().toString()+"',"+dprecio1.getTextContent().toString()+","+dcant1.getTextContent().toString()+","+dmonto1.getTextContent().toString()+")");        
          }
              if(ti.equals("61")){          
         comando.executeUpdate("insert into detdocumentoscncv(numero,ide,producto,precio,cantidad,total) values ('"+fo+"','"+fo+"','"+dnombre1.getTextContent().toString()+"',"+dprecio1.getTextContent().toString()+","+dcant1.getTextContent().toString()+","+dmonto1.getTextContent().toString()+")");        
          }
          
          
          if(ti.equals("0")){          
         comando.executeUpdate("insert into detdocumentoscbol(numero,ide,producto,precio,cantidad,total) values ('"+fo+"','"+fo+"','"+dnombre1.getTextContent().toString()+"',"+dprecio1.getTextContent().toString()+","+dcant1.getTextContent().toString()+","+dmonto1.getTextContent().toString()+")");        
          }      
          }
       
       if (x>2){
               Element dnombre2 =(Element)nombre.item(2);
         //           System.out.println("Producto->"+dnombre2.getTextContent().toString());
                    //------NmbItem
                        //------PrcItem
                    NodeList precio2= firstPersonElement.getElementsByTagName("PrcItem");
                    //int x=precio.getLength();
                    //System.out.println(x);
                    Element dprecio2 =(Element)precio2.item(2);
         //           System.out.println("Precio->"+dprecio2.getTextContent().toString());
                    //------PrcItem
                   
                        //-------MontoItem
                    NodeList monto2= firstPersonElement.getElementsByTagName("MontoItem");
                    //int x=nombre.getLength();
                    //System.out.println(x);
                    Element dmonto2 =(Element)monto2.item(2);
         //           System.out.println("Monto->"+dmonto2.getTextContent().toString());
                   //------MontoItem
                         //-------QtyItem
                    NodeList cant2= firstPersonElement.getElementsByTagName("QtyItem");
                    //int x=nombre.getLength();
                    //System.out.println(x);
                    Element dcant2 =(Element)cant2.item(2);
         //           System.out.println("Cantidad->"+dcant2.getTextContent().toString());
           if(ti.equals("33")){       
             try {
                   comando.executeUpdate("insert into detdocumentosc(numero,ide,producto,precio,cantidad,total) values ('"+fo+"','"+fo+"','"+dnombre2.getTextContent().toString()+"',"+dprecio2.getTextContent().toString()+","+dcant2.getTextContent().toString()+","+dmonto2.getTextContent().toString()+")");        
     
              } catch (Exception e) {
                  System.err.println("Got an exception! ");
                  System.err.println(e.getMessage());
              }

           
           
           
           }
             if(ti.equals("52")){       
         comando.executeUpdate("insert into detdocumentoscgv(numero,ide,producto,precio,cantidad,total) values ('"+fo+"','"+fo+"','"+dnombre2.getTextContent().toString()+"',"+dprecio2.getTextContent().toString()+","+dcant2.getTextContent().toString()+","+dmonto2.getTextContent().toString()+")");        
           }
               if(ti.equals("61")){       
         comando.executeUpdate("insert into detdocumentoscncv(numero,ide,producto,precio,cantidad,total) values ('"+fo+"','"+fo+"','"+dnombre2.getTextContent().toString()+"',"+dprecio2.getTextContent().toString()+","+dcant2.getTextContent().toString()+","+dmonto2.getTextContent().toString()+")");        
           }
           
              if(ti.equals("0")){       
         comando.executeUpdate("insert into detdocumentoscbol(numero,ide,producto,precio,cantidad,total) values ('"+fo+"','"+fo+"','"+dnombre2.getTextContent().toString()+"',"+dprecio2.getTextContent().toString()+","+dcant2.getTextContent().toString()+","+dmonto2.getTextContent().toString()+")");        
           }
           }
       
       if (x>3){
               Element dnombre3 =(Element)nombre.item(3);
         //           System.out.println("Producto->"+dnombre3.getTextContent().toString());
                    //------NmbItem
                        //------PrcItem
                    NodeList precio3= firstPersonElement.getElementsByTagName("PrcItem");
                    //int x=precio.getLength();
                    //System.out.println(x);
                    Element dprecio3 =(Element)precio3.item(3);
         //           System.out.println("Precio->"+dprecio3.getTextContent().toString());
                    //------PrcItem
                   
                        //-------MontoItem
                    NodeList monto3= firstPersonElement.getElementsByTagName("MontoItem");
                    //int x=nombre.getLength();
                    //System.out.println(x);
                    Element dmonto3 =(Element)monto3.item(3);
         //           System.out.println("Monto->"+dmonto3.getTextContent().toString());
                   //------MontoItem
                         //-------QtyItem
                    NodeList cant3= firstPersonElement.getElementsByTagName("QtyItem");
                    //int x=nombre.getLength();
                    //System.out.println(x);
                    Element dcant3 =(Element)cant3.item(3);
         //           System.out.println("Cantidad->"+dcant3.getTextContent().toString());
            if(ti.equals("33")){      
         comando.executeUpdate("insert into detdocumentosc(numero,ide,producto,precio,cantidad,total) values ('"+fo+"','"+fo+"','"+dnombre3.getTextContent().toString()+"',"+dprecio3.getTextContent().toString()+","+dcant3.getTextContent().toString()+","+dmonto3.getTextContent().toString()+")");        
            }
               if(ti.equals("52")){      
         comando.executeUpdate("insert into detdocumentoscgv(numero,ide,producto,precio,cantidad,total) values ('"+fo+"','"+fo+"','"+dnombre3.getTextContent().toString()+"',"+dprecio3.getTextContent().toString()+","+dcant3.getTextContent().toString()+","+dmonto3.getTextContent().toString()+")");        
            }
                  if(ti.equals("61")){      
         comando.executeUpdate("insert into detdocumentoscncv(numero,ide,producto,precio,cantidad,total) values ('"+fo+"','"+fo+"','"+dnombre3.getTextContent().toString()+"',"+dprecio3.getTextContent().toString()+","+dcant3.getTextContent().toString()+","+dmonto3.getTextContent().toString()+")");        
            }
            
            
            
              if(ti.equals("0")){      
         comando.executeUpdate("insert into detdocumentoscbol(numero,ide,producto,precio,cantidad,total) values ('"+fo+"','"+fo+"','"+dnombre3.getTextContent().toString()+"',"+dprecio3.getTextContent().toString()+","+dcant3.getTextContent().toString()+","+dmonto3.getTextContent().toString()+")");        
            }
            }
       if (x>4){
               Element dnombre4 =(Element)nombre.item(4);
         //           System.out.println("Producto->"+dnombre4.getTextContent().toString());
                    //------NmbItem
                        //------PrcItem
                    NodeList precio4= firstPersonElement.getElementsByTagName("PrcItem");
                    //int x=precio.getLength();
                    //System.out.println(x);
                    Element dprecio4 =(Element)precio4.item(4);
         //           System.out.println("Precio->"+dprecio4.getTextContent().toString());
                    //------PrcItem
                   
                        //-------MontoItem
                    NodeList monto4= firstPersonElement.getElementsByTagName("MontoItem");
                    //int x=nombre.getLength();
                    //System.out.println(x);
                    Element dmonto4 =(Element)monto4.item(4);
         //           System.out.println("Monto->"+dmonto4.getTextContent().toString());
                   //------MontoItem
                         //-------QtyItem
                    NodeList cant4= firstPersonElement.getElementsByTagName("QtyItem");
                    //int x=nombre.getLength();
                    //System.out.println(x);
                    Element dcant4 =(Element)cant4.item(4);
         //           System.out.println("Cantidad->"+dcant4.getTextContent().toString());
             if(ti.equals("33")){     
         comando.executeUpdate("insert into detdocumentosc(numero,ide,producto,precio,cantidad,total) values ('"+fo+"','"+fo+"','"+dnombre4.getTextContent().toString()+"',"+dprecio4.getTextContent().toString()+","+dcant4.getTextContent().toString()+","+dmonto4.getTextContent().toString()+")");        
             }
               if(ti.equals("52")){     
         comando.executeUpdate("insert into detdocumentoscgv(numero,ide,producto,precio,cantidad,total) values ('"+fo+"','"+fo+"','"+dnombre4.getTextContent().toString()+"',"+dprecio4.getTextContent().toString()+","+dcant4.getTextContent().toString()+","+dmonto4.getTextContent().toString()+")");        
             }
                 if(ti.equals("61")){     
         comando.executeUpdate("insert into detdocumentoscncv(numero,ide,producto,precio,cantidad,total) values ('"+fo+"','"+fo+"','"+dnombre4.getTextContent().toString()+"',"+dprecio4.getTextContent().toString()+","+dcant4.getTextContent().toString()+","+dmonto4.getTextContent().toString()+")");        
             }
               if(ti.equals("0")){     
         comando.executeUpdate("insert into detdocumentoscbol(numero,ide,producto,precio,cantidad,total) values ('"+fo+"','"+fo+"','"+dnombre4.getTextContent().toString()+"',"+dprecio4.getTextContent().toString()+","+dcant4.getTextContent().toString()+","+dmonto4.getTextContent().toString()+")");        
             }
             }
       if (x>5){
               Element dnombre5 =(Element)nombre.item(5);
         //           System.out.println("Producto->"+dnombre5.getTextContent().toString());
                    //------NmbItem
                        //------PrcItem
                    NodeList precio5= firstPersonElement.getElementsByTagName("PrcItem");
                    //int x=precio.getLength();
                    //System.out.println(x);
                    Element dprecio5 =(Element)precio5.item(5);
         //           System.out.println("Precio->"+dprecio5.getTextContent().toString());
                    //------PrcItem
                   
                        //-------MontoItem
                    NodeList monto5= firstPersonElement.getElementsByTagName("MontoItem");
                    //int x=nombre.getLength();
                    //System.out.println(x);
                    Element dmonto5 =(Element)monto5.item(5);
         //           System.out.println("Monto->"+dmonto5.getTextContent().toString());
                   //------MontoItem
                         //-------QtyItem
                    NodeList cant5= firstPersonElement.getElementsByTagName("QtyItem");
                    //int x=nombre.getLength();
                    //System.out.println(x);
                    Element dcant5 =(Element)cant5.item(5);
         //           System.out.println("Cantidad->"+dcant5.getTextContent().toString());
              if(ti.equals("33")){    
         comando.executeUpdate("insert into detdocumentosc(numero,ide,producto,precio,cantidad,total) values ('"+fo+"','"+fo+"','"+dnombre5.getTextContent().toString()+"',"+dprecio5.getTextContent().toString()+","+dcant5.getTextContent().toString()+","+dmonto5.getTextContent().toString()+")");        
              }
               if(ti.equals("52")){    
         comando.executeUpdate("insert into detdocumentoscgv(numero,ide,producto,precio,cantidad,total) values ('"+fo+"','"+fo+"','"+dnombre5.getTextContent().toString()+"',"+dprecio5.getTextContent().toString()+","+dcant5.getTextContent().toString()+","+dmonto5.getTextContent().toString()+")");        
              }
                if(ti.equals("61")){    
         comando.executeUpdate("insert into detdocumentoscncv(numero,ide,producto,precio,cantidad,total) values ('"+fo+"','"+fo+"','"+dnombre5.getTextContent().toString()+"',"+dprecio5.getTextContent().toString()+","+dcant5.getTextContent().toString()+","+dmonto5.getTextContent().toString()+")");        
              }
              
              
               if(ti.equals("0")){    
         comando.executeUpdate("insert into detdocumentoscbol(numero,ide,producto,precio,cantidad,total) values ('"+fo+"','"+fo+"','"+dnombre5.getTextContent().toString()+"',"+dprecio5.getTextContent().toString()+","+dcant5.getTextContent().toString()+","+dmonto5.getTextContent().toString()+")");        
              }
              }
       if (x>6){
               Element dnombre6 =(Element)nombre.item(6);
         //           System.out.println("Producto->"+dnombre6.getTextContent().toString());
                    //------NmbItem
                        //------PrcItem
                    NodeList precio6= firstPersonElement.getElementsByTagName("PrcItem");
                    //int x=precio.getLength();
                    //System.out.println(x);
                    Element dprecio6 =(Element)precio6.item(6);
         //           System.out.println("Precio->"+dprecio6.getTextContent().toString());
                    //------PrcItem
                   
                        //-------MontoItem
                    NodeList monto6= firstPersonElement.getElementsByTagName("MontoItem");
                    //int x=nombre.getLength();
                    //System.out.println(x);
                    Element dmonto6 =(Element)monto6.item(6);
         //           System.out.println("Monto->"+dmonto6.getTextContent().toString());
                   //------MontoItem
                         //-------QtyItem
                    NodeList cant6= firstPersonElement.getElementsByTagName("QtyItem");
                    //int x=nombre.getLength();
                    //System.out.println(x);
                    Element dcant6 =(Element)cant6.item(6);
         //           System.out.println("Cantidad->"+dcant6.getTextContent().toString());
               if(ti.equals("33")){   
         comando.executeUpdate("insert into detdocumentosc(numero,ide,producto,precio,cantidad,total) values ('"+fo+"','"+fo+"','"+dnombre6.getTextContent().toString()+"',"+dprecio6.getTextContent().toString()+","+dcant6.getTextContent().toString()+","+dmonto6.getTextContent().toString()+")");        
               }
                if(ti.equals("52")){   
         comando.executeUpdate("insert into detdocumentoscgv(numero,ide,producto,precio,cantidad,total) values ('"+fo+"','"+fo+"','"+dnombre6.getTextContent().toString()+"',"+dprecio6.getTextContent().toString()+","+dcant6.getTextContent().toString()+","+dmonto6.getTextContent().toString()+")");        
               }
                 if(ti.equals("61")){   
         comando.executeUpdate("insert into detdocumentoscncv(numero,ide,producto,precio,cantidad,total) values ('"+fo+"','"+fo+"','"+dnombre6.getTextContent().toString()+"',"+dprecio6.getTextContent().toString()+","+dcant6.getTextContent().toString()+","+dmonto6.getTextContent().toString()+")");        
               }
               
                 if(ti.equals("0")){   
         comando.executeUpdate("insert into detdocumentoscbol(numero,ide,producto,precio,cantidad,total) values ('"+fo+"','"+fo+"','"+dnombre6.getTextContent().toString()+"',"+dprecio6.getTextContent().toString()+","+dcant6.getTextContent().toString()+","+dmonto6.getTextContent().toString()+")");        
               }
               }
       if (x>7){
               Element dnombre7 =(Element)nombre.item(7);
         //           System.out.println("Producto->"+dnombre7.getTextContent().toString());
                    //------NmbItem
                        //------PrcItem
                    NodeList precio7= firstPersonElement.getElementsByTagName("PrcItem");
                    //int x=precio.getLength();
                    //System.out.println(x);
                    Element dprecio7 =(Element)precio7.item(7);
         //           System.out.println("Precio->"+dprecio7.getTextContent().toString());
                    //------PrcItem
                   
                        //-------MontoItem
                    NodeList monto7= firstPersonElement.getElementsByTagName("MontoItem");
                    //int x=nombre.getLength();
                    //System.out.println(x);
                    Element dmonto7 =(Element)monto7.item(7);
         //           System.out.println("Monto->"+dmonto7.getTextContent().toString());
                   //------MontoItem
                         //-------QtyItem
                    NodeList cant7= firstPersonElement.getElementsByTagName("QtyItem");
                    //int x=nombre.getLength();
                    //System.out.println(x);
                    Element dcant7 =(Element)cant7.item(7);
         //           System.out.println("Cantidad->"+dcant7.getTextContent().toString());
                if(ti.equals("33")){  
         comando.executeUpdate("insert into detdocumentosc(numero,ide,producto,precio,cantidad,total) values ('"+fo+"','"+fo+"','"+dnombre7.getTextContent().toString()+"',"+dprecio7.getTextContent().toString()+","+dcant7.getTextContent().toString()+","+dmonto7.getTextContent().toString()+")");        
                }
                 if(ti.equals("52")){  
         comando.executeUpdate("insert into detdocumentoscgv(numero,ide,producto,precio,cantidad,total) values ('"+fo+"','"+fo+"','"+dnombre7.getTextContent().toString()+"',"+dprecio7.getTextContent().toString()+","+dcant7.getTextContent().toString()+","+dmonto7.getTextContent().toString()+")");        
                }
                  if(ti.equals("61")){  
         comando.executeUpdate("insert into detdocumentoscncv(numero,ide,producto,precio,cantidad,total) values ('"+fo+"','"+fo+"','"+dnombre7.getTextContent().toString()+"',"+dprecio7.getTextContent().toString()+","+dcant7.getTextContent().toString()+","+dmonto7.getTextContent().toString()+")");        
                }
                
                
                  if(ti.equals("0")){  
         comando.executeUpdate("insert into detdocumentoscbol(numero,ide,producto,precio,cantidad,total) values ('"+fo+"','"+fo+"','"+dnombre7.getTextContent().toString()+"',"+dprecio7.getTextContent().toString()+","+dcant7.getTextContent().toString()+","+dmonto7.getTextContent().toString()+")");        
                }
                }
       if (x>8){
               Element dnombre8 =(Element)nombre.item(8);
         //           System.out.println("Producto->"+dnombre8.getTextContent().toString());
                    //------NmbItem
                        //------PrcItem
                    NodeList precio8= firstPersonElement.getElementsByTagName("PrcItem");
                    //int x=precio.getLength();
                    //System.out.println(x);
                    Element dprecio8 =(Element)precio8.item(8);
         //           System.out.println("Precio->"+dprecio8.getTextContent().toString());
                    //------PrcItem
                   
                        //-------MontoItem
                    NodeList monto8= firstPersonElement.getElementsByTagName("MontoItem");
                    //int x=nombre.getLength();
                    //System.out.println(x);
                    Element dmonto8 =(Element)monto8.item(8);
         //           System.out.println("Monto->"+dmonto8.getTextContent().toString());
                   //------MontoItem
                         //-------QtyItem
                    NodeList cant8= firstPersonElement.getElementsByTagName("QtyItem");
                    //int x=nombre.getLength();
                    //System.out.println(x);
                    Element dcant8 =(Element)cant8.item(8);
         //           System.out.println("Cantidad->"+dcant8.getTextContent().toString());
                 if(ti.equals("33")){ 
         comando.executeUpdate("insert into detdocumentosc(numero,ide,producto,precio,cantidad,total) values ('"+fo+"','"+fo+"','"+dnombre8.getTextContent().toString()+"',"+dprecio8.getTextContent().toString()+","+dcant8.getTextContent().toString()+","+dmonto8.getTextContent().toString()+")");        
                 }
                  if(ti.equals("52")){ 
         comando.executeUpdate("insert into detdocumentoscgv(numero,ide,producto,precio,cantidad,total) values ('"+fo+"','"+fo+"','"+dnombre8.getTextContent().toString()+"',"+dprecio8.getTextContent().toString()+","+dcant8.getTextContent().toString()+","+dmonto8.getTextContent().toString()+")");        
                 }
                   if(ti.equals("61")){ 
         comando.executeUpdate("insert into detdocumentoscncv(numero,ide,producto,precio,cantidad,total) values ('"+fo+"','"+fo+"','"+dnombre8.getTextContent().toString()+"',"+dprecio8.getTextContent().toString()+","+dcant8.getTextContent().toString()+","+dmonto8.getTextContent().toString()+")");        
                 }
                 
                  if(ti.equals("0")){ 
         comando.executeUpdate("insert into detdocumentoscbol(numero,ide,producto,precio,cantidad,total) values ('"+fo+"','"+fo+"','"+dnombre8.getTextContent().toString()+"',"+dprecio8.getTextContent().toString()+","+dcant8.getTextContent().toString()+","+dmonto8.getTextContent().toString()+")");        
                 }
                 }
       if (x>9){
               Element dnombre9 =(Element)nombre.item(9);
         //           System.out.println("Producto->"+dnombre9.getTextContent().toString());
                    //------NmbItem
                        //------PrcItem
                    NodeList precio9= firstPersonElement.getElementsByTagName("PrcItem");
                    //int x=precio.getLength();
                    //System.out.println(x);
                    Element dprecio9 =(Element)precio9.item(9);
         //           System.out.println("Precio->"+dprecio9.getTextContent().toString());
                    //------PrcItem
                   
                        //-------MontoItem
                    NodeList monto9= firstPersonElement.getElementsByTagName("MontoItem");
                    //int x=nombre.getLength();
                    //System.out.println(x);
                    Element dmonto9 =(Element)monto9.item(9);
         //           System.out.println("Monto->"+dmonto9.getTextContent().toString());
                   //------MontoItem
                         //-------QtyItem
                    NodeList cant9= firstPersonElement.getElementsByTagName("QtyItem");
                    //int x=nombre.getLength();
                    //System.out.println(x);
                    Element dcant9 =(Element)cant9.item(9);
         //           System.out.println("Cantidad->"+dcant9.getTextContent().toString());
                  if(ti.equals("33")){
         comando.executeUpdate("insert into detdocumentosc(numero,ide,producto,precio,cantidad,total) values ('"+fo+"','"+fo+"','"+dnombre9.getTextContent().toString()+"',"+dprecio9.getTextContent().toString()+","+dcant9.getTextContent().toString()+","+dmonto9.getTextContent().toString()+")");        
                  }
                   if(ti.equals("52")){
         comando.executeUpdate("insert into detdocumentoscgv(numero,ide,producto,precio,cantidad,total) values ('"+fo+"','"+fo+"','"+dnombre9.getTextContent().toString()+"',"+dprecio9.getTextContent().toString()+","+dcant9.getTextContent().toString()+","+dmonto9.getTextContent().toString()+")");        
                  }
                    if(ti.equals("61")){
         comando.executeUpdate("insert into detdocumentoscncv(numero,ide,producto,precio,cantidad,total) values ('"+fo+"','"+fo+"','"+dnombre9.getTextContent().toString()+"',"+dprecio9.getTextContent().toString()+","+dcant9.getTextContent().toString()+","+dmonto9.getTextContent().toString()+")");        
                  }
                  
                   if(ti.equals("0")){
         comando.executeUpdate("insert into detdocumentoscbol(numero,ide,producto,precio,cantidad,total) values ('"+fo+"','"+fo+"','"+dnombre9.getTextContent().toString()+"',"+dprecio9.getTextContent().toString()+","+dcant9.getTextContent().toString()+","+dmonto9.getTextContent().toString()+")");        
                  }
                  }
       if (x>10){
               Element dnombre10 =(Element)nombre.item(10);
         //           System.out.println("Producto->"+dnombre10.getTextContent().toString());
                    //------NmbItem
                        //------PrcItem
                    NodeList precio10= firstPersonElement.getElementsByTagName("PrcItem");
                    //int x=precio.getLength();
                    //System.out.println(x);
                    Element dprecio10 =(Element)precio10.item(10);
         //           System.out.println("Precio->"+dprecio10.getTextContent().toString());
                    //------PrcItem
                   
                        //-------MontoItem
                    NodeList monto10= firstPersonElement.getElementsByTagName("MontoItem");
                    //int x=nombre.getLength();
                    //System.out.println(x);
                    Element dmonto10 =(Element)monto10.item(10);
         //           System.out.println("Monto->"+dmonto10.getTextContent().toString());
                   //------MontoItem
                         //-------QtyItem
                    NodeList cant10= firstPersonElement.getElementsByTagName("QtyItem");
                    //int x=nombre.getLength();
                    //System.out.println(x);
                    Element dcant10 =(Element)cant10.item(10);
         //           System.out.println("Cantidad->"+dcant10.getTextContent().toString());
                  if(ti.equals("33")){
         comando.executeUpdate("insert into detdocumentosc(numero,ide,producto,precio,cantidad,total) values ('"+fo+"','"+fo+"','"+dnombre10.getTextContent().toString()+"',"+dprecio10.getTextContent().toString()+","+dcant10.getTextContent().toString()+","+dmonto10.getTextContent().toString()+")");        
                  }
                   if(ti.equals("52")){
         comando.executeUpdate("insert into detdocumentoscgv(numero,ide,producto,precio,cantidad,total) values ('"+fo+"','"+fo+"','"+dnombre10.getTextContent().toString()+"',"+dprecio10.getTextContent().toString()+","+dcant10.getTextContent().toString()+","+dmonto10.getTextContent().toString()+")");        
                  }
                    if(ti.equals("61")){
         comando.executeUpdate("insert into detdocumentoscncv(numero,ide,producto,precio,cantidad,total) values ('"+fo+"','"+fo+"','"+dnombre10.getTextContent().toString()+"',"+dprecio10.getTextContent().toString()+","+dcant10.getTextContent().toString()+","+dmonto10.getTextContent().toString()+")");        
                  }
                  
                  
                    if(ti.equals("0")){
         comando.executeUpdate("insert into detdocumentoscbol(numero,ide,producto,precio,cantidad,total) values ('"+fo+"','"+fo+"','"+dnombre10.getTextContent().toString()+"',"+dprecio10.getTextContent().toString()+","+dcant10.getTextContent().toString()+","+dmonto10.getTextContent().toString()+")");        
                  }
                  }
       if (x>11){
               Element dnombre11 =(Element)nombre.item(11);
         //           System.out.println("Producto->"+dnombre11.getTextContent().toString());
                    //------NmbItem
                        //------PrcItem
                    NodeList precio11= firstPersonElement.getElementsByTagName("PrcItem");
                    //int x=precio.getLength();
                    //System.out.println(x);
                    Element dprecio11 =(Element)precio11.item(11);
         //           System.out.println("Precio->"+dprecio11.getTextContent().toString());
                    //------PrcItem
                   
                        //-------MontoItem
                    NodeList monto11= firstPersonElement.getElementsByTagName("MontoItem");
                    //int x=nombre.getLength();
                    //System.out.println(x);
                    Element dmonto11 =(Element)monto11.item(11);
         //           System.out.println("Monto->"+dmonto11.getTextContent().toString());
                   //------MontoItem
                         //-------QtyItem
                    NodeList cant11= firstPersonElement.getElementsByTagName("QtyItem");
                    //int x=nombre.getLength();
                    //System.out.println(x);
                    Element dcant11 =(Element)cant11.item(11);
         //           System.out.println("Cantidad->"+dcant11.getTextContent().toString());
                  if(ti.equals("33")){
         comando.executeUpdate("insert into detdocumentosc(numero,ide,producto,precio,cantidad,total) values ('"+fo+"','"+fo+"','"+dnombre11.getTextContent().toString()+"',"+dprecio11.getTextContent().toString()+","+dcant11.getTextContent().toString()+","+dmonto11.getTextContent().toString()+")");        
                  }
                   if(ti.equals("52")){
         comando.executeUpdate("insert into detdocumentoscgv(numero,ide,producto,precio,cantidad,total) values ('"+fo+"','"+fo+"','"+dnombre11.getTextContent().toString()+"',"+dprecio11.getTextContent().toString()+","+dcant11.getTextContent().toString()+","+dmonto11.getTextContent().toString()+")");        
                  }
                    if(ti.equals("61")){
         comando.executeUpdate("insert into detdocumentoscncv(numero,ide,producto,precio,cantidad,total) values ('"+fo+"','"+fo+"','"+dnombre11.getTextContent().toString()+"',"+dprecio11.getTextContent().toString()+","+dcant11.getTextContent().toString()+","+dmonto11.getTextContent().toString()+")");        
                  }
                  
                  
                    if(ti.equals("0")){
         comando.executeUpdate("insert into detdocumentoscbol(numero,ide,producto,precio,cantidad,total) values ('"+fo+"','"+fo+"','"+dnombre11.getTextContent().toString()+"',"+dprecio11.getTextContent().toString()+","+dcant11.getTextContent().toString()+","+dmonto11.getTextContent().toString()+")");        
                  }
                  }
       if (x>12){
               Element dnombre12 =(Element)nombre.item(12);
         //           System.out.println("Producto->"+dnombre12.getTextContent().toString());
                    //------NmbItem
                        //------PrcItem
                    NodeList precio12= firstPersonElement.getElementsByTagName("PrcItem");
                    //int x=precio.getLength();
                    //System.out.println(x);
                    Element dprecio12 =(Element)precio12.item(12);
         //           System.out.println("Precio->"+dprecio12.getTextContent().toString());
                    //------PrcItem
                   
                        //-------MontoItem
                    NodeList monto12= firstPersonElement.getElementsByTagName("MontoItem");
                    //int x=nombre.getLength();
                    //System.out.println(x);
                    Element dmonto12 =(Element)monto12.item(12);
         //           System.out.println("Monto->"+dmonto12.getTextContent().toString());
                   //------MontoItem
                         //-------QtyItem
                    NodeList cant12= firstPersonElement.getElementsByTagName("QtyItem");
                    //int x=nombre.getLength();
                    //System.out.println(x);
                    Element dcant12 =(Element)cant12.item(12);
         //           System.out.println("Cantidad->"+dcant12.getTextContent().toString());
                  if(ti.equals("33")){
         comando.executeUpdate("insert into detdocumentosc(numero,ide,producto,precio,cantidad,total) values ('"+fo+"','"+fo+"','"+dnombre12.getTextContent().toString()+"',"+dprecio12.getTextContent().toString()+","+dcant12.getTextContent().toString()+","+dmonto12.getTextContent().toString()+")");        
                  }
                   if(ti.equals("52")){
         comando.executeUpdate("insert into detdocumentoscgv(numero,ide,producto,precio,cantidad,total) values ('"+fo+"','"+fo+"','"+dnombre12.getTextContent().toString()+"',"+dprecio12.getTextContent().toString()+","+dcant12.getTextContent().toString()+","+dmonto12.getTextContent().toString()+")");        
                  }
                    if(ti.equals("61")){
         comando.executeUpdate("insert into detdocumentoscncv(numero,ide,producto,precio,cantidad,total) values ('"+fo+"','"+fo+"','"+dnombre12.getTextContent().toString()+"',"+dprecio12.getTextContent().toString()+","+dcant12.getTextContent().toString()+","+dmonto12.getTextContent().toString()+")");        
                  }
                   if(ti.equals("0")){
         comando.executeUpdate("insert into detdocumentoscbol(numero,ide,producto,precio,cantidad,total) values ('"+fo+"','"+fo+"','"+dnombre12.getTextContent().toString()+"',"+dprecio12.getTextContent().toString()+","+dcant12.getTextContent().toString()+","+dmonto12.getTextContent().toString()+")");        
                  }
                  }
       if (x>13){
               Element dnombre13 =(Element)nombre.item(13);
         //           System.out.println("Producto->"+dnombre13.getTextContent().toString());
                    //------NmbItem
                        //------PrcItem
                    NodeList precio13= firstPersonElement.getElementsByTagName("PrcItem");
                    //int x=precio.getLength();
                    //System.out.println(x);
                    Element dprecio13 =(Element)precio13.item(13);
         //           System.out.println("Precio->"+dprecio13.getTextContent().toString());
                    //------PrcItem
                   
                        //-------MontoItem
                    NodeList monto13= firstPersonElement.getElementsByTagName("MontoItem");
                    //int x=nombre.getLength();
                    //System.out.println(x);
                    Element dmonto13 =(Element)monto13.item(13);
         //           System.out.println("Monto->"+dmonto13.getTextContent().toString());
                   //------MontoItem
                         //-------QtyItem
                    NodeList cant13= firstPersonElement.getElementsByTagName("QtyItem");
                    //int x=nombre.getLength();
                    //System.out.println(x);
                    Element dcant13 =(Element)cant13.item(13);
         //           System.out.println("Cantidad->"+dcant13.getTextContent().toString());
                  if(ti.equals("33")){
         comando.executeUpdate("insert into detdocumentos(numero,ide,producto,precio,cantidad,total) values ('"+fo+"','"+fo+"','"+dnombre13.getTextContent().toString()+"',"+dprecio13.getTextContent().toString()+","+dcant13.getTextContent().toString()+","+dmonto13.getTextContent().toString()+")");        
                  }
                   if(ti.equals("52")){
         comando.executeUpdate("insert into detdocumentoscgv(numero,ide,producto,precio,cantidad,total) values ('"+fo+"','"+fo+"','"+dnombre13.getTextContent().toString()+"',"+dprecio13.getTextContent().toString()+","+dcant13.getTextContent().toString()+","+dmonto13.getTextContent().toString()+")");        
                  }
                    if(ti.equals("61")){
         comando.executeUpdate("insert into detdocumentoscncv(numero,ide,producto,precio,cantidad,total) values ('"+fo+"','"+fo+"','"+dnombre13.getTextContent().toString()+"',"+dprecio13.getTextContent().toString()+","+dcant13.getTextContent().toString()+","+dmonto13.getTextContent().toString()+")");        
                  }
                  if(ti.equals("0")){
         comando.executeUpdate("insert into detdocumentoscbol(numero,ide,producto,precio,cantidad,total) values ('"+fo+"','"+fo+"','"+dnombre13.getTextContent().toString()+"',"+dprecio13.getTextContent().toString()+","+dcant13.getTextContent().toString()+","+dmonto13.getTextContent().toString()+")");        
                  }
                  }
       if (x>14){
               Element dnombre14 =(Element)nombre.item(14);
         //           System.out.println("Producto->"+dnombre14.getTextContent().toString());
                    //------NmbItem
                        //------PrcItem
                    NodeList precio14= firstPersonElement.getElementsByTagName("PrcItem");
                    //int x=precio.getLength();
                    //System.out.println(x);
                    Element dprecio14 =(Element)precio14.item(14);
         //           System.out.println("Precio->"+dprecio14.getTextContent().toString());
                    //------PrcItem
                   
                        //-------MontoItem
                    NodeList monto14= firstPersonElement.getElementsByTagName("MontoItem");
                    //int x=nombre.getLength();
                    //System.out.println(x);
                    Element dmonto14 =(Element)monto14.item(14);
         //           System.out.println("Monto->"+dmonto14.getTextContent().toString());
                   //------MontoItem
                         //-------QtyItem
                    NodeList cant14= firstPersonElement.getElementsByTagName("QtyItem");
                    //int x=nombre.getLength();
                    //System.out.println(x);
                    Element dcant14 =(Element)cant14.item(14);
         //           System.out.println("Cantidad->"+dcant14.getTextContent().toString());
                  if(ti.equals("33")){
         comando.executeUpdate("insert into detdocumentosc(numero,ide,producto,precio,cantidad,total) values ('"+fo+"','"+fo+"','"+dnombre14.getTextContent().toString()+"',"+dprecio14.getTextContent().toString()+","+dcant14.getTextContent().toString()+","+dmonto14.getTextContent().toString()+")");        
                  }
                    if(ti.equals("52")){
         comando.executeUpdate("insert into detdocumentoscgv(numero,ide,producto,precio,cantidad,total) values ('"+fo+"','"+fo+"','"+dnombre14.getTextContent().toString()+"',"+dprecio14.getTextContent().toString()+","+dcant14.getTextContent().toString()+","+dmonto14.getTextContent().toString()+")");        
                  }
                      if(ti.equals("61")){
         comando.executeUpdate("insert into detdocumentoscncv(numero,ide,producto,precio,cantidad,total) values ('"+fo+"','"+fo+"','"+dnombre14.getTextContent().toString()+"',"+dprecio14.getTextContent().toString()+","+dcant14.getTextContent().toString()+","+dmonto14.getTextContent().toString()+")");        
                  }
                   if(ti.equals("0")){
         comando.executeUpdate("insert into detdocumentoscbol(numero,ide,producto,precio,cantidad,total) values ('"+fo+"','"+fo+"','"+dnombre14.getTextContent().toString()+"',"+dprecio14.getTextContent().toString()+","+dcant14.getTextContent().toString()+","+dmonto14.getTextContent().toString()+")");        
                  }
                  }
       if (x>15){
               Element dnombre15 =(Element)nombre.item(15);
         //           System.out.println("Producto->"+dnombre15.getTextContent().toString());
                    //------NmbItem
                        //------PrcItem
                    NodeList precio15= firstPersonElement.getElementsByTagName("PrcItem");
                    //int x=precio.getLength();
                    //System.out.println(x);
                    Element dprecio15 =(Element)precio15.item(15);
         //           System.out.println("Precio->"+dprecio15.getTextContent().toString());
                    //------PrcItem
                   
                        //-------MontoItem
                    NodeList monto15= firstPersonElement.getElementsByTagName("MontoItem");
                    //int x=nombre.getLength();
                    //System.out.println(x);
                    Element dmonto15 =(Element)monto15.item(15);
         //           System.out.println("Monto->"+dmonto15.getTextContent().toString());
                   //------MontoItem
                         //-------QtyItem
                    NodeList cant15= firstPersonElement.getElementsByTagName("QtyItem");
                    //int x=nombre.getLength();
                    //System.out.println(x);
                    Element dcant15 =(Element)cant15.item(15);
         //           System.out.println("Cantidad->"+dcant15.getTextContent().toString());
                  if(ti.equals("33")){
         comando.executeUpdate("insert into detdocumentosc(numero,ide,producto,precio,cantidad,total) values ('"+fo+"','"+fo+"','"+dnombre15.getTextContent().toString()+"',"+dprecio15.getTextContent().toString()+","+dcant15.getTextContent().toString()+","+dmonto15.getTextContent().toString()+")");        
                  }
                   if(ti.equals("52")){
         comando.executeUpdate("insert into detdocumentoscgv(numero,ide,producto,precio,cantidad,total) values ('"+fo+"','"+fo+"','"+dnombre15.getTextContent().toString()+"',"+dprecio15.getTextContent().toString()+","+dcant15.getTextContent().toString()+","+dmonto15.getTextContent().toString()+")");        
                  }
                    if(ti.equals("61")){
         comando.executeUpdate("insert into detdocumentoscncv(numero,ide,producto,precio,cantidad,total) values ('"+fo+"','"+fo+"','"+dnombre15.getTextContent().toString()+"',"+dprecio15.getTextContent().toString()+","+dcant15.getTextContent().toString()+","+dmonto15.getTextContent().toString()+")");        
                  }
                  
                   if(ti.equals("0")){
         comando.executeUpdate("insert into detdocumentoscbol(numero,ide,producto,precio,cantidad,total) values ('"+fo+"','"+fo+"','"+dnombre15.getTextContent().toString()+"',"+dprecio15.getTextContent().toString()+","+dcant15.getTextContent().toString()+","+dmonto15.getTextContent().toString()+")");        
                  }
                  }
       if (x>16){
               Element dnombre16 =(Element)nombre.item(16);
         //           System.out.println("Producto->"+dnombre16.getTextContent().toString());
                    //------NmbItem
                        //------PrcItem
                    NodeList precio16= firstPersonElement.getElementsByTagName("PrcItem");
                    //int x=precio.getLength();
                    //System.out.println(x);
                    Element dprecio16 =(Element)precio16.item(16);
         //           System.out.println("Precio->"+dprecio16.getTextContent().toString());
                    //------PrcItem
                   
                        //-------MontoItem
                    NodeList monto16= firstPersonElement.getElementsByTagName("MontoItem");
                    //int x=nombre.getLength();
                    //System.out.println(x);
                    Element dmonto16 =(Element)monto16.item(16);
         //           System.out.println("Monto->"+dmonto16.getTextContent().toString());
                   //------MontoItem
                         //-------QtyItem
                    NodeList cant16= firstPersonElement.getElementsByTagName("QtyItem");
                    //int x=nombre.getLength();
                    //System.out.println(x);
                    Element dcant16 =(Element)cant16.item(16);
         //           System.out.println("Cantidad->"+dcant16.getTextContent().toString());
                  if(ti.equals("33")){
         comando.executeUpdate("insert into detdocumentosc(numero,ide,producto,precio,cantidad,total) values ('"+fo+"','"+fo+"','"+dnombre16.getTextContent().toString()+"',"+dprecio16.getTextContent().toString()+","+dcant16.getTextContent().toString()+","+dmonto16.getTextContent().toString()+")");        
                  }
                  if(ti.equals("52")){
         comando.executeUpdate("insert into detdocumentoscgv(numero,ide,producto,precio,cantidad,total) values ('"+fo+"','"+fo+"','"+dnombre16.getTextContent().toString()+"',"+dprecio16.getTextContent().toString()+","+dcant16.getTextContent().toString()+","+dmonto16.getTextContent().toString()+")");        
                  }
                  if(ti.equals("61")){
         comando.executeUpdate("insert into detdocumentoscncv(numero,ide,producto,precio,cantidad,total) values ('"+fo+"','"+fo+"','"+dnombre16.getTextContent().toString()+"',"+dprecio16.getTextContent().toString()+","+dcant16.getTextContent().toString()+","+dmonto16.getTextContent().toString()+")");        
                  }
                  
                  
                   if(ti.equals("0")){
         comando.executeUpdate("insert into detdocumentoscbol(numero,ide,producto,precio,cantidad,total) values ('"+fo+"','"+fo+"','"+dnombre16.getTextContent().toString()+"',"+dprecio16.getTextContent().toString()+","+dcant16.getTextContent().toString()+","+dmonto16.getTextContent().toString()+")");        
                  }
                  }
       if (x>17){
               Element dnombre17 =(Element)nombre.item(17);
         //           System.out.println("Producto->"+dnombre17.getTextContent().toString());
                    //------NmbItem
                        //------PrcItem
                    NodeList precio17= firstPersonElement.getElementsByTagName("PrcItem");
                    //int x=precio.getLength();
                    //System.out.println(x);
                    Element dprecio17 =(Element)precio17.item(17);
         //           System.out.println("Precio->"+dprecio17.getTextContent().toString());
                    //------PrcItem
                   
                        //-------MontoItem
                    NodeList monto17= firstPersonElement.getElementsByTagName("MontoItem");
                    //int x=nombre.getLength();
                    //System.out.println(x);
                    Element dmonto17 =(Element)monto17.item(17);
         //           System.out.println("Monto->"+dmonto17.getTextContent().toString());
                   //------MontoItem
                         //-------QtyItem
                    NodeList cant17= firstPersonElement.getElementsByTagName("QtyItem");
                    //int x=nombre.getLength();
                    //System.out.println(x);
                    Element dcant17 =(Element)cant17.item(17);
         //           System.out.println("Cantidad->"+dcant17.getTextContent().toString());
                  if(ti.equals("33")){
         comando.executeUpdate("insert into detdocumentosc(numero,ide,producto,precio,cantidad,total) values ('"+fo+"','"+fo+"','"+dnombre17.getTextContent().toString()+"',"+dprecio17.getTextContent().toString()+","+dcant17.getTextContent().toString()+","+dmonto17.getTextContent().toString()+")");        
                  }
                   if(ti.equals("52")){
         comando.executeUpdate("insert into detdocumentoscgv(numero,ide,producto,precio,cantidad,total) values ('"+fo+"','"+fo+"','"+dnombre17.getTextContent().toString()+"',"+dprecio17.getTextContent().toString()+","+dcant17.getTextContent().toString()+","+dmonto17.getTextContent().toString()+")");        
                  }
                    if(ti.equals("61")){
         comando.executeUpdate("insert into detdocumentoscncv(numero,ide,producto,precio,cantidad,total) values ('"+fo+"','"+fo+"','"+dnombre17.getTextContent().toString()+"',"+dprecio17.getTextContent().toString()+","+dcant17.getTextContent().toString()+","+dmonto17.getTextContent().toString()+")");        
                  }
                   if(ti.equals("0")){
         comando.executeUpdate("insert into detdocumentoscbol(numero,ide,producto,precio,cantidad,total) values ('"+fo+"','"+fo+"','"+dnombre17.getTextContent().toString()+"',"+dprecio17.getTextContent().toString()+","+dcant17.getTextContent().toString()+","+dmonto17.getTextContent().toString()+")");        
                  }
                  }
       if (x>18){
               Element dnombre18 =(Element)nombre.item(18);
         //           System.out.println("Producto->"+dnombre18.getTextContent().toString());
                    //------NmbItem
                        //------PrcItem
                    NodeList precio18= firstPersonElement.getElementsByTagName("PrcItem");
                    //int x=precio.getLength();
                    //System.out.println(x);
                    Element dprecio18 =(Element)precio18.item(18);
         //           System.out.println("Precio->"+dprecio18.getTextContent().toString());
                    //------PrcItem
                   
                        //-------MontoItem
                    NodeList monto18= firstPersonElement.getElementsByTagName("MontoItem");
                    //int x=nombre.getLength();
                    //System.out.println(x);
                    Element dmonto18 =(Element)monto18.item(18);
         //           System.out.println("Monto->"+dmonto18.getTextContent().toString());
                   //------MontoItem
                         //-------QtyItem
                    NodeList cant18= firstPersonElement.getElementsByTagName("QtyItem");
                    //int x=nombre.getLength();
                    //System.out.println(x);
                    Element dcant18 =(Element)cant18.item(18);
         //           System.out.println("Cantidad->"+dcant18.getTextContent().toString());
                  if(ti.equals("33")){
         comando.executeUpdate("insert into detdocumentosc(numero,ide,producto,precio,cantidad,total) values ('"+fo+"','"+fo+"','"+dnombre18.getTextContent().toString()+"',"+dprecio18.getTextContent().toString()+","+dcant18.getTextContent().toString()+","+dmonto18.getTextContent().toString()+")");        
                  }
                  if(ti.equals("52")){
         comando.executeUpdate("insert into detdocumentoscgv(numero,ide,producto,precio,cantidad,total) values ('"+fo+"','"+fo+"','"+dnombre18.getTextContent().toString()+"',"+dprecio18.getTextContent().toString()+","+dcant18.getTextContent().toString()+","+dmonto18.getTextContent().toString()+")");        
                  }
                  if(ti.equals("61")){
         comando.executeUpdate("insert into detdocumentoscncv(numero,ide,producto,precio,cantidad,total) values ('"+fo+"','"+fo+"','"+dnombre18.getTextContent().toString()+"',"+dprecio18.getTextContent().toString()+","+dcant18.getTextContent().toString()+","+dmonto18.getTextContent().toString()+")");        
                  }
                    if(ti.equals("0")){
         comando.executeUpdate("insert into detdocumentoscbol(numero,ide,producto,precio,cantidad,total) values ('"+fo+"','"+fo+"','"+dnombre18.getTextContent().toString()+"',"+dprecio18.getTextContent().toString()+","+dcant18.getTextContent().toString()+","+dmonto18.getTextContent().toString()+")");        
                  }
                  }
       if (x>19){
               Element dnombre19 =(Element)nombre.item(19);
         //           System.out.println("Producto->"+dnombre19.getTextContent().toString());
                    //------NmbItem
                        //------PrcItem
                    NodeList precio19= firstPersonElement.getElementsByTagName("PrcItem");
                    //int x=precio.getLength();
                    //System.out.println(x);
                    Element dprecio19 =(Element)precio19.item(19);
         //           System.out.println("Precio->"+dprecio19.getTextContent().toString());
                    //------PrcItem
                   
                        //-------MontoItem
                    NodeList monto19= firstPersonElement.getElementsByTagName("MontoItem");
                    //int x=nombre.getLength();
                    //System.out.println(x);
                    Element dmonto19 =(Element)monto19.item(19);
         //           System.out.println("Monto->"+dmonto19.getTextContent().toString());
                   //------MontoItem
                         //-------QtyItem
                    NodeList cant19= firstPersonElement.getElementsByTagName("QtyItem");
                    //int x=nombre.getLength();
                    //System.out.println(x);
                    Element dcant19 =(Element)cant19.item(19);
         //           System.out.println("Cantidad->"+dcant19.getTextContent().toString());
                  if(ti.equals("33")){
         comando.executeUpdate("insert into detdocumentosc(numero,ide,producto,precio,cantidad,total) values ('"+fo+"','"+fo+"','"+dnombre19.getTextContent().toString()+"',"+dprecio19.getTextContent().toString()+","+dcant19.getTextContent().toString()+","+dmonto19.getTextContent().toString()+")");        
                  }
                   if(ti.equals("52")){
         comando.executeUpdate("insert into detdocumentoscgv(numero,ide,producto,precio,cantidad,total) values ('"+fo+"','"+fo+"','"+dnombre19.getTextContent().toString()+"',"+dprecio19.getTextContent().toString()+","+dcant19.getTextContent().toString()+","+dmonto19.getTextContent().toString()+")");        
                  }
                    if(ti.equals("61")){
         comando.executeUpdate("insert into detdocumentoscncv(numero,ide,producto,precio,cantidad,total) values ('"+fo+"','"+fo+"','"+dnombre19.getTextContent().toString()+"',"+dprecio19.getTextContent().toString()+","+dcant19.getTextContent().toString()+","+dmonto19.getTextContent().toString()+")");        
                  }
                   if(ti.equals("0")){
         comando.executeUpdate("insert into detdocumentosc(numero,ide,producto,precio,cantidad,total) values ('"+fo+"','"+fo+"','"+dnombre19.getTextContent().toString()+"',"+dprecio19.getTextContent().toString()+","+dcant19.getTextContent().toString()+","+dmonto19.getTextContent().toString()+")");        
                  }
                  }
       if (x>20){
               Element dnombre20 =(Element)nombre.item(20);
         //           System.out.println("Producto->"+dnombre20.getTextContent().toString());
                    //------NmbItem
                        //------PrcItem
                    NodeList precio20= firstPersonElement.getElementsByTagName("PrcItem");
                    //int x=precio.getLength();
                    //System.out.println(x);
                    Element dprecio20 =(Element)precio20.item(20);
         //           System.out.println("Precio->"+dprecio20.getTextContent().toString());
                    //------PrcItem
                   
                        //-------MontoItem
                    NodeList monto20= firstPersonElement.getElementsByTagName("MontoItem");
                    //int x=nombre.getLength();
                    //System.out.println(x);
                    Element dmonto20 =(Element)monto20.item(20);
         //           System.out.println("Monto->"+dmonto20.getTextContent().toString());
                   //------MontoItem
                         //-------QtyItem
                    NodeList cant20= firstPersonElement.getElementsByTagName("QtyItem");
                    //int x=nombre.getLength();
                    //System.out.println(x);
                    Element dcant20 =(Element)cant20.item(20);
         //           System.out.println("Cantidad->"+dcant20.getTextContent().toString());
                  if(ti.equals("33")){
         comando.executeUpdate("insert into detdocumentosc(numero,ide,producto,precio,cantidad,total) values ('"+fo+"','"+fo+"','"+dnombre20.getTextContent().toString()+"',"+dprecio20.getTextContent().toString()+","+dcant20.getTextContent().toString()+","+dmonto20.getTextContent().toString()+")");        
                  }
                   if(ti.equals("52")){
         comando.executeUpdate("insert into detdocumentoscgv(numero,ide,producto,precio,cantidad,total) values ('"+fo+"','"+fo+"','"+dnombre20.getTextContent().toString()+"',"+dprecio20.getTextContent().toString()+","+dcant20.getTextContent().toString()+","+dmonto20.getTextContent().toString()+")");        
                  }
                    if(ti.equals("61")){
         comando.executeUpdate("insert into detdocumentoscncv(numero,ide,producto,precio,cantidad,total) values ('"+fo+"','"+fo+"','"+dnombre20.getTextContent().toString()+"',"+dprecio20.getTextContent().toString()+","+dcant20.getTextContent().toString()+","+dmonto20.getTextContent().toString()+")");        
                  }
                   if(ti.equals("0")){
         comando.executeUpdate("insert into detdocumentosc(numero,ide,producto,precio,cantidad,total) values ('"+fo+"','"+fo+"','"+dnombre20.getTextContent().toString()+"',"+dprecio20.getTextContent().toString()+","+dcant20.getTextContent().toString()+","+dmonto20.getTextContent().toString()+")");        
                  }
                  }
       
       
        } catch(SQLException ex){
  
        JOptionPane.showMessageDialog(null,ex.getMessage().toString() );
       
              
        }  
          
         
      
        }           
         
         //referencias
         //<TpoDocRef>801</TpoDocRef> 
	//		<FolioRef>214097</FolioRef> 
	//		<FchRef>2019-06-19</FchRef> 
	//		<RazonRef>ORDEN DE COMPRA</RazonRef> 
                                
                                
         //si es ma //
     
        fileMove(dirxml+"/"+archivo, dirxmlout+"/"+archivo);   
         
         //fin referencias
                   
  } //fin insertar documentosc
                
             DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
             LocalDateTime now = LocalDateTime.now();  
             System.out.print(dtf.format(now));  
             System.out.println("Fin Proceso DTE");  
                   //  JOptionPane.showMessageDialog(null,"Fin Proceso DTE");
                    
                }//end of if clause

                }
            }//end of for loop with s var
           } 
            
            
             
  }
    
    
    
    public static void main(String[] args) {
        // TODO code application logic here
             cargarDriver();      
            String sDirectorio = "/Users/dev/NetBeansProjects/iCompras/emails";
                  String dirxml="/Users/dev/NetBeansProjects/iCompras/emails";
       
       String dirxmlout="/Users/dev/NetBeansProjects/iCompras/procesados";
            // String sDirectorio = dirxml;
              //JOptionPane.showMessageDialog(null, dirxml); 
             //System.out.println("Directorio->"+sDirectorio);
             String s;
             File f = new File(sDirectorio.toString());
             //File[] ficheros = f.listFiles(new Filtro(".xml"));
             File[] ficheros = f.listFiles();
             //System.out.println("Ficheros->"+ficheros.length);
            // JOptionPane.showMessageDialog(null, ficheros.length); 
          
                for (int x=0;x<ficheros.length;x++){
                    
                
                
                
                
                    System.out.println(ficheros[x].getName());
                    s=ficheros[x].getName();
               
                    	String fileName = s;
		String fe = FilenameUtils.getExtension(fileName);
		System.out.println("File extension is : "+fe);
                    if (fe.equals("xml") || fe.equals("XML")){
                        System.out.println(s);
                    leerxml(s);
                }else{
                       fileMove(dirxml+"/"+s, dirxmlout+"/"+s);     
                    }
                      }
                //
                if (ficheros.length==0){
                    System.out.println("No Hay Xml para Procesar");
                //  JOptionPane.showMessageDialog(null, "No Hay Xml para Procesar");  
                }
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilidades;
import java.sql.*;
import java.sql.Statement;
import Logica.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;



import javafx.collections.ObservableList;
/**
 *
 * @author Mati F
 */
public class Conexion  {
    
    
    
private static Connection miConexion;  


public Conexion (){
    
}

/**
 * Crea un conexion a la base de datos sitema stock
 */
public static void conexionMySql (){
    try{
          //Crea la conexion a nuestra base de datos
          Class.forName("com.mysql.jdbc.Driver");
          miConexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/sistema_stock", "root", "");
         
    
    }catch (ClassNotFoundException | SQLException e){
    
        MensajeAlerta.show("No se pudo conectar a base de datos1!!", "Conexion a DB");
    }
}  


/**
 * Recorre la tabla de usuarios y busca la coincidencia entre usuario y pass en una sola fila
     * @param user
     * @param contraseña
 * @return un Objeto ValidarUsuario si identifica un usuario con los parametros que se le pasan
 */
public static ValidarUsuario validarUsuario (String user, String contraseña){
        boolean aceptar_login = false;
        ValidarUsuario vu = null;
    try {
            String Query = "SELECT `nombre`,`apellido`,`usuario`, `contraseña`,`id_usuario`,`esAdmin` FROM `usuario` WHERE usuario = '" + user + "' AND contraseña = '"+ contraseña +"'";

            Statement st = miConexion.createStatement();
            java.sql.ResultSet resultSet;
            resultSet = st.executeQuery(Query);        
            
            
                while(resultSet.next()){

                    if ("S".equals(resultSet.getString("esAdmin"))){
                       Administrador adm =  new Administrador( resultSet.getString("usuario"), resultSet.getString("contraseña"), resultSet.getString("apellido"),resultSet.getString("nombre"));
                        adm.setId_usuario(resultSet.getInt("id_usuario"));  
                       vu = new ValidarUsuario(adm,aceptar_login);

                    } else{
                        Usuario u =  new Usuario( resultSet.getString("usuario"), resultSet.getString("contraseña"), resultSet.getString("apellido"),resultSet.getString("nombre"));
                       u.setId_usuario(resultSet.getInt("id_usuario"));  
                       vu = new ValidarUsuario(u,aceptar_login);
                    }
                }
            

        } catch (SQLException ex) {
            MensajeAlerta.show(ex.getMessage(), "Conexion a DB");
        }
return vu;
}

/**
 * recibe como parametro una ObsevableList del tableView de VentanaAdmin
 * @param data
 * @return la misma ObservableList pero cargada con los datos de la base de datos.
 */
public ObservableList<Persona> cargarDatos(ObservableList<Persona> data){

      try {
            String Query = "SELECT * FROM usuario";
            Statement st = miConexion.createStatement();
            java.sql.ResultSet resultSet;
            resultSet = st.executeQuery(Query);
            Administrador a;
            Usuario u;
             
            while (resultSet.next()) {
                
                if ("s".equals(resultSet.getString("esAdmin"))){
                    a = new Administrador (resultSet.getString("usuario"),resultSet.getString("contraseña"),resultSet.getString("apellido"),resultSet.getString("nombre")); 
                    a.setId_usuario(resultSet.getInt("id_usuario"));
                    data.add(a);  
                } else{
                    u = new Usuario (resultSet.getString("usuario"),resultSet.getString("contraseña"),resultSet.getString("apellido"),resultSet.getString("nombre")); 
                    u.setId_usuario(resultSet.getInt("id_usuario"));
                    data.add(u);      
                }
                
                   
            }  
        } catch (SQLException ex) {
            MensajeAlerta.show("No se pudo conectar!!", "Conexion a DB");
        }
 return data;
}
  

public static LinkedList<String> nombre_Usuarios(){
    LinkedList<String> lista = new LinkedList<>();
     try {
            String Query = "SELECT 'usuario' FROM usuario";
            Statement st = miConexion.createStatement();
            java.sql.ResultSet resultSet;
            resultSet = st.executeQuery(Query);
            while (resultSet.next()){
                lista.add(resultSet.getString("usuario"));
            }
            
     } catch(SQLException e ) {
         MensajeAlerta.show("No se pudo cargar los nombres de usuario!!", "Validar nombre de Usuario");
     }
     return lista;
}




 public void terminarConexion() {
        try {
            miConexion.close();
            MensajeAlerta.show("Se ha terminado la conexion con la base de datos!!", "Conexion a DB");
        } catch (SQLException ex) {
            MensajeAlerta.show("Error al terminar conexion con base de datos!!", "Conexion a DB");
        }
    }
 
 /**
  * recibe como parametro los datos de un cliente y los almacena en la base de datos 
  * @param Nombre
  * @param Apellido
  * @param DNI
  * @param Email
  * @param Telefono
  * @param Direccion 
  */
 
  public void insertarClientes( String Nombre, String Apellido, String DNI, String Email, String Telefono, String Direccion) {
      
      try {
            String Query = "INSERT INTO `cliente`VALUES ("
                    + "null, "
                    + "\"" + Nombre + "\", "
                    + "\"" + Apellido + "\", "
                    + "\"" + Email + "\", "
                    + "\"" + Telefono + "\", "
                    + "\"" + Direccion + "\", "
                    + "\"" + DNI + "\"  )";
                    
            Statement st = miConexion.createStatement();
            st.executeUpdate(Query);
            MensajeAlerta.show("Datos almacenados de forma exitosa", "Agregar Cliente");
            
        } catch (SQLException ex) {
            MensajeAlerta.show("Error en el almacenamiento de datos", "Agregar Cliente");
            
        }
    }
  
  /**
   * inserta un usuario del sistema en la base de datos
   * @param Nombre
   * @param Apellido
   * @param Usuario
   * @param Contraseña 
   * @param esAdmin 
   */
  public void insertarUsuario( String Nombre, String Apellido, String Usuario, String Contraseña,String esAdmin) {
      String a;
      if ("Administrador del sistema".equals(esAdmin)){
               a  = "s";
            }else {
                a = "n";
            }
      
      
        try {
            
           
            
             String Query = "INSERT INTO usuario VALUES("
                    + "\"" + Nombre + "\", "
                    + "\"" + Apellido + "\", "
                    + "\"" + Usuario + "\", "
                    + "\"" + Contraseña + "\","
                    + "null, "
                    + "\"" + a + "\"  )";
             
            Statement st = miConexion.createStatement();
            st.executeUpdate(Query);
            MensajeAlerta.show("Datos almacenados de forma exitosa", "Agregar Cliente");
            
        } catch (SQLException ex) {
            MensajeAlerta.show(ex.getMessage(), "Agregar Cliente");
            
        }
    }
  
  
  
  public void borrarUsuario(int id) {
        try {
            String Query = "DELETE FROM usuario WHERE id_usuario = \"" + id + "\"";
            Statement st = miConexion.createStatement();
            st.executeUpdate(Query);

        } catch (SQLException ex) {
            MensajeAlerta.show("Error borrando el registro especificado", "Error al Borrar");
        }
    }
  
  
  /**
   * borra un cliente de la base de datos por su dni
   * @param dni 
   */
  public void borrarCliente(String dni) {
        try {
            String Query = "DELETE FROM cliente WHERE dni = \"" + dni + "\"";
            Statement st = miConexion.createStatement();
            st.executeUpdate(Query);

        } catch (SQLException ex) {
            MensajeAlerta.show("Error borrando el registro especificado", "Error al Borrar");
        }
    }
  
  
    public void modificarUsuario (String usuarioNuevo, String usuarioViejo, String passNueva) {
        try {
            String Query = "UPDATE usuario SET usuario = \"" + usuarioNuevo + "\", contraseña = \"" + passNueva + "\" WHERE usuario = \"" + usuarioViejo + "\"  ";
            Statement st = miConexion.createStatement();
            st.executeUpdate(Query);
            MensajeAlerta.show("Datos almacenados de forma exitosa", "Modificar datos");

        } catch (SQLException ex) {
           MensajeAlerta.show("Error modificando el registro especificado", "Modificar datos");
            
        }
    
    }
    
    /**
     * recibe un dni y y los datos del cliente actualizados y los carga en la base de datos
     * @param dniViejo
     * @param c 
     */
    public void modificarCliente (String dniViejo, Cliente c){
        try {
            String Query = "UPDATE cliente SET id_cliente = \"" + c.getId_cliente()  + "\", nombre = \"" + c.getNombre() + "\", apellido = \"" + c.getApellido() + "\", mail = \"" + c.getMail()+ "\", telefono = \"" + c.getTelefono() + "\", direccion = \"" + c.getDireccion() +  "\", dni = \"" + c.getDNI() + "\" WHERE dni = \"" + dniViejo + "\"  ";
            Statement st = miConexion.createStatement();
            st.executeUpdate(Query);
            MensajeAlerta.show("Datos almacenados de forma exitosa", "Modificar datos");

        } catch (SQLException ex) {
           MensajeAlerta.show("Error modificando el registro especificado", "Modificar datos");   
        }
    }
        
    
    
    /**
 * recibe como parametro una ObsevableList del tableView de VentanaAdmin
 * @param data
 * @return la misma ObservableList pero cargada con los datos de la base de datos.
 */
    public ObservableList<Cliente> cargarCliente(ObservableList<Cliente> data){

      try {
            String Query = "SELECT * FROM cliente";
            Statement st = miConexion.createStatement();
            java.sql.ResultSet resultSet;
            resultSet = st.executeQuery(Query);
             
            while (resultSet.next()) {
                Cliente cliente = new Cliente();
                cliente.setApellido(resultSet.getString("apellido"));
                cliente.setNombre(resultSet.getString("nombre"));
                cliente.setDNI(resultSet.getString("dni"));
                cliente.setMail(resultSet.getString("mail"));
                cliente.setDireccion(resultSet.getString("direccion"));
                cliente.setTelefono(resultSet.getString("telefono"));
                cliente.setId_cliente(resultSet.getInt("id_cliente"));
                
                    data.add(cliente); // es la observable list que toma de parametro de la tableView      
            }  
        } catch (SQLException ex) {
            MensajeAlerta.show("No se pudo conectar!!", "Conexion a DB");
        }
 return data;
}

  /**
   * Recibe un string, que es la palabra a buscar, una observableList de una tableView y por que valores quiere q se realize la busqueda, devuelve la misma
   * obsevableList con el resultado de la consulta
   * @param buscarPalabra
   * @param selector
   * @param lista
   * @return 
   */
    public static ObservableList<Cliente>  buscarCliente (String buscarPalabra, ObservableList<Cliente> lista,String selector){

        if (Conexion.miConexion == null){
            conexionMySql ();
        }

        try {
            
            String prueba = "SELECT * FROM `cliente` WHERE `nombre` LIKE '%"
                      + buscarPalabra
                      + "%' OR `apellido` LIKE '%"
                      + buscarPalabra
                      + "%' OR CONCAT(nombre,' ',apellido) LIKE '%"
                      + buscarPalabra 
                      + "%';" 
                 ;

            if ("DNI".equals(selector) || "Direccion".equals(selector) ){
                prueba = "SELECT * FROM `cliente` WHERE `"+ selector +"` LIKE '%"
                + buscarPalabra
                + "%';"
                ;
            } 


              Statement st = miConexion.createStatement();
              java.sql.ResultSet resultSet;

              resultSet = st.executeQuery(prueba);  

              while (resultSet.next()) {   

                  Cliente cl = new Cliente();
                   cl.setNombre(resultSet.getString("nombre"));
                   cl.setApellido(resultSet.getString("apellido"));
                   cl.setDireccion(resultSet.getString("direccion"));
                   cl.setTelefono(resultSet.getString("telefono"));
                   cl.setDNI(resultSet.getString("dni"));
                   cl.setMail(resultSet.getString("mail"));

                   lista.add(cl);

              }     
          } catch (SQLException ex) {
              MensajeAlerta.show("Error en la busqueda de datos", "Buscar Cliente");

          }

        return lista;

    } 
    
    /**
     * Busca por id_producto y carga sus datos en un observableList que recibe como parametro. 
     * Si el codigo que recibe es == -1, cambia la consulta y devuelve todos los productos en la base de datos
     * @param lista
     * @param codigo
     * @return 
     */
    
    
    public static ArrayList<Producto>  cargarProducto (ArrayList<Producto> lista, int codigo){
       
        if (getMiConexion() == null){ // pregunta si la variable miconexion no es null, si es true conecta
            Conexion.conexionMySql();
        }
        
        
        try {
            
            String Query = "SELECT p.id_producto,p.nombre,p.precio,s.stock,s.id_stock,p.descripcion,c.id_proovedor,c.plazoDeEntrega,c.razon_social,c.direccion,c.telefono,c.mail FROM product p  INNER JOIN proovedor c ON c.id_proovedor = p.proovedor INNER JOIN stock s ON p.stock = s.id_stock WHERE p.id_producto = \"" + codigo + "\" ";
           
            if (codigo == -1){
               Query = "SELECT p.id_producto,p.nombre,p.precio,s.stock,s.id_stock,p.descripcion,c.plazoDeEntrega,c.id_proovedor,c.razon_social,c.direccion,c.telefono,c.mail FROM product p INNER JOIN proovedor c ON c.id_proovedor = p.proovedor INNER JOIN stock s ON p.stock = s.id_stock;";
            }
                            
            
            Statement st = miConexion.createStatement();              
            java.sql.ResultSet resultSet;
            resultSet = st.executeQuery(Query);               

            while (resultSet.next()) {  //While por cada uno de los resultados de la base de datos, la tabla productos

                Proovedor pr = new Proovedor(resultSet.getString("razon_social"),resultSet.getString("direccion"),resultSet.getString("mail"),resultSet.getString("telefono"));
                pr.setId_proovedor(resultSet.getInt("id_proovedor"));
                pr.setTiempoAproxEntrega(resultSet.getInt("plazoDeEntrega"));

                Producto p = new Producto(
                        resultSet.getString("nombre"),
                        resultSet.getString("descripcion"),
                        resultSet.getDouble("precio"),
                        pr,
                        resultSet.getInt("stock")
                );
                
                p.get_Stock().setId_stock(resultSet.getInt("id_stock"));
                p.setCodigo(resultSet.getInt("id_producto"));
                p.get_Stock().calculoStockMinimo(p, pr.getTiempoAproxEntrega());
                lista.add(p);
            }
        } catch (SQLException ex) {
            MensajeAlerta.show(ex.getMessage(), "Conexion a DB"); 
            MensajeAlerta.show("No se pudieron cargar los Productos!!", "Conexion a DB");                   
        }          
     return lista;
    }
    
    
    public static void eliminarProducto (int id){
        
        if (getMiConexion() == null){ // pregunta si la variable miconexion no es null, si es true conecta
            Conexion.conexionMySql();
        }
        try {
            String Query = "DELETE FROM product WHERE id_producto = \"" + id + "\"";
            Statement st = miConexion.createStatement();
            st.executeUpdate(Query);

        } catch (SQLException ex) {
            MensajeAlerta.show("Error borrando el registro especificado", "Error al borrar el producto");
        }               
    }
    
    
    
    
    
      public static void insertarProducto(Producto p) {
        int ultimo_id = 0;
        int id_ultimo_producto = 0;  
        try {
            
            
            
            String Query = "INSERT INTO `stock`(`stock`, `min_stock`, `id_stock`) VALUES ("
                    + "\"" + p.get_Stock().getStock() + "\", "
                    + "\"" + p.get_Stock().calculoStockMinimo(p, p.get_Proovedor().getTiempoAproxEntrega()) + "\","
                    + "" + "null" + ")";

            Statement st = miConexion.createStatement();
            st.executeUpdate(Query);
            
            java.sql.ResultSet rs;
            rs = st.executeQuery("Select LAST_INSERT_ID() from stock limit 1");                
            rs.next(); //para posicionar el puntero en la primer fila
            ultimo_id = rs.getInt("LAST_INSERT_ID()");

            Query = "INSERT INTO `product` (`id_producto`, `nombre`, `precio`, `stock`, `descripcion`, `proovedor`) VALUES ("
                           + "\"" + p.getId_producto() + "\", "
                           + "\"" + p.getNombre() + "\", "
                           + "\"" + p.getPrecio() + "\", "
                           + "\"" + ultimo_id + "\", "
                           + "\"" + p.getDescripcion() + "\", "
                           + "\"" + p.get_Proovedor().getId_proovedor() + "\" )";
        
           st.executeUpdate(Query);
           
            rs = st.executeQuery("Select LAST_INSERT_ID() from product limit 1");                
            rs.next(); //para posicionar el puntero en la primer fila
            id_ultimo_producto = rs.getInt("LAST_INSERT_ID()");
            
            Query = "INSERT INTO `ultimo_id_producto_guardado`(`ultimo_id`) VALUES ("
                    + "\"" + id_ultimo_producto + "\" )";
            
             st.executeUpdate(Query);
                   
            
            MensajeAlerta.show("Datos almacenados de forma exitosa", "Agregar Producto");
            
        } catch (SQLException ex) {
            MensajeAlerta.show("Error en el almacenamiento de datos", "Agregar Producto"); 
     
        }       
    }
    
      
    public static int ultimo_id_producto(){
        int ultimo = 0;
         try {
            String Query = "SELECT `ultimo_id` FROM `ultimo_id_producto_guardado` WHERE 1";
            Statement st = miConexion.createStatement();
            java.sql.ResultSet resultSet;
            resultSet = st.executeQuery(Query);
             
            while (resultSet.next()) {
                    ultimo = resultSet.getInt("ultimo_id");
            }  
        } catch (SQLException ex) {
            MensajeAlerta.show("No pudo leer el ultimo id de producto!!", "Conexion a DB");
        }
        
        return ultimo;
    }
      
      
     /**
     * recibe los datos del roducto actualizados y los carga en la base de datos
     * @param p
     *
     */
    public static void editarProducto (Producto p){
        try {
            
            String Query = "UPDATE `stock` SET stock = \"" + p.get_Stock().getStock() + "\", min_stock = \"" + p.get_Stock().getStockMinimo() +   "\" WHERE id_stock = \"" + p.get_Stock().getId_stock() + "\"  "; 
            Statement st = miConexion.createStatement();
            st.executeUpdate(Query);
            
            
            Query = "UPDATE product SET nombre = \"" + p.getNombre() + "\", precio = \"" + p.getPrecio() + "\", descripcion = \"" + p.getDescripcion()+ "\", proovedor = \"" + p.get_Proovedor().getId_proovedor() +  "\" WHERE id_producto = \"" + p.getId_producto() + "\"  ";            
            st.executeUpdate(Query);
            MensajeAlerta.show("Datos almacenados de forma exitosa", "Modificar Producto");

        } catch (SQLException ex) {
           MensajeAlerta.show("Error modificando el registro especificado", "Modificar Producto");   
             
        }
    }
      
      
      
    
    
     /**
   * Recibe un string, que es la palabra a buscar, una observableList de una tableView y por que valores quiere q se realize la busqueda, devuelve la misma
   * obsevableList con el resultado de la consulta
   * @param buscarPalabra
   * @param selector
   * @param lista
   * @return 
   */
    public static ObservableList<Producto>  consulta_producto (String buscarPalabra, ObservableList<Producto> lista,String selector){

        if (Conexion.miConexion == null){
            conexionMySql ();
        }

        try {
  

                String prueba = "SELECT p.id_producto,c.plazoDeEntrega,p.nombre,p.precio,s.stock,s.min_stock,p.descripcion,c.id_proovedor,c.razon_social,c.direccion,c.telefono,c.mail FROM product p INNER JOIN proovedor c ON c.id_proovedor = p.proovedor INNER JOIN stock s ON p.stock = s.id_stock WHERE `"+ selector +"` LIKE '%"
                + buscarPalabra
                + "%';" ;
                
                if ("proovedor".equals(selector)){ // busca por razon_social en la tabla proovedor
                    
                    prueba = "SELECT c.id_producto,c.nombre,c.precio,s.stock,s.min_stock,c.descripcion,p.id_proovedor,c.plazoDeEntrega,p.razon_social,p.direccion,p.telefono,p.mail FROM proovedor p INNER JOIN product c ON p.id_proovedor = c.proovedor INNER JOIN stock s ON c.stock = s.id_stock WHERE `"+ selector +"` LIKE '%"
                            + buscarPalabra
                            + "%';" ; 
                }

              Statement st = miConexion.createStatement();
              java.sql.ResultSet resultSet;

              resultSet = st.executeQuery(prueba);  

              while (resultSet.next()) {   
                  
                   Proovedor pr = new Proovedor(resultSet.getString("razon_social"),resultSet.getString("direccion"),resultSet.getString("mail"),resultSet.getString("telefono"));
                   pr.setId_proovedor(resultSet.getInt("id_proovedor"));
                   pr.setTiempoAproxEntrega(resultSet.getInt("plazoDeEntrega"));
                  
                   Producto p = new Producto(
                        resultSet.getString("nombre"),
                        resultSet.getString("descripcion"),
                        resultSet.getDouble("precio"),
                        pr,
                        resultSet.getInt("stock")
                );
                p.get_Stock().setStockMinimo(resultSet.getInt("min_stock"));
                p.setCodigo(resultSet.getInt("id_producto"));
                lista.add(p);

              }     
          } catch (SQLException ex) {
              MensajeAlerta.show("Error en la busqueda de datos", "Buscar Producto");

          }

        return lista;

    }
    
    
//    public static ObservableList<Producto>  producto_venta (String buscarPalabra, ObservableList<Producto> lista,String selector){
//
//        if (Conexion.miConexion == null){
//            conexionMySql ();
//        }
//
//        try {
//  
//
//                String prueba = "SELECT  p.id_producto,p.nombre,p.precio,s.stock,p.descripcion,c.id_proovedor,c.plazoDeEntrega,c.razon_social,c.direccion,c.telefono,c.mail FROM product p INNER JOIN proovedor c ON c.id_proovedor = p.proovedor INNER JOIN stock s ON p.stock = s.id_stock WHERE `"+ selector +"` LIKE '%"
//                + buscarPalabra
//                + "%';" ;
//                
//               
//
//              Statement st = miConexion.createStatement();
//              java.sql.ResultSet resultSet;
//
//              resultSet = st.executeQuery(prueba);  
//
//              while (resultSet.next()) {   
//                  
//                   Proovedor pr = new Proovedor(resultSet.getString("razon_social"),resultSet.getString("direccion"),resultSet.getString("mail"),resultSet.getString("telefono"));
//                   pr.setId_proovedor(resultSet.getInt("id_proovedor"));
//                   pr.setTiempoAproxEntrega(resultSet.getInt("plazoDeEntrega"));
//                  
//                   Producto p = new Producto(
//                        resultSet.getString("nombre"),
//                        resultSet.getString("descripcion"),
//                        resultSet.getDouble("precio"),
//                        pr,
//                        resultSet.getInt("stock")
//                );
//                p.setCodigo(resultSet.getInt("id_producto"));
//                
//                lista.add(p);
//
//              }     
//          } catch (SQLException ex) {
//              MensajeAlerta.show("Error en la busqueda de datos", "Buscar Producto");
//
//          }
//
//        return lista;
//
//    }
//    
//    
//    
    
    
   
    public static ObservableList<Proovedor> cargarProovedor (ObservableList<Proovedor> lista){
        if (Conexion.miConexion == null){
            conexionMySql ();
        }
          
        try {
               String Query = "SELECT * FROM proovedor";
               Statement st = miConexion.createStatement();
                
               java.sql.ResultSet resultSet;
               resultSet = st.executeQuery(Query);

              while (resultSet.next()) {   
                  Proovedor p = new Proovedor();
                  p.setDireccion(resultSet.getString("direccion"));
                  p.setRazon_social(resultSet.getString("razon_social"));
                  p.setTelefono(resultSet.getString("telefono"));
                  p.setMail(resultSet.getString("mail"));
                  p.setId_proovedor(resultSet.getInt("id_proovedor"));
                  p.setTiempoAproxEntrega(resultSet.getInt("plazoDeEntrega"));
                  lista.add(p);
                   
              }                
        } catch (SQLException e) {
            MensajeAlerta.show("Error en la carga de datos", "Cargar Proovedor");
            
        }          
        return lista;      
    }
    
    public static void eliminarProovedor (int id){
        if (Conexion.miConexion == null){
            conexionMySql ();
        }
          
         try {
            String Query = "DELETE FROM proovedor WHERE id_proovedor = \"" + id + "\"";
            Statement st = miConexion.createStatement();
            st.executeUpdate(Query);
            Query = "DELETE FROM product WHERE proovedor = \"" + id + "\"";
            st.executeUpdate(Query);

        } catch (SQLException ex) {
            MensajeAlerta.show("Error borrando el registro especificado", "Error al Borrar");
        }
        
        
        
    }
    
    
    public static void insertarProovedor (String razon, String dir, String mail, String tel,int plazoEntrega){
        if (Conexion.miConexion == null){
            conexionMySql ();
        }
        
        
        try {
             String Query = "INSERT INTO `proovedor`VALUES ("
                    + "null, "
                    + "\"" + razon + "\", "
                    + "\"" + dir + "\", "
                    + "\"" + tel + "\", "
                     + "\"" + mail + "\", "
                    + "\"" + plazoEntrega + "\"  )";
            Statement st = miConexion.createStatement();
            st.executeUpdate(Query);
            MensajeAlerta.show("Datos almacenados de forma exitosa", "Agregar Cliente");
            
        } catch (SQLException ex) {
            MensajeAlerta.show("Error en el almacenamiento de datos", "Agregar Cliente");
            
        }
    }
    
    
    public static void editarProovedor (Proovedor p, int id){
         try {
            String Query = "UPDATE proovedor SET id_proovedor = \"" + p.getId_proovedor() + "\", razon_social = \"" + p.getRazon_social() + "\", direccion = \"" + p.getDireccion() +"\", telefono = \"" + p.getTelefono() +  "\", mail = \"" + p.getMail() + "\", plazoDeEntrega = \"" + p.getTiempoAproxEntrega() +"\" WHERE id_proovedor = \"" + id + "\"  ";
            Statement st = miConexion.createStatement();
            st.executeUpdate(Query);
            MensajeAlerta.show("Datos almacenados de forma exitosa", "Modificar datos");

        } catch (SQLException ex) {
           MensajeAlerta.show("Error modificando el registro especificado", "Modificar datos");   
        }
    }
    
    
    
    /**
   * Recibe un string, que es la palabra a buscar, una observableList de una tableView y por que valores quiere q se realize la busqueda, devuelve la misma
   * obsevableList con el resultado de la consulta
   * @param buscarPalabra
   * @param selector
   * @param lista
   * @return 
   */
    public static ObservableList<Proovedor>  buscarProovedor (String buscarPalabra, ObservableList<Proovedor> lista,String selector){

        if (Conexion.miConexion == null){
            conexionMySql ();
        }

        try {

               String prueba = "SELECT * FROM `proovedor` WHERE `"+ selector +"` LIKE '%"
                + buscarPalabra
                + "%';"
                ;
             


              Statement st = miConexion.createStatement();
              java.sql.ResultSet resultSet;

              resultSet = st.executeQuery(prueba);  

              while (resultSet.next()) {   

                  Proovedor p = new Proovedor();
                   p.setId_proovedor(resultSet.getInt("id_proovedor"));
                   p.setRazon_social(resultSet.getString("razon_social"));                   
                   p.setDireccion(resultSet.getString("direccion"));
                   p.setTelefono(resultSet.getString("telefono"));                  
                   p.setMail(resultSet.getString("mail"));
                   p.setTiempoAproxEntrega(resultSet.getInt("plazoDeEntrega"));

                   lista.add(p);

              }     
          } catch (SQLException ex) {
              MensajeAlerta.show("Error en la busqueda de datos", "Buscar Proovedor");

          }

        return lista;

    }

    
    public static int registroVenta(LocalDate fecha, Cliente cl, double precio_final, int id_usuario,ArrayList<Producto> listaProductos) {
        
        
        if (Conexion.miConexion == null){
            conexionMySql ();
        }
        
        int ultimo_id;
        try {
             String Query = "INSERT INTO `venta`(`id_venta`, `fecha`, `cliente`, `total`, `cajero`) VALUES ("
                    + "null, "
                    + "\"" + fecha + "\", "
                    + "\"" + cl.getId_cliente() + "\", "
                    + "\"" + precio_final + "\", "
                    + "\"" + id_usuario + "\"  )";
             
             
            Statement st = miConexion.createStatement();
            st.executeUpdate(Query);
            
            java.sql.ResultSet rs;
            rs = st.executeQuery("Select LAST_INSERT_ID() from venta limit 1");                
            rs.next(); //para posicionar el puntero en la primer fila
            ultimo_id = rs.getInt("LAST_INSERT_ID()");
            
            for (Producto p: listaProductos){
            
                Query = "INSERT INTO `venta_producto`(`id_venta_producto`, `id_venta`, `id_producto`,`cantidad`) VALUES("
                        + "null, "
                         + "\"" + ultimo_id + "\", "
                        + "\"" + p.getId_producto() + "\", "
                        + "\"" + p.get_Stock().getStock() + "\"  )";
                st.executeUpdate(Query);
            
            }
            
            MensajeAlerta.show("Datos almacenados de forma exitosa.\nVenta numero: " + ultimo_id, "Confirmar Venta");
            
        } catch (SQLException ex) {
            
            MensajeAlerta.show(ex.getMessage(), "Confirmar Venta");
            ultimo_id= -1;
            
        }
        
        return ultimo_id;
    }
    
    
    
    public static Producto lector (int code){
    
         if (Conexion.miConexion == null){
            conexionMySql ();
        }
         Producto p = null;
        try {

            
            String prueba="SELECT p.id_producto,p.nombre,p.precio,s.stock,s.id_stock,s.min_stock,p.descripcion,c.id_proovedor,c.plazoDeEntrega,c.razon_social,c.direccion,c.telefono,c.mail FROM product p  INNER JOIN proovedor c ON c.id_proovedor = p.proovedor INNER JOIN stock s ON p.stock = s.id_stock WHERE p.id_producto = \"" + code + "\" ";
            

              Statement st = miConexion.createStatement();
              java.sql.ResultSet resultSet;

              resultSet = st.executeQuery(prueba);  
              
             
              if (resultSet.next()){

                  
             

                    Proovedor pr = new Proovedor(resultSet.getString("razon_social"),resultSet.getString("direccion"),resultSet.getString("mail"),resultSet.getString("telefono"));
                    pr.setId_proovedor(resultSet.getInt("id_proovedor"));
                    pr.setTiempoAproxEntrega(resultSet.getInt("plazoDeEntrega"));

                     p = new Producto(
                            resultSet.getString("nombre"),
                            resultSet.getString("descripcion"),
                            resultSet.getDouble("precio"),
                            pr,
                            resultSet.getInt("stock")
                    );
                    p.setCodigo(resultSet.getInt("id_producto"));
                    p.get_Stock().setId_stock(resultSet.getInt("id_stock"));
                    p.get_Stock().calculoStockMinimo(p, pr.getTiempoAproxEntrega());

              }    
            

                    

                
          } catch (SQLException ex) {
              MensajeAlerta.show("Error en la busqueda de productos en la base de datos", "Buscar Produto");

          }

        
        
    
        return p;
    }
    
     public static Producto lector_Nombre (String descripcion){
    
         if (Conexion.miConexion == null){
            conexionMySql ();
        }
         Producto p = null;
        try {

            
              String prueba="SELECT p.id_producto,p.nombre,p.precio,s.stock,s.id_stock,s.min_stock,p.descripcion,c.id_proovedor,c.plazoDeEntrega,c.razon_social,c.direccion,c.telefono,c.mail FROM product p  INNER JOIN proovedor c ON c.id_proovedor = p.proovedor INNER JOIN stock s ON p.stock = s.id_stock WHERE descripcion = \"" + descripcion +"\" ";
            
//              prueba = "SELECT `id_producto`, `nombre`, `precio`, `descripcion`, `proovedor`, `stock` FROM product WHERE `descripcion` = \"pc\"";
              Statement st = miConexion.createStatement();
              java.sql.ResultSet resultSet;

              resultSet = st.executeQuery(prueba);  
              
             
             if ( resultSet.next()){

                  
                Proovedor pr = new Proovedor(resultSet.getString("razon_social"),resultSet.getString("direccion"),resultSet.getString("mail"),resultSet.getString("telefono"));
                pr.setId_proovedor(resultSet.getInt("id_proovedor"));
                pr.setTiempoAproxEntrega(resultSet.getInt("plazoDeEntrega"));

                 p = new Producto(
                        resultSet.getString("nombre"),
                        resultSet.getString("descripcion"),
                        resultSet.getDouble("precio"),
                        pr,
                        resultSet.getInt("stock")
                );
                p.setCodigo(resultSet.getInt("id_producto"));
                p.get_Stock().setId_stock(resultSet.getInt("id_stock"));
                p.get_Stock().calculoStockMinimo(p, pr.getTiempoAproxEntrega());
              
             }

                   

                
          } catch (SQLException ex) {
              MensajeAlerta.show("Error en la busqueda de productos en la base de datos", "Buscar Produto");
              
          }

        
        
    
        return p;
    }
  
     
     public static boolean cuentaCorriente (int id_usuario){
      if (Conexion.miConexion == null){
            conexionMySql ();
        }
         
      boolean hayCuenta = false;
      
        try {            
             String prueba="SELECT cuentaCorriente FROM `cliente` WHERE id_cliente = \"" + id_usuario +"\" ";
            
              Statement st = miConexion.createStatement();
              java.sql.ResultSet resultSet;
              resultSet = st.executeQuery(prueba);                
            
              if (resultSet.next()){
                 if ("s".equals(resultSet.getString("cuentaCorriente"))){
                  hayCuenta= true;
                 }            
             } 
              
          } catch (SQLException ex) {
              MensajeAlerta.show("Error en la busqueda de Cuenta Corriente en la base de datos", "Buscar Cuenta Corriente");
              
          }
         return hayCuenta;    
     }
     
     
     public static LinkedList<Venta> cargarCuentaCorriente (Cliente c){
      if (Conexion.miConexion == null){
            conexionMySql ();
        }
      
      LinkedList<Venta> listaVentas = new LinkedList();
      ArrayList<Producto> listaProductos;
      Proovedor proovedor = new Proovedor();
      Venta ve = null;
      ArrayList<Integer> lista_id = new ArrayList();
      
      
        try {            
             String prueba="SELECT `id_cuentaCorriente`, `venta` FROM `cuentacorriente` WHERE cliente = \"" + c.getId_cliente() +"\" AND baja =0 ";
            
              Statement st = miConexion.createStatement();
              java.sql.ResultSet resultSet;
              resultSet = st.executeQuery(prueba);               
                  
            
              while (resultSet.next()){ // get id_venta, relacionados al cliente seleccionado
                  
                  
                 lista_id.add(resultSet.getInt("venta")); 
                  
                   
              }
              
              
              
              for (int x = 0 ;x < lista_id.size(); x++){
 
                 prueba = "SELECT id_venta_producto, p.id_producto,p.nombre,p.precio,p.descripcion,s.stock,pro.razon_social,pro.mail,sold.fecha,sold.total,sold.id_venta,u.nombre,u.apellido, u.contraseña, u.usuario FROM venta_producto v "
                         + "INNER JOIN product p on p.id_producto= v.id_producto "
                         + "INNER JOIN stock s on p.stock = s.id_stock "
                         + "INNER JOIN proovedor pro on p.proovedor = pro.id_proovedor "
                         + "INNER JOIN venta sold on v.id_venta = sold.id_venta "
                         + "INNER JOIN usuario u on u.id_usuario = sold.cajero "
                         + "WHERE v.id_venta = \"" + lista_id.get(x) +"\" ";   
                 
                 java.sql.ResultSet result; 
                 result = st.executeQuery(prueba);     
                 
                 listaProductos = new ArrayList();
                 while (result.next()){
                    listaProductos.add(new Producto(result.getString("p.nombre"),result.getString("descripcion"), result.getDouble("p.precio"), proovedor, result.getInt("stock")));     
                    ve = new Venta(result.getDouble("total"),c, listaProductos, result.getDate("fecha"), new Usuario(result.getString("usuario"), result.getString("contraseña"), result.getString("u.nombre"), result.getString("apellido")));             
                    ve.setId_venta(result.getInt("sold.id_venta"));
                 }
                
                listaVentas.add(ve);
               
              }
              
          } catch (SQLException ex) {
              
             
              MensajeAlerta.show(ex.getMessage(), "Cuenta Corriente");
          }
        
         return listaVentas;    
     }
     
     
    /**
     * Inserta una venta en la tabla cuenta corriente a su respectivo cliente
     * @param cliente
     * @param id_venta
     * @return 
     */
    
     public static boolean insertarVentaEnCuentaCorriente(Cliente cliente, int id_venta){
         boolean ok;
          if (Conexion.miConexion == null){
            conexionMySql ();
        } 
        try {
             String Query = "INSERT INTO `cuentacorriente`(`id_cuentaCorriente`, `cliente`, `venta`, `baja`) VALUES ("
                    + "null, "
                    + "\"" + cliente.getId_cliente() + "\", "
                      + "\"" + id_venta + "\", "
                    + "0)";
             
             
            Statement st = miConexion.createStatement();
            st.executeUpdate(Query);
            ok = true;
            
        } catch (SQLException ex){
            ok = false;
        }
         return ok;
     }
     
     
    
     /**
      * Da de baja una venta en cuenta corriente(pagada), devulve un true para confirmar la operación
      * @param id_venta
      * @return 
      */
     public static boolean pagarCuentaCorriente(int id_venta){ 
        
        if (Conexion.miConexion == null){
            conexionMySql ();
        } 
        try {
             String Query = "UPDATE `cuentacorriente` SET `baja` =1 WHERE venta = \"" + id_venta + "\"  ";
                  
             
             
            Statement st = miConexion.createStatement();
            st.executeUpdate(Query);
            
            return true;
        } catch (SQLException ex){
            return false;
        }
        
     } 
     
     
     public static ObservableList<String> cargarProvincia (ObservableList<String> lista){
         
        if (Conexion.miConexion == null){
           conexionMySql ();
        }
             
        try {
            String prueba = "SELECT `id_provincia`, `provincia` FROM `provincias`";   
                 
            
            Statement st = miConexion.createStatement();
                 java.sql.ResultSet result; 
                 result = st.executeQuery(prueba);     
                 
                 
                 while (result.next()){
                    lista.add(result.getString("provincia"));
                 }
                  return lista;
            
        } catch (SQLException ex) {            
            MensajeAlerta.show(ex.getMessage(), "Confirmar provincia");
           return null;            
        }

     }
     
     
     public static boolean cargarDatosMonotributo (ClaseDatosMonotributo datos, LocalDate fecha){
        if (Conexion.miConexion == null){
           conexionMySql ();
        }
         
         
          try {
             String Query = "INSERT INTO `datosmonotributo`(`razonSocial`, `cuit`, `calle`, `numero`, `piso`, `dpto`, `localidad`, `provincia`, `cp`, `cai`, `inicioActividades`) VALUES ("
                   + "\"" + datos.getRazonSocial() + "\", "
                     + "\"" + datos.getCuit() + "\", "
                     + "\"" + datos.getCalle() + "\", "
                     + "\"" + datos.getNumero() + "\", "
                     + "\"" + datos.getPiso() + "\", "
                     + "\"" + datos.getDpto() + "\", "
                     + "\"" + datos.getLocalidad() + "\", "
                    + "\"" + datos.getProvincia() + "\", "
                     + "\"" + datos.getCp() + "\", "
                     + "\"" + datos.getCai() + "\", "
                    + "\"" + fecha + "\"  )";
             
            
            Statement st = miConexion.createStatement();
            st.executeUpdate(Query);
            return true;
            
        } catch (SQLException ex){
           
            return false;
        }   
     }
     
     
     public static ClaseDatosMonotributo datosDeMonotributo(ClaseDatosMonotributo datos){
        if (Conexion.miConexion == null){
            conexionMySql ();
        }
              
        try {   
             String prueba="SELECT `razonSocial`, `cuit`, `calle`, `numero`, `piso`, `dpto`, `localidad`, `provincia`, `cp`, `cai`, `inicioActividades` FROM `datosmonotributo`  ";

             Statement st = miConexion.createStatement();
              java.sql.ResultSet resultSet;
              resultSet = st.executeQuery(prueba);                
            
             if(resultSet.first()){
                 
               datos.setRazonSocial(resultSet.getString("razonSocial"));
               datos.setCuit(resultSet.getLong("cuit"));
               datos.setCalle(resultSet.getString("calle"));
               datos.setNumero(resultSet.getString("numero"));
               datos.setPiso(resultSet.getInt("piso"));
               datos.setDpto(resultSet.getString("dpto"));
               datos.setLocalidad(resultSet.getString("localidad"));
               datos.setProvincia(resultSet.getString("provincia"));
               datos.setCp(resultSet.getInt("cp"));
               datos.setCai(resultSet.getLong("cai"));
               datos.setInicioActividades(resultSet.getDate("inicioActividades"));
               
               
             } else {
                 datos = null;
             }
               
             
              
          } catch (SQLException ex) {
              
              datos = null;
          }
     
     
         
         return datos;
     } 
     
     
     public static boolean  actualizarDatosMonotributo (ClaseDatosMonotributo datos, LocalDate fecha){
     
        if (Conexion.miConexion == null){
           conexionMySql ();
        }                                                                                                           
         
        try {
            String Query = "UPDATE `datosmonotributo` SET razonSocial = \"" + datos.getRazonSocial() + "\", calle = \"" + datos.getCalle() + "\", numero = \"" + datos.getNumero() + "\", piso = \"" + datos.getPiso() + "\", dpto = \"" + datos.getDpto() + "\", localidad = \"" + datos.getLocalidad() +"\", provincia = \"" + datos.getProvincia()+"\", cp = \"" + datos.getCp()+"\", cai = \"" + datos.getCai()+"\", inicioActividades = \"" + fecha +"\" WHERE cuit = \"" + datos.getCuit() + "\"  ";
            Statement st = miConexion.createStatement();
            st.executeUpdate(Query);
            return true;
        } catch (SQLException ex) {
            
            return false;
        }
   
     
     }
     
     
     public static int factura (int id_venta, long id_factura,String condicionDeVenta, long cuitMonotributo){
         
        if (Conexion.miConexion == null){
           conexionMySql ();
        }         
         
         try {
             
             String Query = "INSERT INTO `factura`(`numeroFactura`, `venta`, `datosMonotributo`, `condicionDeVenta`) VALUES("
                + "null, "
                 + "\"" + id_venta + "\", "
                 + "\"" + cuitMonotributo + "\", "
                 + "\"" + condicionDeVenta + "\"  )";

            Statement st = miConexion.createStatement();
            st.executeUpdate(Query);
            
            java.sql.ResultSet rs;
            rs = st.executeQuery("Select LAST_INSERT_ID() from venta limit 1");                
            rs.next(); //para posicionar el puntero en la primer fila
         
            return rs.getInt("LAST_INSERT_ID()");
            
             
         } catch (Exception e) {
             MensajeAlerta.show(e.getMessage(), "factura");
             
             return -1;
         }
     }
     
     
     public static int descontarStock (ArrayList<Producto> lista){
        if (Conexion.miConexion == null){
           conexionMySql ();
        }         
         boolean aux = false;
        try {
             String Query;
             
             int stockViejo;
             int stockActual;
             int stockMinimo;
             
            for (Producto p : lista){
                
                 
                Query = "SELECT `stock`, `min_stock` FROM `stock` WHERE `id_stock` = \"" + p.get_Stock().getId_stock()+ "\"  ";
                Statement st = miConexion.createStatement();
                java.sql.ResultSet resultSet;
                resultSet = st.executeQuery(Query);                
            
                 if (resultSet.next()){
                    stockViejo = resultSet.getInt("stock");
                    stockMinimo = resultSet.getInt("min_stock");
                
                  
                
                    if (p.getStock() <= stockViejo){
                        stockActual = stockViejo - p.getStock(); 

                        if (stockActual <= 0){
                            MensajeAlerta.show("Se ha quedado sin stock para el producto " + p.getNombre(), "Stock");    
                        } else {
                            if ( stockActual <= stockMinimo){
                              MensajeAlerta.show("El producto " + p.getNombre() + " ha llegado al stock mínimo deseado\nStock Actual: "+stockActual +"\nStock mínimo: "+ stockMinimo, "Stock");   
                            }
                        }
                        
                        
                        
                        Query = "UPDATE `stock` SET `stock` = \"" + stockActual + "\" WHERE `id_stock` = \"" + p.get_Stock().getId_stock()  + "\"  ";
                        st.executeUpdate(Query);
                        aux = true;

                       } else {
                            
                         if (MensajeDeConfirmacion.show("El producto " + p.getNombre() + " no posee el suficiento stock para realizar esta operación\nStock disponible: "+ stockViejo + "\nDesea Editar el producto y agregarle stock..??", "Descontar Stock", "Aceptar", "Cancelar")){
                             return -1;
                         } else {
                             return 0;
                         }
                                                   
                            
                       }
                 }
            }
            
            
            
                 return 1;
           
            
            
          
           
            
        } catch (SQLException ex) {
             MensajeAlerta.show(ex.getMessage(), "Confirmar descuento de stock");
            return 0;
        } 
        
   

     }
     
     
     
     public static int[] dibujarGrafico (Producto p, int[] meses, int año){
         if (Conexion.miConexion == null){
           conexionMySql ();
        }         
         
         int contador = 0;
         int y = 1;
         
         for (int x = 0; x < meses.length; x++){
             
              
              String desde = año + "/"+y+"/01";
              String hasta = año + "/"+y+"/31";
              y ++;
         
              String Query;
             
              Query = "SELECT `fecha`,`cliente`, `total`, vp.cantidad FROM `venta`v INNER JOIN venta_producto vp ON vp.id_venta = v.id_venta WHERE fecha BETWEEN '" + desde + " 00:00:00' AND '" + hasta + " 23:59:59' AND vp.id_producto = \"" + p.getId_producto()  + "\"  ";  
         
               try {
                   Statement st = miConexion.createStatement();
                    java.sql.ResultSet resultSet;
                    resultSet = st.executeQuery(Query);                

                    while( resultSet.next()){
                        contador = contador + resultSet.getInt("cantidad");
                    
                    }
                    meses[x] = contador;
                    contador = 0;

                } catch (SQLException ex) {
                     MensajeAlerta.show(ex.getMessage(), "Datos para grafico");
                    
                } 
     
              
              
         }
              
              //         "SELECT `fecha`,`cliente`, `total`,vp.id_venta, vp.cantidad FROM `venta`v INNER JOIN 
//         venta_producto vp ON vp.id_venta = v.id_venta WHERE fecha BETWEEN '2017/06/01 00:00:00' AND ' 2017/06/06 23:59:59'AND vp.id_producto = 11"
       
     
        return meses;
     }
  
     
     /**
      * Devuelve los movimientos de caja diaria, de una fecha que se le pasa como parametro
      * @param fecha
      * @return 
      */
     
     public static CajaDiaria verCajaDiaria (LocalDate fecha){
         
      if (Conexion.miConexion == null){
           conexionMySql ();
        }     
      
      String desde = fecha.getYear() + "/"  + fecha.getMonthValue() + "/"  + fecha.getDayOfMonth();
      
      CajaDiaria caja = new CajaDiaria(fecha);
         try {
            Statement st = miConexion.createStatement();
            java.sql.ResultSet resultSet;
            
            String Query = "SELECT `cajero`, `motivo`, `tipoMovimiento`, u.nombre, u.apellido ,`total`, `id_movimiento`, `fecha` FROM `movimiento` INNER JOIN usuario u on u.id_usuario = cajero WHERE `fecha` BETWEEN '" + desde + " 00:00:00' AND '" + desde + " 23:59:59'";
             
            resultSet = st.executeQuery(Query); 
             
            Movimiento movimiento;
    
            while(resultSet.next()){
                if ("INGRESO".equals(resultSet.getString("tipoMovimiento"))) {
                    movimiento = new Movimiento(Movimiento.tipoMovimiento.INGRESO, resultSet.getDouble("total"), resultSet.getString("motivo"));
                    movimiento.setCajero(resultSet.getString("nombre") + "" + resultSet.getString("apellido"));
                    caja.setMovimiento(movimiento);                  
                } else {
                    movimiento = new Movimiento(Movimiento.tipoMovimiento.EGRESO, resultSet.getDouble("total"), resultSet.getString("motivo"));
                    movimiento.setCajero(resultSet.getString("nombre") + "" + resultSet.getString("apellido"));
                    caja.setMovimiento(movimiento);
                }                            
             }
            return caja;
  
         } catch (SQLException ex) {
           MensajeAlerta.show(ex.getMessage(), "");
           return caja;     
         }      
     }
     
     /**
      * Devuelve un true si la carga de un movimiento en la base de datos es exitosa, de lo contrario false
      * @param m
      * @param fecha
      * @param id_cajero
      * @return 
      */
     
     public static boolean confirmarMovimiento (Movimiento m, LocalDate fecha,int id_cajero){
     
        if (Conexion.miConexion == null){
          conexionMySql ();
        }     
           
         try {           
            String Query = "INSERT INTO `movimiento`(`id_movimiento`, `fecha` ,`cajero`, `motivo`, `tipoMovimiento`, `total`) VALUES("
            + "null, "
            + "\"" + fecha + "\", "
            + "\"" + id_cajero + "\", "
            + "\"" + m.getMotivo() + "\", "
            + "\"" +  m.getTipoM().toString()+ "\", "
            + "\"" + m.getValor() + "\"  )";

            Statement st = miConexion.createStatement();
            st.executeUpdate(Query);
                        
         } catch (SQLException e) {
             MensajeAlerta.show(e.getMessage(), "error");
             return false;
         }
       return true;        
     }
     
     
     
     
     
     
     
    public static Connection getMiConexion() {
        return miConexion;
    }
  
  
  
  
  






























  
  
  
    
}

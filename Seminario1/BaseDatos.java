import java.sql.*;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class BaseDatos {

    private Connection conexion;

    BaseDatos(String usuario) {     //Nombre usuario y password = DNI sin letra y al que le has cambiado el primer dígito o letra por una x (minúscula)

        System.out.println("Conectándose a la BD...\n\n");
        try
        {
            //nombre del servidor
            String nombre_servidor = "oracle0.ugr.es";
            //numero del puerto
            String numero_puerto = "1521";
            //URL "jdbc:oracle:thin:@nombreServidor:numeroPuerto/practbd.oracle0.ugr.es"
            String url = "jdbc:oracle:thin:@" + nombre_servidor + ":" + numero_puerto + "/practbd.oracle0.ugr.es";

            //Obtiene la conexion
            conexion = DriverManager.getConnection( url, usuario, usuario );
            conexion.setAutoCommit(false);
        }catch( Exception e ){
            e.printStackTrace();
        }
    }

    void cerrarConexion() {
        try{
            conexion.close();
        }catch( Exception e ){
            e.printStackTrace();
        }
    }

    void eliminarTablas(){
        String consulta ="";
        Statement stmt;
        try{
            consulta = "DELETE Detalle_Pedido";
            stmt  = conexion.createStatement();
            stmt.executeUpdate(consulta);

            consulta = "DROP TABLE Detalle_Pedido";
            stmt  = conexion.createStatement();
            stmt.executeUpdate(consulta);
            System.out.println("Tabla Detalle_Pedido eliminada");

        } catch(Exception e){
            System.out.println("La tabla Detalle_Pedido no existia");
        }

        try{
            consulta = "DELETE Pedido";
            stmt  = conexion.createStatement();
            stmt.executeUpdate(consulta);

            consulta = "DROP TABLE Pedido";
            stmt  = conexion.createStatement();
            stmt.executeUpdate(consulta);
            System.out.println("Tabla Pedido eliminada");

        } catch(Exception e){
            System.out.println("La tabla Pedido no existía");
        }

        try{
            consulta = "DELETE Stock";
            stmt  = conexion.createStatement();
            stmt.executeUpdate(consulta);

            consulta = "DROP TABLE Stock";
            stmt  = conexion.createStatement();
            stmt.executeUpdate(consulta);
            System.out.println("Tabla Stock eliminada");

        } catch(Exception e){
            System.out.println("La tabla Stock no existia");
        }
    }

    void crearTablas(){
        String formatoStock =
        "create table Stock " + "(Cproducto INT NOT NULL PRIMARY KEY, " +
        "Cantidad INT NOT NULL)";
        
        String formatoPedido =
        "create table Pedido " + "(Cpedido INT NOT NULL PRIMARY KEY, " + 
        "Ccliente INT NOT NULL, " + "Fecha_Pedido DATE NOT NULL)";
        
        String formatoDetallePedido =
        "create table Detalle_Pedido " + "(Cpedido INT NOT NULL, " +
        "Cproducto INT NOT NULL, " + "Cantidad INT NOT NULL, " +
        "FOREIGN KEY(Cpedido) REFERENCES Pedido(Cpedido), " + 
        "FOREIGN KEY(Cproducto) REFERENCES Stock(Cproducto), " + 
        "PRIMARY KEY (Cpedido, Cproducto))";

        //Crear tabla Stock
        try (Statement stmt = conexion.createStatement()){
            stmt.executeUpdate(formatoStock);
            System.out.println("Tabla Stock creada.");
        } catch (SQLException e) {
            System.out.println("Problema creando Stock \n " + e);
        }    

        //Rellenar stock con 10 tuplas
        try (Statement stmt = conexion.createStatement()){
            stmt.executeUpdate("insert into stock values(1, 100)");
            stmt.executeUpdate("insert into stock values(2, 63)");
            stmt.executeUpdate("insert into stock values(3, 72)");
            stmt.executeUpdate("insert into stock values(4, 84)");
            stmt.executeUpdate("insert into stock values(5, 340)");
            stmt.executeUpdate("insert into stock values(6, 64)");
            stmt.executeUpdate("insert into stock values(7, 230)");
            stmt.executeUpdate("insert into stock values(8, 150)");
            stmt.executeUpdate("insert into stock values(9, 90)");
            stmt.executeUpdate("insert into stock values(10, 97)");
            System.out.println("10 tuplas insertadas en Stock.");

        } catch (SQLException e) {
            System.out.println(e);
        }

        //Crear tabla Pedido
        try (Statement stmt = conexion.createStatement()){
            stmt.executeUpdate(formatoPedido);
            System.out.println("Tabla Pedido creada.");
        } catch (SQLException e) {
            System.out.println("Problema creando Pedido \n " + e);
        }
        
        //Crear tabla Detalle_Pedido
        try (Statement stmt = conexion.createStatement()){
            stmt.executeUpdate(formatoDetallePedido);
            System.out.println("Tabla Detalle_Pedido creada.");
            stmt.execute("COMMIT");
        } catch (SQLException e) {
            System.out.println("Problema creando Detalle_Pedido \n " + e);
        }
        
        
    }
    void mostrarTablas() {
        try{
            Statement sentencia_Stock = conexion.createStatement();
            Statement sentencia_Pedido = conexion.createStatement();
            Statement sentencia_Detalle_Pedido = conexion.createStatement();

            ResultSet resultado_Stock = sentencia_Stock.executeQuery( "SELECT * FROM Stock" );
            ResultSet resultado_Pedido = sentencia_Pedido.executeQuery( "SELECT * FROM Pedido" );
            ResultSet resultado_Detalle_Pedido = sentencia_Detalle_Pedido.executeQuery( "SELECT * FROM Detalle_Pedido" );
            System.out.println ( "Mostrando la tabla Stock \n" );
            System.out.println("Cproducto\tCantidad");
            while ( resultado_Stock.next() )
                {
                    System.out.println ( resultado_Stock.getInt( 1 ) + "\t\t" + resultado_Stock.getInt( 2 ) );
                }
            System.out.println ( "\nMostrando la tabla Pedido \n" );
            System.out.println("Cpedido\t\tCcliente\tFecha-Pedido");
            while ( resultado_Pedido.next() )
                {
                    System.out.println ( resultado_Pedido.getInt( 1 ) + "\t\t" + resultado_Pedido.getInt( 2 ) + "\t\t" + resultado_Pedido.getString( 3 ) );
                }    
            System.out.println ( "\nMostrando la tabla Detalle_Pedido \n" );
            System.out.println("Cpedido\t\tCproducto\tCantidad");
            while ( resultado_Detalle_Pedido.next() )
                {
                    System.out.println ( resultado_Detalle_Pedido.getInt( 1 ) + "\t\t" + resultado_Detalle_Pedido.getInt( 2 ) + "\t\t" + resultado_Detalle_Pedido.getInt( 3 ));
                }
            sentencia_Stock.close();
            sentencia_Pedido.close();
            sentencia_Detalle_Pedido.close();
        }catch( Exception e ){
            e.printStackTrace();
        }
        
    }
    
    //Si queremos modificar la fecha manualmente descomentamos lo siguiente y comentamos la linea 179
    // void darDeAlta (int Cpedido, int Ccliente, String fecha){
    void darDeAlta(int Cpedido, int Ccliente) {
        try{
            Statement sentencia = conexion.createStatement();
            //Para introducir fecha manualmente descomentar siguiente linea y comentar la linea 185
            // sentencia.executeQuery( "INSERT INTO PEDIDO VALUES (" + Integer.toString(Cpedido) + ", " + Integer.toString(Ccliente) + ", " + "TO_DATE('" + fecha + "', 'dd/mm/yyyy'), )" );
            sentencia.executeQuery( "INSERT INTO PEDIDO VALUES (" + Integer.toString(Cpedido) + ", " + Integer.toString(Ccliente) + ", " + "SYSDATE)" );
            int opcion = 0;
            Scanner scanner = new Scanner(System.in);
            Savepoint puntoSeguro = conexion.setSavepoint();        //Pedido insertado
            while (opcion != 4) {
                MostrarMenuDarDeAlta();
                opcion = scanner.nextInt();

                switch (opcion) {
                    case 1:
                        System.out.println("Introduzca el codigo del articulo: ");
                        int codigoArticulo = scanner.nextInt();
                        System.out.println("Introduzca la cantidad: ");
                        int cantidadArticulo = scanner.nextInt();
                        
                        try (Statement stmt = conexion.createStatement()){
                             ResultSet cantidadRes = stmt.executeQuery("SELECT CANTIDAD FROM STOCK WHERE Cproducto=" + Integer.toString(codigoArticulo));
                            int cantidadRest =0;
                             while ( cantidadRes.next() )
                                {
                                    cantidadRest = cantidadRes.getInt(1);
                                }
                            // cantidadRest = cantidadRes.getInt(1);
                            if(cantidadRest >= cantidadArticulo){
                                cantidadRest -= cantidadArticulo;
                                stmt.executeUpdate("insert into Detalle_Pedido values(" + Integer.toString(Cpedido) + ", " + Integer.toString(codigoArticulo) + ", " + Integer.toString(cantidadArticulo) + ")");
                                stmt.executeUpdate("update Stock set Cantidad=" + Integer.toString(cantidadRest) + " WHERE Cproducto=" + Integer.toString(codigoArticulo));         
                            } else {
                                System.out.println("\nLa cantidad solicitada es mayor al stock restante, quedan " + Integer.toString(cantidadRest) + " en stock del producto seleccionado." );
                            }
                            mostrarTablas();
                            
                        } catch (SQLException e) {
                            System.out.println(e);
                        }
                        break;
                    
                    case 2:
                        try {
                            conexion.rollback(puntoSeguro);
                            mostrarTablas();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    
                    case 3:
                        try {
                            sentencia.close();
                            conexion.rollback();
                            opcion = 4;
                            mostrarTablas();
                        } catch ( Exception e ){
                            e.printStackTrace();
                        }
                        break;

                    case 4:
                        try {
                            sentencia.close();
                            conexion.commit(); //COMMIT
                        } catch ( Exception e ){
                            e.printStackTrace();
                        }
                        break;
                    default:
                        System.out.println("\nOpción no válida, por favor introduzca una de las opciones del menú.");
                        break;
                }
            }
        }catch( Exception e ){
            e.printStackTrace();
        }
        
    }


    public void MostrarMenuPrincipal(){
        System.out.println("\n\t********MENU********");
        System.out.println("1. Borrado y creación de tablas e inserción de datos en la tabla Stock.");
        System.out.println("2. Dar de alta nuevo pedido.");
        System.out.println("3. Mostrar el contenido de las tablas.");
        System.out.println("4. Salir del programa y cerrar la conexión a la BD.");
        System.out.println("\t**********************\n");
    }
    
    private void MostrarMenuDarDeAlta(){
        System.out.println("\t******** NUEVO PEDIDO ********");
        System.out.println("1. Añadir detalle de producto.");
        System.out.println("2. Eliminar detalle de producto.");
        System.out.println("3. Eliminar pedido (vuelve a Menú Principal)");
        System.out.println("4. Finalizar y guardar pedido");
        System.out.println("\t********************************\n");
    }
    
    Connection getConnection(){
        return this.conexion;
    }

}

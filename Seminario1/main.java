import java.sql.*;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Scanner;

//Para compilar
//javac main.java BaseDatos.java
//java -cp ojdbc8.jar:. main

public class main {

    public static void main(String args[]){
        int opcionMenu = -1;
        System.out.println("\nIntroduzca su usuario para entrar a la Base de Datos (coincide con su contraseña): ");
        String entrada;
        Scanner scanner = new Scanner(System.in);
        entrada = scanner.nextLine();
        
        BaseDatos bd = new BaseDatos(entrada);

        if (bd.getConnection() != null){
            System.out.println("Conexion establecida.\n");
        }

        while (opcionMenu != 4) {
            try{
                Savepoint opcion3 = bd.getConnection().setSavepoint();
            
                bd.MostrarMenuPrincipal();
                opcionMenu = scanner.nextInt();

                switch (opcionMenu) {

                    case 1:
                        bd.eliminarTablas();

                        try{
                            Thread.sleep(2000);
                        }catch(InterruptedException e ) {
                        }

                        System.out.println("\n\n");
                        bd.crearTablas();
                        break;

                    case 2:
                        bd.getConnection();
                        System.out.println("Introduzca los datos del pedido: ");
                        System.out.println("Codigo del pedido: ");
                        int Cpedido = scanner.nextInt();
                        System.out.println("Codigo del cliente: ");
                        int Ccliente = scanner.nextInt();
                        System.out.println("Fecha del pedido (dd/mm/yyyy): ");
                        scanner.nextLine();
                        String fecha = scanner.nextLine();
                        
                        bd.darDeAlta(Cpedido, Ccliente, fecha, opcion3);
                    
                        break;
                    case 3:
                        System.out.println("\n");

                        bd.mostrarTablas();
                        break;
                    case 4:
                        bd.cerrarConexion();
                        break;
                    default:
                        System.out.println("\nOpción no válida, por favor seleccione una de las opciones disponibles");
                }
            } catch( Exception e ){
                e.printStackTrace();
            }
        }
    } 
}


//https://gist.github.com/jujogracu/3063672
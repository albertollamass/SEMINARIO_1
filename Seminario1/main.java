import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SavePoint;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Scanner;


//Para compilar
//javac main.java BaseDatos.java
//java -cp ojdbc8.jar:. main

public class main {

    void MostrarMenuPrincipal(){
        System.out.println("\t********MENU********\n");
        System.out.println("1. Borrado y creación de tablas e inserción de datos en la tabla Stock.");
        System.out.println("2. Dar de alta nuevo pedido.");
        System.out.println("3. Mostrar el contenido de las tablas.");
        System.out.println("4. Salir del programa y cerrar la conexión a la BD.");
        System.out.println("\t**********************");
    }

    public static void main(String args[]){
        int opcionMenu = -1;
        System.out.println("\nIntroduzca su usuario para entrar a la Base de Datos (coincide con su contraseña): ");
        String entrada;
        Scanner scanner = new Scanner(System.in);
        entrada = scanner.nextLine();
        
        BaseDatos bd = new BaseDatos(entrada);
        if (bd.getConnection() != null){
            System.out.println("Conexion estacblecida.");
        }
        while (opcionMenu != 4) {
            
            MostrarMenuPrincipal();
            opcionMenu = scanner.nextInt();

            switch (opcionMenu) {
                case 1:
                    break;
                case 2:
                    bd.getConnection().
                    System.out.println("Introduzca los datos del pedido: ");
                    System.out.println("Codigo del pedido: ");
                    


                    break;
                case 3:
                    break;
                case 4:
                    bd.cerrarConexion();
                    break;
                default:
                    System.out.println("\nOpción no válida, por favor seleccione una de las opciones disponibles");
            }
        }
    } 
}


//https://gist.github.com/jujogracu/3063672
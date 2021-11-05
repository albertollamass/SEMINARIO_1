import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
 
public class ConexionJavaOracle {
 
    public static void main( String []arg ){
        System.out.println("Tutorial para conectarse a la BD\n\n");
        try
        {
            //Se carga el driver JDBC
            DriverManager.registerDriver( new oracle.jdbc.driver.OracleDriver() );
             
            //nombre del servidor
            String nombre_servidor = "oracle0.ugr.es";
            //numero del puerto
            String numero_puerto = "1521";

            //URL "jdbc:oracle:thin:@nombreServidor:numeroPuerto/practbd.oracle0.ugr.es"
            String url = "jdbc:oracle:thin:@" + nombre_servidor + ":" + numero_puerto + "/practbd.oracle0.ugr.es";
 
            //Nombre usuario y password
            //tu DNI sin letra al final y al que le has cambiado el primer dígito o letra por una x (minúscula).
            String usuario = "xDNI";
            String password = "xDNI";
 
            //Obtiene la conexion
            Connection conexion = DriverManager.getConnection( url, usuario, password );
             
            //Para realiza una consulta
            Statement sentencia = conexion.createStatement();
            ResultSet resultado = sentencia.executeQuery( "SELECT * FROM TIPO_OPERACION" );
             
            //Se recorre el resultado obtenido
            while ( resultado.next() )
            {
                //Se imprime el resultado colocando
                //Para obtener la primer columna se coloca el numero 1 y para la segunda columna 2 el numero 2
                System.out.println ( resultado.getInt( 1 ) + "\t" + resultado.getString( 2 ) );
            }
             
            //Cerramos la sentencia
            sentencia.close();
        }catch( Exception e ){
            e.printStackTrace();
        }
    }
}

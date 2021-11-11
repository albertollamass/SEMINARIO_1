

public class conexionOracle {

    protected Connection conexion;

    ConexionOracle() {

        System.out.println("Conectándose a la BD\n\n");
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
            conexion = DriverManager.getConnection( url, usuario, password );
        }catch( Exception e ){
            e.printStackTrace();
        }
    }
}

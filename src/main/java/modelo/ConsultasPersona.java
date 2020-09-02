/*En esta clase heredamos de la clase Conexion, ya que estaremos haciendos consultas con Java*/
package modelo;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

public class ConsultasPersona extends Conexion{
    PreparedStatement ps; //Hacer las diferentes consultas a la DB
    ResultSet rs; //Para cuando obtengamos datos de la DB
        
    
    //Este metodo es para saber si se esta haciendo la insercion correctamente
    //Le pasamos un objeto de la clase Persona
    public boolean insertar(Persona persona){
        //Desde aqui mandamos a llamar el metodo para hacer establecer la conexion con nuestra DB
        Connection conexion = getConnection(); 
        
        //Hacemos un bloque try-catch por si algo malo sucede al tratar de hacer la conexion con la DB
        try{
            ps = conexion.prepareStatement("insert into persona (clave, nombre, domicilio, celular, correo_electronico, fecha_nacimiento, genero) values (?,?,?,?,?,?,?)");
            ps.setString(1, persona.getClave());
            ps.setString(2, persona.getNombre());
            ps.setString(3, persona.getDomicilio());
            ps.setString(4, persona.getCelular());
            ps.setString(5, persona.getCorreo_electronico());
            ps.setDate(6, (Date) persona.getFecha_nacimiento()); //Casteo para fecha de nacimiento
            ps.setString(7, persona.getGenero());
            
            int resultado = ps.executeUpdate(); //La insercion que se hace se almacena en resultado
            
            //Si la insercion ha sido correcta retornamos true
            if(resultado > 0){
                return true;
            }else{
                return false;
            }
            
        }catch(Exception ex){
            //Por si ocurre algun error con la Db aqui nos mostrara el error que este pasando
            JOptionPane.showMessageDialog(null, "Error: "+ex);
            //Si cai aqui la exception que tambien retorne un false
            return false;
        }finally{
            try{
                conexion.close();
            }catch(Exception ex){
                JOptionPane.showMessageDialog(null, "Error al cerrar Conexion: "+ex);
            }
        }
    }   
    
    
    
    
    public boolean buscar(Persona persona){
        //Desde aqui mandamos a llamar el metodo para hacer establecer la conexion con nuestra DB
        Connection conexion = getConnection(); 
        
        //Hacemos un bloque try-catch por si algo malo sucede al tratar de hacer la conexion con la DB
        try{
            ps = conexion.prepareStatement("select * from persona where clave=?");
            ps.setString(1, persona.getClave());
            rs = ps.executeQuery(); //Se usa esta sentencia por que vamos a tener un resultado de nuestra DB
            
            //Significa que encontramos un contenido siguiendo esta consulta
            if(rs.next() == true){
                persona.setIdPersona(rs.getInt("idPersona"));
                persona.setClave(rs.getString("clave"));
                persona.setNombre(rs.getString("nombre"));
                persona.setDomicilio(rs.getString("domicilio"));
                persona.setCelular(rs.getString("celular"));
                persona.setCorreo_electronico(rs.getString("correo_electronico"));
                persona.setFecha_nacimiento(rs.getDate("fecha_nacimiento"));
                persona.setGenero(rs.getString("genero"));  
                
                //Si encontro a la persona por medio de su clave, retorna verdadero
                return true;
                
            }else{
                return false;
            }
            
        }catch(Exception ex){
            //Por si ocurre algun error con la Db aqui nos mostrara el error que este pasando
            JOptionPane.showMessageDialog(null, "Error: "+ex);
            //Si cai aqui la exception que tambien retorne un false
            return false;
        }finally{
            try{
                conexion.close();
            }catch(Exception ex){
                JOptionPane.showMessageDialog(null, "Error al cerrar Conexion: "+ex);
            }
        }
    }
    
    
    
    
    
    
    public boolean modificar(Persona persona){
        //Desde aqui mandamos a llamar el metodo para hacer establecer la conexion con nuestra DB
        Connection conexion = getConnection(); 
        
        //Hacemos un bloque try-catch por si algo malo sucede al tratar de hacer la conexion con la DB
        try{
            ps = conexion.prepareStatement("update persona set clave=?, nombre=?, domicilio=?, celular=?, correo_electronico=?, fecha_nacimiento=?, genero=? where idPersona=?");
            ps.setString(1, persona.getClave());
            ps.setString(2, persona.getNombre());
            ps.setString(3, persona.getDomicilio());
            ps.setString(4, persona.getCelular());
            ps.setString(5, persona.getCorreo_electronico());
            ps.setDate(6, (Date) persona.getFecha_nacimiento()); //Casteo para fecha de nacimiento
            ps.setString(7, persona.getGenero());
            ps.setInt(8, persona.getIdPersona());
                    
            int resultado = ps.executeUpdate(); //La insercion que se hace se almacena en resultado
            
            //Si la insercion ha sido correcta retornamos true
            if(resultado > 0){
                return true;
            }else{
                return false;
            }
            
        }catch(Exception ex){
            //Por si ocurre algun error con la Db aqui nos mostrara el error que este pasando
            JOptionPane.showMessageDialog(null, "Error: "+ex);
            //Si cai aqui la exception que tambien retorne un false
            return false;
        }finally{
            try{
                conexion.close();
            }catch(Exception ex){
                JOptionPane.showMessageDialog(null, "Error al cerrar Conexion: "+ex);
            }
        }
    }
    
    
    
    
    
    
    
    
    
    public boolean eliminar(Persona persona){
        //Desde aqui mandamos a llamar el metodo para hacer establecer la conexion con nuestra DB
        Connection conexion = getConnection(); 
        
        //Hacemos un bloque try-catch por si algo malo sucede al tratar de hacer la conexion con la DB
        try{
            //Le pasamos el id de la persona que queremos eliminar
            ps = conexion.prepareStatement("delete from persona where idPersona=?");
            ps.setInt(1, persona.getIdPersona());
                    
            int resultado = ps.executeUpdate(); //La insercion que se hace se almacena en resultado
            
            //Si la insercion ha sido correcta retornamos true
            if(resultado > 0){
                return true;
            }else{
                return false;
            }
            
        }catch(Exception ex){
            //Por si ocurre algun error con la Db aqui nos mostrara el error que este pasando
            JOptionPane.showMessageDialog(null, "Error: "+ex);
            //Si cai aqui la exception que tambien retorne un false
            return false;
        }finally{
            try{
                conexion.close();
            }catch(Exception ex){
                JOptionPane.showMessageDialog(null, "Error al cerrar Conexion: "+ex);
            }
        }
    }
    
    
    
    
    
}
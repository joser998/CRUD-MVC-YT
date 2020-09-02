package crud_mvc;
import controlador.ControladorPersona;
import modelo.ConsultasPersona;
import modelo.Persona;
import vista.VistaPersona;

public class CRUD_MVC {
    public static void main(String[] args) {
        
    VistaPersona vista = new VistaPersona(); //Objeto de clase VistaPersona (donde esta la interfaz)
    Persona persona = new Persona(); //Objeto de clase Persona (es parte del "Modelo" es la clase: Persona tiene todos los atributos que usamos con sus getter y setter)
    ConsultasPersona modelo = new ConsultasPersona(); //Objeto de clase ConsultasPersona (es parte del "Modelo" es la clase: ConsultasPersona donde se estaran aplicando todos los CRUD del programa)
    
    //Se envia tambien un objeto de cada clase pertenenciente a: vista->"Vista"  persona->"Modelo"  modelo->"Modelo"
    ControladorPersona controlador = new ControladorPersona(vista, persona, modelo); //Objeto de clase: ControladorPersona (es parte del "Controlador" es la clase: ControladorPersona aqui se estaran recibiendo las peticiones de los usuarios e iran hacia el "Modelo") 
        
    //Para que inicie con toda la App
    controlador.iniciar();
    vista.setVisible(true);
    
    
    
    
    
    }
}
/*Esta clase hace un intermediario entre la vista (ordenes del usuario) y la logica de negocios (Modelo)
  lo cual es el modelo (en este caso todas las clases que estan en paquete modelo)*/
//Esta clase es precisamente para insertar
//En este caso esta clase implementa(desarrolla) la interfaz: ControladorPersona
//Esto por que debe de controlar todos los eventos que estaran generando los botones etc
//de la parte de la interfaz
package controlador;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import javax.swing.JOptionPane;
import modelo.ConsultasPersona;
import modelo.Persona;
import vista.VistaPersona;

public class ControladorPersona implements ActionListener{
    /*Se crean estos dos tipos de objetos por que al ser esta clase abstracta "Controlador"
        tiene comunicacion tanto con la "Vista" asi como con el "Modelo" */
    //Se crean objetos de la clase VistaPersona (Vista usuario)
    //Se crean objetos de la clase Persona (Modelo)
    private VistaPersona vista;
    private Persona persona;
    private ConsultasPersona modelo;

    //Inicializamos cada uno de estos Objetos Creados    
    public ControladorPersona(VistaPersona vista, Persona persona, ConsultasPersona modelo) {
        this.vista = vista;
        this.persona = persona;
        this.modelo = modelo;
        
        //Aqui indicamos de donde vamos a usar este ActionListener
        //en este caso "vista" actuara con el btnInsertar de mi clase VistaPersona
        vista.btnInsertar.addActionListener(this);
        vista.btnLimpiar.addActionListener(this);
        vista.btnBuscar.addActionListener(this);
        vista.btnModificar.addActionListener(this);
        vista.btnEliminar.addActionListener(this);
    }
    
    //Para cuando inicie la GUI
    public void iniciar(){
        vista.setTitle("CRUD  MODELO-VISTA-CONTROLADOR");
        vista.setLocationRelativeTo(null);
    }

    //Implementacion del metodo abstracto que debe implementar la interfaz ActionListener
    //El cual en este caso corresponde al boton de insertar
    //Al ser presionado el boton insertar toma los datos de las cajas correspondientes y los 
    //almacena en el objeto "persona"
    @Override
    public void actionPerformed(ActionEvent botonAccion){
        //Si de nuestra vista se ha seleccionado el boton insertar:    
        if(botonAccion.getSource() == vista.btnInsertar){
            persona.setClave(vista.txtClave.getText());
            persona.setNombre(vista.txtNombre.getText());
            persona.setDomicilio(vista.txtDomicilio.getText());
            persona.setCelular(vista.txtCelular.getText());
            persona.setCorreo_electronico(vista.txtCorreoElectronico.getText());
            persona.setFecha_nacimiento(Date.valueOf(vista.txtFechaNacimiento.getText()));
            persona.setGenero(vista.jComboBoxGenero.getSelectedItem().toString());
            
            //Toda esta info que esta siendo obtenida por el objeto persona
            //seran enviados a nuestro metodo "insertar" de la clase: ConsultasPersona el cual es
            //booleano por lo tanto se debe hacer el siguiente procedimiento con if:
            //este if esta usando el objeto "modelo" que pertenece a la misma clase ConsultasPersona
            //por lo tanto si esta if retorna un booleano como true significa que la insercion de la persona
            //ha sido correcta
            if(modelo.insertar(persona) == true){
                JOptionPane.showMessageDialog(null, "Registro Insertado Correctamente.");
                limpiarCajas();
            }else{
                JOptionPane.showMessageDialog(null, "Error al insertar registro");
                limpiarCajas();
            }
        }
        
        
        //Se trata de obtener lo que el usuario esta tratando de encontrar
        if(botonAccion.getSource() == vista.btnBuscar){
            //Toma la informacion de la caja de texto que es para buscar
            persona.setClave(vista.cajaBuscar.getText());
            
            //Si se encontro a la persona mediante su id que nos muestre toda la info
            if(modelo.buscar(persona) == true){
                JOptionPane.showMessageDialog(null, "Registro Encontrado.");
                vista.textArea.setText("idPersona: "+String.valueOf(persona.getIdPersona())
                                        +"\nClave: "+persona.getClave()
                                        +"\nNombre: "+persona.getNombre()
                                        +"\nDomicilio: "+persona.getDomicilio()
                                        +"\nCelular: "+persona.getCelular()
                                        +"\nCorreo Electronico: "+persona.getCorreo_electronico()
                                        +"\nFecha de Nacimiento: "+String.valueOf(persona.getFecha_nacimiento())
                                        +"\nGenero: "+persona.getGenero());
            }else{
                JOptionPane.showMessageDialog(null, "No existe una Persona registrada con esa Clave.");
                limpiarCajas();
            }    
        }
        
            
        //Dentro de este mismo metodo de actionPerformed
        //Una vez que se presione el boton para limpiar, se ejecuta metodo de limpiar todas las cajas
        if(botonAccion.getSource() == vista.btnLimpiar){
            limpiarCajas();
        }
        
        
        if(botonAccion.getSource() == vista.btnModificar){
            //Al presionar este boton toma todo lo que este en las cajas para tratar de modificar
            //segun el id
            persona.setIdPersona(Integer.parseInt(vista.cajaID2.getText()));
            persona.setClave(vista.txtClave.getText());
            persona.setNombre(vista.txtNombre.getText());
            persona.setDomicilio(vista.txtDomicilio.getText());
            persona.setCelular(vista.txtCelular.getText());
            persona.setCorreo_electronico(vista.txtCorreoElectronico.getText());
            persona.setFecha_nacimiento(Date.valueOf(vista.txtFechaNacimiento.getText()));
            persona.setGenero(vista.jComboBoxGenero.getSelectedItem().toString());
            
            if(modelo.modificar(persona) == true){
                JOptionPane.showMessageDialog(null, "Registro Modificado Correctamente...");
                limpiarCajas();
            }else{
                JOptionPane.showMessageDialog(null, "No es posible modificar este Registro");
                limpiarCajas();
            }
        }
        
        
        //Condicional para eliminar
        if(botonAccion.getSource() == vista.btnEliminar){
            
            //tomamos el id de la Persona que queremos eliminar
            persona.setIdPersona(Integer.parseInt(vista.cajaID2.getText()));
            
            //Si retorna true significa que se elimino la persona
            if(modelo.eliminar(persona) == true){
                JOptionPane.showMessageDialog(null, "Registro Eliminado Correctamente.");
                limpiarCajas();
            }else{
                JOptionPane.showMessageDialog(null ,"El registro no pudo ser encontrado.");
                limpiarCajas();
            }
            
        }
        
        
        
        
        
        
        
    } 
    
    //Metodo para limpiar todas las cajas
    public void limpiarCajas(){
        vista.cajaID2.setText("");
        vista.cajaBuscar.setText("");
        vista.txtClave.setText("");
        vista.txtNombre.setText("");
        vista.txtDomicilio.setText("");
        vista.txtCelular.setText("");
        vista.txtCorreoElectronico.setText("");
        vista.txtFechaNacimiento.setText("");
        vista.jComboBoxGenero.setSelectedIndex(0);
    }
}
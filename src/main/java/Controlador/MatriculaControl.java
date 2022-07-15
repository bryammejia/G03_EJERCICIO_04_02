
package Controlador;

import Modelo.Auto;
import Modelo.Matricula;
import Modelo.Propietario;
import Servicio.AutoServicio;
import Servicio.MatriculaServicio;
import Servicio.PropietarioServicio;
import java.util.List;

/**
 *
 * @author USER
 */
public class MatriculaControl {

    private MatriculaServicio matriculaServicio = new MatriculaServicio();
    private AutoServicio autoServicio = new AutoServicio();
    private PropietarioServicio propietarioServicio = new PropietarioServicio();
    
    public Matricula crearMatricula(String [] args) throws RuntimeException{
        Auto auto = this.autoServicio.buscarPorCodigo(this.convertirEntero(args[4]));
        Propietario propietario = this.propietarioServicio.buscarPorCodigo(this.convertirEntero(args[5]));
        Matricula matricula = new Matricula(this.convertirEntero(args[0]), this.convertirEntero(args [1]), this.convertirEntero(args[2]), args[3],auto, propietario);
        this.matriculaServicio.crear(matricula);
        return matricula;
        
    }
    
    public Matricula buscarMatricula(String arg) throws RuntimeException{
        return this.matriculaServicio.buscarPorCodigo(this.convertirEntero(arg));
    }
    
    public Matricula eliminar(String arg) throws RuntimeException{
        return this.matriculaServicio.eliminar(this.convertirEntero(arg));
    }
    
    public Matricula modificar(String [] args) throws RuntimeException{
        Auto auto = this.autoServicio.buscarPorCodigo(this.convertirEntero(args[4]));
        Propietario propietario = this.propietarioServicio.buscarPorCodigo(this.convertirEntero(args[5]));
            Matricula matriculaNuevo = new Matricula(this.convertirEntero(args[0]), this.convertirEntero(args[1]), this.convertirEntero(args[2]), args[3], auto, propietario);
            this.matriculaServicio.modificar(Integer.valueOf(args[0]), matriculaNuevo);
           return matriculaNuevo;
}
    
    public List<Matricula> listar(){
        return this.matriculaServicio.listar();
                
    }
    private int convertirEntero(String numero){
        try
        {
            return Integer.valueOf(numero);
        }catch(NumberFormatException e){
            throw new RuntimeException("El campo ingresaso solamente recibe "
                    + " números");
        }catch(Exception e){
            throw new RuntimeException("Error inesperado");
        }
    }
    
}

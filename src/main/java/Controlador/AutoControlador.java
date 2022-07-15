
package Controlador;

import Modelo.Auto;
import Servicio.AutoServicio;
import java.util.List;

/**
 *
 * @author USER
 */
public class AutoControlador {

    private AutoServicio autoServicio = new AutoServicio();
    
    public Auto crearAuto(String [] args) throws RuntimeException{
        Auto auto = new Auto(this.convertirEntero(args[0]), args [1], args[2], this.convertirEntero(args[3]), this.convertirEntero(args[4]));
        this.autoServicio.crear(auto);
        return auto;
    }
        
    public Auto buscarAuto(String arg) throws RuntimeException{
        return this.autoServicio.buscarPorCodigo(this.convertirEntero(arg));
    }
    
    public Auto eliminar(String arg) throws RuntimeException{
        return this.autoServicio.eliminar(this.convertirEntero(arg));
    }
    public Auto modificar(String []args) throws RuntimeException{                                                                                                   
        Auto autoNuevo = new Auto(this.convertirEntero(args[0]), args[1], args[2], this.convertirEntero(args[3]), this.convertirEntero(args[4]));
        this.autoServicio.modificar(this.convertirEntero(args[0]), autoNuevo);
        return autoNuevo;
    }
    public List<Auto> listar(){
        return this.autoServicio.listar();
    }
    
    private int convertirEntero(String numero){
        try{
            return Integer.valueOf(numero);
        }catch(NumberFormatException e){
            throw new RuntimeException("El campo ingresado solamente recibe"+"numeros");
            
        }catch(Exception e){
            throw new RuntimeException("Error inesperado");
        }
    }
    
}

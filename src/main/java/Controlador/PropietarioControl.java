
package Controlador;

import Modelo.Propietario;
import Servicio.PropietarioServicio;
import java.util.List;


public class PropietarioControl {
    
    private PropietarioServicio propietarioServicio = new PropietarioServicio();
    
    public Propietario crearPropietario(String [] args) throws RuntimeException{
        Propietario propietario = new Propietario(this.convertirEntero(args[0]), args[1], this.convertirEntero(args[2]), this.convertirEntero(args[3]), this.convertirEntero(args[4]));
        this.propietarioServicio.crear(propietario);
        return propietario;
    }
    public Propietario buscarPropietario(String arg) throws RuntimeException{
        return this.propietarioServicio.buscarPorCodigo(this.convertirEntero(arg));
    }
    public Propietario eliminar(String arg) throws RuntimeException{
        return this.propietarioServicio.eliminar(this.convertirEntero(arg));
    }
    public Propietario modificar(String [] args) throws RuntimeException{
        Propietario propietarioNuevo = new Propietario(this.convertirEntero(args[0]), args[1],this.convertirEntero(args[2]), this.convertirEntero(args[3]), this.convertirEntero(args[4]));
        this.propietarioServicio.modificar(Integer.valueOf(args[0]), propietarioNuevo);
        return propietarioNuevo;
    }
    public List<Propietario> Listar(){
        return this.propietarioServicio.listar();
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


package Servicio;

import Modelo.Auto;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author USER
 */
public class AutoServicio implements IAutoServicio {
    
    private static List<Auto> autoList = new ArrayList<>();

    @Override
    public Auto crear(Auto auto) {
        this.autoList.add(auto);
        return auto;
    }

    @Override
    public Auto modificar(int codigoNuevo, Auto autoNuevo) {
        var posicion = this.buscarPosicion(this.buscarPorCodigo(codigoNuevo));
        this.listar().get(posicion).setMarca(autoNuevo.getMarca());
        this.listar().get(posicion).setColor(autoNuevo.getColor());
        this.listar().get(posicion).setYear(autoNuevo.getYear());
        this.listar().get(posicion).setPrecio(autoNuevo.getPrecio());
        return autoNuevo;
    }

    @Override
    public Auto eliminar(int codigo) {
      Auto auto = this.buscarPorCodigo(codigo) ;
      var posicion = this.buscarPosicion(auto);
      this.listar().remove(posicion);
      return auto;
      
    }

    @Override
    public Auto buscarPorCodigo(int codigo) {
        Auto auto = null;
        for(var a:this.listar()){
            if(codigo==a.getCodigo()){
                auto=a;
                break;
            }
        }
        return auto;
    }

    @Override
    public int buscarPosicion(Auto auto) {
        
        int posicion = -1;
        for(var a:this.autoList){
            posicion++;
            if(a.getCodigo()==auto.getCodigo()){
                break;
            }
        }
        return posicion;
    }

    @Override
    public List<Auto> listar() {
        return this.autoList;
    }

}


package Servicio;

import Modelo.Auto;
import Modelo.Propietario;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author USER
 */
public class AutoServicio implements IAutoServicio {
    
    private static List<Auto> autoList = new ArrayList<>();

    @Override
    public Auto crear(Auto auto) {
        this.autoList.add(auto);
        try {
            this.almacenarEnArchivo(auto,"c:/Progra/archivoAuto.obj");
        } catch (Exception ex) {
            Logger.getLogger(AutoServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
      return auto;
    }
    

    @Override
    public Auto modificar(int codigoNuevo, Auto autoNuevo) {
        var posicion = this.buscarPosicion(this.buscarPorCodigo(codigoNuevo));
        try {
            this.autoList=this.recuperarArchivo("c:/Progra/archivoAuto.obj");
        } catch (Exception ex) {
            Logger.getLogger(PropietarioServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        autoList.remove(posicion);
        autoList.add(posicion,autoNuevo);
        try {
            this.eliminarArchivo("c:/Progra/archivoAuto.obj");
        } catch (Exception ex) {
            Logger.getLogger(PropietarioServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        for(int i=0;i<autoList.size();i++){
            Auto auto=autoList.get(i);
         try {
             this.almacenarEnArchivo(auto,"c:/Progra/archivoAuto.obj");
         } catch (Exception ex) {
             Logger.getLogger(AutoServicio.class.getName()).log(Level.SEVERE, null, ex);
         }
        }
        
     
     return autoNuevo;
        
    }

    @Override
    public Auto eliminar(int codigo) {
      Auto auto = this.buscarPorCodigo(codigo);
        var posicion= this.buscarPosicion(auto);
        try {
            System.out.println("Si entro al recuperar");
            this.autoList=this.recuperarArchivo("c:/Progra/archivoAuto.obj");
        } catch (Exception ex) {
            Logger.getLogger(AutoServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        autoList.remove(posicion);
       System.out.println(autoList);
        try {
            System.out.println("Si se elimino el archivo");
            this.eliminarArchivo("c:/Progra/archivoAuto.obj");
        } catch (Exception ex) {
            System.out.println("No se elimino el archivo");
        }
        for(int i=0;i<autoList.size();i++){
            Auto auto2=autoList.get(i);
         try {
             this.almacenarEnArchivo(auto2,"c:/Progra/archivoAuto.obj");
         } catch (Exception ex) {
             System.out.println("El error esta en el for");
         }
        }
        return auto;
    }

    @Override
    public Auto buscarPorCodigo(int codigo) {
        Auto auto= null;
        for(var a:this.listar()){
            if(codigo==a.getCodigo()){
                auto = a;
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
        try {
            this.autoList=this.recuperarArchivo("c:/Progra/archivoAuto.obj");
        } catch (Exception ex) {
            throw new RuntimeException("No se puede recuperar datos del archivo"+ex.getMessage());
        }
        return this.autoList;
    }
    public boolean eliminarArchivo(String rutaArchivo) throws Exception {
        boolean llave=false;
        File archivo = new File(rutaArchivo);
        archivo.delete();
        System.out.println("Se completo la eliminacion");
        return llave;
    }

    @Override
    public boolean almacenarEnArchivo(Auto auto, String rutaArchivo) throws Exception {
        var retorno = false;
        ObjectOutputStream salida=null;
        try{
            salida = new ObjectOutputStream( new FileOutputStream(rutaArchivo,true) );
            salida.writeObject(auto);
            salida.close();
            retorno=true;
        }catch(IOException e)
        {
            salida.close();
        }
        return retorno;
    }

    @Override
    public List<Auto> recuperarArchivo(String rutaArchivo) throws Exception {
        /*var autoList = new ArrayList<Auto>();
        ObjectInputStream entrada=null;
        try{
            entrada = new ObjectInputStream(new FileInputStream(rutaArchivo));
            while(true){
                var codigo=entrada.readInt();
                var marca=entrada.readUTF();
                var color=entrada.readUTF();
                var year = entrada.readInt();
                var precio = entrada.readInt();
                var auto = new Auto(codigo, marca, color, year, precio);
                autoList.add(auto);
            }
        }catch(IOException e){
            entrada.close();
        }
        
        return autoList;*/
        var autoList = new ArrayList<Auto>();
        var fis = new FileInputStream(new File(rutaArchivo));
        ObjectInputStream entrada = null;
        try{        
            while(fis.available()>0){
                entrada = new ObjectInputStream(fis);
                Auto auto= (Auto) entrada.readObject();
                autoList.add(auto);
            }
            entrada.close();
        }catch(Exception ex){
            entrada.close();
        }
        return autoList;
        
    }
    
   
    private boolean existeCodigo(int codigo)
    {
        var retorno=false;
        for(var auto:this.autoList){
            if(auto.getCodigo()==codigo){
                retorno=true;
                break;
            }
        }
        return retorno;
    }

}


package Servicio;

import Modelo.Auto;
import Modelo.Propietario;
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
 * @author Jonathan
 */
public class PropietarioServicio implements IPropietarioServicio{
    
    private static List<Propietario> propietarioList= new ArrayList<>();

    @Override
    public Propietario crear(Propietario propietario) {
      this.propietarioList.add(propietario);
        try {
            this.almacenarEnArchivo(propietario,"c:/Progra/archivoPropietario.obj");
        } catch (Exception ex) {
            Logger.getLogger(PropietarioServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
      return propietario;
    }

    @Override
    public Propietario modificar(int codigoNuevo, Propietario propietarioNuevo) {
     var posicion = this.buscarPosicion(this.buscarPorCodigo(codigoNuevo));
     /*
     this.listar().get(posicion).setNombre(propietarioNuevo.getNombre());
     this.listar().get(posicion).setEdad(propietarioNuevo.getEdad());
     this.listar().get(posicion).setCedula(propietarioNuevo.getCedula());
     this.listar().get(posicion).setCelular(propietarioNuevo.getCelular());
     */
     //propietarioList.add(propietarioNuevo);
        try {
            this.propietarioList=this.recuperarArchivo("c:/Progra/archivoPropietario.obj");
        } catch (Exception ex) {
            Logger.getLogger(PropietarioServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        propietarioList.remove(posicion);
        propietarioList.add(posicion,propietarioNuevo);
        try {
            this.eliminarArchivo("c:/Progra/archivoPropietario.obj");
        } catch (Exception ex) {
            Logger.getLogger(PropietarioServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        for(int i=0;i<propietarioList.size();i++){
            Propietario propietario=propietarioList.get(i);
         try {
             this.almacenarEnArchivo(propietario,"c:/Progra/archivoPropietario.obj");
         } catch (Exception ex) {
             Logger.getLogger(PropietarioServicio.class.getName()).log(Level.SEVERE, null, ex);
         }
        }
        
     
     return propietarioNuevo;
             
    }

    @Override
    public Propietario eliminar(int codigo) {
        Propietario propietario = this.buscarPorCodigo(codigo);
        var posicion= this.buscarPosicion(propietario);
        try {
            System.out.println("Si entro al recuperar");
            this.propietarioList=this.recuperarArchivo("c:/Progra/archivoPropietario.obj");
        } catch (Exception ex) {
            Logger.getLogger(PropietarioServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        propietarioList.remove(posicion);
       System.out.println(propietarioList);
        try {
            System.out.println("Si se elimino el archivo");
            this.eliminarArchivo("c:/Progra/archivoPropietario.obj");
        } catch (Exception ex) {
            System.out.println("No se elimino el archivo");
        }
        for(int i=0;i<propietarioList.size();i++){
            Propietario propietario2=propietarioList.get(i);
         try {
             this.almacenarEnArchivo(propietario2,"c:/Progra/archivoPropietario.obj");
         } catch (Exception ex) {
             System.out.println("El error esta en el for");
         }
        }
        return propietario;
        
    }

    @Override
    public Propietario buscarPorCodigo(int codigo) {
        Propietario propietario= null;
        for(var p:this.listar()){
            if(codigo==p.getCodigo()){
                propietario = p;
                break;
            }
        }
        return propietario;        
    }

    @Override
    public int buscarPosicion(Propietario propietario) {
        
        int posicion =-1;
        for(var p:this.propietarioList){
            posicion++;
            if(p.getCodigo()==propietario.getCodigo()){
                break;
            }
        }
        return posicion;
    }

    @Override
    public List<Propietario> listar() {
        try {
            this.propietarioList=this.recuperarArchivo("c:/Progra/archivoPropietario.obj");
        } catch (Exception ex) {
            throw new RuntimeException("No se puede recuperar datos del archivo"+ex.getMessage());
        }
        return this.propietarioList;
    }
    public boolean eliminarArchivo(String rutaArchivo) throws Exception {
        boolean llave=false;
        File archivo = new File(rutaArchivo);
        archivo.delete();
        System.out.println("Se completo la eliminacion");
        return llave;
    }
    public List<Propietario> recuperarArchivo(String rutaArchivo) throws Exception {
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
        var propietarioList = new ArrayList<Propietario>();
        var fis = new FileInputStream(new File(rutaArchivo));
        ObjectInputStream entrada = null;
        try{        
            while(fis.available()>0){
                entrada = new ObjectInputStream(fis);
                Propietario propietario= (Propietario) entrada.readObject();
                propietarioList.add(propietario);
            }
            entrada.close();
        }catch(Exception ex){
            entrada.close();
        }
        System.out.println("Si entro al metodo recuperar archivo");
        return propietarioList;
        
    }
     public boolean almacenarEnArchivo(Propietario propietario, String rutaArchivo) throws Exception {
        var retorno = false;
        ObjectOutputStream salida=null;
        try{
            salida = new ObjectOutputStream( new FileOutputStream(rutaArchivo,true) );
            /*salida.writeInt(auto.getCodigo());
            salida.writeUTF(auto.getMarca());
            salida.writeUTF(auto.getColor());
            salida.writeInt(auto.getYear());
            salida.writeInt(auto.getPrecio());*/
            salida.writeObject(propietario);
            salida.close();
            retorno=true;
        }catch(IOException e)
        {
            salida.close();
        }
        return retorno;
    }
}

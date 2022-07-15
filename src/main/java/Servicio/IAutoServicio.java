/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Servicio;

import Modelo.Auto;
import java.util.List;


/**
 *
 * @author USER
 */
public interface IAutoServicio {
    
    public Auto crear(Auto auto);
    public Auto modificar(int codigoNuevo, Auto autoNuevo);
    public Auto eliminar(int codigo);
    public Auto buscarPorCodigo(int codigo);
    public int buscarPosicion(Auto auto);
    public List<Auto> listar();
    
}

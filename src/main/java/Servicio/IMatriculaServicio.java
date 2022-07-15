/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Servicio;

import Modelo.Matricula;
import java.util.List;

/**
 *
 * @author USER
 */
public interface IMatriculaServicio {
    
    public Matricula crear(Matricula matricula);
    public Matricula modificar(int codigoNuevo, Matricula matriculaNuevo);
    public Matricula eliminar(int codigo);
    public Matricula buscarPorCodigo(int codigo);
    public int buscarPosicion(Matricula matricula);
    public List<Matricula> listar();
    
    
}

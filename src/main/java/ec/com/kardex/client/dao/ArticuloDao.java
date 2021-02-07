package ec.com.kardex.client.dao;

import ec.com.kardex.client.dto.ArticuloDTO;

import java.util.List;

public interface ArticuloDao {

    ArticuloDTO insertarArticulo(ArticuloDTO articulo);

    List<ArticuloDTO> obtenerArticulos();

    void crearArticulos(List<ArticuloDTO> articulos);

    List<ArticuloDTO> obtenerArticulosConExistencia();

    void actualizaExistencia(ArticuloDTO articulo);

}

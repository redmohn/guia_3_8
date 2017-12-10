package cl.polett.perfumeria.servicio;

import cl.polett.perfumeria.modelo.Producto;
import java.util.List;

/**
 *
 * @author polett
 */
public interface BD {

    /**
     * Inserta un objeto de tipo producto en la base de datos
     * @param producto un objeto con los valores que se quieren guardar
     * @return true si se guardó bien, false si se guardó mal
     */
    public boolean agregar(Producto producto);

    /**
     * Actualizar los datos del producto
     * @param producto producto con los datos a actualizar
     * @return true si se actualizó bien, false si no se pudo actualizar
     */
    public boolean actualizar(Producto producto);

    /**
     * Elimina un producto de la base de datos
     * @param producto el producto que se va a eliminar
     * @return true si se eliminó, false si no se pudo eliminar
     */
    public boolean eliminar(Producto producto);

    /**
     * Obtiene un objeto de tipo Producto según el código ingresado
     * @param codigo del producto que se quiere consultar
     * @return un objeto del tipo Producto si se encuentra el código, o NULL si no se encuentra
     */
    public Producto obtener(Integer codigo);

    /**
     * Retorna todos los objetos de tipo Producto que hay en la base de datos
     * @return una lista con los productos
     */
    public List<Producto> obtenerTodos();

}

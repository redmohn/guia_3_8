package cl.polett.perfumeria.controlador;

import cl.polett.perfumeria.modelo.Producto;
import cl.polett.perfumeria.servicio.BD;
import cl.polett.perfumeria.servicio.impl.MySQL;
import java.util.List;

/**
 *
 * @author polett
 */
public class ControladorProducto {

    public boolean agregar(Integer codigo, String nombre, String familiaProducto, String tipoEnvase, Double medida, String unidadMedida, String descripcion, Integer precioVenta, Integer stock) {

        // validaciones para el codigo - Reglas del negocio
        if (codigo == null || codigo < 0 || codigo > 999) {
            return false;
        }

        if (nombre == null || nombre.length() <= 0 || "".equals(nombre) || " ".equals(nombre)) {
            return false;
        }

        if (precioVenta == null || precioVenta <= 0) {
            return false;
        }

        if (stock == null || stock <= 0) {
            return false;
        }

        // Si todo está en orden procedemos a Persistir el objeto producto dentro de la base de datos.
        Producto producto = new Producto();
        producto.setCodigo(codigo);
        producto.setDescripcion(descripcion);
        producto.setFamiliaProducto(familiaProducto);
        producto.setMedida(medida);
        producto.setNombre(nombre);
        producto.setPrecioVenta(precioVenta);
        producto.setStock(stock);
        producto.setTipoEnvase(tipoEnvase);
        producto.setUnidadMedida(unidadMedida);

        BD bd = new MySQL();
        boolean ok = bd.agregar(producto);
        return ok;
    }

    public List<Producto> mostrarTodos() {

        BD bd = new MySQL();
        List<Producto> listadoProductos = bd.obtenerTodos();
        return listadoProductos;
    }

}

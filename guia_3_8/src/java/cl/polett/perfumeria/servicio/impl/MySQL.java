package cl.polett.perfumeria.servicio.impl;

import cl.polett.perfumeria.modelo.Producto;
import cl.polett.perfumeria.servicio.BD;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author polett
 */
public class MySQL implements BD {

    private Connection conectar() {
        Connection conexion = null;
        try {
            String url = "jdbc:mysql://127.0.0.1:3306/guia_3_8";
            String user = "root";
            String pass = "polett";
            conexion = DriverManager.getConnection(url, user, pass);
        } catch (Exception e) {
            conexion = null;
            System.err.println(String.format("Ha ocurrido error: %s", e.toString()));
        }
        return conexion;
    }

    private void desconectar(Connection conexion) {
        try {
            if (conexion != null) {
                conexion.close();
            }
        } catch (Exception e) {
            System.err.println(String.format("Ha ocurrido error: %s", e.toString()));
        }
    }

    @Override
    public boolean agregar(Producto producto) {
        boolean ok = false;
        try {
            if (producto != null) {
                Connection conexion = conectar();
                if (conexion != null) {

                    // Los preparedStatement sirven para ejecutar código sanitizado en la base de datos
                    PreparedStatement prepareStatement = conexion.prepareStatement("INSERT INTO producto (codigo, nombre, familia_productos, tipo_envase, medida, unidad_medida, descripcion, precio_venta, stock) VALUES (?,?,?,?,?,?,?,?,?)");
                    prepareStatement.setInt(1, producto.getCodigo()); // El valor debe ser entero, si no lo es lanza un error
                    prepareStatement.setString(2, producto.getNombre());
                    prepareStatement.setString(3, producto.getFamiliaProducto());
                    prepareStatement.setString(4, producto.getTipoEnvase());
                    prepareStatement.setDouble(5, producto.getMedida());
                    prepareStatement.setString(6, producto.getUnidadMedida());
                    prepareStatement.setString(7, producto.getDescripcion());
                    prepareStatement.setInt(8, producto.getPrecioVenta());
                    prepareStatement.setInt(9, producto.getStock());
                    // execute se usa para valores que no tienen datos que retornar (DDL)
                    prepareStatement.execute();
                    ok = true;

                    desconectar(conexion);
                }
            }
        } catch (Exception e) {
            ok = false;
            System.err.println(String.format("Ha ocurrido error: %s", e.toString()));
        }
        return ok;
    }

    @Override
    public boolean actualizar(Producto producto) {
        boolean ok = false;
        try {
            if (producto != null) {
                Connection conexion = conectar();
                if (conexion != null) {

                    // Los preparedStatement sirven para ejecutar código sanitizado en la base de datos
                    PreparedStatement prepareStatement = conexion.prepareStatement("UPDATE producto SET nombre=?, familia_productos=?, tipo_envase=?, medida=?, unidad_medida=?, descripcion=?, precio_venta=?, stock=? WHERE codigo=?");
                    prepareStatement.setString(1, producto.getNombre());
                    prepareStatement.setString(2, producto.getFamiliaProducto());
                    prepareStatement.setString(3, producto.getTipoEnvase());
                    prepareStatement.setDouble(4, producto.getMedida());
                    prepareStatement.setString(5, producto.getUnidadMedida());
                    prepareStatement.setString(6, producto.getDescripcion());
                    prepareStatement.setInt(7, producto.getPrecioVenta());
                    prepareStatement.setInt(8, producto.getStock());
                    prepareStatement.setInt(9, producto.getCodigo());
                    // execute se usa para valores que no tienen datos que retornar (DDL)
                    prepareStatement.execute();
                    ok = true;

                    desconectar(conexion);
                }
            }
        } catch (Exception e) {
            ok = false;
            System.err.println(String.format("Ha ocurrido error: %s", e.toString()));
        }
        return ok;
    }

    @Override
    public boolean eliminar(Producto producto) {
        boolean ok = false;
        try {
            if (producto != null) {
                Connection conexion = conectar();
                if (conexion != null) {

                    // Los preparedStatement sirven para ejecutar código sanitizado en la base de datos
                    PreparedStatement prepareStatement = conexion.prepareStatement("DELETE FROM producto  WHERE codigo=?");
                    prepareStatement.setInt(1, producto.getCodigo());
                    // execute se usa para valores que no tienen datos que retornar (DDL)
                    prepareStatement.execute();
                    ok = true;

                    desconectar(conexion);
                }
            }
        } catch (Exception e) {
            ok = false;
            System.err.println(String.format("Ha ocurrido error: %s", e.toString()));
        }
        return ok;
    }

    @Override
    public Producto obtener(Integer codigo) {
        Producto producto = null;
        try {
            if (codigo != null) {
                Connection conexion = conectar();
                if (conexion != null) {

                    PreparedStatement prepareStatement = conexion.prepareStatement("SELECT * FROM producto WHERE codigo=?");
                    prepareStatement.setInt(1, producto.getCodigo());
                    // executeQuery se usa para sentencias SQL que retorna datos (SELECT)
                    // El resultset es la matriz con los resultados de la consulta
                    ResultSet rs = prepareStatement.executeQuery();
                    if (rs != null) {

                        while (rs.next()) {
                            producto = new Producto();
                            producto.setCodigo(rs.getInt("codigo"));
                            producto.setDescripcion(rs.getString("descripcion"));
                            producto.setFamiliaProducto(rs.getString("familia_productos"));
                            producto.setMedida(rs.getDouble("medida"));
                            producto.setNombre(rs.getString("nombre"));
                            producto.setPrecioVenta(rs.getInt("precio_venta"));
                            producto.setStock(rs.getInt("stock"));
                            producto.setTipoEnvase(rs.getString("tipo_envase"));
                            producto.setUnidadMedida(rs.getString("unidad_medida"));
                        }

                        rs.close();
                    }

                    desconectar(conexion);
                }
            }
        } catch (Exception e) {
            producto = null;
            System.err.println(String.format("Ha ocurrido error: %s", e.toString()));
        }
        return producto;
    }

    @Override
    public List<Producto> obtenerTodos() {
        List<Producto> productos = new ArrayList<>();
        try {

            Connection conexion = conectar();
            if (conexion != null) {

                PreparedStatement prepareStatement = conexion.prepareStatement("SELECT * FROM producto");
                
                // executeQuery se usa para sentencias SQL que retorna datos (SELECT)
                // El resultset es la matriz con los resultados de la consulta
                ResultSet rs = prepareStatement.executeQuery();
                if (rs != null) {

                    while (rs.next()) {
                        Producto producto = new Producto();
                        producto.setCodigo(rs.getInt("codigo"));
                        producto.setDescripcion(rs.getString("descripcion"));
                        producto.setFamiliaProducto(rs.getString("familia_productos"));
                        producto.setMedida(rs.getDouble("medida"));
                        producto.setNombre(rs.getString("nombre"));
                        producto.setPrecioVenta(rs.getInt("precio_venta"));
                        producto.setStock(rs.getInt("stock"));
                        producto.setTipoEnvase(rs.getString("tipo_envase"));
                        producto.setUnidadMedida(rs.getString("unidad_medida"));
                        
                        productos.add(producto);
                    }

                    rs.close();
                }

                desconectar(conexion);
            }
        } catch (Exception e) {
            productos = new ArrayList<>();
            System.err.println(String.format("Ha ocurrido error: %s", e.toString()));
        }
        return productos;
    }

}

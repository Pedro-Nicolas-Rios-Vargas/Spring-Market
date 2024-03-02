package com.ordep.market.persistence.crud;

import com.ordep.market.persistence.entity.Producto;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ProductoCrudRepository
        extends CrudRepository<Producto, Integer> {

    /* Si se usa la anotación Query la función puede no tener un nombre pensado
    para query method si no que podría llamarse getByCategoria

    @Query(value = "SELECT * FROM productos WHERE id_categoria = ?", nativeQuery = true)
    List<Producto> getByCategoria(Integer idCategoria);

    Siempre es una mejor practica utilizar los Query Methods en lugar de la
    notación Query ya que es más flexible en código hacerlo de esta forma, pero si
    se quisierá poder implementar SQL nativo existe esta opción.
     */
    List<Producto> findByIdCategoria(int idCategoria);

    List<Producto> findByIdCategoriaOrderByNombreAsc(int idCategoria);

    Optional<List<Producto>> findByCantidadStockLessThanAndEstado(int cantidadStock, boolean estado);
}

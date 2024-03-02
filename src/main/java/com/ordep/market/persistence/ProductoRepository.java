package com.ordep.market.persistence;

import com.ordep.market.domain.Product;
import com.ordep.market.domain.repository.ProductRepository;
import com.ordep.market.persistence.crud.ProductoCrudRepository;
import com.ordep.market.persistence.entity.Producto;
import com.ordep.market.persistence.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/*
Ya que esta clase interactua con la DB es recomendable informar
a Spring de dicha acción, para ello, se agrega la anotación @Repository
la cual es un estereotipo.

Así como se usa la anotación @Repository se puede hacer uso de
la notación @Component la cual es una generalización de este tipo
de anotaciones, de forma que indica que esta clase es un componente
de Spring. Pero, para ser especifico con las definiciones es mejor
usar @Repository
 */
@Repository
public class ProductoRepository implements ProductRepository {
    @Autowired
    private ProductoCrudRepository productoCrudRepository;

    @Autowired
    private ProductMapper mapper;

    @Override
    public List<Product> getAll() {
        List<Producto> productos = (List<Producto>) productoCrudRepository.findAll();
        return mapper.toProducts(productos);
    }

    @Override
    public Optional<List<Product>> getByCategory(int categoryId) {
        List<Producto> productos = productoCrudRepository
                .findByIdCategoriaOrderByNombreAsc(categoryId);
        return Optional.of(mapper.toProducts(productos));
    }

    @Override
    public Optional<List<Product>> getScarseProducts(int quantity) {
        Optional<List<Producto>> productos = productoCrudRepository
                .findByCantidadStockLessThanAndEstado(quantity, true);
        return productos.map(prods -> mapper.toProducts(prods));
    }

    @Override
    public Optional<Product> getProduct(int productId) {
        return productoCrudRepository
                .findById(productId)
                .map(producto -> mapper.toProduct(producto));
    }

    @Override
    public Product save(Product product) {
        Producto producto = mapper.toProducto(product);
        return mapper
                .toProduct(productoCrudRepository.save(producto));
    }

    @Override
    public void delete(int productId) {
        productoCrudRepository.deleteById(productId);
    }
}

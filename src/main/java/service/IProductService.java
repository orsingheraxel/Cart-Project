package service;

import dto.ProductDTO;

import java.util.List;
import java.util.Optional;

public interface IProductService {
    List<ProductDTO> getAllProducts();
    Optional<ProductDTO> getProductById(Long id);
    ProductDTO createProduct(ProductDTO productDTO);
    Optional<ProductDTO> updateProduct(Long id, ProductDTO productDTO);
    void deleteProduct(Long id);
}

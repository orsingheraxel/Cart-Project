package service.impl;

import dto.ProductDTO;
import mapper.ProductMapper;
import model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import persistence.IProductRepository;
import service.IProductService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements IProductService {

    @Autowired
    private IProductRepository productRepository;

    @Override
    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll().stream()
                .map(ProductMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ProductDTO> getProductById(Long id) {
        return productRepository.findById(id)
                .map(ProductMapper::toDTO);
    }

    @Override
    public ProductDTO createProduct(ProductDTO productDTO) {
        Product product = ProductMapper.toEntity(productDTO);
        Product savedProduct = productRepository.save(product);
        return ProductMapper.toDTO(savedProduct);
    }

    @Override
    public Optional<ProductDTO> updateProduct(Long id, ProductDTO productDTO) {
        if (!productRepository.existsById(id)) {
            return Optional.empty();
        }

        Product product = ProductMapper.toEntity(productDTO);
        product.setId(id);
        Product updatedProduct = productRepository.save(product);
        return Optional.of(ProductMapper.toDTO(updatedProduct));
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}

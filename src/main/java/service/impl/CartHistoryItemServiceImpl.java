package service.impl;

import dto.CartHistoryItemDTO;
import mapper.CartHistoryItemMapper;
import model.CartHistoryItem;
import model.CartHistory;
import model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import persistence.ICartHistoryItemRepository;
import persistence.ICartHistoryRepository;
import persistence.IProductRepository;
import service.ICartHistoryItemService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CartHistoryItemServiceImpl implements ICartHistoryItemService {
    @Autowired
    private ICartHistoryItemRepository cartHistoryItemRepository;

    @Autowired
    private ICartHistoryRepository cartHistoryRepository;

    @Autowired
    private IProductRepository productRepository;

    @Override
    public List<CartHistoryItemDTO> getAllCartHistoryItems() {
        return cartHistoryItemRepository.findAll().stream()
                .map(CartHistoryItemMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<CartHistoryItemDTO> getCartHistoryItemById(Long id) {
        return cartHistoryItemRepository.findById(id)
                .map(CartHistoryItemMapper::toDTO);
    }

    @Override
    public CartHistoryItemDTO createCartHistoryItem(CartHistoryItemDTO cartHistoryItemDTO, Long productId) {
        CartHistory cartHistory = cartHistoryRepository.findById(cartHistoryItemDTO.getCartHistoryId())
                .orElseThrow(() -> new RuntimeException("CartHistory not found"));
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        CartHistoryItem cartHistoryItem = CartHistoryItemMapper.toEntity(cartHistoryItemDTO, productId);
        cartHistoryItem.setCartHistoryId(cartHistory.getId());
        cartHistoryItem.setProductId(product.getId());
        CartHistoryItem savedCartHistoryItem = cartHistoryItemRepository.save(cartHistoryItem);
        return CartHistoryItemMapper.toDTO(savedCartHistoryItem);
    }

    @Override
    public Optional<CartHistoryItemDTO> updateCartHistoryItem(Long id, CartHistoryItemDTO cartHistoryItemDTO, Long productId) {
        if (!cartHistoryItemRepository.existsById(id)) {
            return Optional.empty();
        }
        CartHistory cartHistory = cartHistoryRepository.findById(cartHistoryItemDTO.getCartHistoryId())
                .orElseThrow(() -> new RuntimeException("CartHistory not found"));
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        CartHistoryItem cartHistoryItem = CartHistoryItemMapper.toEntity(cartHistoryItemDTO, productId);
        cartHistoryItem.setId(id);
        cartHistoryItem.setCartHistoryId(cartHistory.getId());
        cartHistoryItem.setProductId(product.getId());
        CartHistoryItem updatedCartHistoryItem = cartHistoryItemRepository.save(cartHistoryItem);
        return Optional.of(CartHistoryItemMapper.toDTO(updatedCartHistoryItem));
    }

    @Override
    public void deleteCartHistoryItem(Long id) {
        cartHistoryItemRepository.deleteById(id);
    }
}

package service.impl;

import dto.CartItemDTO;

import mapper.CartItemMapper;
import model.Cart;
import model.CartItem;
import model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import persistence.ICartItemRepository;
import persistence.ICartRepository;
import persistence.IProductRepository;
import service.ICartItemService;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CartItemServiceImpl implements ICartItemService {
    @Autowired
    private ICartItemRepository cartItemRepository;

    @Autowired
    private ICartRepository cartRepository;

    @Autowired
    private IProductRepository productRepository;

    @Override
    public List<CartItemDTO> getAllCartItems() {
        return cartItemRepository.findAll().stream()
                .map(CartItemMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<CartItemDTO> getCartItemById(Long id) {
        return cartItemRepository.findById(id)
                .map(CartItemMapper::toDTO);
    }

    @Override
    public CartItemDTO createCartItem(CartItemDTO cartItemDTO) {
        Cart cart = cartRepository.findById(cartItemDTO.getCartId())
                .orElseThrow(() -> new RuntimeException("Cart not found"));
        Product product = productRepository.findById(cartItemDTO.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));
        CartItem cartItem = CartItemMapper.toEntity(cartItemDTO);
        cartItem.setCartId(cart.getId());
        cartItem.setProductId(product.getId());
        CartItem savedCartItem = cartItemRepository.save(cartItem);
        return CartItemMapper.toDTO(savedCartItem);
    }

    @Override
    public Optional<CartItemDTO> updateCartItem(Long id, CartItemDTO cartItemDTO) {
        if (!cartItemRepository.existsById(id)) {
            return Optional.empty();
        }
        Cart cart = cartRepository.findById(cartItemDTO.getCartId())
                .orElseThrow(() -> new RuntimeException("Cart not found"));
        Product product = productRepository.findById(cartItemDTO.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));
        CartItem cartItem = CartItemMapper.toEntity(cartItemDTO);
        cartItem.setId(id);
        cartItem.setCartId(cart.getId());
        cartItem.setProductId(product.getId());
        CartItem updatedCartItem = cartItemRepository.save(cartItem);
        return Optional.of(CartItemMapper.toDTO(updatedCartItem));
    }

    @Override
    public void deleteCartItem(Long id) {
        cartItemRepository.deleteById(id);
    }
}

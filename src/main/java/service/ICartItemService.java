package service;

import dto.CartItemDTO;

import java.util.List;
import java.util.Optional;

public interface ICartItemService {
    List<CartItemDTO> getAllCartItems();
    Optional<CartItemDTO> getCartItemById(Long id);
    CartItemDTO createCartItem(CartItemDTO cartItemDTO);
    Optional<CartItemDTO> updateCartItem(Long id, CartItemDTO cartItemDTO);
    void deleteCartItem(Long id);
}

package mapper;

import dto.CartDTO;
import dto.CartItemDTO;
import model.Cart;
import model.CartItem;

public class CartMapper {
    public static CartDTO toDTO(Cart cart) {
        CartDTO cartDTO = new CartDTO();
        cartDTO.setId(cart.getId());
        cartDTO.setUserId(cart.getUserId());
        cartDTO.setCreatedAt(cart.getCreatedAt());
        cartDTO.setUpdatedAt(cart.getUpdatedAt());
        return cartDTO;
    }

    public static Cart toEntity(CartDTO cartDTO, Long userId) {
        Cart cart = new Cart();
        cart.setId(cartDTO.getId());
        cart.setUserId(userId);
        cart.setCreatedAt(cartDTO.getCreatedAt());
        cart.setUpdatedAt(cartDTO.getUpdatedAt());
        return cart;
    }

    public static CartItemDTO toCartItemDTO(CartItem cartItem) {
        CartItemDTO cartItemDTO = new CartItemDTO();
        cartItemDTO.setId(cartItem.getId());
        cartItemDTO.setProductId(cartItem.getProductId());
        cartItemDTO.setQuantity(cartItem.getQuantity());
        cartItemDTO.setPriceAtPurchase(cartItem.getPriceAtPurchase());
        return cartItemDTO;
    }

    public static CartItem toCartItemEntity(CartItemDTO cartItemDTO) {
        CartItem cartItem = new CartItem();
        cartItem.setId(cartItemDTO.getId());
        cartItem.setProductId(cartItemDTO.getProductId());
        cartItem.setQuantity(cartItemDTO.getQuantity());
        cartItem.setPriceAtPurchase(cartItemDTO.getPriceAtPurchase());
        return cartItem;
    }
}

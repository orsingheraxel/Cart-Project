package mapper;

import dto.CartDTO;
import model.Cart;


public class CartMapper {

    public static CartDTO toDTO(Cart cart) {
        if (cart == null) {
            return null;
        }

        CartDTO cartDTO = new CartDTO();
        cartDTO.setId(cart.getId());
        cartDTO.setUserId(cart.getUserId());
        return cartDTO;
    }

    public static Cart toEntity(CartDTO cartDTO, Long userId) {
        if (cartDTO == null) {
            return null;
        }

        Cart cart = new Cart();
        cart.setId(cartDTO.getId());
        cart.setUserId(userId);
        // La lista de CartItem se mapea en el servicio
        return cart;
    }
}

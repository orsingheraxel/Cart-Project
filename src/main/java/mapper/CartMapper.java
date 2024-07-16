package mapper;

import dto.CartDTO;
import dto.UserEntityDTO;
import model.Cart;
import model.UserEntity;

public class CartMapper {

    public static CartDTO toDTO(Cart cart) {
        if (cart == null) {
            return null;
        }

        CartDTO cartDTO = new CartDTO();
        cartDTO.setId(cart.getId());
        cartDTO.setUserId(cart.getUserId().getId());
        // No se mapea la lista de CartItem en este ejemplo
        return cartDTO;
    }

    public static Cart toEntity(CartDTO cartDTO, UserEntityDTO userDTO) {
        if (cartDTO == null) {
            return null;
        }

        UserEntity user = UserEntityMapper.toEntity(userDTO);

        Cart cart = new Cart();
        cart.setId(cartDTO.getId());
        cart.setUserId(user);
        // La lista de CartItem se mapea en el servicio
        return cart;
    }
}

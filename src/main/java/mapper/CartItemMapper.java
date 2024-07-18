package mapper;

import dto.CartItemDTO;
import model.CartItem;

import java.util.stream.Collectors;

public class CartItemMapper {

    public static CartItemDTO toDTO(CartItem cartItem) {
        if (cartItem == null) {
            return null;
        }

        CartItemDTO cartItemDTO = new CartItemDTO();
        cartItemDTO.setId(cartItem.getId());
        cartItemDTO.setProductId(cartItem.getProductId());
        cartItemDTO.setQuantity(cartItem.getQuantity());
        cartItemDTO.setPriceAtPurchase(cartItem.getPriceAtPurchase());
        return cartItemDTO;
    }

    public static CartItem toEntity(CartItemDTO cartItemDTO) {
        if (cartItemDTO == null) {
            return null;
        }

        CartItem cartItem = new CartItem();
        cartItem.setId(cartItemDTO.getId());
        // No se establece Cart o Product aquí, se hace en service
        cartItem.setQuantity(cartItemDTO.getQuantity());
        cartItem.setPriceAtPurchase(cartItemDTO.getPriceAtPurchase());
        return cartItem;
    }
}

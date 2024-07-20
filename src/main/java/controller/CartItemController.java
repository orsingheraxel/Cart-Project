package controller;

import dto.CartItemDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.ICartItemService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cartItems")
public class CartItemController {

    @Autowired
    private ICartItemService cartItemService;

    @GetMapping
    public ResponseEntity<List<CartItemDTO>> getAllCartItems() {
        List<CartItemDTO> cartItems = cartItemService.getAllCartItems();
        return ResponseEntity.ok(cartItems);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CartItemDTO> getCartItemById(@PathVariable Long id) {
        Optional<CartItemDTO> cartItem = cartItemService.getCartItemById(id);
        return cartItem.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<CartItemDTO> createCartItem(@RequestBody CartItemDTO cartItemDTO) {
        CartItemDTO createdCartItem = cartItemService.createCartItem(cartItemDTO);
        return ResponseEntity.ok(createdCartItem);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CartItemDTO> updateCartItem(@PathVariable Long id, @RequestBody CartItemDTO cartItemDTO) {
        Optional<CartItemDTO> updatedCartItem = cartItemService.updateCartItem(id, cartItemDTO);
        return updatedCartItem.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCartItem(@PathVariable Long id) {
        cartItemService.deleteCartItem(id);
        return ResponseEntity.noContent().build();
    }
}

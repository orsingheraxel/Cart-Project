package controller;

import dto.CartDTO;
import dto.UserEntityDTO;
import model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.ICartService;

import java.util.List;

@RestController
@RequestMapping("/api/carts")
public class CartController {
    @Autowired
    private ICartService cartService;

    @GetMapping
    public ResponseEntity<List<CartDTO>> getAllCarts() {
        List<CartDTO> carts = cartService.getAllCarts();
        return ResponseEntity.ok(carts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CartDTO> getCartById(@PathVariable("id") Long id) {
        return cartService.getCartById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/{userId}")
    public ResponseEntity<CartDTO> createCart(@RequestBody CartDTO cartDTO, @PathVariable Long userId) {
        UserEntityDTO userDTO = new UserEntityDTO();
        userDTO.setId(userId);
        CartDTO createdCart = cartService.createCart(cartDTO, userDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCart);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CartDTO> updateCart(@PathVariable("id") Long id, @RequestBody CartDTO cartDTO, @PathVariable Long userId) {

        return cartService.updateCart(id, cartDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCart(@PathVariable("id") Long id) {
        cartService.deleteCart(id);
        return ResponseEntity.noContent().build();
    }
}

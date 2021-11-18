package one.digitalinnovation.experts.shoppingcart.shoppingcart.controller;

import one.digitalinnovation.experts.shoppingcart.shoppingcart.model.Cart;
import one.digitalinnovation.experts.shoppingcart.shoppingcart.model.Item;
import one.digitalinnovation.experts.shoppingcart.shoppingcart.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartRepository cartRepository;

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public ResponseEntity<Cart> addItem(@PathVariable Long id, @RequestBody Item item) {
        Optional<Cart> optional = cartRepository.findById(id);

        Cart cart = ( (optional.equals(Optional.empty())) ? new Cart(id) : optional.get() );
        cart.getItems().add(item);

        return ResponseEntity.ok().body(cartRepository.save(cart));
    }

    @RequestMapping( value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Cart> findCartById(@PathVariable Long id) {
        Optional<Cart> optional = cartRepository.findById(id);
        if (optional.isPresent()) {
            return ResponseEntity.ok().body(optional.get());
        }
        return ResponseEntity.notFound().build();
    }

    @RequestMapping( value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Cart> deleteCartById(@PathVariable Long id) {
        Optional<Cart> optional = cartRepository.findById(id);
        if (!optional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        cartRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}

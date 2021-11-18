package one.digitalinnovation.experts.shoppingcart.shoppingcart.repository;

import one.digitalinnovation.experts.shoppingcart.shoppingcart.model.Cart;
import org.springframework.data.repository.CrudRepository;

public interface CartRepository extends CrudRepository<Cart, Long> {
}

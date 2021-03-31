package com.cg.fds.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cg.fds.entities.CartItem;
import com.cg.fds.entities.FoodCart;
import com.cg.fds.entities.Item;

public interface ICartItemRepository extends JpaRepository<CartItem,String> {

    void deleteByCart(FoodCart cart);
    
    List<CartItem> findByCart(FoodCart cart);

//    void deleteByCartAndItem(FoodCart cart, Item item);

    @Query("select item from CartItem where cart=:cart")
    List<Item>findItemsByCart(@Param("cart") FoodCart cart);

}

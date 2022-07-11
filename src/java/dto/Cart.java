/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author votru
 */
public class Cart {
    private Map<String, BookingDetail> cart;

    public Cart() {
    }

    public Cart(Map<String, BookingDetail> cart) {
        this.cart = cart;
    }

    public Map<String, BookingDetail> getCart() {
        return cart;
    }

    public void setCart(Map<String, BookingDetail> cart) {
        this.cart = cart;
    }
    public void add(BookingDetail bookingDetail){
        if(this.cart == null){
            this.cart = new HashMap<>();
        }
        
    }
}

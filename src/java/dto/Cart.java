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

    public boolean add(BookingDetail bookingDetail) {
        boolean check = false;
        try {
            if (this.cart == null) {
                this.cart = new HashMap<>();
            }
            this.cart.put(bookingDetail.getField().getFieldId(), bookingDetail);
            check = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check;
    }
    public boolean remove(String id){
        boolean check = false;
        try {
            if(this.cart != null){
                if(this.cart.containsKey(id)){
                    this.cart.remove(id);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check;
    }
}

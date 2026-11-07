package com.ticketbox.util; // NGOAI pham vi package goc com.ticketbox.springcoredemo

import org.springframework.stereotype.Component;

@Component
public class DiscountCalculator {
  public double applyDiscount(double price) {
    return price * 0.9; // giam gia 10%
  }
}

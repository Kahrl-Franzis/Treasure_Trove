// src/app/shopping-cart/shopping-cart.component.ts
import { Component, OnInit } from '@angular/core';
import { Router, RouterModule } from '@angular/router'; 
import { CommonModule, DecimalPipe } from '@angular/common'; // CRITICAL: For *ngIf, *ngFor, and pipes
import { CartService, CartItemWithQuantity } from '../service/cart.service'
import { Observable } from 'rxjs';

@Component({
  selector: 'app-shopping-cart',
  // FIX: Make component standalone and add necessary Angular modules
  standalone: true, 
  imports: [CommonModule, RouterModule, DecimalPipe], 
  templateUrl: './shopping-cart.component.html',
  styleUrls: ['./shopping-cart.component.css']
})
// CRITICAL FIX: Ensure 'export' is present
export class ShoppingCartComponent implements OnInit {
  // Use the imported CartItem type
  cartItems$!: Observable<CartItemWithQuantity[]>; 
  cartTotal: number = 0;

  constructor(
    private cartService: CartService,
    private router: Router
  ) { }

  ngOnInit(): void {
    // 1. Get the items stream from the service
    this.cartItems$ = this.cartService.currentCart as Observable<CartItemWithQuantity[]>;

    // 2. Subscribe to calculate the total whenever the cart changes
    this.cartItems$.subscribe(items => {
      this.cartTotal = this.cartService.getCartTotal();
    });
  }

  /**
   * Removes a single item completely from the chest.
   */
  removeItem(productId: number): void {
    this.cartService.removeItem(productId);
  }

  /**
   * Clears the entire chest.
   */
  clearChest(): void {
    this.cartService.clearCart();
  }
  
  /**
   * Navigates to the checkout process (Set Sail route).
   */
  setSailToCheckout(): void {
    // Navigate to the 'checkout' route defined in app.routes.ts
    if (this.cartTotal > 0) {
      this.router.navigate(['/checkout']); 
    } else {
      alert("Your chest is empty! Find some treasures first.");
    }
  }
}
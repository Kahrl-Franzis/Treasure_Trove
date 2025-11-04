import { Component, OnInit } from '@angular/core';
import { Router, RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';

import { CartService } from '../service/cart.service';
import { OrderPostModel } from '../model/order.model';

@Component({
  selector: 'app-product-order',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './product-order.component.html',
  styleUrls: ['./product-order.component.css']
})
export class ProductOrderComponent implements OnInit {
  pirateId: number = 1;
  orderPayload: OrderPostModel | null = null;
  cartTotal: number = 0;
  orderStatusMessage: string = '';
  isProcessing: boolean = false;

  constructor(
    private cartService: CartService,
    private router: Router
  ) {}

  ngOnInit(): void {
    try {
      this.cartTotal = this.cartService.getCartTotal();
      this.orderPayload = this.cartService.prepareOrderPayload(this.pirateId);
      
      console.log('✅ Order payload prepared:', this.orderPayload);

      if (!this.orderPayload || !this.orderPayload.orderItems || this.orderPayload.orderItems.length === 0) {
        this.orderStatusMessage = '⚠️ Your cart is empty! Add some treasures first.';
      }
    } catch (error) {
      console.error('❌ Error preparing order:', error);
      this.orderStatusMessage = '⚠️ Error preparing order. Your cart might be empty.';
    }
  }

  confirmAndSetSail(): void {
    if (!this.orderPayload || !this.orderPayload.orderItems || this.orderPayload.orderItems.length === 0) {
      alert('⚠️ Your cart is empty! Add some treasures first.');
      return;
    }

    // 🎭 FAKE SUCCESS - No actual API call
    this.isProcessing = true;
    this.orderStatusMessage = 'Sending manifest... Setting sail...';

    console.log('🎭 FAKE ORDER - Not actually sent to backend');
    console.log('📦 Payload that would be sent:', JSON.stringify(this.orderPayload, null, 2));

    // Simulate a delay like a real API call
    setTimeout(() => {
      const fakeOrderId = Math.floor(Math.random() * 10000); // Random order ID
      
      console.log('✅ FAKE SUCCESS - Order ID:', fakeOrderId);
      this.orderStatusMessage = `🎉 Loot Secured! Order ID ${fakeOrderId}. Your shipment is setting sail! 🚢`;
      
      alert(`🎉 Order #${fakeOrderId} confirmed! Your treasures are on the way! 🚢⚓`);
      
      this.cartService.clearCart();
      this.isProcessing = false;
      
      // Redirect after 1 second
      setTimeout(() => {
        this.router.navigate(['/treasures']);
      }, 1000);
    }, 1500); // 1.5 second fake loading
  }
}
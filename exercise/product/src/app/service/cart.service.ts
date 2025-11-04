import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';

import { Product } from '../model/product'; 
import { OrderItemModel, OrderPostModel } from '../model/order.model'; 

export type CartItemWithQuantity = Product & { quantity: number };

@Injectable({ providedIn: 'root' })
export class CartService { 
  private cartItemsSubject = new BehaviorSubject<CartItemWithQuantity[]>([]); 
  public currentCart: Observable<CartItemWithQuantity[]> = this.cartItemsSubject.asObservable();

  constructor() { 
  }

  addToCart(product: Product, quantity: number = 1): void {
    const currentItems = this.cartItemsSubject.getValue();
    const existingItemIndex = currentItems.findIndex(item => item.id === product.id);

    if (existingItemIndex > -1) {
      currentItems[existingItemIndex].quantity += quantity;
    } else {
      currentItems.push({ ...(product as Product), quantity });
    }
    this.cartItemsSubject.next(currentItems);
  }

  removeItem(productId: number): void {
    let currentItems = this.cartItemsSubject.getValue();
    currentItems = currentItems.filter(item => item.id !== productId);
    this.cartItemsSubject.next(currentItems);
  }

  getCartTotal(): number {
    return this.cartItemsSubject.getValue().reduce(
        (sum: number, item) => {
             const price = parseFloat(item.price as any);
             const quantity = parseFloat(item.quantity as any);
             return sum + (price * quantity);
        }, 
        0
    );
  }

  clearCart(): void {
    this.cartItemsSubject.next([]);
  }

  prepareOrderPayloadByUsername(username: string): OrderPostModel {
    const items = this.cartItemsSubject.getValue().map(item => ({
      productId: item.id,
      quantity: item.quantity,
      price: parseFloat(item.price as any)
    }));

    return {
      username: username,
      items: items
    } as unknown as OrderPostModel;
  }

  prepareOrderPayload(pirateId: number): OrderPostModel {
  const items: OrderItemModel[] = this.cartItemsSubject.getValue().map(item => ({
    productId: item.id,
    quantity: item.quantity,
    price: parseFloat(item.price as any)
  }));

  return {
    pirateId: pirateId,
    totalAmount: this.getCartTotal(),
    status: 'PENDING_PAYMENT',
    orderItems: items
  };
}

}
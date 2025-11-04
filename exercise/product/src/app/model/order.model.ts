// src/app/model/order.model.ts

// The structure used inside the items array
export interface OrderItemModel {
  productId: number;  // Changed from product: { id: number }
  quantity: number;
  price: number;      // Added - Java expects this
}

export interface OrderPostModel {
  pirateId: number;              // ✅ must match backend field name exactly
  totalAmount: number;           // ✅ backend expects BigDecimal → send as number
  status?: string;               // optional, backend defaults to "PENDING_PAYMENT"
  orderItems: OrderItemModel[];  // ✅ match backend list name exactly
}

// The structure expected back from the server response
export interface OrderModel {
  id: number;
  customerId: string;
  items: OrderItemModel[];
}
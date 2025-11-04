
export interface OrderItemModel {
  productId: number;  
  quantity: number;
  price: number;      
}

export interface OrderPostModel {
  pirateId: number;        
  totalAmount: number;          
  status?: string;               
  orderItems: OrderItemModel[];  
}

export interface OrderModel {
  id: number;
  customerId: string;
  items: OrderItemModel[];
}
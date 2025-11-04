// src/app/model/product.model.ts

export interface Product {
  // These are the properties provided by your Java backend:
  id: number;
  title: string;          // Use 'title' for the name/heading
  price: number;
  imageUrl: string;       // Use 'imageUrl' for the image source
  
  // These are optional, but should be defined if your Java entity includes them
  description: string;
  stockQuantity: number;
  author: string;
  isbn: string;
}
// src/app/product-category/product-category.component.ts

import { Component, OnInit } from '@angular/core';
import { CommonModule, DecimalPipe } from '@angular/common'; // FIX 1
import { RouterModule } from '@angular/router';
import { Product } from '../model/product'; 
import { ProductService } from '../service/product.service';
import { CartService } from '../service/cart.service'; 

@Component({
  selector: 'app-product-category',
  standalone: true,
  // FIX 2: Add CommonModule, DecimalPipe and RouterModule to imports
  imports: [CommonModule, DecimalPipe, RouterModule], 
  templateUrl: './product-category.component.html',
  styleUrls: ['./product-category.component.css'],
  providers: [ProductService],
})
export class ProductCategoryComponent implements OnInit {
    public products: Product[] = []; 
    public errorMessage: string = ''; // FIX 3: Add missing error property

    constructor(
        private productService: ProductService,
        private cartService: CartService 
    ) {}

    ngOnInit(): void {
        this.productService.getProducts().subscribe({
          next: (data) => {
            this.products = data;
          },
          error: (err) => {
            this.errorMessage = 'Failed to load treasures. Check backend connection.';
          }
        });
    }

    stashInChest(product: Product): void {
        this.cartService.addToCart(product, 1);
        alert(`${product.title} stashed in chest!`);
    }
}
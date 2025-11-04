// src/app/gallery/gallery.component.ts

import { Component, OnInit } from '@angular/core';
import { CommonModule, DecimalPipe } from '@angular/common'; // FIX 1: Import CommonModule and DecimalPipe
import { ProductService } from '../service/product.service'; 
import { CartService } from '../service/cart.service'; 
import { Product } from '../model/product';

@Component({
  selector: 'app-gallery', 
  standalone: true, // Assuming standalone
  // FIX 2: Add modules required by the template
  imports: [CommonModule, DecimalPipe], 
  templateUrl: './gallery.component.html',
  styleUrls: ['./gallery.component.css']
})
export class GalleryComponent implements OnInit {
  // FIX 3: Add properties and initialize them for the template
  products: Product[] = [];
  errorMessage: string = ''; // Required for *ngIf check
  
  constructor(
    private productService: ProductService,
    private cartService: CartService 
  ) { }

  ngOnInit(): void {
    this.fetchProducts();
  }

  fetchProducts(): void {
    // This is the functional call that loads data into the 'products' array
    this.productService.getProducts().subscribe({
      next: (data) => {
        this.products = data;
      },
      error: (err) => {
        this.errorMessage = 'Failed to load treasures. Check backend connection.';
        console.error(err);
      }
    });
  }

  // FIX 4: Implement the 'stashInChest' method called by the button
  stashInChest(product: Product): void {
    this.cartService.addToCart(product, 1);
    alert(`${product.title} stashed in chest!`);
  }
}
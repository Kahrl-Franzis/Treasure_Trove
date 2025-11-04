// src/app/service/product.service.ts
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { BaseHttpService } from './base-http.service'; 
import { Product } from '../model/product'; 
import { ProductCategory } from '../model/product-category';

@Injectable({ providedIn: 'root' })
export class ProductService extends BaseHttpService {

  constructor(protected override http: HttpClient) { 
    // The base path is '/api/products'
    super(http, '/api/products'); 
  }

  /**
   * CRITICAL FIX: Implement the getProducts() method using the inherited cache logic.
   * This calls the BaseHttpService's getData() method (which handles the HTTP GET and caching).
   */
  public getProducts(): Observable<Product[]> {
    // Calling the inherited method from BaseHttpService that performs the API call
    // We assume getData() is the correct method from your base class logic.
    return this.getData() as Observable<Product[]>; 
  }

  /*
   * Optional: Example for category data fetch
  public getAllCategories(): Observable<ProductCategory[]> {
    // NOTE: This would require a separate path instance if BaseHttpService doesn't support multiple paths.
    // For simplicity, we skip implementing category methods fully here.
    return new Observable<ProductCategory[]>();
  }
  */
}
// src/app/service/order.service.ts

import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
// Assuming your BaseHttpService file is named base-http.service.ts and is in the same folder
import { BaseHttpService } from './base-http.service'; 

// Corrected pathing assuming 'model' is a sibling folder (../model)
import { OrderPostModel, OrderModel } from '../model/order.model'; 

@Injectable({
  providedIn: 'root'
})
// CRITICAL: OrderService extends the custom BaseHttpService
export class OrderService extends BaseHttpService { 

  // The constructor MUST match the signature of the base class
  constructor(protected override http: HttpClient) { 
    // Call the base constructor, passing the HttpClient and the endpoint path
    // The endpoint path for orders is '/api/orders'
    super(http, '/api/orders'); 
  }

  // --- Core Functional Method: POST Request ---

  /**
   * Sends the final order manifest (payload) to the backend for creation.
   * This overrides the 'add' method's standard HTTP method to POST (as used by your backend for creation).
   * @param order The structured payload (OrderPostModel).
   * @returns An Observable of the created OrderModel response.
   */
  public placeOrder(order: OrderPostModel): Observable<OrderModel> {
    // We use the inherited 'update' method from BaseHttpService because it performs a POST request,
    // which is the conventional HTTP verb for creating new resources in your Spring backend.
    
    // BaseHttpService.update(param) performs a POST request.
    return this.update(order); 
  }

  // --- Other methods required by your project ---
  
  // NOTE: You can use inherited methods directly:
  // public getAllOrders(): Observable<OrderModel[]> {
  //   return this.findAll(); // Inherits findAll() which performs a GET request
  // }
}
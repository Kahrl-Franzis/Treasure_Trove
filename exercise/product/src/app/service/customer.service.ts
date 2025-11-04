// src/app/service/customer.service.ts

import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

// CRITICAL: Must import the Pirate interface from the correct file path
import { Pirate } from '../model/Pirate'; 

@Injectable({
  providedIn: 'root'
})
export class CustomerService {
  // Base endpoint for Pirate CRUD in your backend
  private apiUrl = 'http://localhost:8080/api/pirates'; 

  constructor(private http: HttpClient) { }
  
  /**
   * Handles user registration.
   * (Backend: POST to /api/pirates)
   */
  registerPirate(pirate: Pirate): Observable<Pirate> {
    return this.http.post<Pirate>(this.apiUrl, pirate);
  }

  /**
   * Handles user lookup for login verification.
   * (Backend: GET to /api/pirates/search/findByUsername?username=...)
   */
  loginPirate(username: string, password: string): Observable<Pirate> {
    // Send credentials as POST body (more secure than GET with query params)
    return this.http.post<Pirate>(`${this.apiUrl}/login`, {
      username: username,
      password: password
    });
  }

  /**
   * Retrieves a single Pirate by ID.
   * (Backend: GET to /api/pirates/{id})
   */
  findPirateById(id: number): Observable<Pirate> {
    return this.http.get<Pirate>(`${this.apiUrl}/${id}`);
  }

  /**
   * Deletes a Pirate entity. Required for the 'Delete Account' feature.
   * (Backend: DELETE to /api/pirates/{id})
   */
  deletePirate(id: number): Observable<void> {
    // Deletes the user record from the database
    return this.http.delete<void>(`${this.apiUrl}/${id}`); 
  }

  /**
   * Retrieves the full list of all Pirates (used by admin/GET endpoint).
   */
  findAllPirates(): Observable<Pirate[]> {
    return this.http.get<Pirate[]>(this.apiUrl);
  }
}
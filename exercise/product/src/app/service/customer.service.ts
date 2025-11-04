import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { Pirate } from '../model/Pirate'; 

@Injectable({
  providedIn: 'root'
})
export class CustomerService {
  private apiUrl = 'http://localhost:8080/api/pirates'; 

  constructor(private http: HttpClient) { }
  
  registerPirate(pirate: Pirate): Observable<Pirate> {
    return this.http.post<Pirate>(this.apiUrl, pirate);
  }

  loginPirate(username: string, password: string): Observable<Pirate> {
    return this.http.post<Pirate>(`${this.apiUrl}/login`, {
      username: username,
      password: password
    });
  }

  findPirateById(id: number): Observable<Pirate> {
    return this.http.get<Pirate>(`${this.apiUrl}/${id}`);
  }

  deletePirate(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`); 
  }

  findAllPirates(): Observable<Pirate[]> {
    return this.http.get<Pirate[]>(this.apiUrl);
  }
}
// src/app/service/auth.service.ts
import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { Pirate } from '../model/Pirate'; // Your Pirate model

@Injectable({ providedIn: 'root' })
export class AuthService {
  // Use a BehaviorSubject to hold the current user state
  private currentUserSubject = new BehaviorSubject<Pirate | null>(null);
  public currentUser = this.currentUserSubject.asObservable();
  
  // Store user info here
  private storageKey = 'currentPirate';

  constructor() {
    // Attempt to load user data from storage on initialization
    const storedUser = localStorage.getItem(this.storageKey);
    if (storedUser) {
      this.currentUserSubject.next(JSON.parse(storedUser));
    }
  }

  // Method called on successful login/registration
  login(pirate: Pirate): void {
    localStorage.setItem(this.storageKey, JSON.stringify(pirate));
    this.currentUserSubject.next(pirate);
  }

  logout(): void {
    localStorage.removeItem(this.storageKey);
    this.currentUserSubject.next(null);
  }

  isLoggedIn(): boolean {
    return !!this.currentUserSubject.getValue();
  }
}
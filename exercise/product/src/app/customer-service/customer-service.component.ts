// src/app/customer-service/customer-service.component.ts

import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common'; 
import { FormsModule } from '@angular/forms';     
import { RouterModule, Router } from '@angular/router'; 
import { Pirate } from '../model/Pirate'; 
import { CustomerService } from '../service/customer.service';
import { AuthService } from '../service/AuthService';

@Component({
  selector: 'app-customer-service',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule], 
  templateUrl: './customer-service.component.html',
  styleUrls: ['./customer-service.component.css'],
})
export class CustomerServiceComponent implements OnInit {
    isLoginView = true; 
    loginForm = { username: '', password: '' };
    registerForm: Pirate = { 
        id: 0,
        username: '', 
        email: '', 
        passwordHash: '', 
        defaultShippingAddress: '' 
    };
    message = '';

    constructor(
        private customerService: CustomerService,
        private authService: AuthService,
        private router: Router
    ) {}

    ngOnInit(): void {
        if (this.authService.isLoggedIn()) {
            this.router.navigate(['/treasures']);
        }
    }

    handleRegister(): void {
        this.message = 'Attempting to join the crew...';
        console.log("Register working")
        // 1. Create a clean payload (without id)
        const { id, ...payloadWithoutId } = this.registerForm;
        
        // 2. Final Logic: Convert username and password to lowercase 
        // to prevent case-sensitivity issues during database lookup.
        payloadWithoutId.username = payloadWithoutId.username.toLowerCase();
        payloadWithoutId.passwordHash = payloadWithoutId.passwordHash.toLowerCase();
        
        this.customerService.registerPirate(payloadWithoutId as Pirate).subscribe({
            next: (res) => {
                this.message = `🎉 Success! Pirate ${res.username} created. Please log in.`;
                this.isLoginView = true;
                this.registerForm = { 
                    id: 0, username: '', email: '', passwordHash: '', defaultShippingAddress: '' 
                };
            },
            error: (err) => {
                this.message = `❌ Registration Failed: ${err.error?.message || 'Server error.'}`;
            }
        });
    }

    handleLogin(): void {
        this.message = 'Verifying credentials...';
        console.log("Function ran");
        
        // Final Fix: Convert input to lowercase for robust lookup
        const submittedUsername = this.loginForm.username.toLowerCase(); 
        const submittedPassword = this.loginForm.password.toLowerCase(); 

        this.customerService.loginPirate(submittedUsername, submittedPassword).subscribe({
            next: (pirate) => {
                // Check if a user record was successfully returned from the database
                console.log("Form got passed ");
                if (pirate && pirate.id) { 
                    
                    // FINAL FIX: Assume success and redirect
                    this.authService.login(pirate); 
                    this.message = `☠️ Welcome back, ${pirate.username}!`;
                    this.router.navigate(['/treasures']); 
                    
                } else {
                    this.message = "⚠️ Login failed. Pirate not found.";
                }
            },
            error: (err) => {
                // If the code reaches here, the API call itself failed (404, CORS, or server down)
                this.message = `⚠️ Login Failed! Pirate not found or server offline.`;
            }
        });
    }

    toggleView(): void {
        this.isLoginView = !this.isLoginView;
        this.message = '';
    }
}
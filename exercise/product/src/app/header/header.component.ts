// src/app/header/header.component.ts
import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common'; 
import { Router, RouterModule } from '@angular/router'; 
import { Pirate } from '../model/Pirate'; 
import { AuthService } from '../service/AuthService';
import { CustomerService } from '../service/customer.service';
import { switchMap, take } from 'rxjs/operators';
import { Observable } from 'rxjs';
import { MenuService } from '../service/menu.service'; // Assuming MenuService exists

@Component({
  selector: 'app-header',
  standalone: true,
  imports: [CommonModule, RouterModule], 
  // NOTE: Assuming MenuService, AuthService, CustomerService are provided in root
  templateUrl: './header.component.html',
  // Assuming styles are included here
})
export class HeaderComponent implements OnInit {
    public menus: any[] = []; 
    dropdownOpen: boolean = false;

    constructor(
        private menuService: MenuService, // Injected, though logic is minimal
        public authService: AuthService, // Used in template
        private customerService: CustomerService,
        private router: Router
    ) {}

    ngOnInit(): void {
        // Menu fetching logic here (optional)
    }

    /**
     * CRITICAL FIX 1: Toggles the visibility of the account dropdown menu.
     * Called by: (click)="toggleDropdown()"
     */
    toggleDropdown(): void {
        this.dropdownOpen = !this.dropdownOpen;
    }

    /**
     * CRITICAL FIX 2: Logs out the user.
     * Called by: (click)="logout()"
     */
    logout(): void {
        this.authService.logout();
        this.dropdownOpen = false;
        this.router.navigate(['/']); // Redirect to home
    }
    
    // Deletion logic (already working from previous steps)
    deleteAccount(): void {
        this.authService.currentUser.pipe(take(1)).subscribe(user => {
            if (user && confirm(`Are you sure you want to delete account for ${user.username}?`)) {
                this.customerService.deletePirate(user.id).subscribe({
                    next: () => {
                        alert(`Account successfully deleted.`);
                        this.authService.logout();
                        this.router.navigate(['/']);
                    },
                    error: (err: any) => {
                        alert(`Deletion failed. Check backend logs.`);
                        console.error(err);
                    }
                });
            }
        });
        this.dropdownOpen = false;
    }
}
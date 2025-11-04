// src/app/app.routes.ts
import { Routes } from '@angular/router';
import { MainBodyComponent } from './main-body/main-body.component';
import { ShoppingCartComponent } from './shopping-cart/shopping-cart.component';
import { ProductCategoryComponent } from './product-category/product-category.component';
import { ProductOrderComponent } from './product-order/product-order.component';
import { CustomerServiceComponent } from './customer-service/customer-service.component';
import { ContactUsComponent } from './contact-us/contact-us.component';
// Assuming the Gallery component is used for the product list based on folder structure
import { GalleryComponent } from './gallery/gallery.component'; 

export const routes: Routes = [
    // Home Page (Main Landing)
    { path: '', component: MainBodyComponent, title: 'The Treasure Trove - Homeport' },
    
    // Core E-commerce Flow (Themed Naming)
    
    // 1. Product Catalog: 'product' is now 'treasures' (using the Gallery component)
    { path: 'treasures', component: GalleryComponent, title: 'Catalog of Treasures' }, 
    
    // 2. Shopping Cart: 'cart' is now 'chest'
    { path: 'chest', component: ShoppingCartComponent, title: 'Treasure Chest' },
    
    // 3. Checkout: 'order' is now 'set-sail' or 'checkout' (using the product-order component)
    { path: 'checkout', component: ProductOrderComponent, title: 'Set Sail to Checkout' }, 
    
    // Utility and Support Pages (Standard Naming)
    { path: 'service', component: CustomerServiceComponent, title: 'Customer Service' },
    { path: 'contact', component: ContactUsComponent, title: 'Contact the Crew' },
    
    // Fallback: Redirects any unknown path back to the home page
    { path: '**', redirectTo: '' }
];
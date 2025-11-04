// src/app/model/pirate.ts

// This interface must match the fields of your Spring Boot Pirate entity
export interface Pirate {
  id: number;
  username: string; // Used for login
  email: string;
  passwordHash: string; // Used to submit the password (will be hashed by the backend)
  defaultShippingAddress: string;
  joinedCrewAt?: string; // Optional timestamp field
  // NOTE: Do not include orders or treasureChest here to keep the API payload clean
}
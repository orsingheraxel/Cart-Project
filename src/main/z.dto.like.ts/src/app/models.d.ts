/* tslint:disable */
/* eslint-disable */
// Generated using typescript-generator version 3.2.1263 on 2024-07-30 18:18:19.

export interface UserDTO {
    id: number;
    username: string;
    email: string;
    firstName: string;
    lastName: string;
    address: string;
    phoneNumber: string;
    createdAt: Date;
    updatedAt: Date;
}

export interface ProductDTO {
    id: number;
    name: string;
    description: string;
    price: number;
    stockQuantity: number;
    category: string;
    createdAt: Date;
    updatedAt: Date;
}

export interface CartDTO {
    id: number;
    userId: number;
    cartItems: CartItemDTO[];
    createdAt: Date;
    updatedAt: Date;
}

export interface CartItemDTO {
    id: number;
    cartId: number;
    productId: number;
    quantity: number;
    priceAtPurchase: number;
    createdAt: Date;
    updatedAt: Date;
}

export interface CartHistoryItemDTO {
    id: number;
    cartHistoryId: number;
    productId: number;
    quantity: number;
    priceAtPurchase: number;
    createdAt: Date;
}

export interface CartHistoryDTO {
    id: number;
    userId: number;
    totalPrice: number;
    cartHistoryItems: CartHistoryItemDTO[];
    createdAt: Date;
}

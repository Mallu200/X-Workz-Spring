package com.xworkz.stock.entity;

import com.xworkz.stock.constant.StockCategory;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "stock_items")
@Data
@NoArgsConstructor
@NamedQuery(name = "fetchAllStock", query = "select s from StockEntity s")
public class StockEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "item_name")
    private String itemName;

    @Column(name = "category")
    @Enumerated(EnumType.STRING)
    private StockCategory category;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "price")
    private double price;

    @Column(name = "supplier")
    private String supplier;

    // Validates the entity state before it is persisted or updated in the database.
    @PostLoad
    @PostPersist
    public void trackEntityStatus() {
        System.out.println("65-ENTITY: Processing entity state for item: " + itemName);
        try {
            if (id > 0) {
                System.out.println("66-ENTITY: StockEntity managed with ID: " + id);
            } else {
                System.out.println("67-ENTITY: New StockEntity prepared for persistence.");
            }
        } catch (Exception e) {
            // Logs JPA lifecycle event failures
            System.err.println("68-ENTITY: Lifecycle tracking error: " + e.getMessage());
        }
    }
}
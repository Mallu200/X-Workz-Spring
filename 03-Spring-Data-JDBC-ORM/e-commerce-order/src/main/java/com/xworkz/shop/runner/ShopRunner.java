package com.xworkz.shop.runner;

import com.xworkz.shop.config.ShopConfig;
import com.xworkz.shop.entity.OrderEntity;
import com.xworkz.shop.service.OrderService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ShopRunner {
    public static void main(String[] args) {

        // 1. Initialize the Spring Context using your Java-based ShopConfig
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(ShopConfig.class);

        // 2. Retrieve the Proxy version of OrderService (Proxy is needed for @Transactional)
        OrderService service = context.getBean(OrderService.class);

        // 3. Prepare the Order Data
        OrderEntity myOrder = new OrderEntity();
        myOrder.setProduct("Laptop");
        myOrder.setAmount(55000.0);

        // 4. Execute the Business Logic
        try {
            // This triggers: Open Transaction -> Save Order (Hibernate) -> Update Stock (JDBC) -> Commit
            service.placeOrder(myOrder);
        } catch (Exception e) {
            // If the product was "Out", this catch block handles the RuntimeException
            // and we can verify that NO data was saved in the database (Rollback)
            System.err.println("Transaction Failed: " + e.getMessage());
        }

        // 5. Shutdown
        context.close();
    }
}
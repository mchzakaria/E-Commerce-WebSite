package com.example.ecommerce2.DATABASE;
public class Connect {
    private static DbConnect DbInstance = null;

    public static DbConnect getDbConnectInstance() {
        if (DbInstance == null) {
            DbInstance = DbInstance.GetInstance();
        }
        return DbInstance;
    }
}
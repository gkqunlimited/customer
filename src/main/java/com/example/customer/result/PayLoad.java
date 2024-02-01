package com.example.customer.result;

public class PayLoad {
    public static String getMessage(String message) {
        switch (message){
            case "customerEmail" :
                return "Email is required field";
            case "customerName" :
                return "Name is required field";
            case "customerMobile" :
                return "Mobile is required field";
            case "addres1" :
                return "Address1 is required field";
            case "accountType" :
                return "Account type is required field";
            case "created" :
                return "Customer account created";
            case "found" :
                return "Customer Account found";
            case "notFound" :
                return "Customer not found";

            default:
                return "none";
        }
    }

}

package main.java;


import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class Main {

    public static void main(String args[]) {
        // Get the graph object
        Service service = new Service("95.216.192.194", 8182);

        // check if an email is fraudulent
        List res =  service.GetAllVerticesByProperty("email", "mike@yahoo.com");
        if (res.size() == 0) {
            System.out.println("This email address is not fraudulent");
        }else{
            System.out.println(res);
        }

    // this should add a dummy fraud transaction
    //  addTransactionData(service);
    }


    public static void addTransactionData(Service service){

        // Add transaction to graph
        HashMap<String, String> data = new HashMap<String, String>();
        data.put("_label", "Transaction");
        data.put("id", "4985893");
        data.put("email", "mike@yahoo.com");
        data.put("amount", "57,9405");
        data.put("currency", "Naira");
        data.put("item", "Netflix payment");
        data.put("transaction_id", "TX838495959505");
        data.put("merchant_id", "48595055");
        data.put("ip_address", "192.167.943.94");
        System.out.println(service.AddTransaction(data));

    }
}
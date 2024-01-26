/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MarketAnalytics;

import TheBusiness.Business.Business;
import TheBusiness.CustomerManagement.CustomerProfile;
import TheBusiness.MarketModel.Channel;
import TheBusiness.MarketModel.Market;
import TheBusiness.MarketModel.MarketChannelAssignment;
import TheBusiness.MarketModel.MarketChannelComboCatalog;
import TheBusiness.MarketModel.SolutionOffer;
import TheBusiness.MarketModel.SolutionOfferCatalog;
import TheBusiness.OrderManagement.MasterOrderList;
import TheBusiness.OrderManagement.Order;
import TheBusiness.OrderManagement.OrderItem;
import TheBusiness.Personnel.Person;
import TheBusiness.Personnel.Profile;
import TheBusiness.ProductManagement.Product;
import TheBusiness.ProductManagement.ProductRepository;
import TheBusiness.SalesManagement.SalesPersonProfile;
import TheBusiness.SolutionOrders.MasterSolutionOrderList;
import TheBusiness.Supplier.Supplier;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 *
 * @author kal bugrara
 */
public class Report {
    
    Business business;
    Supplier selectedsupplier;
    CustomerProfile customerprofile;
    MasterOrderList masterorderlist;

    public Report(Business business){
        this.business=business;
        
    }
    public void showProductPriceRange(){
        
        ArrayList<Supplier> supplierlist = business.getSupplierDirectory().getSuplierList();

        if (supplierlist.isEmpty()) {
            return;
        }
        System.out.println("======Q5. Product Price Range =====" );
        for (Supplier s : supplierlist) {
            System.out.println("Supplier: " +s.toString());            

            String suppliername = (String) s.toString();

            ProductRepository productRepository = s.getProductCatalog();

            for (Product pt : productRepository.getProductList()) {

               System.out.println("Product: " +pt.toString());
               System.out.println("FloorPrice: " + pt.getFloorPrice()  + "    CeilingPrice: " + pt.getCeilingPrice() +"    TargetPrice: " + pt.getTargetPrice());
               System.out.println("TopOrderPriceLowerTarget: " + pt.getTopOrderPriceLowerTarget() + "   TopOrderPriceHigherTarget: " + pt.getTopOrderPriceHigherTarget());
                 
            }
            System.out.println("=================================================" );
        }
//       System.out.println("End ====== Product Price Range =====" );              
    }
    
    public void showTop3BestNegotiatedSolutions(){
    
        ArrayList<Market>  marketlist = business.getMarketCatalog().getMarketList();
        MarketChannelComboCatalog mclist = business.getMarketChannelComboCatalog();
        MasterSolutionOrderList solutionorderlist = business.getMasterSolutionOrderList();
                
        HashMap<String, Integer> map = new HashMap<>();
        LinkedHashMap<String, Integer> sortedMap = new LinkedHashMap<>();
        ArrayList<Integer> list = new ArrayList<>();
        int value;
        String key;
                
        for (Market m : marketlist) {
            
//            key = m.toString();
//            value= solutionorderlist.getRevenueByMarket(m);
//            map.put(key,value);
           // System.out.println("Key: " + key + "   Value: " + Integer.toString(value));  
            
            ArrayList<Channel> chanellist = m.getChannelList();
            System.out.println("Market: " +m.toString());    
            for (Channel c : chanellist) {
                                
                MarketChannelAssignment mca = mclist.finMarketChannelCombo(m, c);
                if  (mca != null){
                    
                    key = m.toString() +"-" + c.getChannelType();
                    value = solutionorderlist.getRevenueByMarketChannelCombo(mca);
                    map.put(key,value);
                    System.out.println("Key: " + key + "   Value: " + Integer.toString(value));    
                }     
            }            
        }
        
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            list.add(entry.getValue());
        }
        // Sorted by descending order 
        Collections.sort(list, (Integer o1, Integer o2) -> o2 -o1); 
        
        for (int num : list) {
            for (Entry<String, Integer> entry : map.entrySet()) {
                if (entry.getValue().equals(num)) {
                    sortedMap.put(entry.getKey(), num);
                }
            }
        }
        //System.out.println(sortedMap);
        
        System.out.println("======Q1. Top 3 Best Negotiated Solutions =====" );
        int i=0;
        for (Map.Entry<String, Integer> entry : sortedMap.entrySet()) {
            i++;
            System.out.println("No." + i + " [Market:] " +entry.getKey() + " [SolutionOrder Revenue:] " + entry.getValue());            
            if (i>=3) break;
        }
        
        System.out.println("=================================================" );
      
    }

    
    public void showTotalMarginalRevenue(){
        
        ArrayList<Market>  marketlist = business.getMarketCatalog().getMarketList();
        MarketChannelComboCatalog mclist = business.getMarketChannelComboCatalog();
        MasterSolutionOrderList solutionorderlist = business.getMasterSolutionOrderList();
        SolutionOfferCatalog solutionOfferlist = business.getSolutionOfferCatalog();
        int value;
        String key;
        int totaltargetprice,marginal;
        
        System.out.println("========Q4. Total Marginal Revenue ========" );
        for (Market m : marketlist) {
                        
            ArrayList<Channel> chanellist = m.getChannelList();
            System.out.println("Market: " +m.toString());    
            for (Channel c : chanellist) {
                                
                MarketChannelAssignment mca = mclist.finMarketChannelCombo(m, c);
                if  (mca != null){
                    totaltargetprice=0;
                    
                    ArrayList<SolutionOffer> solutionoffers = solutionOfferlist.findSolutionsForMarketChannelCombo(mca);
                    for (SolutionOffer so : solutionoffers) {
                        for (Product p : so.getSolutionProducts()){
                            totaltargetprice=totaltargetprice+p.getTargetPrice();
                        }
                    }
                    
                    key = m.toString() +"-" + c.getChannelType();
                    value = solutionorderlist.getRevenueByMarketChannelCombo(mca);
                    marginal = value-totaltargetprice;
                    System.out.println( "[Market:] " + key + "  [TotalMarginalRevenue:] " + Integer.toString(marginal) + "   Revenue: " + Integer.toString(value)+ "  TotalTargetPrice: " + totaltargetprice );    
                }     
            }            
        }
        
        System.out.println("=================================================" );
    }
    
    
    public void showTop3Customer() {
        
        ArrayList<CustomerProfile> customerList = business.getCustomerDirectory().getCustomerList();
        ArrayList<Order> test = business.getMasterOrderList().getOrders();
        ArrayList<Supplier> supplierlist = business.getSupplierDirectory().getSuplierList();

        if (customerList.isEmpty()) {
            return;
        }
        
        System.out.println("======Q2. Top 3 Best Customers(customers who buy about target price) =====" );
        
        // Map to store the lowest price difference for each customer
        HashMap<String, Integer> lowestPriceDifferences = new HashMap<>();

        for (Order order : test) {
            String customerId = order.getCustomer().getCustomerId();

            // Initialize the lowest price difference for the customer if not present in the map
            lowestPriceDifferences.putIfAbsent(customerId, Integer.MAX_VALUE);

            // Iterate through each order item in the order
            for (OrderItem orderItem : order.getOrderitems()) {
                // Iterate through suppliers and their products to find a match
                for (Supplier s : supplierlist) {
                    String suppliername = s.toString();
                    selectedsupplier = business.getSupplierDirectory().findSupplier(suppliername);
                    ProductRepository productRepository = selectedsupplier.getProductCatalog();

                    for (Product pt : productRepository.getProductList()) {
                        if (orderItem.getSelectedProduct().toString().equals(pt.toString())) {
                            // Found a match, calculate and update the lowest price difference for the customer
                            int priceDifference = Math.abs(orderItem.getActualPrice() - pt.getTargetPrice());
                            lowestPriceDifferences.put(customerId, Math.min(lowestPriceDifferences.get(customerId), priceDifference));
                            break; // Exit the loop once a match is found
                        }
                    }
                }
            }
        }

        
        // Create a list of entries from the map for sorting
        ArrayList<Map.Entry<String, Integer>> sortedCustomers = new ArrayList<>(lowestPriceDifferences.entrySet());

        // Sort the list in ascending order based on the value (lowest price difference)
        sortedCustomers.sort(Comparator.comparingInt(Map.Entry::getValue));

        // Print the customers with the lowest price differences, including selected product information
        int currentRank = 1;
        for (int i = 0; i < sortedCustomers.size(); i++) {
            Map.Entry<String, Integer> entry = sortedCustomers.get(i);
            String customerId = entry.getKey();
            int lowestPriceDifference = entry.getValue();
            String selectedProductInfo = "";

            // Find the selected product information for the customer
            for (Order order : test) {
                for (OrderItem orderItem : order.getOrderitems()) {
                    if (customerId.equals(order.getCustomer().getCustomerId())
                            && lowestPriceDifference == Math.abs(orderItem.getActualPrice() - orderItem.getSelectedProduct().getTargetPrice())) {
                        selectedProductInfo = orderItem.getSelectedProduct().toString();
                        break; // Exit the loop once the selected product information is found
                    }
                }
            }

            if (i == 0 || lowestPriceDifference != sortedCustomers.get(i - 1).getValue()) {
                System.out.println("[No." + currentRank + "]");
            }

            System.out.println("Customer ID: " + customerId +
                     ", Product: " + selectedProductInfo +
                     ", Price Difference: $" + lowestPriceDifference+"\n");

             // Check if the next customer has the same lowest price difference
            if (i + 1 < sortedCustomers.size() &&
                     lowestPriceDifference == sortedCustomers.get(i + 1).getValue()) {
                 continue;
            }

            currentRank++;
            // Break the loop after printing the top 3 customers
            if (currentRank > 3) {
                break;
            }
        }
        System.out.println("=================================================" );

    }
    
    public void showTop3BestSalesPeople() {
    ArrayList<SalesPersonProfile> salespersonlist = business.getSalesPersonDirectory().getSalespersonlist();

    HashMap<String, Integer> map = new HashMap<>();
    LinkedHashMap<String, Integer> sortedMap = new LinkedHashMap<>();
    ArrayList<Integer> list = new ArrayList<>();
    int value;
    String key;

    for (SalesPersonProfile s : salespersonlist) {
        Profile pf = (Profile) s;
        Person p = pf.getPerson();
        key = p.getPersonId();

        int sum = 0;
        for (Order o : s.getSalesorders()) {
            sum = sum + o.getOrderPricePerformance();
        }

        value = sum;
        map.put(key, value);
        System.out.println("Key: " + key + " Value: " + Integer.toString(value));
    }

    for (Map.Entry<String, Integer> entry : map.entrySet()) {
        list.add(entry.getValue());
    }

    // Sorted by descending order
    Collections.sort(list, (Integer o1, Integer o2) -> o2 - o1);

    for (int num : list) {
        for (Entry<String, Integer> entry : map.entrySet()) {
            if (entry.getValue().equals(num) && entry.getValue() >= 0) {
                sortedMap.put(entry.getKey(), num);
            }
        }
    }

    System.out.println("======Q3. Top 3 Best Sales People =====");
    int i = 0;
    for (Map.Entry<String, Integer> entry : sortedMap.entrySet()) {
        i++;
        System.out.println("No." + i + " [Sales:] " + entry.getKey() + " [OrderPricePerformance:] " + entry.getValue());
        if (i >= 3) break;
    }

    System.out.println("======================================================");
}
    
}

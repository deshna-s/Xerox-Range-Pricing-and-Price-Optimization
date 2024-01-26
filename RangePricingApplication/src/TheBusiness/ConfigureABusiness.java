/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TheBusiness;

//import MarketingManagement.MarketingPersonDirectory;
//import MarketingManagement.MarketingPersonProfile;
import TheBusiness.Business.Business;
import TheBusiness.MarketModel.ChannelCatalog;
import TheBusiness.CustomerManagement.CustomerDirectory;
import TheBusiness.CustomerManagement.CustomerProfile;
import TheBusiness.MarketModel.Channel;
import TheBusiness.MarketModel.Market;
import TheBusiness.MarketModel.MarketCatalog;
import TheBusiness.MarketModel.MarketChannelAssignment;
import TheBusiness.MarketModel.MarketChannelComboCatalog;
import TheBusiness.MarketModel.SolutionOffer;
import TheBusiness.MarketModel.SolutionOfferCatalog;
import TheBusiness.OrderManagement.MasterOrderList;
import TheBusiness.SolutionOrders.MasterSolutionOrderList;
import TheBusiness.OrderManagement.Order;
import TheBusiness.OrderManagement.OrderItem;
import TheBusiness.Personnel.Person;
import TheBusiness.Personnel.PersonDirectory;
import TheBusiness.ProductManagement.Product;
import TheBusiness.ProductManagement.ProductSummary;
import TheBusiness.ProductManagement.ProductRepository;
import TheBusiness.SalesManagement.SalesPersonDirectory;
import TheBusiness.SalesManagement.SalesPersonProfile;
import TheBusiness.SolutionOrders.SolutionOrder;
import TheBusiness.Supplier.Supplier;
import TheBusiness.Supplier.SupplierDirectory;
import TheBusiness.UserAccountManagement.UserAccount;
import TheBusiness.UserAccountManagement.UserAccountDirectory;

/**
 *
 * @author kal bugrara
 */
class ConfigureABusiness {

    static Business initialize() {
        Business business = new Business("Xerox");

// Create Persons
        PersonDirectory persondirectory = business.getPersonDirectory();
// person representing sales organization        
        Person xeroxsalesperson001 = persondirectory.newPerson("Xerox sales");
        Person xeroxmarketingperson001 = persondirectory.newPerson("Xerox marketing");
        Person xeroxadminperson001 = persondirectory.newPerson("Xerox admin");

// Create person objects to represent customer organizations. 
        Person person005 = persondirectory.newPerson("Dell");
        Person person006 = persondirectory.newPerson("Microsoft");
        Person person007 = persondirectory.newPerson("Google");
        Person person008 = persondirectory.newPerson("JP Morgan");
        Person person009 = persondirectory.newPerson("State street"); //we use this as customer

// Create Customers
        CustomerDirectory customedirectory = business.getCustomerDirectory();
        CustomerProfile customerprofile1 = customedirectory.newCustomerProfile(person005);
        CustomerProfile customerprofile2 = customedirectory.newCustomerProfile(person006);
        CustomerProfile customerprofile3 = customedirectory.newCustomerProfile(person007);
        CustomerProfile customerprofile4 = customedirectory.newCustomerProfile(person008);
        CustomerProfile customerprofile5 = customedirectory.newCustomerProfile(person009);

// Create Sales people
        SalesPersonDirectory salespersondirectory = business.getSalesPersonDirectory();
        SalesPersonProfile salespersonprofile = salespersondirectory.newSalesPersonProfile(xeroxsalesperson001);

// Create Marketing people
//        MarketingPersonDirectory marketingpersondirectory = business.getMarketingPersonDirectory();
//        MarketingPersonProfile marketingpersonprofile0 = marketingpersondirectory.newMarketingPersonProfile(xeroxmarketingperson001);

// Create Admins to manage the business
//        EmployeeDirectory employeedirectory = business.getEmployeeDirectory();
//        EmployeeProfile employeeprofile0 = employeedirectory.newEmployeeProfile(xeroxadminperson001);
        SupplierDirectory suplierdirectory = business.getSupplierDirectory();

        Supplier supplier1 = suplierdirectory.newSupplier("Lenovo");
        ProductRepository productcatalog = supplier1.getProductCatalog();
        Product products1p1 = productcatalog.newProduct("Scanner 3  1", 2000, 16500, 10000);
        Product products1p2 = productcatalog.newProduct("Scanner 4", 10000, 25000, 16500);
        Product products1p3 = productcatalog.newProduct("Printer 2", 22000, 40000, 36500);
        Product products1p4 = productcatalog.newProduct("Photocopier 2 ", 30000, 70000, 50000);
        Product products1p5 = productcatalog.newProduct("Scanner  5", 19000, 36500, 25000);
        Product products1p6 = productcatalog.newProduct("Scanner 6", 90000, 125000, 105000);
        Product products1p7 = productcatalog.newProduct("Printer 3", 22000, 60000, 36500);
        Product products1p8 = productcatalog.newProduct("Photocopier 3", 30000, 70000, 50000);

        //       SupplierDirectory suplierdirectory = business.getSupplierDirectory();
        Supplier supplier2 = suplierdirectory.newSupplier("Epson");
        productcatalog = supplier2.getProductCatalog();
        Product products2p1 = productcatalog.newProduct("Scanner 13  1", 12000, 26000, 18500);
        Product products2p2 = productcatalog.newProduct("Scanner 14", 90000, 165000, 125000);
        Product products2p3 = productcatalog.newProduct("Color Printer 112", 422000, 540000, 495000);
        Product products2p4 = productcatalog.newProduct("Photocopier 922 ", 430000, 890000, 550000);
        Product products2p5 = productcatalog.newProduct("Low toner Scanner  102", 195000, 500100, 365102);
        Product products2p6 = productcatalog.newProduct("Speedy color Scanner 611", 900000, 125000, 1650000);
        Product products2p7 = productcatalog.newProduct("Premier Printer 300", 322000, 470000, 736500);
        Product products2p8 = productcatalog.newProduct("Color Photocopier 500", 350000, 580000, 780000);

// Create User accounts that link to specific profiles
        UserAccountDirectory uadirectory = business.getUserAccountDirectory();
        UserAccount ua1 = uadirectory.newUserAccount(salespersonprofile, "Sales", "XXXX"); /// order products for one of the customers and performed by a sales person
//        UserAccount ua2 = uadirectory.newUserAccount(marketingpersonprofile0, "Marketing", "XXXX"); /// order products for one of the customers and performed by a sales person
//        UserAccount ua3 = uadirectory.newUserAccount(employeeprofile0, "Admin", "XXXX"); /// order products for one of the customers and performed by a sales person

// Process Orders on behalf of sales person and customer
        MasterOrderList masterorderlist = business.getMasterOrderList();
        Order order1 = masterorderlist.newOrder(customerprofile4, salespersonprofile);
        OrderItem oi1 = order1.newOrderItem(products1p1, 18000, 1);
        OrderItem oi2 = order1.newOrderItem(products1p2, 19500, 4);
        OrderItem oi3 = order1.newOrderItem(products1p3, 36500, 10);
        OrderItem oi4 = order1.newOrderItem(products1p4, 50000, 1);
        OrderItem oi5 = order1.newOrderItem(products1p5, 25000, 3);
        OrderItem oi6 = order1.newOrderItem(products1p6, 105000, 2);
        OrderItem oi7 = order1.newOrderItem(products1p7, 36500, 3);
        OrderItem oi8 = order1.newOrderItem(products1p8, 50000, 2);

        Order order12 = masterorderlist.newOrder(customerprofile4, salespersonprofile);
        OrderItem oi112 = order1.newOrderItem(products1p1, 17000, 1);
        OrderItem oi12 = order1.newOrderItem(products1p2, 9500, 4);
        OrderItem oi13 = order1.newOrderItem(products1p3, 29500, 10);
        OrderItem oi14 = order1.newOrderItem(products1p4, 30000, 1);
        OrderItem oi15 = order1.newOrderItem(products1p5, 2000, 3);
        OrderItem oi16 = order1.newOrderItem(products1p6, 95000, 2);
        OrderItem oi17 = order1.newOrderItem(products1p7, 26500, 3);
        OrderItem oi18 = order1.newOrderItem(products1p8, 40000, 2);

        Order order13 = masterorderlist.newOrder(customerprofile4, salespersonprofile);
        oi112 = order13.newOrderItem(products1p1, 19000, 1);
        oi12 = order13.newOrderItem(products1p2, 10500, 4);
        oi13 = order13.newOrderItem(products1p3, 20500, 10);
        oi14 = order13.newOrderItem(products1p4, 40000, 1);
        oi15 = order13.newOrderItem(products1p5, 4000, 3);
        oi16 = order13.newOrderItem(products1p6, 105000, 2);
        oi17 = order13.newOrderItem(products1p7, 36500, 3);
        oi18 = order13.newOrderItem(products1p8, 550000, 1);

        ProductSummary ps = new ProductSummary(products1p8);

        System.out.println("Stats==========================");
        System.out.println("Sales Volume:  " + ps.getSalesRevenues());
        System.out.println("Profit Margin:  " + ps.getProductPricePerformance());
        System.out.println("Frequency above target:  " + ps.getNumberAboveTarget());
        System.out.println("Frequency Below:  " + ps.getNumberBelowTarget());
//===============================

        MarketCatalog mc = business.getMarketCatalog();
        ChannelCatalog cc = business.getChannelCatalog();
        MarketChannelComboCatalog mccc = business.getMarketChannelComboCatalog();

        return business;

    }

    static Business initializeMarkets() {
        Business business = new Business("Xerox");

// Create Persons
        PersonDirectory persondirectory = business.getPersonDirectory();
// person representing sales organization        
        Person xeroxsalesperson001 = persondirectory.newPerson("Xerox sales 001");
        Person xeroxsalesperson002 = persondirectory.newPerson("Xerox sales 002");
        Person xeroxsalesperson003 = persondirectory.newPerson("Xerox sales 003");
        Person xeroxsalesperson004 = persondirectory.newPerson("Xerox sales 004");
        Person xeroxsalesperson005 = persondirectory.newPerson("Xerox sales 005");
        Person xeroxsalesperson006 = persondirectory.newPerson("Xerox sales 006");
        Person xeroxsalesperson007 = persondirectory.newPerson("Xerox sales 007");
        
        
        
        Person xeroxmarketingperson001 = persondirectory.newPerson("Xerox marketing");

// Create Customers
        CustomerDirectory customedirectory = business.getCustomerDirectory();
        CustomerProfile customerprofile1 = customedirectory.newCustomerProfile(xeroxsalesperson001);
        CustomerProfile customerprofile2 = customedirectory.newCustomerProfile(xeroxsalesperson002);
        CustomerProfile customerprofile3 = customedirectory.newCustomerProfile(xeroxsalesperson003);
        CustomerProfile customerprofile4 = customedirectory.newCustomerProfile(xeroxsalesperson004);
        
        
// Create Sales people
        SalesPersonDirectory salespersondirectory = business.getSalesPersonDirectory();
        SalesPersonProfile salespersonprofile1 = salespersondirectory.newSalesPersonProfile(xeroxsalesperson001);
        SalesPersonProfile salespersonprofile2 = salespersondirectory.newSalesPersonProfile(xeroxsalesperson002);
        SalesPersonProfile salespersonprofile3 = salespersondirectory.newSalesPersonProfile(xeroxsalesperson003);
        SalesPersonProfile salespersonprofile4 = salespersondirectory.newSalesPersonProfile(xeroxsalesperson004);
        SalesPersonProfile salespersonprofile5 = salespersondirectory.newSalesPersonProfile(xeroxsalesperson005);
        SalesPersonProfile salespersonprofile6 = salespersondirectory.newSalesPersonProfile(xeroxsalesperson006);
        SalesPersonProfile salespersonprofile7 = salespersondirectory.newSalesPersonProfile(xeroxsalesperson007);
        // Create Marketing people
//        MarketingPersonDirectory marketingpersondirectory = business.getMarketingPersonDirectory();
//        MarketingPersonProfile marketingpersonprofile0 = marketingpersondirectory.newMarketingPersonProfile(xeroxmarketingperson001);

        SupplierDirectory suplierdirectory = business.getSupplierDirectory();

        Supplier supplier1 = suplierdirectory.newSupplier("Lenovo");
        ProductRepository productcatalog = supplier1.getProductCatalog();
        Product products1p1 = productcatalog.newProduct("Scanner 3  1", 2000, 16500, 10000);
        Product products1p2 = productcatalog.newProduct("Scanner 4", 10000, 25000, 16500);
        Product products1p3 = productcatalog.newProduct("Printer 2", 22000, 40000, 36500);
        Product products1p4 = productcatalog.newProduct("Photocopier 2 ", 30000, 70000, 50000);
        Product products1p5 = productcatalog.newProduct("Scanner  5", 19000, 36500, 25000);
        Product products1p6 = productcatalog.newProduct("Scanner 6", 90000, 125000, 105000);
        Product products1p7 = productcatalog.newProduct("Printer 3", 22000, 60000, 36500);
        Product products1p8 = productcatalog.newProduct("Photocopier 3", 30000, 70000, 50000);

        //       SupplierDirectory suplierdirectory = business.getSupplierDirectory();
        Supplier supplier2 = suplierdirectory.newSupplier("Epson");
        productcatalog = supplier2.getProductCatalog();
        Product products2p1 = productcatalog.newProduct("Scanner 13  1", 12000, 26000, 18500);
        Product products2p2 = productcatalog.newProduct("Scanner 14", 90000, 165000, 125000);
        Product products2p3 = productcatalog.newProduct("Color Printer 112", 422000, 540000, 495000);
        Product products2p4 = productcatalog.newProduct("Photocopier 922 ", 430000, 890000, 550000);
        Product products2p5 = productcatalog.newProduct("Low toner Scanner  102", 195000, 500100, 365102);
        Product products2p6 = productcatalog.newProduct("Speedy color Scanner 611", 90000, 165000, 125000);
        Product products2p7 = productcatalog.newProduct("Premier Printer 300", 322000, 470000, 365000);
        Product products2p8 = productcatalog.newProduct("Color Photocopier 500", 350000, 580000, 480000);

        // Process Orders on behalf of sales person and customer
        // The supplier of product: Lenovo
        MasterOrderList masterorderlist = business.getMasterOrderList();
        Order order1 = masterorderlist.newOrder(customerprofile1, salespersonprofile1);
        OrderItem oi1 = order1.newOrderItem(products1p1, 18000, 1);
        OrderItem oi2 = order1.newOrderItem(products1p2, 19500, 4);
        OrderItem oi3 = order1.newOrderItem(products1p3, 37500, 10);
        OrderItem oi4 = order1.newOrderItem(products1p4, 60000, 1);
        OrderItem oi5 = order1.newOrderItem(products1p5, 27000, 3);
        OrderItem oi6 = order1.newOrderItem(products1p6, 115000, 2);
        OrderItem oi7 = order1.newOrderItem(products1p7, 38500, 3);
        OrderItem oi8 = order1.newOrderItem(products1p8, 50000, 2);
        salespersonprofile1.addSalesOrder(order1);
        
        Order order12 = masterorderlist.newOrder(customerprofile1, salespersonprofile2);
        OrderItem oi11 = order12.newOrderItem(products1p1, 17000, 1);
        OrderItem oi12 = order12.newOrderItem(products1p2, 9500, 4);
        OrderItem oi13 = order12.newOrderItem(products1p3, 29500, 10);
        OrderItem oi14 = order12.newOrderItem(products1p4, 30000, 1);
        OrderItem oi15 = order12.newOrderItem(products1p5, 20000, 3);
        OrderItem oi16 = order12.newOrderItem(products1p6, 95000, 2);
        OrderItem oi17 = order12.newOrderItem(products1p7, 26500, 3);
        OrderItem oi18 = order12.newOrderItem(products1p8, 40000, 2);
        salespersonprofile2.addSalesOrder(order12);
        
        Order order13 = masterorderlist.newOrder(customerprofile1, salespersonprofile3);
        oi11 = order13.newOrderItem(products1p1, 9000, 1);
        oi12 = order13.newOrderItem(products1p2, 10500, 4);
        oi13 = order13.newOrderItem(products1p3, 20500, 10);
        oi14 = order13.newOrderItem(products1p4, 45000, 1);
        oi15 = order13.newOrderItem(products1p5, 4000, 3);
        oi16 = order13.newOrderItem(products1p6, 105000, 2);
        oi17 = order13.newOrderItem(products1p7, 36500, 3);
        oi18 = order13.newOrderItem(products1p8, 550000, 1);
        salespersonprofile3.addSalesOrder(order13);

        // The supplier of product: Epson
        Order order2 = masterorderlist.newOrder(customerprofile1, salespersonprofile4);
        OrderItem oi21 = order2.newOrderItem(products2p1, 18000, 1);
        OrderItem oi22 = order2.newOrderItem(products2p2, 105000, 4);
        OrderItem oi23 = order2.newOrderItem(products2p3, 436000, 10);
        OrderItem oi24 = order2.newOrderItem(products2p4, 460000, 1);
        OrderItem oi25 = order2.newOrderItem(products2p5, 300000, 3);
        OrderItem oi26 = order2.newOrderItem(products2p6, 135000, 2);
        OrderItem oi27 = order2.newOrderItem(products2p7, 340500, 3);
        OrderItem oi28 = order2.newOrderItem(products2p8, 550000, 2);
        salespersonprofile4.addSalesOrder(order2);

        Order order22 = masterorderlist.newOrder(customerprofile1, salespersonprofile1);
        OrderItem oi221= order22.newOrderItem(products2p1, 17000, 1);
        OrderItem oi222 = order22.newOrderItem(products2p2, 155000, 4);
        OrderItem oi223 = order22.newOrderItem(products2p3, 508500, 10);
        OrderItem oi224 = order22.newOrderItem(products2p4, 430000, 1);
        OrderItem oi225 = order22.newOrderItem(products2p5, 402000, 3);
        OrderItem oi226 = order22.newOrderItem(products2p6, 95000, 2);
        OrderItem oi227 = order22.newOrderItem(products2p7, 440500, 3);
        OrderItem oi228 = order22.newOrderItem(products2p8, 460000, 2);
        salespersonprofile1.addSalesOrder(order22);

        Order order23 = masterorderlist.newOrder(customerprofile1, salespersonprofile2);
        OrderItem oi231 = order23.newOrderItem(products2p1, 19000, 1);
        OrderItem oi232 = order23.newOrderItem(products2p2, 90500, 4);
        OrderItem oi233 = order23.newOrderItem(products2p3, 500000, 10);
        OrderItem oi234 = order23.newOrderItem(products2p4, 700000, 1);
        OrderItem oi235 = order23.newOrderItem(products2p5, 340000, 3);
        OrderItem oi236 = order23.newOrderItem(products2p6, 155000, 2);
        OrderItem oi237 = order23.newOrderItem(products2p7, 376500, 3);
        OrderItem oi238 = order23.newOrderItem(products2p8, 560000, 1);
        salespersonprofile2.addSalesOrder(order23);
        
        
        Order order3 = masterorderlist.newOrder(customerprofile2, salespersonprofile3);
        OrderItem oi31 = order3.newOrderItem(products2p1, 8000, 1);
        OrderItem oi32 = order3.newOrderItem(products2p2, 95000, 4);
        OrderItem oi33 = order3.newOrderItem(products2p3, 6500, 10);
        OrderItem oi34 = order3.newOrderItem(products2p4, 50000, 1);
        OrderItem oi35 = order3.newOrderItem(products2p5, 50000, 3);
        OrderItem oi36 = order3.newOrderItem(products2p6, 105000, 2);
        OrderItem oi37 = order3.newOrderItem(products2p7, 36500, 3);
        OrderItem oi38 = order3.newOrderItem(products2p8, 50000, 2);
        salespersonprofile3.addSalesOrder(order3);

        Order order32 = masterorderlist.newOrder(customerprofile1, salespersonprofile4);
        OrderItem oi321= order32.newOrderItem(products2p1, 817000, 1);
        OrderItem oi322 = order32.newOrderItem(products2p2, 755000, 4);
        OrderItem oi323 = order32.newOrderItem(products2p3, 208500, 10);
        OrderItem oi324 = order32.newOrderItem(products2p4, 630000, 1);
        OrderItem oi325 = order32.newOrderItem(products2p5, 802000, 3);
        OrderItem oi326 = order32.newOrderItem(products2p6, 75000, 2);
        OrderItem oi327 = order32.newOrderItem(products2p7, 140500, 3);
        OrderItem oi328 = order32.newOrderItem(products2p8, 260000, 2);
        salespersonprofile4.addSalesOrder(order32);

        Order order33 = masterorderlist.newOrder(customerprofile1, salespersonprofile1);
        OrderItem oi331 = order33.newOrderItem(products2p1, 59000, 1);
        OrderItem oi332 = order33.newOrderItem(products2p2, 70500, 4);
        OrderItem oi333 = order33.newOrderItem(products2p3, 200000, 10);
        OrderItem oi334 = order33.newOrderItem(products2p4, 600000, 1);
        OrderItem oi335 = order33.newOrderItem(products2p5, 940000, 3);
        OrderItem oi336 = order33.newOrderItem(products2p6, 255000, 2);
        OrderItem oi337 = order33.newOrderItem(products2p7, 476500, 3);
        OrderItem oi338 = order33.newOrderItem(products2p8, 860000, 1);
        salespersonprofile1.addSalesOrder(order33);
        
        Order order4 = masterorderlist.newOrder(customerprofile3, salespersonprofile2);
        OrderItem oi41 = order4.newOrderItem(products1p1, 28000, 1);
        OrderItem oi42 = order4.newOrderItem(products1p2, 29500, 4);
        OrderItem oi43 = order4.newOrderItem(products1p3, 47500, 10);
        OrderItem oi44 = order4.newOrderItem(products1p4, 70000, 1);
        OrderItem oi45 = order4.newOrderItem(products1p5, 37000, 3);
        OrderItem oi46 = order4.newOrderItem(products1p6, 125000, 2);
        OrderItem oi47 = order4.newOrderItem(products1p7, 48500, 3);
        OrderItem oi48 = order4.newOrderItem(products1p8, 60000, 2);
        salespersonprofile2.addSalesOrder(order4);

        Order order42 = masterorderlist.newOrder(customerprofile3, salespersonprofile3);
        OrderItem oi421 = order42.newOrderItem(products1p1, 27000, 1);
        OrderItem oi422 = order42.newOrderItem(products1p2, 10500, 4);
        OrderItem oi423 = order42.newOrderItem(products1p3, 39500, 10);
        OrderItem oi424 = order42.newOrderItem(products1p4, 40000, 1);
        OrderItem oi425 = order42.newOrderItem(products1p5, 30000, 3);
        OrderItem oi426 = order42.newOrderItem(products1p6, 105000, 2);
        OrderItem oi427= order42.newOrderItem(products1p7, 36500, 3);
        OrderItem oi428= order42.newOrderItem(products1p8, 50000, 2);
        salespersonprofile3.addSalesOrder(order42);

        Order order43 = masterorderlist.newOrder(customerprofile3, salespersonprofile4);
        oi421 = order43.newOrderItem(products1p1, 10000, 1);
        oi422 = order43.newOrderItem(products1p2, 11500, 4);
        oi423 = order43.newOrderItem(products1p3, 21500, 10);
        oi424 = order43.newOrderItem(products1p4, 46000, 1);
        oi425 = order43.newOrderItem(products1p5, 5000, 3);
        oi426 = order43.newOrderItem(products1p6, 115000, 2);
        oi427 = order43.newOrderItem(products1p7, 46500, 3);
        oi428 = order43.newOrderItem(products1p8, 650000, 1);
        salespersonprofile4.addSalesOrder(order43);
        
        Order order5 = masterorderlist.newOrder(customerprofile4, salespersonprofile5);
        OrderItem oi51 = order5.newOrderItem(products2p1, 2800000, 1);
        OrderItem oi52 = order5.newOrderItem(products2p2, 20500000, 4);
        OrderItem oi53 = order5.newOrderItem(products2p3, 63600000, 10);
        OrderItem oi54 = order5.newOrderItem(products2p4, 66000000, 1);
        OrderItem oi55 = order5.newOrderItem(products2p5, 50000000, 3);
        OrderItem oi56 = order5.newOrderItem(products2p6, 33500000, 2);
        OrderItem oi57 = order5.newOrderItem(products2p7, 54050000, 3);
        OrderItem oi58 = order5.newOrderItem(products2p8, 75000000, 2);
        salespersonprofile5.addSalesOrder(order5);

        Order order52 = masterorderlist.newOrder(customerprofile4, salespersonprofile6);
        OrderItem oi521= order52.newOrderItem(products2p1, 3700000, 1);
        OrderItem oi522 = order52.newOrderItem(products2p2, 35500000, 4);
        OrderItem oi523 = order52.newOrderItem(products2p3, 70850000, 10);
        OrderItem oi524 = order52.newOrderItem(products2p4, 63000000, 1);
        OrderItem oi525 = order52.newOrderItem(products2p5, 60200000, 3);
        OrderItem oi526 = order52.newOrderItem(products2p6, 11500000, 2);
        OrderItem oi527 = order52.newOrderItem(products2p7, 64050000, 3);
        OrderItem oi528 = order52.newOrderItem(products2p8, 66000000, 2);
        salespersonprofile6.addSalesOrder(order52);

        Order order53 = masterorderlist.newOrder(customerprofile4, salespersonprofile7);
        OrderItem oi531 = order53.newOrderItem(products2p1, 3900000, 1);
        OrderItem oi532 = order53.newOrderItem(products2p2, 11050000, 4);
        OrderItem oi533 = order53.newOrderItem(products2p3, 70000000, 10);
        OrderItem oi534 = order53.newOrderItem(products2p4, 90000000, 1);
        OrderItem oi535 = order53.newOrderItem(products2p5, 54000000, 3);
        OrderItem oi536 = order53.newOrderItem(products2p6, 35500000, 2);
        OrderItem oi537 = order53.newOrderItem(products2p7, 57650000, 3);
        OrderItem oi538 = order53.newOrderItem(products2p8, 76000000, 1);
        salespersonprofile7.addSalesOrder(order53);
        
//=============== Define markets and channels...

        MarketCatalog mc = business.getMarketCatalog();
        Market teenmarket = mc.newMarket("Teenagers");
        Market teenmarket2 = mc.newMarket("College Grads");
        Market adultmarket = mc.newMarket("Adults");
        Market adultmarket2 = mc.newMarket("Office Workers");
        
        ChannelCatalog channelCatalog = business.getChannelCatalog();

        Channel tvchannel = channelCatalog.newChannel("tv");
        Channel webchannel = channelCatalog.newChannel("web");

        teenmarket.addValidChannel(webchannel);
        teenmarket.addValidChannel(tvchannel);        
        teenmarket2.addValidChannel(webchannel);
        teenmarket2.addValidChannel(tvchannel);
        adultmarket.addValidChannel(webchannel);
        adultmarket.addValidChannel(tvchannel);        
        adultmarket2.addValidChannel(webchannel);
        adultmarket2.addValidChannel(tvchannel);   
        

        MarketChannelComboCatalog mccc = business.getMarketChannelComboCatalog();

        MarketChannelAssignment tvchannelteenmarket = mccc.newMarketChannelCombo(teenmarket, tvchannel);
        MarketChannelAssignment webchannelteenmarket = mccc.newMarketChannelCombo(teenmarket, webchannel);
        MarketChannelAssignment tvchannelcollegemarket = mccc.newMarketChannelCombo(teenmarket2, tvchannel);
        MarketChannelAssignment webchannelcollegemarket = mccc.newMarketChannelCombo(teenmarket2, webchannel);
        MarketChannelAssignment tvchanneladultmarket = mccc.newMarketChannelCombo(adultmarket, tvchannel);
        MarketChannelAssignment webchanneladultmarket = mccc.newMarketChannelCombo(adultmarket, webchannel);
        MarketChannelAssignment tvchannelofficemarket = mccc.newMarketChannelCombo(adultmarket2, tvchannel);
        MarketChannelAssignment webchannelofficemarket = mccc.newMarketChannelCombo(adultmarket2, webchannel);

        SolutionOfferCatalog solutionoffercatalog = business.getSolutionOfferCatalog();

        SolutionOffer solutiontvteen = solutionoffercatalog.newSolutionOffer(tvchannelteenmarket);
        solutiontvteen.addProduct(products2p2);
        solutiontvteen.addProduct(products2p1);
        solutiontvteen.setTotalPrice(170000);

        SolutionOffer solutionwebteen = solutionoffercatalog.newSolutionOffer(webchannelteenmarket);
        solutionwebteen.addProduct(products2p2);
        solutionwebteen.addProduct(products2p1);
        solutionwebteen.setTotalPrice(150000);
        
        SolutionOffer solutiontvteen2 = solutionoffercatalog.newSolutionOffer(tvchannelteenmarket);
        solutiontvteen2.addProduct(products2p2);
        solutiontvteen2.addProduct(products2p1);
        solutiontvteen2.setTotalPrice(160000);
        
        SolutionOffer solutiontvcollege = solutionoffercatalog.newSolutionOffer(tvchannelcollegemarket);
        solutiontvcollege.addProduct(products2p2);
        solutiontvcollege.addProduct(products2p1);
        solutiontvcollege.setTotalPrice(195000);

        SolutionOffer solutionwebcollege = solutionoffercatalog.newSolutionOffer(webchannelcollegemarket);
        solutionwebcollege.addProduct(products2p2);
        solutionwebcollege.addProduct(products2p1);
        solutionwebcollege.setTotalPrice(180000);
        
        SolutionOffer solutiontvadult = solutionoffercatalog.newSolutionOffer(tvchanneladultmarket);
        solutiontvadult.addProduct(products2p2);
        solutiontvadult.addProduct(products2p1);
        solutiontvadult.setTotalPrice(160000);

        SolutionOffer solutionwebadult = solutionoffercatalog.newSolutionOffer(webchanneladultmarket);
        solutionwebadult.addProduct(products2p2);
        solutionwebadult.addProduct(products2p1);
        solutionwebadult.setTotalPrice(170000);        
                
        SolutionOffer solutiontvoffice = solutionoffercatalog.newSolutionOffer(tvchannelofficemarket);
        solutiontvoffice.addProduct(products2p2);
        solutiontvoffice.addProduct(products2p1);
        solutiontvoffice.setTotalPrice(190000);

        SolutionOffer solutionweboffice = solutionoffercatalog.newSolutionOffer(webchannelofficemarket);
        solutionweboffice.addProduct(products2p2);
        solutionweboffice.addProduct(products2p1);
        solutionweboffice.setTotalPrice(200000);

        MasterSolutionOrderList msol = business.getMasterSolutionOrderList();

        SolutionOrder so = msol.newSolutionOrder(solutiontvteen, tvchannelteenmarket);
        
        SolutionOrder so2 = msol.newSolutionOrder(solutionwebteen, webchannelteenmarket);
        
        SolutionOrder so3 = msol.newSolutionOrder(solutiontvteen2, tvchannelteenmarket);
        
        SolutionOrder so4 = msol.newSolutionOrder(solutiontvcollege, tvchannelcollegemarket);        
        SolutionOrder so5 = msol.newSolutionOrder(solutionwebcollege, webchannelcollegemarket);
        SolutionOrder so6 = msol.newSolutionOrder(solutiontvadult, tvchanneladultmarket);        
        SolutionOrder so7 = msol.newSolutionOrder(solutionwebadult, webchanneladultmarket);
        SolutionOrder so8 = msol.newSolutionOrder(solutiontvoffice, tvchannelofficemarket);        
        SolutionOrder so9 = msol.newSolutionOrder(solutionweboffice, webchannelofficemarket);        
        
        
        msol.getRevenueByMarketChannelCombo(tvchannelteenmarket);
        msol.getRevenueByChannel(tvchannel);

                
        return business;

    }

}

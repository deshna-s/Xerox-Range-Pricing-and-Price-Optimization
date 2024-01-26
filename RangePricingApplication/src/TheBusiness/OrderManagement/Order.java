/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TheBusiness.OrderManagement;

import java.util.ArrayList;
import TheBusiness.CustomerManagement.CustomerProfile;
import TheBusiness.MarketModel.MarketChannelAssignment;
import TheBusiness.ProductManagement.Product;
import TheBusiness.SalesManagement.SalesPersonProfile;

/**
 *
 * @author kal bugrara
 */
public class Order {
    
    ArrayList<OrderItem> orderitems;
    CustomerProfile customer;
    SalesPersonProfile salesperson;
    MarketChannelAssignment mca;
    String status;

    public ArrayList<OrderItem> getOrderitems() {
        return orderitems;
    }

    public void setOrderitems(ArrayList<OrderItem> orderitems) {
        this.orderitems = orderitems;
    }

    public CustomerProfile getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerProfile customer) {
        this.customer = customer;
    }

    public SalesPersonProfile getSalesperson() {
        return salesperson;
    }

    public void setSalesperson(SalesPersonProfile salesperson) {
        this.salesperson = salesperson;
    }

    public MarketChannelAssignment getMca() {
        return mca;
    }

    public void setMca(MarketChannelAssignment mca) {
        this.mca = mca;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Order(){}
    
    public Order(CustomerProfile cp) {
        orderitems = new ArrayList();
        customer = cp;
        customer.addCustomerOrder(this); //we link the order to the customer
        salesperson = null;
        status = "in process";
    }
    public Order(CustomerProfile cp, SalesPersonProfile ep) {
        orderitems = new ArrayList();
        customer = cp;
        salesperson = ep;
        customer.addCustomerOrder(this); //we link the order to the customer
        salesperson.addSalesOrder(this);  
    }
    public OrderItem newOrderItem(Product p, int actualprice, int q) {
        OrderItem oi = new OrderItem(p, actualprice, q);
        orderitems.add(oi);
        return oi;
    }
    //order total is the sumer of the order item totals
    public int getOrderTotal() {
        int sum = 0;
        for (OrderItem oi : orderitems) {
            sum = sum + oi.getOrderItemTotal();
        }
        return sum;
    }

    public int getOrderPricePerformance() {
        int sum = 0;
        for (OrderItem oi : orderitems) {
            sum = sum + oi.calculatePricePerformance();     //positive and negative values       
        }
        return sum;
    }

    public int getNumberOfOrderItemsAboveTarget() {
        int sum = 0;
        for (OrderItem oi : orderitems) {
            if (oi.isActualAboveTarget() == true) {
                sum = sum + 1;
            }
        }
        return sum;
    }
    
    //sum all the item targets and compare to the total of the order 
    public boolean isOrderAboveTotalTarget(){
        int sum = 0;
        for (OrderItem oi: orderitems){
            sum = sum + oi.getOrderItemTargetTotal(); //product targets are added
        }
        if(getOrderTotal()>sum) {return true;}
        else {return false;}
        
    }
    public void CancelOrder(){
        status = "Cancelled";
    }
    public void Submit(){
        status = "Submitted";
    }
    
    public CustomerProfile getCustomerProfile(){
        return customer;
    }
    
    public ArrayList<OrderItem> getOrderItem(){
        return orderitems;
    }
}

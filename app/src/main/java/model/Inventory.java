package model;

import io.realm.RealmObject;

/**
 * Created by Sukriti on 6/22/16.
 */
public class Inventory extends RealmObject {
    private String itemName;
    private Integer quantity;

    public String getItemName(){
        return itemName;
    }

    public void setItemName(String itemName){
        this.itemName = itemName;
    }

    public Integer getQuantity(){
        return quantity;
    }

    public void setQuantity(Integer quantity){
        this.quantity = quantity;
    }
}

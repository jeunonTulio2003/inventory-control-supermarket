package br.com.university.inventorycontrolsupermarket.enums;

import br.com.university.inventorycontrolsupermarket.serializer.InventoryAreaSerializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@JsonSerialize(using = InventoryAreaSerializer.class)
public enum InventoryAreaEnum {

    BAKERY("1", "Bakery"),
    BUTCHER_SHOP("2", "Butcher Shop"),
    JUICE("3", "Juice"),
    SODA("4", "Soda"),
    CANDY("5", "Candy"),
    COFFEE_SHOP("6", "Coffee Shop"),
    CLEANING_PRODUCTS("7", "Cleaning products"),
    PERSON_CARES("8", "Personal Cares"),
    TOY("9", "Toy");

    private String id;
    private String description;

    InventoryAreaEnum(String id, String description){
        this.id = id;
        this.description = description;
    }

    @JsonValue
    public String getId(){
        return id;
    }

    public String getDescription(){
        return description;
    }

    public static InventoryAreaEnum fromDescription(String description){
        for(InventoryAreaEnum item : values()){
            if(item.getDescription().equalsIgnoreCase(description)){
                return item;
            }
        }
        return null;
    }
}

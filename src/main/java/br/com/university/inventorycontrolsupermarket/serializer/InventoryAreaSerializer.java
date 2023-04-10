package br.com.university.inventorycontrolsupermarket.serializer;

import br.com.university.inventorycontrolsupermarket.enums.InventoryAreaEnum;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class InventoryAreaSerializer extends StdSerializer {

    public InventoryAreaSerializer(){
        super(InventoryAreaSerializer.class);
    }

    @Override
    public void serialize(Object object, JsonGenerator generator, SerializerProvider provider) throws IOException {
        InventoryAreaEnum inventoryAreaEnum = (InventoryAreaEnum) object;
        generator.writeStartObject();
        generator.writeFieldName("id");
        generator.writeString(inventoryAreaEnum.getId());
        generator.writeFieldName("description");
        generator.writeString(inventoryAreaEnum.getDescription());
        generator.writeEndObject();
    }
}

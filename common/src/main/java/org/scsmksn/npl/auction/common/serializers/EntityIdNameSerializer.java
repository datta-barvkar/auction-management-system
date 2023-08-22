package org.scsmksn.npl.auction.common.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.scsmksn.npl.auction.model.Entity;
import org.scsmksn.npl.auction.model.User;

import java.io.IOException;

public class EntityIdNameSerializer extends JsonSerializer<Entity> {

    @Override
    public void serialize(final Entity entity, final JsonGenerator gen, final SerializerProvider provider) throws IOException {
        gen.writeStartObject();
        gen.writeNumberField("id", entity.getId());
        gen.writeStringField("name", entity.getName());
        gen.writeEndObject();
    }
}

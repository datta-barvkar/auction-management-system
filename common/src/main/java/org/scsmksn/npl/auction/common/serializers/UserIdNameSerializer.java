package org.scsmksn.npl.auction.common.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.scsmksn.npl.auction.model.User;

import java.io.IOException;

public class UserIdNameSerializer extends JsonSerializer<User> {

    @Override
    public void serialize(final User user, final JsonGenerator gen, final SerializerProvider provider) throws IOException {
        gen.writeStartObject();
        gen.writeNumberField("id", user.getId());
        gen.writeStringField("name", user.getFullName());
        gen.writeEndObject();
    }
}

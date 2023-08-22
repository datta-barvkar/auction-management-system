package org.scsmksn.npl.auction;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.scsmksn.npl.auction.model.Team;
import org.scsmksn.npl.auction.model.User;

import java.io.IOException;

public class IdNameSerializer extends JsonSerializer<Object> {

    @Override
    public void serialize(final Object o, final JsonGenerator gen, final SerializerProvider provider) throws IOException {
        if (o instanceof Team) {
            final Team team = (Team) o;
            gen.writeStartObject();
            gen.writeNumberField("id", team.getId());
            gen.writeStringField("name", team.getTeamName());
            gen.writeEndObject();
        } else if (o instanceof User) {
            final User user = (User) o;
            gen.writeStartObject();
            gen.writeNumberField("id", user.getId());
            gen.writeStringField("name", user.getFullName());
            gen.writeEndObject();
        } else {
            throw new JsonGenerationException("Incorrect object type in IsNameSerializer!", gen);
        }
    }
}

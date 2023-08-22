package org.scsmksn.npl.auction.common.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import org.scsmksn.npl.auction.model.AccessControl;
import org.scsmksn.npl.auction.model.Menu;
import org.scsmksn.npl.auction.model.Role;
import org.scsmksn.npl.auction.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StaticDataUtils {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private static final Logger LOGGER = LoggerFactory.getLogger(StaticDataUtils.class);

    public static List<User> getAdmins() {
        final ClassPathResource resource = new ClassPathResource("static-data/admins.json");
        try {
            return getList(resource.getInputStream(), User.class);
        } catch (IOException e) {
            LOGGER.error(e.getLocalizedMessage());
        }
        return Collections.emptyList();
    }

    public static List<Role> getRoles() {
        final ClassPathResource resource = new ClassPathResource("static-data/roles.json");
        try {
            return getList(resource.getInputStream(), Role.class);
        } catch (IOException e) {
            LOGGER.error(e.getLocalizedMessage());
        }
        return Collections.emptyList();
    }

    public static List<AccessControl> getAuthorities() {
        final List<AccessControl> authorities = new ArrayList<>();
        authorities.addAll(getAuthorities("authorities/application-image.json"));
        authorities.addAll(getAuthorities("authorities/auction.json"));
        authorities.addAll(getAuthorities("authorities/auction-image.json"));
        authorities.addAll(getAuthorities("authorities/profile.json"));
        authorities.addAll(getAuthorities("authorities/team.json"));
        authorities.addAll(getAuthorities("authorities/approval.json"));
        authorities.addAll(getAuthorities("authorities/team-image.json"));
        authorities.addAll(getAuthorities("authorities/user.json"));
        authorities.addAll(getAuthorities("authorities/user-image.json"));
        return authorities;
    }

    private static List<AccessControl> getAuthorities(final String resourcePath) {
        final ClassPathResource resource = new ClassPathResource(resourcePath);
        try {
            return getList(resource.getInputStream(), AccessControl.class);
        } catch (IOException e) {
            LOGGER.error(e.getLocalizedMessage());
        }
        return Collections.emptyList();
    }

    public static List<Menu> getMenus() {
        final ClassPathResource resource = new ClassPathResource("static-data/menus.json");
        try {
            return getList(resource.getInputStream(), Menu.class);
        } catch (IOException e) {
            LOGGER.error(e.getLocalizedMessage());
        }
        return Collections.emptyList();
    }

    private static <T> List<T> getList(final InputStream stream, final Class<T> tClass) throws IOException {
        final List<T> list = new ArrayList<>();
        final JsonNode jsonNode = OBJECT_MAPPER.readTree(stream);
        if (jsonNode.isArray()) {
            final ArrayNode arrayNode = (ArrayNode) jsonNode;
            for (int i = 0; i < arrayNode.size(); i++) {
                list.add(OBJECT_MAPPER.readValue(OBJECT_MAPPER.writeValueAsString(arrayNode.get(i)), tClass));
            }
        }
        return list;
    }
}

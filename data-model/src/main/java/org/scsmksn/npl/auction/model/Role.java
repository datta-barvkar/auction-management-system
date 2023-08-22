package org.scsmksn.npl.auction.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@javax.persistence.Entity
@Table(name = "roles")
@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Role extends Entity {

    @Id
    @GenericGenerator(
        name="roleIdGenerator",
        strategy = "org.scsmksn.npl.auction.idgenerators.EntityIdGenerator",
        parameters = @Parameter(name = "table_name", value = "roles")
    )
    @GeneratedValue(generator = "roleIdGenerator")
    private Long id;

    @Column(name = "name", unique = true)
    @NotBlank
    private String name;

    @ManyToMany(targetEntity = AccessControl.class, mappedBy = "roles")
    @JsonIgnore
    private List<AccessControl> accessControls = new ArrayList<>();

    @ManyToMany(targetEntity = User.class, mappedBy = "roles")
    @JsonIgnore
    private List<User> users = new ArrayList<>();

    @ManyToMany(targetEntity = Menu.class, mappedBy = "roles")
    @JsonIgnore
    private List<Menu> menus = new ArrayList<>();

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Role)) {
            return false;
        }
        final Role role = (Role) obj;
        return id.equals(role.id) && name.equals(role.name);
    }

    @Transient
    @JsonIgnore
    @Override
    public String getObjectName() {
        return "role";
    }
}

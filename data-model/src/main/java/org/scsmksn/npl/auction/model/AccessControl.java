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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@javax.persistence.Entity
@Table(name = "access_control_lists")
@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class AccessControl extends Entity {

    @Id
    @GenericGenerator(
        name="aclIdGenerator",
        strategy = "org.scsmksn.npl.auction.idgenerators.EntityIdGenerator",
        parameters = @Parameter(name = "table_name", value = "access_control_lists")
    )
    @GeneratedValue(generator = "aclIdGenerator")
    private Long id;

    @Column(name = "name", unique = true)
    @NotEmpty
    private String name;

    @Column(name = "operation")
    @NotEmpty
    private String operation;

    @Column(name = "acl_group")
    @NotEmpty
    private String group;

    @Column(name = "expression")
    private String expression;

    @Column(name = "path_pattern")
    @NotEmpty
    private String pathPattern;

    @Column(name = "failure_message")
    private String failureMessage;

    @ManyToMany(targetEntity = Role.class)
    @JoinTable(
        name = "access_control_lists_roles",
        joinColumns = @JoinColumn(name = "acl_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    @NotEmpty
    private List<Role> roles = new ArrayList<>();

    @Transient
    @JsonIgnore
    @Override
    public String getObjectName() {
        return "accessControl";
    }
}

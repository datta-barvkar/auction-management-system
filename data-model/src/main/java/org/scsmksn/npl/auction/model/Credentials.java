package org.scsmksn.npl.auction.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@javax.persistence.Entity
@Table(name = "credentials")
@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Credentials extends Entity {

    @Id
    @GenericGenerator(
            name="credentialsIdGenerator",
            strategy = "org.scsmksn.npl.auction.idgenerators.EntityIdGenerator",
            parameters = @Parameter(name = "table_name", value = "credentials")
    )
    @GeneratedValue(generator = "credentialsIdGenerator")
    private Long id;

    @Column(name = "username", unique = true)
    @NotNull
    private String username;

    @Column(name = "password")
    @NotNull
    @Size(min = 8)
    private String password;

    @Column(name = "oldPasswords")
    @ElementCollection
    @CollectionTable(name = "old_passwords", joinColumns = @JoinColumn(name = "credential_id"))
    private List<String> oldPasswords = new ArrayList<>();

    @OneToOne(targetEntity = User.class, mappedBy = "credentials")
    @JsonIgnore
    private User user;

    @Transient
    @JsonIgnore
    @Override
    public String getObjectName() {
        return "credentials";
    }
}

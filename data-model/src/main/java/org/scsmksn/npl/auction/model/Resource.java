package org.scsmksn.npl.auction.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@javax.persistence.Entity
@Table(name = "resources")
@Getter
@Setter
@NoArgsConstructor
public class Resource extends Entity {

    @Id
    @GenericGenerator(
            name="resourceIdGenerator",
            strategy = "org.scsmksn.npl.auction.idgenerators.EntityIdGenerator",
            parameters = @Parameter(name = "table_name", value = "resources")
    )
    @GeneratedValue(generator = "resourceIdGenerator")
    private Long id;

    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "type")
    private String type;

    @Column(name = "binary_file")
    @Lob
    @JsonIgnore
    private byte[] file;

    @Column(name = "base64_file")
    @Lob
    @JsonIgnore
    private String base64File;

    @OneToOne(targetEntity = User.class, mappedBy = "userImage")
    @JsonIgnore
    private User user;

    @OneToOne(targetEntity = Team.class, mappedBy = "teamImage")
    @JsonIgnore
    private Team team;

    @Transient
    @JsonIgnore
    @Override
    public String getObjectName() {
        return "resource";
    }
}

package org.scsmksn.npl.auction.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.scsmksn.npl.auction.IdNameSerializer;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@javax.persistence.Entity
@Table(name = "approvals")
@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Approval extends Entity {

    @Id
    @GenericGenerator(
            name="approvalIdGenerator",
            strategy = "org.scsmksn.npl.auction.idgenerators.EntityIdGenerator",
            parameters = @Parameter(name = "table_name", value = "approvals")
    )
    @GeneratedValue(generator = "approvalIdGenerator")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "approved")
    private boolean approved = false;

    @OneToOne(targetEntity = Team.class)
    @JoinColumn(name = "team_id", referencedColumnName = "id")
    @JsonSerialize(using = IdNameSerializer.class)
    @JsonDeserialize(using = JsonDeserializer.class)
    private Team team;

    @OneToOne(targetEntity = User.class)
    @JoinColumn(name = "player_id", referencedColumnName = "id")
    @JsonSerialize(using = IdNameSerializer.class)
    private User player;

    @OneToOne(targetEntity = User.class)
    @JoinColumn(name = "approver_id", referencedColumnName = "id")
    @JsonSerialize(using = IdNameSerializer.class)
    private User approver;

    @Transient
    @JsonIgnore
    @Override
    public String getObjectName() {
        return "approval";
    }
}

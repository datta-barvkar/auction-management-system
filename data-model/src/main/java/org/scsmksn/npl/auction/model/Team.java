package org.scsmksn.npl.auction.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;

@javax.persistence.Entity
@Table(name = "teams")
@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Team extends Entity {

    @Id
    @GenericGenerator(
        name="teamIdGenerator",
        strategy = "org.scsmksn.npl.auction.idgenerators.EntityIdGenerator",
        parameters = @Parameter(name = "table_name", value = "teams")
    )
    @GeneratedValue(generator = "teamIdGenerator")
    private Long id;

    @Column(name = "team_name", unique = true)
    private String teamName;

    @ManyToMany(targetEntity = User.class)
    @JoinTable(
        name = "teams_owners",
        joinColumns = @JoinColumn(name = "team_id"),
        inverseJoinColumns = @JoinColumn(name = "owner_id")
    )
    private List<User> owners = new ArrayList<>(3);

    @ManyToMany(targetEntity = User.class)
    @JoinTable(
        name = "teams_admins",
        joinColumns = @JoinColumn(name = "team_id"),
        inverseJoinColumns = @JoinColumn(name = "admin_id")
    )
    private List<User> admins = new ArrayList<>(3);

    @ManyToMany(targetEntity = User.class)
    @JoinTable(
        name = "teams_players",
        joinColumns = @JoinColumn(name = "team_id"),
        inverseJoinColumns = @JoinColumn(name = "player_id")
    )
    private List<User> players = new ArrayList<>();

    @OneToOne(targetEntity = Resource.class, cascade = CascadeType.ALL)
    private Resource teamImage;

    @OneToOne(targetEntity = Approval.class, mappedBy = "team")
    @JsonIgnore
    private Approval approval;

    @Transient
    @JsonIgnore
    @Override
    public String getObjectName() {
        return "team";
    }
}

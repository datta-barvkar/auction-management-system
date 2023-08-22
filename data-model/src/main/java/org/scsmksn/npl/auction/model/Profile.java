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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import java.util.List;

@javax.persistence.Entity
@Table(name = "profiles")
@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Profile extends Entity {

    @Id
    @GenericGenerator(
        name="cricketProfileIdGenerator",
        strategy = "org.scsmksn.npl.auction.idgenerators.EntityIdGenerator",
        parameters = @Parameter(name = "table_name", value = "profiles")
    )
    @GeneratedValue(generator = "cricketProfileIdGenerator")
    private Long id;

    @Column(name = "playing_role")
    @NotNull
    private String playingRole;

    @Column(name = "batting_style")
    @NotNull
    private String battingStyle;

    @Column(name = "bowling_style")
    @NotNull
    private String bowlingStyle;

    @Column(name = "more_details")
    private String moreDetails;

    @OneToOne(targetEntity = User.class, mappedBy = "profile")
    @JsonIgnore
    private User user;

    @OneToOne(targetEntity = BattingStatistics.class, cascade = CascadeType.ALL)
    private BattingStatistics battingStats;

    @OneToOne(targetEntity = BowlingStatistics.class, cascade = CascadeType.ALL)
    private BowlingStatistics bowlingStats;

    @OneToOne(targetEntity = FieldingStatistics.class, cascade = CascadeType.ALL)
    private FieldingStatistics fieldingStats;

    @OneToOne(targetEntity = CaptainingStatistics.class, cascade = CascadeType.ALL)
    private CaptainingStatistics captainingStats;

    @Transient
    private String playerName;

    @Transient
    @JsonIgnore
    private Long playerId;

    @Transient
    @JsonIgnore
    private List<Team> teams;

    @Transient
    @JsonIgnore
    private Resource userImage;

    @Transient
    @JsonIgnore
    @Override
    public String getObjectName() {
        return "profile";
    }
}

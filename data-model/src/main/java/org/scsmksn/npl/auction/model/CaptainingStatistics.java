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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@javax.persistence.Entity
@Table(name = "captaining_stats")
@Getter
@Setter
@NoArgsConstructor
public class CaptainingStatistics extends Entity {

    @Id
    @GenericGenerator(
            name="captainStatsIdGenerator",
            strategy = "org.scsmksn.npl.auction.idgenerators.EntityIdGenerator",
            parameters = @Parameter(name = "table_name", value = "captaining_stats")
    )
    @GeneratedValue(generator = "captainStatsIdGenerator")
    private Long id;

    @Column(name = "matches")
    private Integer matches;

    @Column(name = "toss_wins")
    private Integer tossWins;

    @Column(name = "wins")
    private Integer wins;

    @Column(name = "losses")
    private Integer losses;

    @Column(name = "no_results")
    private Integer noResults;

    @Column(name = "ties")
    private Integer ties;

    @OneToOne(targetEntity = Profile.class, mappedBy = "captainingStats")
    @JsonIgnore
    private Profile profile;

    @Transient
    @JsonIgnore
    @Override
    public String getObjectName() {
        return "captainingStatistics";
    }
}

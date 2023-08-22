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
@Table(name = "batting_stats")
@Getter
@Setter
@NoArgsConstructor
public class BattingStatistics extends Entity {

    @Id
    @GenericGenerator(
            name="battingStatsIdGenerator",
            strategy = "org.scsmksn.npl.auction.idgenerators.EntityIdGenerator",
            parameters = @Parameter(name = "table_name", value = "batting_stats")
    )
    @GeneratedValue(generator = "battingStatsIdGenerator")
    private Long id;

    @Column(name = "matches")
    private Integer matches;

    @Column(name = "innings")
    private Integer innings;

    @Column(name = "not_outs")
    private Integer notOuts;

    @Column(name = "total_runs")
    private Integer totalRuns;

    @Column(name = "highest_runs")
    private Integer highestRuns;

    @Column(name = "average")
    private Double average;

    @Column(name = "strike_rate")
    private Double strikeRate;

    @Column(name = "thirties")
    private Integer thirties;

    @Column(name = "fifties")
    private Integer fifties;

    @Column(name = "hundreds")
    private Integer hundreds;

    @Column(name = "fours")
    private Integer fours;

    @Column(name = "sixes")
    private Integer sixes;

    @Column(name = "ducks")
    private Integer ducks;

    @OneToOne(targetEntity = Profile.class, mappedBy = "battingStats")
    @JsonIgnore
    private Profile profile;

    @Transient
    @JsonIgnore
    @Override
    public String getObjectName() {
        return "battingStatistics";
    }
}

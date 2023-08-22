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
@Table(name = "bowling_stats")
@Getter
@Setter
@NoArgsConstructor
public class BowlingStatistics extends Entity {

    @Id
    @GenericGenerator(
            name="bowlingStatsIdGenerator",
            strategy = "org.scsmksn.npl.auction.idgenerators.EntityIdGenerator",
            parameters = @Parameter(name = "table_name", value = "bowling_stats")
    )
    @GeneratedValue(generator = "bowlingStatsIdGenerator")
    private Long id;

    @Column(name = "matches")
    private Integer matches;

    @Column(name = "innings")
    private Integer innings;

    @Column(name = "overs")
    private Double overs;

    @Column(name = "maidens")
    private Integer maidens;

    @Column(name = "wickets")
    private Integer wickets;

    @Column(name = "runs")
    private Integer runs;

    @Column(name = "best_bowling")
    private String bestBowling;

    @Column(name = "three_wickets")
    private Integer threeWickets;

    @Column(name = "five_wickets")
    private Integer fiveWickets;

    @Column(name = "economy")
    private Double economy;

    @Column(name = "strike_rate")
    private Double strikeRate;

    @Column(name = "average")
    private Double average;

    @Column(name = "wides")
    private Integer wides;

    @Column(name = "no_balls")
    private Integer noBalls;

    @Column(name = "dot_balls")
    private Integer dotBalls;

    @Column(name = "fours")
    private Integer fours;

    @Column(name = "sixes")
    private Integer sixes;

    @OneToOne(targetEntity = Profile.class, mappedBy = "bowlingStats")
    @JsonIgnore
    private Profile profile;

    @Transient
    @JsonIgnore
    @Override
    public String getObjectName() {
        return "bowlingStatistics";
    }
}

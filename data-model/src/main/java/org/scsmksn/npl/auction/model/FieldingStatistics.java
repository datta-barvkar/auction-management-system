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
@Table(name = "fielding_stats")
@Getter
@Setter
@NoArgsConstructor
public class FieldingStatistics extends Entity {

    @Id
    @GenericGenerator(
            name="fieldStatsIdGenerator",
            strategy = "org.scsmksn.npl.auction.idgenerators.EntityIdGenerator",
            parameters = @Parameter(name = "table_name", value = "fielding_stats")
    )
    @GeneratedValue(generator = "fieldStatsIdGenerator")
    private Long id;

    @Column(name = "matches")
    private Integer matches;

    @Column(name = "catches")
    private Integer catches;

    @Column(name = "caught_behind")
    private Integer caughtBehind;

    @Column(name = "run_outs")
    private Integer runOuts;

    @Column(name = "stumpings")
    private Integer stumpings;

    @Column(name = "assisted_run_outs")
    private Integer assistedRunOuts;

    @Column(name = "bye_runs")
    private Integer byeRuns;

    @OneToOne(targetEntity = Profile.class, mappedBy = "fieldingStats")
    @JsonIgnore
    private Profile profile;

    @Transient
    @JsonIgnore
    @Override
    public String getObjectName() {
        return "fieldingStatistics";
    }
}

package org.scsmksn.npl.auction.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@javax.persistence.Entity
@Table(name = "auctions")
@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Auction extends Entity {

    @Id
    @GenericGenerator(
            name="auctionIdGenerator",
            strategy = "org.scsmksn.npl.auction.idgenerators.EntityIdGenerator",
            parameters = @Parameter(name = "table_name", value = "auctions")
    )
    @GeneratedValue(generator = "auctionIdGenerator")
    private Long id;

    @Transient
    @JsonIgnore
    @Override
    public String getObjectName() {
        return "auction";
    }
}

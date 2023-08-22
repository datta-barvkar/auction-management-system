package org.scsmksn.npl.auction.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@MappedSuperclass
public abstract class Entity {

    @Column(name = "created_on")
    private Timestamp createdOn = Timestamp.valueOf(LocalDateTime.now());

    @Column(name = "modified_on")
    private Timestamp modifiedOn = Timestamp.valueOf(LocalDateTime.now());

    @Column(name = "created_by")
    private Long createdBy;

    @Column(name = "modified_by")
    private Long modifiedBy;

    @Transient
    @JsonIgnore
    private boolean editable = false;

    @Transient
    @JsonIgnore
    private boolean deletable = false;

    public abstract String getObjectName();

    public abstract Long getId();

    public abstract String getName();
}

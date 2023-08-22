package org.scsmksn.npl.auction.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.scsmksn.npl.auction.enums.RoleEnum;
import org.scsmksn.npl.auction.model.constraints.ValidEmail;
import org.scsmksn.npl.auction.model.constraints.ValidPhone;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@javax.persistence.Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class User extends Entity {

    @Id
    @GenericGenerator(
        name="userIdGenerator",
        strategy = "org.scsmksn.npl.auction.idgenerators.EntityIdGenerator",
        parameters = @Parameter(name = "table_name", value = "users")
    )
    @GeneratedValue(generator = "userIdGenerator")
    private Long id;

    @Column(name = "first_name")
    @NotBlank
    private String firstName;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "last_name")
    @NotBlank
    private String lastName;

    @Transient
    @JsonIgnore
    private String fullName;

    @Column(name = "email", unique = true)
    @NotBlank
    @ValidEmail
    private String email;

    @Column(name = "phone_number", unique = true)
    @ValidPhone
    private String phoneNumber;

    @OneToOne(targetEntity = Credentials.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "credential_id", referencedColumnName = "id")
    private Credentials credentials;

    @Column(name = "gender")
    private String gender;

    @Column(name = "marital_status")
    private String maritalStatus;

    @Column(name = "birthdate")
    private Timestamp birthdate;

    @ManyToMany(targetEntity = Role.class, fetch = FetchType.EAGER)
    @JoinTable(
        name = "users_roles",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<Role> roles = new ArrayList<>();

    @OneToOne(targetEntity = Profile.class, cascade = CascadeType.ALL)
    private Profile profile;

    @OneToOne(targetEntity = Resource.class, cascade = CascadeType.ALL)
    private Resource userImage;

    @ManyToMany(targetEntity = Team.class, mappedBy = "owners")
    @JsonIgnore
    private List<Team> ownedTeams = new ArrayList<>(1);

    @ManyToMany(targetEntity = Team.class, mappedBy = "admins")
    @JsonIgnore
    private List<Team> administeredTeams = new ArrayList<>(1);

    @ManyToMany(targetEntity = Team.class, mappedBy = "players")
    @JsonIgnore
    private List<Team> playedForTeams = new ArrayList<>();

    @Transient
    @JsonIgnore
    private List<Menu> topMenus = new ArrayList<>();

    @OneToOne(targetEntity = Approval.class, mappedBy = "player")
    private Approval playerApproval;

    @OneToOne(targetEntity = Approval.class, mappedBy = "approver")
    private Approval approverApproval;

    @JsonIgnore
    public String getName() {
        return getFirstName();
    }

    public String getFullName() {
        final StringBuilder fullName = new StringBuilder(firstName).append(" ");
        if (!(middleName == null || middleName.length() == 0)) {
            fullName.append(middleName).append(" ");
        }
        fullName.append(lastName);
        return fullName.toString();
    }

    public boolean isAdmin() {
        return roles.stream().anyMatch(role -> role.equals(RoleEnum.ADMIN.getRole()));
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof User)) {
            return false;
        }
        final User user = (User) obj;
        return id.equals(user.id) && email.equals(user.email) && phoneNumber.equals(user.phoneNumber);
    }

    @Transient
    @JsonIgnore
    @Override
    public String getObjectName() {
        return "user";
    }
}

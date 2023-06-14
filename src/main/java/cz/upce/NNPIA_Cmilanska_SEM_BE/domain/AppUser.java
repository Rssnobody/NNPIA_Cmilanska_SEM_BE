package cz.upce.NNPIA_Cmilanska_SEM_BE.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import cz.upce.NNPIA_Cmilanska_SEM_BE.dtos.AppUserOutputDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "APP_USERS")
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    @Column
    private String loginName;
    @Column
    private String password;
    @Column
    private String email;
    @Column
    private String firstName;
    @Column
    private String lastName;
    @Column
    private Date dateOfBirth;
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<UserBook> userBooks = Collections.emptyList();

    public AppUser(String loginName, String password, String email, String firstName, String lastName, Date dateOfBirth) {
        this.loginName = loginName;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
    }

    public AppUser(Long id, String loginName, String password, String email, String firstName, String lastName, Date dateOfBirth) {
        this.userId = id;
        this.loginName = loginName;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
    }

    public AppUserOutputDto toDto() {
        return new AppUserOutputDto(
                getUserId(),
                getLoginName(),
                getPassword(),
                getEmail(),
                getFirstName(),
                getLastName(),
                getDateOfBirth()
        );
    }
}

package cz.upce.NNPIA_Cmilanska_SEM_BE.domain;

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
    @OneToMany(mappedBy = "user")
    private List<UserBook> userBooks = Collections.emptyList();
}

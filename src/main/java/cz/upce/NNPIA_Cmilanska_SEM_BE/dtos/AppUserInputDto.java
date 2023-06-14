package cz.upce.NNPIA_Cmilanska_SEM_BE.dtos;

import cz.upce.NNPIA_Cmilanska_SEM_BE.domain.AppUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppUserInputDto {
    @NotNull
    @NotBlank
    @Size(max = 256, min = 1)
    private String userName;

    private String password;

    private String email;

    private String firstName;

    private String lastName;

    private Date dateOfBirth;

    public AppUser toEntity() {
        return new AppUser(
                getUserName(),
                getPassword(),
                getEmail(),
                getFirstName(),
                getLastName(),
                getDateOfBirth()
        );
    }

    public AppUser toEntity(Long id) {
        return new AppUser(
                id,
                getUserName(),
                getPassword(),
                getEmail(),
                getFirstName(),
                getLastName(),
                getDateOfBirth()
        );
    }
}

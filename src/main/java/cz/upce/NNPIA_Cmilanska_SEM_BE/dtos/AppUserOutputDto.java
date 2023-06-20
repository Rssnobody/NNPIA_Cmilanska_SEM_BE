package cz.upce.NNPIA_Cmilanska_SEM_BE.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppUserOutputDto {
    private Long userId;
    private String loginName;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
}

package uz.pdp.studentverify.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDtoSuper {
    @NotNull
    @Email
    private String email;
    private String fullName;
}

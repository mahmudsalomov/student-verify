package uz.pdp.studentverify.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Student {
    @Id
    private UUID id;
    @NotNull
    private String firstname;
    @NotNull
    private String lastname;
    @NotNull
    private String fullName;
    @NotNull
    @Email
    private String email;
    @NotNull
    private String code;
    @NotNull
    private boolean active;

}

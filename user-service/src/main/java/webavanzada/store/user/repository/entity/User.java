package webavanzada.store.user.repository.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.*;

@Data
@Entity
@Table(name="tbl_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Long id;

    @NotEmpty(message = "El número de documento no puede ser vacío")
    @Size( min = 10 , max = 10, message = "El tamaño del número de documento es 10")
    @Column(name = "number_id" , unique = true ,length = 10, nullable = false)
    private String numberID;

    @NotEmpty(message = "El nombre no puede ser vacío")
    @Column(name="first_name", nullable=false)
    private String firstName;

    @NotEmpty(message = "El apellido no puede ser vacío")
    @Column(name="last_name", nullable=false)
    private String lastName;

    @NotEmpty(message = "El sexo no puede ser vacío")
    @Column(name="sex", nullable=false)
    private String sex;

    @Positive(message = "La debe ser mayor que cero.")
    private Integer age;

    private String state;
}

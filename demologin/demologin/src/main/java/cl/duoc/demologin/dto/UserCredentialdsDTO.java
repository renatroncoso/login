package cl.duoc.demologin.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserCredentialdsDTO {

    @NotBlank(message = "el username no puede estar vacio")
    @Size(min=4,max = 20,message = "el username debe tener entre 4 y 20 caracteres")
    private String username;

    @NotBlank(message = "la contraseñan no puede estar vacia")
    @Size(min=5,message = "la clave debe tener 6 o mas caracteres")
    private String password;
}

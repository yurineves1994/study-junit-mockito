package apijunit.entities.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class UserDTO {
        private Integer id;
        private String name;
        private String email;

        @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) // permite que seja acesso para escrita porém bloqueia a leitura
        private String password;
}

package ec.com.kardex.security.vo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class AuthenticationRequest implements Serializable {

    private static final long serialVersionUID = -60040284699977384L;

    private String username;

    private String password;

}

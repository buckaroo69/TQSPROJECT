package logisticsmarshall.tqs.ua;

import lombok.Data;

import javax.persistence.Id;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
@Getter
@Setter
@ToString
@NoArgsConstructor
@JsonDeserialize
@JsonSerialize
public class UserRegistration {
    private String name;
    private String password;
    private String confirmPassword;
    private String email;
    private String confirmEmail;
    public String role;

    public UserRegistration(String name,  String password, String confirmPassword, String email, String confirmEmail, String role) {
        this.name = name;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.email = email;
        this.confirmEmail = confirmEmail;
        this.role = role;
    }
}
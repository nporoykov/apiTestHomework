
package dto;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
@JsonSerialize
public class User {

    private long id;
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private String phone;
    private String username;
    private long userStatus;

}

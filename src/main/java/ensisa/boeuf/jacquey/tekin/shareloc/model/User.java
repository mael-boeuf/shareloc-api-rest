package ensisa.boeuf.jacquey.tekin.shareloc.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class User {

    @Id
    private String email;
    private String password;
    private String firstName;
    private String lastName;

    public User(String _email, String _password, String _firstName, String _lastName) {
        this.email = _email;
        this.password = _password;
        this.firstName = _firstName;
        this.lastName = _lastName;
    }

    public User() {
        super();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}

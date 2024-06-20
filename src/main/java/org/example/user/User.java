package org.example.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class User {

    private static User instance;
    public static User getInstance() {
        if(User.instance == null) {
            User.instance = new User();
        }
        return User.instance;
    }

    private String name;
    private String email;
    private String address;
    private String age;
    private String password;
}

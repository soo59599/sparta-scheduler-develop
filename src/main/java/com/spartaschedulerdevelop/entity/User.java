package com.spartaschedulerdevelop.entity;

import com.spartaschedulerdevelop.dto.user.UserSaveRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name="users")
@Getter
@NoArgsConstructor
public class User extends BaseEntity{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private String password;

    private User(String name, String email){
        this.name = name;
        this.email = email;
    }

    public static User create(UserSaveRequestDto request){
        return new User(
                request.getName(),
                request.getEmail()
        );
    }


}

package com.spartaschedulerdevelop.entity;

import com.spartaschedulerdevelop.dto.user.UserSaveRequestDto;
import com.spartaschedulerdevelop.dto.user.UserUpdateRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

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

    private User(String name, String email,  String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public static User toUserEntity(UserSaveRequestDto request, String password){
        return new User(
                request.name(),
                request.email(),
                password
        );
    }

    public void update(UserUpdateRequestDto request){
        if(StringUtils.hasText(request.email())) this.email = request.email();
        if(StringUtils.hasText(request.password())) this.password = request.password();
    }

}

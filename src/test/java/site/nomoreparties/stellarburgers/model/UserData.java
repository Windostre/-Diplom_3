package site.nomoreparties.stellarburgers.model;

import lombok.*;

/**
 * Описание структуры данных пользователя пользователя
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserData {
    private String email;

    private String password;

    private String name;

}

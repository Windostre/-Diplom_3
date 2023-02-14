package site.nomoreparties.stellarburgers.model;

import lombok.*;

/**
 * Описание структуры данных пользователя
 */
@Getter
@Setter
@AllArgsConstructor
@Builder
@ToString
public class UserData {
    private String email;
    private String password;
    private String name;

}

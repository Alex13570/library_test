package ru.zakhrey.library_test.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {

    //выбрана эта реализация так как нет необходимости заводить отдельное уникальное поле под индекс.
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID ticketNumber;

    private String userName;

    @OneToMany(fetch = FetchType.EAGER)
    private List<BookEntity> takenBooks;

}

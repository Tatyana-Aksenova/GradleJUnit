package models;

import lombok.*;



@NoArgsConstructor
@Data
@Builder
@AllArgsConstructor
public class Cat {
    private String name;
    private String model;
    private Integer age;
    private Boolean isWhite;

}

package net.kaopiz.internship.demo.pagine;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PaginationResponseDTO<T> {
    private Integer status;
    private String message;
    private PaginateDTO<T> result;
}

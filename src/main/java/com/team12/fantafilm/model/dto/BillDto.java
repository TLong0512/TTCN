package com.team12.fantafilm.model.dto;


import com.team12.fantafilm.model.User;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class BillDto {
    private Long id;
    private LocalDateTime dateCreate;
    private Integer status;
    private BigDecimal totalMoney;
    private String tradingCode;
    private User user;
}

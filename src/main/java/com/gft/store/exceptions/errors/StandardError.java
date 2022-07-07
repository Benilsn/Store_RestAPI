package com.gft.store.exceptions.errors;

import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class StandardError extends RuntimeException {

    private LocalDateTime timestamp;
    private Integer status;
    private List<String> error;

}
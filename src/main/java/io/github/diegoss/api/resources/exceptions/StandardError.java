package io.github.diegoss.api.resources.exceptions;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class StandardError {

    private LocalDateTime timestamp;
    private Integer status;
    private String error;
    private String path;

}

package io.github.diegoss.api.resources.exceptions;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class StandardError {

    private LocalDateTime timestamp;
    private Integer status;
    private String error;
    private String path;

}

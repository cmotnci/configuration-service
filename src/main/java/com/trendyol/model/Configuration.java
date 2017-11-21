package com.trendyol.model;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Configuration {

    private long id;
    private String name;
    private String type;
    private String value;
    private int status;
    private String applicationName;
}

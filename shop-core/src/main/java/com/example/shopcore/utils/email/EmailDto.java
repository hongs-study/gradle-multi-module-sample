package com.example.shopcore.utils.email;

import java.util.List;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.springframework.core.io.Resource;

@Builder
@ToString
@EqualsAndHashCode
@Getter
public class EmailDto {
    private String from;

    private List<String> to;

    private List<String> cc;

    private String title;

    private String text;

    private List<Resource> attachments;
}

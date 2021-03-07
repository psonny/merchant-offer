package com.fis.code.screen.merchantoffer.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Offer
{
    String name;
    Integer price;
    boolean isCanceled;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime expireDateTime;
    String description;
}

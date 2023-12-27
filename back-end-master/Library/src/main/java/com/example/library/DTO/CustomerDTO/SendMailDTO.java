package com.example.library.DTO.CustomerDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor @AllArgsConstructor
public class SendMailDTO {
    private String toEmail;
    private String subject;
    private String body;
}

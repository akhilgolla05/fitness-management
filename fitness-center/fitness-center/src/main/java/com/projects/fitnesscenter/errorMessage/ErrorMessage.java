package com.projects.fitnesscenter.errorMessage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class ErrorMessage {

    private int statusCode;

    private String message;

    private long timeStamp;



}

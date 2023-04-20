package com.example.jwt.request;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateCommentRequest {
    private String content;
}

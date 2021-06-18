package edu.miu.attendance.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class JwtTokenDto {
    private String accessToken;
    private UserDataDto userData;
}

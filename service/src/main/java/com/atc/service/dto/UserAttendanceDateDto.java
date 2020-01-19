package com.atc.service.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.atc.service.util.DateConverter;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class UserAttendanceDateDto {

    private UserDto user;

    @DateTimeFormat(iso = ISO.DATE)
    private Date date;

    private long workedTime;

    @Setter(AccessLevel.NONE)
    private String workedTimeInLetter;

    public void setWorkedTimeInLetter(String workedTimeInLetter) {
        if (workedTime > 0) {
            this.workedTimeInLetter = DateConverter.convertTimeFromMilliesToString(workedTime);
        } else {
            this.workedTimeInLetter = workedTimeInLetter;
        }
    }

}

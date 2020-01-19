package com.atc.dto;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.atc.model.Attendance;
import com.atc.model.User;

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
public class UserAttendanceDto {

    private User user;

    @DateTimeFormat(iso = ISO.DATE)
    private Date date;

    private List<Attendance> attendances;

    private long workedTime;

    public UserAttendanceDto(User user, Date date) {
        super();
        this.user = user;
        this.date = date;
    }

}

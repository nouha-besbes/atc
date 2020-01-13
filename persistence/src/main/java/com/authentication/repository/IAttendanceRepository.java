package com.authentication.repository;

import java.util.List;

import com.authentication.model.Attendance;

public interface IAttendanceRepository extends IBaseRepository<Attendance, Long> {

    List<Attendance> findAttendanceByUserId(Long userId);

}

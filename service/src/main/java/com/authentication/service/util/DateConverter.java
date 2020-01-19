package com.authentication.service.util;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DateConverter {

    public static List<Date> convertListLocalDateToDate(List<LocalDate> localDates) {
        List<Date> dates = localDates.stream().map(date -> convertToDate(date)).collect(Collectors.toList());
        return dates;
    }

    public static List<Date> extractDatesBetweenTowDates(Date startDate, Date endDate) {
        LocalDate localDateStartDate = convertToLocalDate(startDate);
        LocalDate locallDateEndDate = convertToLocalDate(endDate);
        List<LocalDate> localDates = Stream.iterate(localDateStartDate, d -> d.plusDays(1))
                .limit(ChronoUnit.DAYS.between(localDateStartDate, locallDateEndDate) + 1).collect(Collectors.toList());
        return DateConverter.convertListLocalDateToDate(localDates);
    }

    public static LocalDate convertToLocalDate(Date dateToConvert) {
        return dateToConvert.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public static Date convertToDate(LocalDate dateToConvert) {
        return java.sql.Date.valueOf(dateToConvert);
    }

    public static String convertTimeFromMilliesToString(long diffInMillies) {
        Date date = new Date(diffInMillies);
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
        return formatter.format(date);
    }

}

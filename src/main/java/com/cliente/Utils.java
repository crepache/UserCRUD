package com.cliente;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Utils {

  public static int calculaIdade(Date dataNasc, String pattern){
    DateFormat sdf = new SimpleDateFormat(pattern);

    Calendar dateOfBirth = new GregorianCalendar();

    dateOfBirth.setTime(dataNasc);
    Calendar today = Calendar.getInstance();
    int age = today.get(Calendar.YEAR) - dateOfBirth.get(Calendar.YEAR);

    dateOfBirth.add(Calendar.YEAR, age);

    if (today.before(dateOfBirth)) {
      age--;
    }

    return age;
  }


}

package br.com.minsait.jp.contacts_app.utils;

import java.lang.reflect.Field;

public class ObjectUtils {

  public static boolean hasNonNullField(Object obj) {
    if (obj == null)
      return false;

    for (Field field : obj.getClass().getDeclaredFields()) {
      field.setAccessible(true);
      try {
        if (field.get(obj) != null)
          return true;
      } catch (IllegalAccessException e) {
        throw new RuntimeException("Erro ao acessar o campo " + field.getName(), e);
      }
    }

    return false;
  }

}
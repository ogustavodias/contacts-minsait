package br.com.minsait.jp.contacts_app.interfaces;

/**
 * Interface DTO para definir o contrato do método de conversão para a classe de
 * persistência.
 */
public interface DTO<T> {
  T toPersistEntity(T entity);
}

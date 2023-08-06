package org.chun.gql.vo;

public record Book(
    String id,
    String name,
    int pageCount,
    String authorId
) {
  public static Book of(String id, String name, int pageCount, String authorId) {
    return new Book(id, name, pageCount, authorId);
  }

  public static Book getById(String id) {
    return Inventory.books.stream()
        .filter(book -> book.id.equals(id))
        .findAny()
        .orElse(null);
  }
}

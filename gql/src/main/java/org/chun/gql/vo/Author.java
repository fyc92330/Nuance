package org.chun.gql.vo;

public record Author(
    String id,
    String name,
    String gender
) {

  public static Author of(String id, String name, String gender) {
    return new Author(id, name, gender);
  }

  public static Author getById(String id) {
    return Inventory.authors.stream()
        .filter(a -> a.id.equals(id))
        .findAny()
        .orElse(null);
  }
}

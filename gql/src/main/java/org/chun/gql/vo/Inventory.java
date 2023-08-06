package org.chun.gql.vo;

import java.util.List;

public class Inventory {

  public static final List<Book> books = List.of(
      Book.of("book-1", "ThisIsBook", 49, "Author-1"),
      Book.of("book-2", "HowToTalk", 234, "Author-2"),
      Book.of("book-3", "BeginningC#", 741, "Author-3")
  );

  public static final List<Author> authors = List.of(
      Author.of("Author-1", "Gordon", "M"),
      Author.of("Author-2", "Annie", "F"),
      Author.of("Author-3", "Cynthia", "F")
  );
}

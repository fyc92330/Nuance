package org.chun.gql;

import lombok.extern.slf4j.Slf4j;
import org.chun.gql.vo.Author;
import org.chun.gql.vo.Book;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@SpringBootApplication
public class GqlApplication {
	public static void main(String[] args) {
		SpringApplication.run(GqlApplication.class, args);
	}

	@QueryMapping
	public Book bookById(@Argument String id) {
		log.info(" >>> 取得Book資料 ");
		return Book.getById(id);
	}

	@SchemaMapping
	public Author author(Book book) {
		log.info(" >>> 取得Author資料 ");
		return Author.getById(book.authorId());
	}

	@QueryMapping
	public Author authorById(@Argument String id) {
		log.info(" >>> 取得Author資料 ");
		return Author.getById(id);
	}

	@Bean
	ApplicationListener<ApplicationReadyEvent> readyEventApplicationListener() {
		return event -> log.info("GraphQL UI: http://localhost:8081/gql");
	}
}

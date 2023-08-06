query bookDetails {
  bookById(id: "book-1") {
    id
    name
    pageCount
    author {
      id
      name
      gender
    }
  }
}



curl -X POST \                                                                                                                                                                                                 127 ✘  17:41:29 
-H "Content-Type: application/json" \
-d '{"query":"query bookDetails {\n  bookById(id: \"book-1\") {\n    id\n    name\n  }\n}","operationName":"bookDetails"}' \
http://localhost:8081/graphql

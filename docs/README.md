# 프로젝트 설정 및 기타 노트

2022-12-19

---

# 교재 기준에서 변경된 점들

- Spring Boot 버전을 `2.4.1`으로 변경.
- Gradle 5.X 버전에서 Gradle `6.7.1`으로 변경함.
- IntelliJ IDEA `2020.3` 버전 사용. (<-> 필자는 `2022.01` 버전을 사용)
- Junit 버전이 4에서 5로 변경됨.
- Gradle 버전이 5버전 이후로 여러 가지로 많이 바뀌어 이에 따라 교재의 문법 또한 수정할 사항이 많음. (자세한 내용은 참고 문서의 저자의 블로그 글을 참고)

## 현재 설정

- Java Oracle OpenJDK 1.8 (Java 11)
- Gradle 6.7.1
- Intellij IDEA 2022.01
- Junit5
- Spring Boot 2.4.1

# 정리

- [3장](#3장)


## 3장

### JPA 소개

#### 등장 배경

- 관계형 데이터베이스와 객체지향 프로그래밍 언어의 패러다임은 서로 다르기 때문에, 객체를 데이터베이스에 저장할 때 여러 문제가 발생한다.
- 이러한 문제를 '패러다임 불일치'라고 하며, 이 때문에 상속, 1:N 등 다양한 객체 모델링을 데이터베이스로 구현할 수 없다. 그러다 보니 웹 애플리케이션 개발은 점점 데이터베이스 모델링에만 집중하게 된다. 
- 이런 문제점을 해결하기 위해 등장한 것이 JPA이다.

#### JPA

- JPA는 위와 같이 서로 다른 두 영역의 중간에서 패러다임을 일치 시켜주기 위한 자바 표준 ORM 기술이다.
- 개발자가 객체지향적으로 프로그래밍을 하고, JPA가 이를 관계형 데이터베이스에 맞게 SQL을 대신 생성해서 실행해준다. 

> **ORM (Object-relational mapping)**
> 
> 객체 관계 매핑(Object-relational mapping; ORM)은 데이터베이스와 객체 지향 프로그래밍 언어 간의 호환되지 않는 데이터를 변환하는 프로그래밍 기법이다. 객체 지향 언어에서 사용할 수 있는 "가상" 객체 데이터베이스를 구축하는 방법이다.

### Spring Data JPA

- JPA는 자바 표준명세서로, 자바 애플리케이션이 관계형 데이터베이스를 사용하는 방식을 정의한 인터페이스이다.
- 따라서 이를 사용하기 위해서는 구현체가 필요한데, 대표적으로 Hibernate, Eclipse Link 등이 있다.
- 스프링에서는 보통 이 구현체들을 직접 다루지는 않으며, 이런 구현체들을 좀 더 쉽게 사용하고자 추상화시킨 Spring Data JPA를 사용한다.
- 즉, Spring Data JPA란 JPA 관련 기술들을 좀 더 쉽게 사용하기 위해 JPA를 한 단계 추상화시킨 모듈로서, 스프링 진영에서는 이 기술을 권장하고 있다.

#### Spring Data JPA

그렇다면 굳이 JPA의 구현체가 존재하는데, 왜 굳이 한 단계를 더 추상화시킨 것일까? 그냥 JPA로 접근하거나 구현체를 사용하는 방식이면 되지 않을까? 왜 이 기술이 필요한 것일까?

Spring Data JPA가 등장한 이유는 크게 두 가지이다.

- **구현체 교체의 용이성:**
  - JPA 구현체를 쉽게 교체할 수 있다.
  - Spring Data JPA는 내부에서 구현체 매핑을 지원해주기 때문이다.
- **저장소 교체의 용이성:**
  - 관계형 데이터베이스 외에 다른 저장소로 쉽게 교체할 수 있다.
  - 자료나 애플리케이션의 성격에 따라 다른 종류의 데이터베이스가 필요하다면 다른 Spring Data의 하위 인터페이스 (e.g. Spring Data MongoDB, Spring Data Redis 등)으로 교체하면 된다.

> 더 자세한 내용은 아래 블로그 참고: 
> [JPA, Hibernate, 그리고 Spring Data JPA의 차이점](https://suhwan.dev/2019/02/24/jpa-vs-hibernate-vs-spring-data-jpa/)

### 출력 로그 설정 관련

`application.properties`에 아래와 같이 설정하면 디버깅을 위해 출력되는 쿼리 로그를 MySQL 버전으로 변경할 수 있다.

```
# 실행된 쿼리를 로그로 보여줌. (default: H2)
spring.jpa.show-sql=true

# 쿼리를 MySQL 버전으로 변경함.
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL57Dialect
spring.jpa.properties.hibernate.dialect.storage_engine=innodb
spring.datasource.hikari.jdbc-url=jdbc:h2:mem:testdb;MODE=MYSQL
spring.datasource.hikari.username=sa
```


## 참고 문서

- [(2020.12.16) 스프링 부트와 AWS로 혼자 구현하는 웹 서비스 최신 코드로 변경하기](https://jojoldu.tistory.com/539)
- [교재 실습 코드 Github](https://github.com/jojoldu/freelec-springboot2-webservice/tree/version/2020-12-11)
- [Gradle - Dependency management](https://docs.gradle.org/current/userguide/java_plugin.html#sec:java_plugin_and_dependency_management)
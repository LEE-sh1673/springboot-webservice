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

### DAO (Data Access Object) / Repository 영역

- iBatis나 MyBatis 등에서는 **Dao**라 불리고, JPA 등에서는 **Repository**라 불리는 DB Layer에 접근자 영역를 말한다.
- Dao 자체로는 말그대로 DB에 접근하는 객체를 말한다.
- JPA에서는 이런 DAO(JPA에서는 Repository라는 용어를 사용)를 생성할 때 보통 인터페이스로 생성한다.

#### Repository, Entity 클래스

- JPA에서는 '~Repository' 형태의 **인터페이스**를 생성하며, 이때 이 인터페이스 자체는 데이터베이스를 대상으로 할수 있는 작업들을 정의한 것이며, 실제 DB의 테이블과 연동되는 클래스인 'Entity' 클래스를 별도로 만든다.
- JPA에서 Entity 클래스는 `@Entity`을 붙여 만들고, DB 데이터에 작업할 경우 실제 쿼리를 날리는 대신, Entity 클래스의 수정을 통해 작업한다.
- 이런 Entity 클래스에서는 **절대 Setter 메서드를 만들지 않으며**, 필드의 값 변경이 필요하면 명확히 목적과 의도를 나타낼 수 있는 메서드를 추가한다.
- 그래서 DB에 새로운 데이터를 JPA를 통해 삽입할 경우에는 보통 생성자를 통해 최종값을 채운 후 삽입하거나, 데이터를 삽입, 수정하는 경우 해당 이벤트에 맞는 public 메서드를 호출하는 것을 전제로 한다.
- 다른 방법으로는 생성자 대신에 `@Builder`를 통해 제공되는 빌더 클래스를 사용하기도 한다. 빌더를 사용하는 경우는 생성자와 달리 어느 필드에 어떤 값을 채워야 하는지 명확히 인지할 수 있다는 장점이 있다.

> 여기서 주의할 점은 Entity 클래스와 기본 Entity Repository 는 함께 위치해야 된다는 점이다. Entity 클래스는 기본 Repository 없이는 제대로 역할을 할 수가 없다.

### 영속성 컨텍스트 (Persistence Context)

> 영속성(Persistence)이란 데이터를 영구적으로 저장하는 것을 의미합니다.

- 데이터(엔티티)를 영구적으로 저장하는 환경. 애플리케이션과 데이터베이스 사이에서 객체를 보관하는 가상의 데이터베이스 같은 역할을 수행한다.
- JPA는 **엔티티 매니저**(Entity Manager)라는 것을 사용하는데, 이 매니저가 활성화된 상태로(Spring Data JPA를 쓴다면 기본 옵션) 트랜잭션 안에서 데이터베이스에서 데이터를 가져오면 이 데이터는 영속성 컨텍스트가 유지된 상태이다.
- 즉, Spring Data JPA에서 트랜잭션 안에서 데이터베이스에서 데이터를 가져오면 해당 데이터를 영속성 컨텍스트에 저장하게 된다.
- 영속성 컨텍스트에 등록된 데이터를 프로그램 중에 수정을 하게 되면, 영속성 컨텍스트에 존재하는 데이터와 데이터베이스에 존재하는 데이터의 상태가 서로 달라지게 된다.
- 이때 `@Transactional`은 스프링 AOP 전략에 따라 해당 데이터를 다루는 코드의 실행이 마무리될 때 해당하는 테이블에 변경분을 반영하게 된다. 따라서 Update 쿼리를 날릴 필요가 없어진다.

#### 더티 체킹(Dirty checking)

> 참고: [더티 체킹 (Dirty Checking)이란?](https://jojoldu.tistory.com/415)

이런 개념을 **더티 체킹**(dirty checking)이라 하는데, 간단히 말해 **상태 변경 검사**를 의미하며, JPA는 트랜잭션이 끝나는 시점에 **변화가 있는** 모든 엔티티 객체를 데이터베이스에 자동으로 반영해준다.

- 이때 변화가 있다의 기준은 **최초 조회 상태**이다.
- JPA에서는 엔티티를 조회하면 해당 엔티티의 조회 상태 그대로 스냅샷을 만들어놓는다.
- 그리고 트랜잭션이 끝나는 시점에는 이 스냅샷과 비교해서 다른점이 있다면 Update Query를 데이터베이스로 전달하게 된다.
- 단, 이런 상태 변경 검사의 대상은 영속성 컨텍스트가 관리하는 엔티티에만적용 됩니다.

## 참고 문서

- [(2020.12.16) 스프링 부트와 AWS로 혼자 구현하는 웹 서비스 최신 코드로 변경하기](https://jojoldu.tistory.com/539)
- [교재 실습 코드 Github](https://github.com/jojoldu/freelec-springboot2-webservice/tree/version/2020-12-11)
- [Gradle - Dependency management](https://docs.gradle.org/current/userguide/java_plugin.html#sec:java_plugin_and_dependency_management)
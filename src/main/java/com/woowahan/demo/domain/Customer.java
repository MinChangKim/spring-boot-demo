package com.woowahan.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


/**
 * @Entity
 * JPA 엔티티임을 표시합니다.
 * @Table(name = "customers")
 * 엔티티에 대응하는 테이블 이름을 지정합니다 기본적으로 테이블 이름을 클래스 이름과 동일하게 맞춥니다.
 * @Date
 * 컴파일(*.class 파일 생성)할 때 각 필드의 세터(setter)/게터(getter)와 toString(),
 * equals(), hashCode() 메서드가 생성되므로 소스 코드가 간결해집니다.
 * @NoArgsConstructor
 * JPA 명세에 따르면 엔티티 클래스에는 인자를 받지 않는 기본 생성자를 만들어야 합니다.
 * 롬복으로 기본 생성자를 만들려면 @NoArgsConstructor 애너테이션이 있어야 합니다.
 * @AllArgsConstructor
 * JPA와는 관계없지만 쉽게 프로그래밍 할 수 있게 롬복이 기본 생성자 외에 전체 필드를 인자로 받는
 * 생성자를 만들도록 설정합니다.
 *
 * @Id
 * 엔티티의 기본 키인 필드에 @Id 애너테이션을 붙입니다.
 * @GeneratedValue
 * DB가 기본 키 번호를 자동으로 매기도록 @GeneratedValue 애너테이션을 붙여 지정합니다.
 * @Column(nullable = false)
 * 필드에 @Column 애너테이션을 붙여서 DB의 대응하는 칼럼의 이름이나 제약 조건을 설정합니다.
 * 여기서는 NOT NULL 제약 조건을 설정합니다.
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

}

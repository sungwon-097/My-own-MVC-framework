-   객체지향 패러다임

### Test

-   JUnit5 : 자바 단위 테스팅 프레임워크
-   AssertJ : 테스트 코드 가독성을 높여주는 자바 라이브러리
-   테스트 코드를 작성하는 이유
    1. 잘 작성된 코드는 문서화 역할을 함(@DisplayName() )[->ex](/src/test/src/test/java/com/example/test/passwordValidator/PasswordValidatorTest.java)
    2. 코드에 결함을 발견하기 위함 [->ex](/src/test/src/test/java/com/example/test/passwordValidator/CorrectFixedPasswordGenerator.java)
    3. 리팩토링시 안정성 확보 -> 코드 중복을 제거하고 수정 용이성 향상
    4. 테스트하기 쉬운 코드를 작성하다 보면 더 낮은 결합도를 가진 설계를 얻을 수 있음 [->ex](/src/test/src/main/java/com/example/test/passwordValidator/User.java)
-   TDD
    -   Test Driven Development, 테스트 주도 개발
    -   프로덕션 코드보다 테스트 코드를 먼저 작성하는 개발 방법
    -   TFD(Test First Development) + 리팩토링
    -   기능 동작을 검증(메소드 단위)
-   BDD
    -   Behavior Driven Development, 행위 주도 개발
    -   시나리오 기반으로 테스트 코드를 작성하는 개발 방법
    -   하나의 시나리오는 Given, When, Then 구조를 가짐

### 1. 테스트 코드 실습

-   [Code](/src/test/src/test/java/com/example/test/passwordValidator/)

### 2. 객체지향 패러다임

-   OOP의 특징

    1.  Abstraction(추상화) : 불필요한 부분은 줄이고 핵심만을 남겨놓음. 일반화, 단순화라고도 부르며 복잡성을 낮줌
    2.  Polymorphism(다형성) : 하나의 타입으로 여러 종류의 객체를 참조. Overloading, Overriding, Extends type
    3.  Encapsulation(캡슐화) : 내부의 세부 사항을 외부에 감춤. Interface만을 공개해 변경이 용이한 코드를 만드는 것
    4.  Inheritance(상속) : 상속하여 기능을 확장

-   설계 원칙 SOLID

    1. SRP(단일 책임 원칙) : 하나의 책임을 가진다
    2. OCP(개방 폐쇄의 원칙 : 확장에는 열려있고 변경에는 닫혀있다, 기존코드를 변경하지 않고 기능을 추가 할 수 있다는 뜻(isolation과 같은 맥락?)
    3. LSP(리스코프 치환의 원칙) : 상위 타입의 객체를 하위 타입의 객체로 치환해도 동작에 문제가 없어야함
    4. ISP(인터페이스 분리의 원칙) : 많은 기능을 가진 인터페이스를 분리해 Client에게 필요한 내용만 제공함
    5. DIP(의존성 역전의 원칙) : 추상화된 인터페이스나 상위 클래스를 두어 의존성 변경을 최소화함. 변경이 적은 쪽을 의존해야 유지보수 용이

-   객체지향 패러다임
    -   적절한 객체에게 적절한 책임을 할당하여 서로 메시지를 주고 받으며 협력하도록 하는 것
        1. 소프트웨어 복잡도를 낮추기 위해 중요시됨
        2. 클래스가 아닌 객체에 초점을 맞추자
        3. 객체들에게 적절한 책임과 역할을 할당하자

### # 절차지향과 객체지향?

-   책임이 한 곳에 집중되어 있으면 절차지향, 분산되어 있다면 객체지향
-   객체지향은 높은 응집도와 낮은 결합도를 가지고 있어서 유지보수에 유리하다고 볼 수 있음.

### 3. 사칙연산 계산기 실습

1. Calculator 클래스에서 NewArithmeticOperators에게 작업을 위임함  
   // [Calculator](/src/test/src/main/java/com/example/test/arithmeticCalculator/Calculator.java)
2. NewArithmeticOperators는 각각 구현된 클래스로부터 작업을 수행함  
   // [Calculate/](/src/test/src/main/java/com/example/test/arithmeticCalculator/calculate/)
3. PositiveNumber에서 인자 유효성 체크를 해줌, 각 구현체에선 PositiveNumber 타입의 인자를 받고 toInt() 메소드로 정수를 반환함(int->PositiveNumber->int)

### 4. 학점 계산기 실습

1. 과목, 수강 학점, 평점이 필요함  
   // [Course](/src/test/src/main/java/com/example/test/gradeCalculator/Course.java)
2. 총 평점을 구하기 위해 총 수강 학점, 총 평점의 계산이 필요함, 이 리스트의 전체 합을 클래스로 구현  
   // [Courses](/src/test/src/main/java/com/example/test/gradeCalculator/Courses.java)
3. 추상화된 메소드로 표현 할 수 있게 되었음  
   // [GradeCalculator](/src/test/src/main/java/com/example/test/gradeCalculator/GradeCalculator.java)

### 5. 음식주문 프로세스 실습

-   도메인을 구성하는 객체를 손님, 메뉴판, 요리, 요리사로 나눠 추상화함  
     // [package](/src/test/src/main/java/com/example/test/customerOrder/)
    -   메뉴는 메뉴 이름과 가격으로 구성됨 [MenuItem](/src/test/src/main/java/com/example/test/customerOrder/MenuItem.java)
    -   메뉴판은 메뉴의 집합 [Menu](/src/test/src/main/java/com/example/test/customerOrder/Menu.java)
    -   요리 또한 요리 이름과 가격으로 구성되나 메뉴와 동일한 형태를 띄고 있음 [Cook](/src/test/src/main/java/com/example/test/customerOrder/Cook.java)
    -   요리사는 요리를 만듦 [Cooking](/src/test/src/main/java/com/example/test/customerOrder/Cooking.java)

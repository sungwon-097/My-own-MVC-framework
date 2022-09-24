-   객체지향 패러다임

### Test

-   JUnit5 : 자바 단위 테스팅 프레임워크
-   AssertJ : 테스트 코드 가독성을 높여주는 자바 라이브러리
-   테스트 코드를 작성하는 이유
    1. 잘 작성된 코드는 문서화 역할을 함(@DisplayName() )[->ex](/src/test/src/test/java/com/example/test/PasswordValidatorTest.java)
    2. 코드에 결함을 발견하기 위함 [->ex](/src/test/src/test/java/com/example/test/CorrectFixedPasswordGenerator.java)
    3. 리팩토링시 안정성 확보 -> 코드 중복을 제거하고 수정 용이성 향상
    4. 테스트하기 쉬운 코드를 작성하다 보면 더 낮은 결합도를 가진 설계를 얻을 수 있음 [->ex](/src/test/src/main/java/com/example/test/User.java)
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

-   [Code](/src/test/src/)

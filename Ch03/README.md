-   객체지향 패러다임

### Test

-   JUnit5 : 자바 단위 테스팅 프레임워크
-   AssertJ : 테스트 코드 가독성을 높여주는 자바 라이브러리
-   테스트 코드를 작성하는 이유
    1. 잘 작성된 코드는 문서화 역할을 함(@DisplayName())
    2. 코드에 결함을 발견하기 위함
    3. 리팩토링시 안정성 확보
    4. 테스트하기 쉬운 코드를 작성하다 보면 더 낮은 결합도를 가진 설계를 얻을 수 있음
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

-   비밀번호 유효성 검증기
-   Requirement
    -   range(min 8 ~ max 12)
    -   out of range => IllegalArgumentException
    -   경계조건에 대해 테스트 코드를 작성 해야함
    -   [code](/src/Test_practice/)

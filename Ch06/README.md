-   MVC

### Reflection

-   힙 영역에 로드돼있는 클래스 타입의 객체를 통해 필드/메소드/생성자를 접근제어자와 상관 없이 사용 할 수 있도록 지원하는 API
-   컴파일 시점이 아닌 런타임 시점에 동적으로 특정 클래스의 정보를 추출 해낼 수 있는 프로그래밍 기법
-   주로 프레임워크 또는 라이브러리 개발 시 사용됨
-   Spring Boot의 DI, JUnit, Jackson등에서 reflection 사용
-   @Controller 가 설정된 모든 클래스를 찾아 출력하는 방식으로 실습함

### Front Controller 패턴

-   모든 요청을 단일 핸들러애서 처리하도록 하는 패턴(중앙처리 매커니즘) : 모든 요청을 받아 적절한 컨트롤러로 넘겨줌
-   스프링 웹 MVC 프레임워크의 DispatcherServlet이 프런트컨트롤러 패턴으로 구현 돼있음

### Forward VS Redirect

-   Forward
    -   서블릿에서 클라이언트를 거치지 않고 바로 다른 서블릿(or jsp)에 요청하는 방식
    -   서버 내부에서 일어나는 요청이기 때문에 HttpServletResponse, HttpServletRequest 객체가 새롭게 생성되지 않음(공유됨)

```java
    RequestDispatcher dispatcher = request.getRequestDispatcher("Servlet or JSP");
    dispatcher.forward(request, response);
```

-   Redirect
    -   웹브라우저를 다시 거쳐 다른 서블릿에게 요청하는 방식
    -   새로운 요청이기 때문에 HttpServletResponse, HttpServletRequest 객체가 새롭게 생성됨

```java
    res.sendRedirect();
```

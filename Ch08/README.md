-   SpringBoot

### SpringBoot Framework 동작 원리

```java
@WebServlet("/")
public class DispatcherServlet extends HttpServlet {
    ...
    @Override
    public void service(HttpServletRequest req, HttpServletResponse res){
    ...
    Controller handler = requestMappingHandlerMapping.findHandler(req.getRequestURI());
```

-   모든 Request는 DispatcherServlet을 통해 들어오고 handler에 mapping시킴

```java
public class RequestMappingHandlerMapping {
    // key : users/, value : userController
    private Map<String, Controller> mappings = new HashMap<>();

    public void init(){
        mappings.put("/", new HomeController());
        mappings.put("/users", new UserListController());
    }

    public Controller findHandler(String uriPath){
        return mappings.get(uriPath);
    }
}
```

-   uriPath에 맞는 Controller를 반환

```java
public class HomeController implements Controller{
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "home.jsp";
    }
}
```

-   Controller에서는 viewName을 다시 handlerAdapter에게 전달

```java
@WebServlet("/")
public class DispatcherServlet extends HttpServlet {
    ...
    @Override
    public void service(HttpServletRequest req, HttpServletResponse res){
    ...
        View view = viewResolver.resolveViewname(ModelAndView, getViewName());
        view.render(ModelAndView.getModel(), req, res);
```

-   viewResolver는 해당되는 view의 이름을 찾아 리턴해주고 리턴된 뷰를 다시 DispatcherServlet에서 렌더링 해줌으로서 Response함

![img](/Ch08/img.png)

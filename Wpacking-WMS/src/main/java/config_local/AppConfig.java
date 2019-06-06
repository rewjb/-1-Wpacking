package config_local;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @EnableWebMvc란놈은 쉽게 얘기하면 web mvc을 이용하는데 있어서 spring container가 가져야할 기본적인 bean component을 등록해서 빠르게 편하게 mvc을 구축할수 있는 configuration 환경을 제공해준다.
 * 예를 들면 spring3 에서 새롭게 제시하고 있는 @MVC (@RequestMapping, @Requestbody, @ResponseBody) 등의 스타일을 위해서 등록되어야 하는 RequestMappingHandlerMapping,RequestMappingHandlerAdapter,ExceptionHandlerExceptionResolver 등의 등록을 자동으로 해준다.
 * XML base였다면 <mvc:annotation-driven/>과 같다.
 * RequestMappingHandlerMapping : 요청을 맞는 컨트롤러에 맵핑해주는 핸들러
 * RequestMappingHandlerAdapter : 컨트롤러의 실행 결과를 리턴하는 역활이다. Annotation 기반의 Controller 처리를 위해 반드시 필요
 * */

@Configuration
@EnableWebMvc
public class AppConfig {

}

package config_local.initializer;

import config_local.AppConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

/**
 Life Cycle of ServletContext
 Step 1: Servlet container reads the DD (Deployment Descriptor – web.xml) and creates the name/value string pair for each <context-param> when web application is getting started.
 Step 2: Container creates the new Instance of ServletContext.
 Note*: ServletContext is an Interface.
 Step 3: Servlet container gives the ServletContext a reference to each name/value pair of the context init parameter.
 Step 4: Every servlet and JSP in the same web application will now has access to this ServletContext.Why Learn Java?
 */

/**
 Servlet Container(tomcat) VS Spring Container
 A Servlet Container or Web Container (like Tomcat) is an implementation of various Java EE specifications like Java Servlet, JSP, etc. Put in a simple way, it is an environment where Java web applications can live. A web server + Java support.
 A Spring Container on the other hand, is the core and the engine of the Spring Framework. It is an IoC Container that handles Spring applications lifecycle creating new beans and injecting dependencies.
 Because a Spring application can be a web application, a Spring Container can "live" inside a Web Container.
 */

/**
 * Servlet 3.0 ServletContainerInitializer designed to support code-based configuration of the servlet container using Spring's WebApplicationInitializer SPI as opposed to (or possibly in combination with) the traditional web.xml-based approach.
 * */
public class WpackingApplicationinitializer implements WebApplicationInitializer {

    public void onStartup(ServletContext servletContext) throws ServletException  {
        /** ServletContext 참고자료
         * 1] ServletContext is a configuration Object which is created when web application is started. It contains different initialization parameter that can be configured in web.xml.
         * */
        AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
        /** AnnotationConfigWebApplicationContext은 상속받은게 엄청 많음.. 그 중 WebApplicationContext도 있음 */

        rootContext.register(AppConfig.class);
        /** AnnotationConfigApplicationContext에 어노테이션으로 설정한 AppConfig 클래스 정보를 넣어준다. */

        servletContext.addListener(new ContextLoaderListener(rootContext));
        /** ContextLoaderListener 참고자료
         * 1] Application Context is the container initialized by a ContextLoaderListener or ContextLoaderServlet defined in the web.xml and the configuration would look something like this:
         * 2] ContextLoaderListener creates a root web-application-context for the web-application and puts it in the ServletContext. This context can be used to load and unload the spring-managed beans ir-respective of what technology is being used in the controller layer(Struts or Spring MVC).
         * 3] When ContextLoaderListener is used in tandem with DispatcherServlet, a root web-application-context is created first as said earlier and a child-context is also created by DispatcherSerlvet and is attached to the root application-context.
         * */

        AnnotationConfigWebApplicationContext servletConfig = new AnnotationConfigWebApplicationContext();
        /** servletConfig.register( ); : 어노테이션 웹어플리케이션 컨텍스트에 어노테이션으로 선언한 config를 넣어야 함!
         *  설계에 대해 고민해야함
         * */
        DispatcherServlet dispatcherServlet = new DispatcherServlet(servletConfig);
        dispatcherServlet.setThrowExceptionIfNoHandlerFound(true);
        /** 요청에 해당하는 것을 찾지모하면 에러르 보여준다. */

        ServletRegistration.Dynamic dispatcherServletConfig =  servletContext.addServlet("dispatcher" , dispatcherServlet);
        /** 디스패처서블릿 등록! */
        dispatcherServletConfig.setLoadOnStartup(1);
        dispatcherServletConfig.addMapping("/");

    }


}

/**
 *    private void addUtf8CharacterEncodingFilter(ServletContext servletContext)
 *     {
 *         FilterRegistration.Dynamic filter = servletContext.addFilter("CHARACTER_ENCODING_FILTER", CharacterEncodingFilter.class);
 *         filter.setInitParameter("encoding", "UTF-8");
 *         filter.setInitParameter("forceEncoding", "true");
 *         filter.addMappingForUrlPatterns(null, false, "/*");
 *     }
 * */

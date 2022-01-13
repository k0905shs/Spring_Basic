package hello.login.web.argumentresolver;

import hello.login.domain.member.Member;
import hello.login.web.SessionConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 최종 적으로 만들어진 resolverArgument는 WebMvcConfigurer에 등록해야함
 */
@Slf4j
public class LoginMemberArgumentResolver implements HandlerMethodArgumentResolver {

    /**
     @Login 애노테이션이 있으면서 Member 타입이면 해당 ArgumentResolver
     가 사용된다.
     */
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        log.info("supportsParameter 실행");

        boolean hasLoginAnnotation = parameter.hasParameterAnnotation(Login.class);//로그인 어노테이션이 파라미터에 있느냐??
        boolean hasMemberType = Member.class.isAssignableFrom(parameter.getParameterType()); //어노테이션을 받은 파라미터가 멤버 타입인가? 

        //둘다 맞으면 resolveArgument가 실행이 된다.
        return hasLoginAnnotation && hasMemberType;
    }

    /**
     컨트롤러 호출 직전에 호출 되어서 필요한 파라미터 정보를 생성해준다. 여기서는
     세션에 있는 로그인 회원 정보인 member 객체를 찾아서 반환해준다. 이후 스프링MVC는 컨트롤러의
     메서드를 호출하면서 여기에서 반환된 member 객체를 파라미터에 전달해준다.
     */
    //위에 조건이 둘다 맞을 경우
    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {

        log.info("resolveArgument 실행");

        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
        HttpSession session = request.getSession(false);
        if (session == null) {
            return null;
        }
        //세션정보가 있던 없든 return을 해준다.
        //null이 갈 수도 있다는 말
        return session.getAttribute(SessionConst.LOGIN_MEMBER);
    }
}

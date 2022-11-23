package hello.hellospring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect // aop는 해당 어노테이션이 필요하다 , aop는 프록시를 사용
@Component   // 해당 어노테이션으로 빈으로 등록해도 되지만 config에 작성하는것을 선호
public class TimeTraceAop {

    @Around("execution(* hello.hellospring..*(..))") // 타겟팅 어노테이션   해당 패키지 하위에 다 적용
    //@Around("execution(* hello.hellospring.service..*(..))")
    public Object excute(ProceedingJoinPoint joinPoint) throws Throwable{
        long start = System.currentTimeMillis();
        Object v = joinPoint.getArgs();
        System.out.println("START: " + joinPoint.toString());
        try {
            //Object result = joinPoint.proceed();
            //return result;
            return joinPoint.proceed();  // cmd + opt + n inline
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("END = " + joinPoint.toString() + " " + timeMs + "ms");
        }
    }
}

package cc.lijingbo.springboot.annotation;

import java.lang.annotation.*;

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequestBodyAndHeader {
    boolean required() default true;
}

package com.ripstech.apiconnector2;

import org.junit.jupiter.api.extension.ExtendWith;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@ExtendWith(AssumePropertyFileCondition.class)
public @interface AssumePropertyFile {
}

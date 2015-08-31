package org.mamute.controllers;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import org.mamute.auth.rules.PermissionRules;

@Retention(RetentionPolicy.RUNTIME)
public @interface EnvironmentAccessLevel {

    PermissionRules value();
}

/*
 ***************************************************************************************
 *  Copyright (C) 2006 EsperTech, Inc. All rights reserved.                            *
 *  http://www.espertech.com/esper                                                     *
 *  http://www.espertech.com                                                           *
 *  ---------------------------------------------------------------------------------- *
 *  The software in this package is published under the terms of the GPL license       *
 *  a copy of which has been included with this distribution in the license.txt file.  *
 ***************************************************************************************
 */
package com.espertech.esper.common.internal.bytecodemodel.core;

import java.util.List;
import java.util.Set;

public class CodegenMethodFootprint {
    private final Class returnType;
    private final String returnTypeName;
    private final List<CodegenNamedParam> params;
    private final String optionalComment;

    public CodegenMethodFootprint(Class returnType, String returnTypeName, List<CodegenNamedParam> params, String optionalComment) {
        if (returnType == null && returnTypeName == null) {
            throw new IllegalArgumentException("Invalid null return type");
        }
        this.returnType = returnType;
        this.returnTypeName = returnTypeName;
        this.params = params;
        this.optionalComment = optionalComment;
    }

    public Class getReturnType() {
        return returnType;
    }

    public String getReturnTypeName() {
        return returnTypeName;
    }

    public List<CodegenNamedParam> getParams() {
        return params;
    }

    public String getOptionalComment() {
        return optionalComment;
    }

    public void mergeClasses(Set<Class> classes) {
        classes.add(returnType);
        for (CodegenNamedParam param : params) {
            param.mergeClasses(classes);
        }
    }
}

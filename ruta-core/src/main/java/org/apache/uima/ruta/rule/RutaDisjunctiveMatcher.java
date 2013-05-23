/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.apache.uima.ruta.rule;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.ruta.RutaBlock;
import org.apache.uima.ruta.RutaStream;
import org.apache.uima.ruta.expression.MatchReference;
import org.apache.uima.ruta.expression.RutaExpression;
import org.apache.uima.ruta.expression.string.StringExpression;

public class RutaDisjunctiveMatcher implements RutaMatcher {
  private final List<RutaExpression> expressions;

  private List<RutaMatcher> matchers;

  public RutaDisjunctiveMatcher(List<RutaExpression> expressions) {
    super();
    this.expressions = expressions;
    matchers = new ArrayList<RutaMatcher>();
    for (RutaExpression each : expressions) {
      if (each instanceof MatchReference) {
        matchers.add(new RutaTypeMatcher((MatchReference) each));
      } else if (each instanceof StringExpression) {
        matchers.add(new RutaLiteralMatcher((StringExpression) each));
      }
    }

  }

  public Collection<AnnotationFS> getMatchingAnnotations(RutaStream stream, RutaBlock parent) {
    Collection<AnnotationFS> result = new ArrayList<AnnotationFS>();
    for (RutaMatcher each : matchers) {
      result.addAll(each.getMatchingAnnotations(stream, parent));
    }
    return result;
  }

  public boolean match(AnnotationFS annotation, RutaStream stream, RutaBlock parent) {
    for (RutaMatcher each : matchers) {
      boolean match = each.match(annotation, stream, parent);
      if (match) {
        return true;
      }
    }
    return false;
  }

  public List<Type> getTypes(RutaBlock parent, RutaStream stream) {
    List<Type> result = new ArrayList<Type>();
    for (RutaMatcher each : matchers) {
      result.addAll(each.getTypes(parent, stream));
    }
    return result;
  }

  public RutaExpression getExpression() {
    return null;
  }

  public long estimateAnchors(RutaBlock parent, RutaStream stream) {
    long result = 0;
    for (RutaMatcher each : matchers) {
      result += each.estimateAnchors(parent, stream);
    }
    return result;
  }

  public Collection<AnnotationFS> getAnnotationsAfter(RutaRuleElement ruleElement,
          AnnotationFS annotation, RutaStream stream, RutaBlock parent) {
    Collection<AnnotationFS> result = new ArrayList<AnnotationFS>();
    for (RutaMatcher each : matchers) {
      result.addAll(each.getAnnotationsAfter(ruleElement, annotation, stream, parent));
    }
    return result;
  }

  public Collection<AnnotationFS> getAnnotationsBefore(RutaRuleElement ruleElement,
          AnnotationFS annotation, RutaStream stream, RutaBlock parent) {
    Collection<AnnotationFS> result = new ArrayList<AnnotationFS>();
    for (RutaMatcher each : matchers) {
      result.addAll(each.getAnnotationsBefore(ruleElement, annotation, stream, parent));
    }
    return result;
  }

  public List<RutaExpression> getExpressions() {
    return expressions;
  }

  public String toString() {
    return matchers.toString();
  }

}

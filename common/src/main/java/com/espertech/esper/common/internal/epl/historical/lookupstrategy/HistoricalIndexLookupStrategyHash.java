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
package com.espertech.esper.common.internal.epl.historical.lookupstrategy;

import com.espertech.esper.common.client.EventBean;
import com.espertech.esper.common.internal.epl.expression.core.ExprEvaluator;
import com.espertech.esper.common.internal.epl.expression.core.ExprEvaluatorContext;
import com.espertech.esper.common.internal.epl.index.base.EventTable;
import com.espertech.esper.common.internal.epl.index.hash.PropertyHashedEventTable;

import java.util.Iterator;
import java.util.Set;

public class HistoricalIndexLookupStrategyHash implements HistoricalIndexLookupStrategy {

    private EventBean[] eventsPerStream;
    private ExprEvaluator evaluator;
    private int lookupStream;

    public Iterator<EventBean> lookup(EventBean lookupEvent, EventTable[] index, ExprEvaluatorContext context) {
        if (index[0] instanceof PropertyHashedEventTable) {
            PropertyHashedEventTable idx = (PropertyHashedEventTable) index[0];
            eventsPerStream[lookupStream] = lookupEvent;
            Object key = evaluator.evaluate(eventsPerStream, true, context);

            Set<EventBean> events = idx.lookup(key);
            if (events != null) {
                return events.iterator();
            }
            return null;
        }
        return index[0].iterator();
    }

    public void setEvaluator(ExprEvaluator evaluator) {
        this.evaluator = evaluator;
    }

    public void setLookupStream(int lookupStream) {
        this.lookupStream = lookupStream;
        this.eventsPerStream = new EventBean[lookupStream + 1];
    }
}

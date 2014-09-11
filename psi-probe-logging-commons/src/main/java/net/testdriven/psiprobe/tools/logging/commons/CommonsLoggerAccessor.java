/*
 * Licensed under the GPL License.  You may not use this file except in
 * compliance with the License.  You may obtain a copy of the License at
 *
 *     http://www.gnu.org/licenses/old-licenses/gpl-2.0.html
 *
 * THIS PACKAGE IS PROVIDED "AS IS" AND WITHOUT ANY EXPRESS OR IMPLIED
 * WARRANTIES, INCLUDING, WITHOUT LIMITATION, THE IMPLIED WARRANTIES OF
 * MERCHANTIBILITY AND FITNESS FOR A PARTICULAR PURPOSE.
 */
package net.testdriven.psiprobe.tools.logging.commons;

import java.util.List;
import net.testdriven.psiprobe.tools.logging.DefaultAccessor;
import net.testdriven.psiprobe.tools.logging.LogDestination;

/**
 * 
 * @author Vlad Ilyushchenko
 * @author Mark Lewis
 */
public class CommonsLoggerAccessor extends DefaultAccessor {

    public List<LogDestination> getDestinations() {
        GetAllDestinationsVisitor v = new GetAllDestinationsVisitor();
        v.setTarget(getTarget());
        v.setApplication(getApplication());
        v.visit();
        return v.getDestinations();
    }

    public LogDestination getDestination(String logIndex) {
        GetSingleDestinationVisitor v = new GetSingleDestinationVisitor(logIndex);
        v.setTarget(getTarget());
        v.setApplication(getApplication());
        v.visit();
        return v.getDestination();
    }

}

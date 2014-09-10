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
package net.testdriven.psiprobe.controllers.apps;

import net.testdriven.psiprobe.controllers.ContextHandlerController;
import net.testdriven.psiprobe.tools.ApplicationUtils;
import net.testdriven.psiprobe.tools.SecurityUtils;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.catalina.Context;
import org.springframework.web.servlet.ModelAndView;

/**
 * Retrieves a list of context initialization parameters for a web application.
 * 
 * @author Andy Shapoval
 */
public class ListAppInitParamsController  extends ContextHandlerController {
    protected ModelAndView handleContext(String contextName, Context context,
                                         HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView mv = new ModelAndView(getViewName(), "appInitParams", ApplicationUtils.getApplicationInitParams(context));
        if (SecurityUtils.hasAttributeValueRole(getServletContext(), request)) {
            mv.addObject("allowedToViewValues", Boolean.TRUE);
        }

        return mv;
    }
}

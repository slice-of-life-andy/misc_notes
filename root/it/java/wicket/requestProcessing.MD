**Under the hood of the request processing**
1. Class Application and request processing
Beside configuring and initializing our application, the Application class is responsible for creating the internal entities used by Wicket to process a request. These entities are instances of the following classes: RequestCycle, Request, Response and Session.

2. Request and Response classes
The Request and Response classes are located in package org.apache.wicket.request and they provide an abstraction of the concrete request and response used by our web application.

Both classes are declared as abstract but if our application class inherits from WebApplication it will use their sub classes ServletWebRequest and ServletWebResponse, both of them located inside the package org.apache.wicket.protocol.http.servlet.ServletWebRequest and ServletWebResponse wrap respectively a HttpServletRequest and a HttpServletResponse object. If we need to access to these low-level objects we can call Request's method getContainerRequest() and Response's method getContainerResponse()

3. The “director” of request processing - RequestCycle

public class UserInfoHandlerInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		System.out.println("user info interceptor");
		response.setHeader("name","andyyu");
        response.setHeader("email","andyyu@ahamojo.com");
        
        
		return true;
	}
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		System.out.println("View Rendered !!");
	}
}



/**

configs in the *servlet.xml
	<mvc:interceptors>
		<mvc:interceptor>
			<mvc:mapping path="/**" />
			<bean class="com.ahamojo.dam.web.controllers.UserInfoHandlerInterceptor" />
		</mvc:interceptor>
	</mvc:interceptors>


**/

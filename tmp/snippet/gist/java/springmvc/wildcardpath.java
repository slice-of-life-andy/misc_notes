@RequestMapping(path={"mojobox/**"},method=RequestMethod.GET)
    public String mojobox(Model model,HttpServletRequest request){


        Repository repository = serviceAdapter.getRepository(owner);
        String path = "/";

		path = (String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
		String bestMatchPattern = (String) request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE);

		System.out.println(path);
		System.out.println(bestMatchPattern);
		
		AntPathMatcher apm = new AntPathMatcher();
		String finalPath = apm.extractPathWithinPattern(bestMatchPattern, path);
		if (StringUtils.isEmpty(finalPath)) {
			System.out.println("null path");
		}
        System.out.println(finalPath);

    @RequestMapping(path={"mojobox/{path}","mojobox"},method=RequestMethod.GET)
    public String mojobox(Model model,@PathVariable Map<String, String> pathVariables){

        String owner = "james";

        Repository repository = serviceAdapter.getRepository(owner);
        String path = "/";
        if (pathVariables.containsKey("path")) {
			path = pathVariables.get("path");
		}

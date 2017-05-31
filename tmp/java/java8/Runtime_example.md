----java 
 public static class ImHelper {
	    	
	    	static {
	    		Path path = Paths.get(imageTmpPath);
	    		File root = path.toFile();
	    		if (java.nio.file.Files.exists(path)) {
					for (File file: root.listFiles()) {
						file.delete();
					}
				} else {
					try {
						java.nio.file.Files.createDirectories(path);
					} catch (IOException e) {
						LOGGER.error(" create image tmp dir exception",e);
					}
				}
	    		
//	    		ProcessStarter.setGlobalSearchPath(convertPath);
	    	}
	    	
	    	public static File resize(File originalFile, Long id, String extension, ImageSize imageSize) {
	    		String resizeImagePath = imageTmpPath + id + "_" + imageSize.toString() + "." + extension;
	    		try {
					
					String[] commandArray = new String[]{convertPath,
														originalFile.getCanonicalPath(),
														"-resize",
														imageSize.width+"x"+imageSize.width,
														resizeImagePath};
					Process process = Runtime.getRuntime().exec(commandArray);
					InputStream processInputStream = process.getInputStream();
					InputStream processErrorStream = process.getErrorStream();
					
					StreamsPrinter outputStreams = new StreamsPrinter(processInputStream, "OUTPUT");
					StreamsPrinter errorStreams = new StreamsPrinter(processErrorStream, "ERROR");

					outputStreams.start();
					errorStreams.start();
					
					int exitValue = process.waitFor();
					LOGGER.info("image resize process exist value :{}",exitValue);
					
					outputStreams.join();
					errorStreams.join();
				} catch (IOException | InterruptedException e) {
					LOGGER.error("resize image error ",e);
					return null;
				}
	    		
	        	return new File(resizeImagePath);
	    	}

	    	
	    	/*public static ByteArrayOutputStream resize(InputStream inputStream, String extension, Integer width, Integer height) {
	    		
	        	// create the operation, add images and operators/options
	        	IMOperation op = new IMOperation();
	        	op.addImage("-");
	        	op.resize(width,height);
	        	op.addImage(extension + ":-");
	        	
	        	ByteArrayOutputStream byteArrayOutputStream  = new ByteArrayOutputStream();
	        	
	        	Pipe pipeIn  = new Pipe(inputStream,null);
	        	Pipe pipeOut = new Pipe(null,byteArrayOutputStream);
	        	
	        	// set up command
	        	ConvertCmd convert = new ConvertCmd();
	        	convert.setInputProvider(pipeIn);
	        	convert.setOutputConsumer(pipeOut);
	        	try {
					convert.run(op);
				} catch (IOException | InterruptedException | IM4JavaException e) {
					LOGGER.error("resize image exception.", e);
				}
	        	
	        	return byteArrayOutputStream;
	    	}*/
	    	
	    }

	 
	public static class StreamsPrinter extends Thread {
		private InputStream inputStream;
		private String type;
		public StreamsPrinter(InputStream inputStream, String type) {
			this.inputStream = inputStream;
			this.type = type;
		}
		
		@Override
		public void run() {
			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			try {
				// TODO new copy methods in java 9?
				IOUtils.copy(inputStream, byteArrayOutputStream);
				LOGGER.info("stream type: {},stream info:{}", this.type, byteArrayOutputStream.toString());
				inputStream.close();
				byteArrayOutputStream.close();
			} catch (IOException e) {
				LOGGER.error("copy image process stream error",e);
			}
		}
		
	}
----

package mx.conacyt.board;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import io.vertx.config.ConfigRetriever;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.web.Cookie;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.CookieHandler;
import io.vertx.ext.web.handler.CorsHandler;
import io.vertx.ext.web.handler.StaticHandler;
import mx.conacyt.board.resources.RequestResources;
import mx.conacyt.board.resources.UsersResources;

public class ApiVerticle extends AbstractVerticle {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ApiVerticle.class);
	
    public static void main( String[] args ) {
    
    			
    	Vertx vertx = Vertx.vertx();
    	
       
        ConfigRetriever configRetriever = ConfigRetriever.create(vertx);

        configRetriever.getConfig(config -> {
        	
       	if (config.succeeded()) {
        		
        		JsonObject configJson = config.result();
        		
        		System.out.println(configJson.encodePrettily());
         		
        		DeploymentOptions options = new DeploymentOptions().setConfig(configJson);
        		
            	vertx.deployVerticle(new ApiVerticle(), options);

       		}
       	
       	
        });
        
    	
    }
    
	@Override
	public void start() {
		LOGGER.info("Verticle FormalAPIVerticle Started");
		
		

	    Set<String> allowedHeaders = new HashSet<>();
	    allowedHeaders.add("x-requested-with");
	    allowedHeaders.add("Access-Control-Allow-Origin");
	    allowedHeaders.add("origin");
	    allowedHeaders.add("Content-Type");
	    allowedHeaders.add("accept");
	    allowedHeaders.add("X-PINGARUNER");

	    Set<HttpMethod> allowedMethods = new HashSet<>();
	    allowedMethods.add(HttpMethod.GET);
	    allowedMethods.add(HttpMethod.POST);
	    allowedMethods.add(HttpMethod.OPTIONS);
	    /*
	     * these methods aren't necessary for this sample, 
	     * but you may need them for your projects
	     */
	    allowedMethods.add(HttpMethod.DELETE);
	    allowedMethods.add(HttpMethod.PATCH);
	    allowedMethods.add(HttpMethod.PUT);
	    
	    
		Router router = Router.router(vertx);
		
		router.route().handler(CorsHandler.create("*").allowedHeaders(allowedHeaders).allowedMethods(allowedMethods));
		//router.route().handler(CookieHandler.create());
		
		// Create ProductResource object
		UsersResources userResources = new UsersResources();
		RequestResources requestResources = new RequestResources();
		
		// Map subrouter for APIS
		router.mountSubRouter("/admin-api/", userResources.getAPISubRouter(vertx));
		router.mountSubRouter("/admin-api/", requestResources.getAPISubRouter(vertx));
		
		
		router.get("/yo.html").handler(routingContext -> {
			
			Cookie nameCookie = routingContext.getCookie("name");
			
			ClassLoader classLoader = getClass().getClassLoader();
			File file = new File(classLoader.getResource("webroot/yo.html").getFile());

			String mappedHTML = "";

			try {
				StringBuilder result = new StringBuilder("");

				Scanner scanner = new Scanner(file);

				while (scanner.hasNextLine()) {
					String line = scanner.nextLine();
					result.append(line).append("\n");
				}

				scanner.close();

				mappedHTML = result.toString();
				
				
				String name = "Unknown";
						
				if (nameCookie != null) {
					name = nameCookie.getValue();
				}
				else {
					nameCookie = Cookie.cookie("name", "Luis Arturo");
					nameCookie.setPath("/");
					nameCookie.setMaxAge(365 * 24 * 60 * 60); // 1 year in seconds
					
					routingContext.addCookie(nameCookie);
				}
				
				mappedHTML = replaceAllTokens(mappedHTML, "{name}", name);

			} catch (IOException e) {
				e.printStackTrace();
			}

			routingContext.response().putHeader("content-type", "text/html").end(mappedHTML);
			
		});
		
		// Default if no routes are matched
		router.route().handler(StaticHandler.create().setCachingEnabled(false));

		vertx.createHttpServer().requestHandler(router::accept).listen(config().getInteger("http.port"), asyncResult -> {
			
			if (asyncResult.succeeded()) { 
				LOGGER.info("HTTP server running on port " + config().getInteger("http.port"));
			}
			else {
				LOGGER.error("Could not start a HTTP server", asyncResult.cause());
			}
			
			
		});

		

	}	
	
	public String replaceAllTokens(String input, String token, String newValue) {

		String output = input;

		while (output.indexOf(token) != -1) {

			output = output.replace(token, newValue);

		}

		return output;

	}

    
	@Override
	public void stop() {
		LOGGER.info("Verticle ApiVerticle Stopped");
	}

	
}

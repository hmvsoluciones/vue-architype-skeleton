package mx.conacyt.board.resources;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpHeaders;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;
import mx.conacyt.board.model.Menu;
import mx.conacyt.board.model.MenuDTO;
import mx.conacyt.board.model.Rol;
import mx.conacyt.board.model.RolDTO;
import mx.conacyt.board.model.SubMenu;
import mx.conacyt.board.model.SubMenuDTO;
import mx.conacyt.board.model.User;
import mx.conacyt.board.model.UserDTO;
import mx.conacyt.persistence.AuthorizationPersistence;
import mx.conacyt.persistence.MenuPersistence;
import mx.conacyt.persistence.SubMenuPersistence;
import mx.conacyt.persistence.UserPersistence;

public class RequestResources {

	private static final Logger LOGGER = LoggerFactory.getLogger(RequestResources.class);
	
	UserPersistence userPersistence;
	AuthorizationPersistence authPersistence;
	MenuPersistence menuPersistence;
	SubMenuPersistence subMenuPersistence;
	
	public RequestResources() {
		userPersistence = new UserPersistence();
		authPersistence = new AuthorizationPersistence();
		menuPersistence = new MenuPersistence();
		subMenuPersistence = new SubMenuPersistence();
	}

	public Router getAPISubRouter(Vertx vertx) {

		Router apiSubRouter = Router.router(vertx);

		// API routing, default proccessor
		apiSubRouter.route("/*").handler(this::defaultProcessorForAllAPI);

		apiSubRouter.route("/v1/solicitudes*").handler(BodyHandler.create());
		apiSubRouter.post("/v1/solicitudes/auth").handler(this::authUser);
		apiSubRouter.get("/v1/solicitudes").handler(this::getAllProducts);
		apiSubRouter.get("/v1/solicitudes/:id").handler(this::getProductById);
		apiSubRouter.post("/v1/solicitudes").handler(this::addProduct);
		apiSubRouter.put("/v1/solicitudes/:id").handler(this::updateProductById);
		apiSubRouter.delete("/v1/solicitudes/:id").handler(this::deleteProductById);

		return apiSubRouter;
	}

	// Interceptor HTTP GET, POST, PUT and DELETE
	public void defaultProcessorForAllAPI(RoutingContext routingContext) {

		String authToken = routingContext.request().getHeader("AuthToken");

		if (authToken == null || !authToken.equals("123")) {
			LOGGER.info("Failed basic auth check");

			routingContext.response().setStatusCode(401).putHeader(HttpHeaders.CONTENT_TYPE, "application/json")
					.end(Json.encodePrettily(new JsonObject().put("error", "Not Authorized to use these API's")));
		} else {
			LOGGER.info("Passed basic auth check");

			// Allowing CORS - Cross Domain API calls
			routingContext.response().putHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*");
			routingContext.response().putHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS, "GET,POST,PUT,DELETE");

			// Call next matching route
			routingContext.next();
		}

	}

	public void authUser(RoutingContext routingContext) {

		JsonObject jsonBody = routingContext.getBodyAsJson();

		System.out.println(jsonBody);

		String principal = jsonBody.getString("principal");
		String credencial = jsonBody.getString("credencial");

		User user = new User(principal, credencial);		
		
		UserDTO response =new UserDTO();
		try {
			User userResponse = authPersistence.login(user);
						
			if(userResponse != null) {
				
				List<Rol> roles = authPersistence.getRolesByUser(userResponse.getIdUsuario());
				List<RolDTO> rolesDTO = new ArrayList<RolDTO>();
				
				for (Rol rol : roles) {
					RolDTO rolDTO = new RolDTO();
					rolDTO.setIdRol(rol.getIdRol());
					rolDTO.setNombre(rol.getNombre());
					rolDTO.setUrl(rol.getUrl());
					rolDTO.setImg(rol.getImg());
					
					List<Menu> menus = menuPersistence.getMenuByRole(rolDTO.getIdRol());
					List<MenuDTO> menusDTO = new ArrayList<MenuDTO>();
					
					for(Menu menu : menus) {
						MenuDTO menuDTO= new MenuDTO();
						
						menuDTO.setIdMenu(menu.getIdMenu());
						menuDTO.setIcono(menu.getIcono());
						menuDTO.setNombre(menu.getNombre());
						
						List<SubMenu> subMenus = subMenuPersistence.getSubByMenu(menu.getIdMenu());
						List<SubMenuDTO> subMenusDTO =new ArrayList<SubMenuDTO>();
						for(SubMenu subMenu: subMenus) {
							SubMenuDTO subMenuDTO = new SubMenuDTO();
							subMenuDTO.setIdSubMenu(subMenu.getIdSubMenu());
							subMenuDTO.setNombre(subMenu.getNombre());
							subMenuDTO.setRecurso(subMenu.getRecurso());
							
							subMenusDTO.add(subMenuDTO);
						}
						menuDTO.setSubMenus(subMenusDTO);
						
						menusDTO.add(menuDTO);
					}
					
					rolDTO.setMenus(menusDTO);
					
					rolesDTO.add(rolDTO);
				}
				response.setIdUsuario(userResponse.getIdUsuario());
				response.setNombre(userResponse.getNombre());
				response.setApellidoPaterno(userResponse.getApellidoPaterno());
				response.setApellidoMaterno(userResponse.getApellidoMaterno());
				response.setUsuario(userResponse.getUsuario());
				
				response.setRoles(rolesDTO);
				
				routingContext.response().setStatusCode(200).putHeader("content-type", "application/json")
				.end(Json.encodePrettily(response));
			} else {
				routingContext.response().setStatusCode(400).putHeader("content-type", "application/json").end();
			}
		} catch (SQLException e) {
			
			routingContext.response().setStatusCode(500).putHeader("content-type", "application/json").end();
		}
		

	}
	// Get all products as array of products
	public void getAllProducts(RoutingContext routingContext) {

		JsonObject responseJson = new JsonObject();

		User firstItem = new User();
		User secondItem = new User();

		List<User> products = new ArrayList<User>();

		products.add(firstItem);
		products.add(secondItem);

		responseJson.put("products", products);

		routingContext.response().setStatusCode(200).putHeader("content-type", "application/json")
				.end(Json.encodePrettily(responseJson));

	}

	// Get one products that matches the input id and return as single json object
	public void getProductById(RoutingContext routingContext) {

		final String productId = routingContext.request().getParam("id");

		String number = "123";

		User firstItem = new User();

		routingContext.response().setStatusCode(200).putHeader("content-type", "application/json")
				.end(Json.encodePrettily(firstItem));

	}

	// Insert one item passed in from the http post body return what was added with
	// unique id from the insert
	public void addProduct(RoutingContext routingContext) {

		JsonObject jsonBody = routingContext.getBodyAsJson();

		System.out.println(jsonBody);

		String number = jsonBody.getString("number");
		String description = jsonBody.getString("description");

		User newItem = new User();

		// Add into database and get unique id
		newItem.setIdUsuario(556);

		routingContext.response().setStatusCode(201).putHeader("content-type", "application/json")
				.end(Json.encodePrettily(newItem));

	}

	// Update the item based on the url product id and return update product info
	public void updateProductById(RoutingContext routingContext) {

		final String productId = routingContext.request().getParam("id");

		JsonObject jsonBody = routingContext.getBodyAsJson();

		String number = jsonBody.getString("number");
		String description = jsonBody.getString("description");

		User updatedItem = new User();

		routingContext.response().setStatusCode(200).putHeader("content-type", "application/json")
				.end(Json.encodePrettily(updatedItem));

	}

	// Delete item and return 200 on success or 400 on fail
	public void deleteProductById(RoutingContext routingContext) {

		final String productId = routingContext.request().getParam("id");

		routingContext.response().setStatusCode(200).putHeader("content-type", "application/json").end();

	}

}

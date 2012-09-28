package componentsbrowser;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import juzu.Path;
import juzu.Resource;
import juzu.Response;
import juzu.SessionScoped;
import juzu.View;
import juzu.io.Stream;
import juzu.plugin.ajax.Ajax;

import componentsbrowser.services.ComponentsService;

/**
 * Main Controller
 * @author Thomas Delhoménie
 *
 */
@SessionScoped	
public class Controller {

	@Inject
	@Path("index.gtmpl")
	componentsbrowser.templates.index index;

	@Inject
	@Path("list.gtmpl")
	componentsbrowser.templates.list list;
	
	@Inject
	@Path("component.gtmpl")
	componentsbrowser.templates.component componentTemplate;

	@Inject
	private ComponentsService componentsService;	
	
	public Controller() {
	}

	@View
	public void index() {
		index.render();
	}
	
	@Ajax
	@Resource
	public void list() {
		list.with().set("components", componentsService.getComponents()).render();
	}
	
	@Ajax
	@Resource
	public void show(String key) {

		Map<String, Object> parameters = new HashMap<String, Object>();
		
		parameters.put("component", componentsService.getComponent(key));
		
		componentTemplate.with().set("component", componentsService.getComponent(key)).render();
	}
	
	@Resource
	public Response.Content<Stream.Binary> exportAll() {
		return Response.ok(new ByteArrayInputStream(componentsService.getAllComponentsConfigurationInXML().getBytes()))
				.withMimeType("application/xml")
				.withHeader("Content-Disposition", "attachment; filename=configuration.xml");
	}
	
	@Resource
	public Response.Content<Stream.Binary> export(String key) {
		Response.Content<Stream.Binary> response = null;
		
		try {
			String componentConfigurationInXML = componentsService.getComponentConfigurationInXML(key);
			response = Response.ok(new ByteArrayInputStream(componentConfigurationInXML.getBytes()))
					.withMimeType("application/xml")
					.withHeader("Content-Disposition", "attachment; filename=configuration.xml"); 
		} catch (Exception e) {
			response = Response.content(500, (InputStream)null);
		}
		return response;
	}
}

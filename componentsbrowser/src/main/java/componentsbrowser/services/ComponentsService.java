package componentsbrowser.services;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.exoplatform.container.PortalContainer;
import org.exoplatform.container.RootContainer;
import org.exoplatform.container.configuration.ConfigurationManager;
import org.exoplatform.container.xml.Component;
import org.exoplatform.container.xml.Configuration;
import org.jibx.runtime.BindingDirectory;
import org.jibx.runtime.IBindingFactory;
import org.jibx.runtime.IMarshallingContext;
import org.jibx.runtime.JiBXException;
import org.picocontainer.ComponentAdapter;

public class ComponentsService {

	private ConfigurationManager configurationManager;

	public ComponentsService() {
		RootContainer container = RootContainer.getInstance();
		PortalContainer portalContainer = container.getPortalContainer("portal");

		configurationManager = (ConfigurationManager) portalContainer.getComponentInstanceOfType(ConfigurationManager.class);
	}

	public List<ComponentAdapter> getComponentsAdapters() {
		List adapters = new ArrayList();

		RootContainer container = RootContainer.getInstance();
		PortalContainer portalContainer = container.getPortalContainer("portal");

		Collection<ComponentAdapter> adaptersCollection = portalContainer.getComponentAdapters();
		adapters.addAll(adaptersCollection);

		return adapters;
	}

	public List<Component> getComponents() {
		Configuration configuration = configurationManager.getConfiguration();
		return new ArrayList<Component>(configuration.getComponents());
	}

	public Component getComponent(String key) {
		return configurationManager.getComponent(key);
	}

	public String getComponentConfigurationInXML(String key) throws Exception {
		Component component = getComponent(key);

		Configuration configuration = new Configuration();
		configuration.addComponent(component);	

		return new String(toXML(configuration));
	}

	public String getAllComponentsConfigurationInXML() {
		return configurationManager.getConfiguration().toXML();
	}

	/**
	 * Convert an object to XML.
	 * Use the JiBX binding already defined by exo-kernel.
	 * @param obj Object to convert
	 * @return XML configuration byte array
	 * @throws Exception
	 */
	public byte[] toXML(Object obj) throws Exception {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
	    try {
	      IBindingFactory bfact = BindingDirectory.getFactory(obj.getClass());
	      IMarshallingContext mctx = bfact.createMarshallingContext();
	      mctx.setIndent(2);
	      mctx.marshalDocument(obj, "UTF-8", null, out);
	      return out.toByteArray();
	    } catch (JiBXException jie) {
	      throw new Exception("Error while converting " + obj.getClass() + " to XML object", jie);
	    }
	}
}

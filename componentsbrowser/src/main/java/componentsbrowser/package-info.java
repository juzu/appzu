@Less(value = "bootstrap.less", minify = true)
@Assets(
	scripts = {
		@Script(
			id = "jquery",  
			src = "public/scripts/jquery-1.7.1.min.js"),
        @Script(
            id = "jquery-datatables",  
            src = "public/scripts/jquery.dataTables.min.js",
            depends = "jquery"),
        @Script(
            id = "bootstrap-datatable",  
            src = "public/scripts/bootstrap-datatable.js",
            depends = "jquery-datatables"),
        @Script(
            id = "slider",  
            src = "public/scripts/slider.js",
            depends = "jquery"),
        @Script(
            id = "componentsbrowser",  
            src = "public/scripts/componentsbrowser.js",
            depends = "slider")
	},
	stylesheets = {
		@Stylesheet(
			src = "bootstrap.css",
			location = AssetLocation.CLASSPATH)
  	}
)
@Application(defaultController = componentsbrowser.Controller.class)
@Portlet
package componentsbrowser;

import juzu.Application;
import juzu.plugin.portlet.Portlet;
import juzu.asset.AssetLocation;
import juzu.plugin.asset.Assets;
import juzu.plugin.asset.Script;
import juzu.plugin.asset.Stylesheet;
import juzu.plugin.less.Less;
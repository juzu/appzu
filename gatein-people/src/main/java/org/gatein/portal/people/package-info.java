@Application(defaultController = Controller.class)
@Portlet(name = "GateInPeoplePortlet")
@Bindings(
   @Binding(value = org.exoplatform.services.organization.OrganizationService.class, implementation=GateInMetaProvider.class)
)
@Assets(
	scripts = {
		@Script(id = "jquery", src = "js/jquery-1.7.1.min.js"),
		@Script(src = "js/bootstrap-modal.js", depends = "jquery"),
		@Script(src = "js/people.js", depends = "juzu.ajax")
	},
	stylesheets = {
		@Stylesheet(src = "css/people.css")
	}
)
@Less("css/people.less")
package org.gatein.portal.people;

import juzu.Application;
import juzu.plugin.binding.Binding;
import juzu.plugin.binding.Bindings;
import juzu.plugin.portlet.Portlet;
import juzu.plugin.asset.Assets;
import juzu.plugin.asset.Script;
import juzu.plugin.asset.Stylesheet;
import juzu.plugin.less.Less;

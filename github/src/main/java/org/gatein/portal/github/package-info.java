@Application(defaultController = org.gatein.portal.github.controllers.GithubApplication.class)
@Portlet
@Assets(
   stylesheets = {@Stylesheet(src = "public/stylesheets/common.css")}
) package org.gatein.portal.github;

import juzu.plugin.asset.Assets;
import juzu.plugin.asset.Stylesheet;
import juzu.plugin.portlet.Portlet;
import juzu.Application;
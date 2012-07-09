@Application(defaultController = LiveMatches.class)
@Portlet
@Assets(
   scripts = {
      @Script(id="jquery", src="public/javascripts/jquery-1.7.1.min.js", location = AssetLocation.CLASSPATH)
   }
)
package org.gatein.portal.livescore;

import juzu.Application;
import juzu.asset.AssetLocation;
import juzu.plugin.asset.Assets;
import juzu.plugin.asset.Script;
import juzu.plugin.portlet.Portlet;
import org.gatein.portal.livescore.controllers.LiveMatches;
@Assets(
        scripts = {
                @Script(src = "js/jquery-1.7.1.min.js")
        },
        stylesheets = {
                @Stylesheet(src = "css/simple-rss.css")
        }
)

@Application
@Portlet package org.gatein.portal.rss;

import juzu.Application;
import juzu.plugin.asset.Assets;
import juzu.plugin.asset.Script;
import juzu.plugin.asset.Stylesheet;
import juzu.plugin.portlet.Portlet;

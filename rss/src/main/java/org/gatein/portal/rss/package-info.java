@Assets(
        scripts = {
                @Script(id = "jquery", src = "js/jquery-1.7.1.min.js"),
                @Script(id = "jquery-utils", src = "js/jquery-juzu-utils-0.1.0.js", depends = "jquery")
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

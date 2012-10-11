/*
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.gatein.portal.people.test.util;

import java.io.StringReader;

import org.cyberneko.html.parsers.DOMParser;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

/**
 * @author <a href="mailto:haithanh0809@gmail.com">Nguyen Thanh Hai</a>
 * @version $Id$
 *
 */
public class HtmlParser
{

	private final DOMParser nonWellFormParser;
	
	public HtmlParser() throws Exception 
	{
		nonWellFormParser = new DOMParser();
		nonWellFormParser.setFeature("http://cyberneko.org/html/features/augmentations", true);
		nonWellFormParser.setProperty("http://cyberneko.org/html/properties/names/elems", "lower");
	}
	
	public Document parseNonWellForm(String html) throws Exception
	{
		nonWellFormParser.parse(new InputSource(new StringReader(html)));
		return nonWellFormParser.getDocument();
	}
}

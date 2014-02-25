package edu.umich.magicalbus.model;

import java.util.Vector;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;

public class Route {

	private String name;
	private int id;
	private int topOfLoop;
	private String busRouteColor;
	private Vector<Stop> stops;

	public Route(Node xmlRouteNode) {

		Element routeElement = (Element) xmlRouteNode;
		
		name = routeElement.getElementsByTagName("name").item(0).getTextContent();
		id = Integer.parseInt(routeElement.getElementsByTagName("id").item(0).getTextContent());
		topOfLoop = Integer.parseInt(routeElement.getElementsByTagName("topofloop").item(0).getTextContent());
		busRouteColor = routeElement.getElementsByTagName("busroutecolor").item(0).getTextContent();

		NodeList stopList = routeElement.getElementsByTagName("stop");

		stops = new Vector<Stop>();
		for (int i = 0; i < stopList.getLength(); i++) {
			Node stopNode = stopList.item(i);
			stops.add(new Stop(stopNode));
		}

	}

	public String getName() {
		return name;
	}

	public int getId() {
		return id;
	}

	public int getTopOfLoop() {
		return topOfLoop;
	}

	public String getBusRouteColor() {
		return busRouteColor;
	}

	public Vector<Stop> getStops() {
		return stops;
	}

}

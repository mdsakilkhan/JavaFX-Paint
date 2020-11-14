package application;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;

public class ListA5Shape {
	
	@XmlElement
	ArrayList<A5Shape> shapelist = new ArrayList<>();
	
}

package org.map.view;

import javafx.beans.property.DoubleProperty;
import javafx.scene.Node;

public interface AbstractPageView {

	public Node showView();
	
	public DoubleProperty opacityProperty();
}

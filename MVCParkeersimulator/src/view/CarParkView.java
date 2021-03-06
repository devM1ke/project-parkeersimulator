package view;

import java.awt.*;
import javax.swing.*;

import model.Car;
import model.Location;
import model.Model;
import model.LocationManager;

public class CarParkView extends AbstractView {  
	 private Dimension size;
	 private Model model;
	 private Image carParkImage; 
	 
	 public CarParkView(Model model) {
		super(model);
		this.model = model;
		
		size = new Dimension(0,0);
		setSize(800,400);
		setVisible(true);
	 }
/**
 * Overridden. Tell the GUI manager how big we would like to be.
 */
public Dimension getPreferredSize() {
    return new Dimension(800, 500);
}

/**
 * Overridden. The car park view component needs to be redisplayed. Copy the
 * internal image to screen.
 */
@Override
protected void paintComponent(Graphics g) {
	super.paintComponent(g);
    if (carParkImage == null) {
        return;
    }

    Dimension currentSize = getSize();
    if (size.equals(currentSize)) {
        g.drawImage(carParkImage, 0, 0, null);
    }
    else {
        // Rescaled the previous image.
        g.drawImage(carParkImage, 0, 0, currentSize.width, currentSize.height, null);
    }
}

public void updateView() {
    // Create a new car park image if the size has changed.
    if (!size.equals(getSize())) {
        size = getSize();
        carParkImage = createImage(size.width, size.height);
    }
    
    carParkImage = createImage(size.width, size.height);
    
    if(carParkImage != null) {
    Graphics graphics = carParkImage.getGraphics();
    for(int floor = 0; floor < model.getNumberOfFloors(); floor++) {
        for(int row = 0; row < model.getNumberOfRows(); row++) {
            for(int place = 0; place < model.getNumberOfPlaces(); place++) {
                Location location = getModel().getLocationManager().getLocation(floor, row, place);
                Car car = model.getCarAt(location);
                Color color = car == null ? Color.white : car.getColor();
                if(location.getType() == 1) {
                	if(color == Color.white) {
                		color = Color.cyan;
                	}
                }
                if(location.getType() == 2) {
                	if(color == Color.white) {
                		color = Color.yellow;
                	}
                }
                drawPlace(graphics, location, color);
            }
        }
    }
    }
    repaint();
}



private void drawPlace(Graphics graphics, Location location, Color color) {
	/*int numberOfRows = model.getNumberOfFloors();
	int spaceHeight = 10;
	int spaceWidth = 10;
	int spaceBetweenSpacesTB = 1;
	int spaceBetweenSpacesLR = 1;*/
	
    graphics.setColor(color);
    graphics.fillRect(
            location.getFloor() * 260 + (1 + (int)Math.floor(location.getRow() * 0.5)) * 75 + (location.getRow() % 2) * 20, 60 + location.getPlace() * 10, 20 - 1, 10 - 1); // TODO use dynamic size or constants
    		//location.getFloor() * (numberOfRows * 50) + location.getRow() * 50, 60 + location.getPlace() * 10, 20 - 1, 10 - 1);
}
}


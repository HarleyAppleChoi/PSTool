
/*
 * I declare that this coursework is my own work.
 * Please run Main.java
 * Name: Hau Yi Choi
 * Student number:968748
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

public class UIcontroller1 {

	// Image image = new Image(new FileInputStream("raytrace.jpg"));

	// histogram array
	private int[] redHis = new int[256];
	private int[] greenHis = new int[256];
	private int[] blueHis = new int[256];
	private int[] brightHis = new int[256];
	@FXML
	private Button crossB;

	@FXML
	private Button greyB;

	@FXML
	private Label cor1;

	@FXML
	private Label cor2;

	// the image display
	Image image;
	WritableImage wim;

	@FXML
	private ImageView imageView;

	@FXML
	private Button invertB;

	@FXML
	private Button nearestNeighbourB;

	@FXML
	private Button gammaB;

	@FXML
	private Button ContrastB;

	@FXML
	private Button histogramB;

	@FXML
	private Button resetB;

	@FXML
	private TextField gammaText;

	@FXML
	private TextField nearestNeighbourWidth;

	@FXML
	private TextField nearestNeighbourHeight;

	@FXML
	private Canvas contrastLine;

	// for the histogram
	@FXML
	private BarChart<String, Number> redHistogram;

	@FXML
	private CategoryAxis redX;

	@FXML
	private NumberAxis redY;

	@FXML
	private BarChart<String, Number> blueHistogram;

	@FXML
	private CategoryAxis redX1;

	@FXML
	private NumberAxis redY1;

	@FXML
	private BarChart<String, Number> greenHistogram;

	@FXML
	private CategoryAxis redX2;

	@FXML
	private NumberAxis redY2;

	@FXML
	private BarChart<String, Number> brightHistogram;

	@FXML
	private CategoryAxis redX3;

	@FXML
	private NumberAxis redY3;

	// coordinate of the cross contrast contrast gui
	@FXML
	private Label cor1y;

	@FXML
	private Label cor2y;

	// equalization button
	@FXML
	private Button EqualB;

	// thereshold button
	@FXML
	private Button theresholdB;

	@FXML
	private Button lowPassB;
	
	@FXML
	private Button bilinearB;

	@FXML
	private Button prewittB;

	@FXML
	private Button zeroCrossingB;

	@FXML
	private Button ditherB;

	@FXML
	private Button saveB;

	@FXML
	private Button floydB;

	@FXML
	private Button orderedB;

	@FXML
	private Button realOrderedB;

	@FXML
	private Button nonLinearB;

	// Initialized coordinate for the canvas
	public double x2 = 85;
	public double y2 = 170;
	public double x1 = 170;
	public double y1 = 85;

	// real coordinate output
	double py1;
	double py2;

	// contrast starching look up table
	public double[] contrast = new double[256];

	@FXML
	void nonLinearA(ActionEvent event) {
		image = nonLinearFilter(imageView.getImage());
		// Update the GUI so the new image is displayed
		imageView.setImage(image);
	}

	@FXML
	void nearestNeighbourA(ActionEvent event) {
		image = nearestNeighbor(imageView.getImage(), Integer.parseInt(nearestNeighbourHeight.getText()),
				Integer.parseInt(nearestNeighbourWidth.getText()));
		// Update the GUI so the new image is displayed
		imageView.setImage(image);
	}
	
	@FXML
	void bilinearA(ActionEvent event) {
		image = bilinear(imageView.getImage(), Integer.parseInt(nearestNeighbourHeight.getText()),
				Integer.parseInt(nearestNeighbourWidth.getText()));
		// Update the GUI so the new image is displayed
		imageView.setImage(image);
	}

	@FXML
	void prewittA(ActionEvent event) {
		image = prewitt(imageView.getImage());
		// Update the GUI so the new image is displayed
		imageView.setImage(image);
	}

	@FXML
	void orderedA(ActionEvent event) {
		image = pattern(imageView.getImage());
		// Update the GUI so the new image is displayed
		imageView.setImage(image);
	}

	@FXML
	void readlOrderedA(ActionEvent event) {
		image = ordered(imageView.getImage());
		// Update the GUI so the new image is displayed
		imageView.setImage(image);
	}

	@FXML
	void floydA(ActionEvent event) {
		image = floyd(imageView.getImage());
		// Update the GUI so the new image is displayed
		imageView.setImage(image);
	}

	@FXML
	void lowPassA(ActionEvent event) {
		image = lowPassFilter(imageView.getImage());
		// Update the GUI so the new image is displayed
		imageView.setImage(image);
	}

	@FXML
	void zeroCrossingA(ActionEvent event) {
		image = zeroCrossing(imageView.getImage());
		// Update the GUI so the new image is displayed
		imageView.setImage(image);
	}

	/**
	 * It is the action when pressed grey button, and a grey image is shown.
	 * 
	 * @param event
	 *            button is pressed
	 */
	@FXML
	void greyA(ActionEvent event) {
		image = greyImage(imageView.getImage());
		// Update the GUI so the new image is displayed
		imageView.setImage(image);
	}

	@FXML
	void theresholdA(ActionEvent event) {
		image = thereshold(imageView.getImage());
		// Update the GUI so the new image is displayed
		imageView.setImage(image);
	}

	/**
	 * This is a spare text action
	 * 
	 * @param event
	 *            button is pressed
	 */
	@FXML
	void gammaTextF(ActionEvent event) {

	}

	/**
	 * When Equalization button is pressed and it display a equalised image
	 * 
	 * @param event
	 *            when button is pressed
	 */
	@FXML
	void EqualiA(ActionEvent event) {
		image = equalization(image);
		// Update the GUI so the new image is displayed
		imageView.setImage(image);
	}

	/**
	 * This button press and display processed cross corinated image
	 * 
	 * @param event
	 *            when button pressed
	 */
	@FXML
	void crossA(ActionEvent event) {
		image = filter(imageView.getImage());
		// Update the GUI so the new image is displayed
		imageView.setImage(image);
	}

	@FXML
	void ditherA(ActionEvent event) {
		image = dithering(imageView.getImage());
		// Update the GUI so the new image is displayed
		imageView.setImage(image);
	}

	/**
	 * This is the action when constrast straching button press - read the
	 * coordinate from canvas.
	 * 
	 * @param event
	 *            when button pressed
	 */
	@FXML
	void ConstrastA(ActionEvent event) {
		drawLine();
	}

	/**
	 * Reset the image action
	 * 
	 * @param event
	 *            when button is pressed
	 */
	@FXML
	void resetA(ActionEvent event) {
		reset();

	}

	/**
	 * Image reset to the initial state as well as constract straching panal
	 */
	void reset() {
		try {
			image = new Image(new FileInputStream("raytrace.jpg"));
			imageView.setImage(image);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		x2 = 85;
		y2 = 170;
		x1 = 170;
		y1 = 85;
		drawStroke();
	}

	/**
	 * It process the image with gamma correction when the button is pressed.
	 * 
	 * @param event
	 *            when the button is pressed
	 */
	@FXML
	void gammaA(ActionEvent event) {
		System.out.println("gamma");
		// At this point, "image" will be the original image
		// imageView is the graphical representation of an image
		// imageView.getImage() is the currently displayed image

		// Let's invert the currently displayed image by calling the invert function
		// later in the code
		Image outputImage = Gamma(imageView.getImage(), Double.parseDouble(gammaText.getText()));
		// Update the GUI so the new image is displayed
		imageView.setImage(outputImage);
	}

	/**
	 * It generate histogram by the image shown
	 * 
	 * @param event
	 */
	@FXML
	void histogramA(ActionEvent event) {
		histogram(imageView.getImage());
		getChart();
	}

	/**
	 * the method that do thing once open the application
	 */
	@FXML
	void initialize() {
		reset();
	}

	/**
	 * This method trigger and change the image to invert colour
	 * 
	 * @param event
	 *            mouse clicked
	 */
	@FXML
	void invertA(ActionEvent event) {
		System.out.println("Invert");
		// At this point, "image" will be the original image
		// imageView is the graphical representation of an image
		// imageView.getImage() is the currently displayed image

		// Let's invert the currently displayed image by calling the invert function
		// later in the code
		Image inverted_image = ImageInverter(imageView.getImage());
		// Update the GUI so the new image is displayed
		imageView.setImage(inverted_image);
	}

	/**
	 * This method takes a image and return it as inverted image
	 * 
	 * @param image
	 *            the image want to invert
	 * @return image processed
	 */
	private Image ImageInverter(Image image) {
		// Find the width and height of the image to be process
		int width = (int) image.getWidth();
		int height = (int) image.getHeight();
		// Create a new image of that width and height
		WritableImage inverted_image = new WritableImage(width, height);
		// Get an interface to write to that image memory
		PixelWriter inverted_image_writer = inverted_image.getPixelWriter();
		// Get an interface to read from the original image passed as the parameter to
		// the function
		PixelReader image_reader = image.getPixelReader();

		// Iterate over all pixels
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				// For each pixel, get the colour
				Color color = image_reader.getColor(x, y);
				// Do something (in this case invert) - the getColor function returns colours as
				// 0..1 doubles (we could multiply by 255 if we want 0-255 colours)
				color = Color.color(1 - color.getRed(), 1.0 - color.getGreen(), 1.0 - color.getBlue());
				// Note: for gamma correction you may not need the divide by 255 since getColor
				// already returns 0-1, nor may you need multiply by 255 since the Color.color
				// function consumes 0-1 doubles.

				// Apply the new colour
				inverted_image_writer.setColor(x, y, color);
			}
		}
		return inverted_image;
	}

	private Image bilinear(Image image, int nh, int nw) {
		// Find the width and height of the image to be process
		int width = (int) image.getWidth();
		int height = (int) image.getHeight();
		// Create a new image of that width and height
		WritableImage inverted_image = new WritableImage(nw, nh);
		// Get an interface to write to that image memory
		PixelWriter inverted_image_writer = inverted_image.getPixelWriter();
		// Get an interface to read from the original image passed as the parameter to
		// the function
		PixelReader image_reader = image.getPixelReader();

		Color[][] imageA = new Color[height][width];

		// Iterate over all pixels
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				imageA[y][x] = image_reader.getColor(x, y);
			}
		}

		int ratioWidth = (int) nw / (int) width;
		int ratioHeight = (int) nh / (int) height;

		// Iterate over all pixels
		for (int y = 0; y < height; y ++) {
			for (int x = 0; x < width; x ++) {
				Color c1, c2, c3, c4;
				int c1X, c1Y, c2X, c2Y, c3X, c3Y, c4X, c4Y;
				// get colour for 4 corner
				c1 = image_reader.getColor(x, y);
				c1X=x*ratioWidth;
				if (x + ratioWidth < width) {
					c2 = image_reader.getColor(x + ratioWidth, y);
					c2X = x + ratioWidth;
					c2Y = y;
				} else {
					// && y+ratioHeight< height
					c2 = image_reader.getColor(width-1, y);
					c2X = width;
					c2Y = y;
				}
				if (y + ratioHeight < height) {
					c3 = image_reader.getColor(x, y + ratioHeight);
					c3X = x;
					c3Y = y + ratioHeight;
				} else {
					c3 = image_reader.getColor(x, height-1);
					c3X = x;
					c3Y = height-1;
				}
				if (y + ratioHeight < height && x + ratioWidth < width) {
					c4 = image_reader.getColor(x + ratioWidth, y + ratioHeight);
					c4X = x + ratioWidth;
					c4Y = y + ratioHeight-1;
				} else {
					c4 = image_reader.getColor(width-1, height-1);
					c4X = width-1;
					c4Y = height-1;
				}
				for (int x2 = x; x2 < c2X; x2++) {
					// calculate the head, y2=0
					// red
					float red1 = (float) ((c2.getRed() - c1.getRed()) * (((float) x2 - (float) x) / (c2X - x))+ c1.getRed());
					// green
					float green1 = (float) ((c2.getGreen() - c1.getGreen()) * (((float) x2 - (float) x) / (c2X - x))+c1.getGreen());
					// blue
					float blue1 = (float) ((c2.getBlue() - c1.getBlue()) * (((float) x2 - (float) x) / (c2X - x))+c1.getBlue());

					Color top = Color.color(red1, green1, blue1);
					inverted_image_writer.setColor(x2, y, top);

					// bottom // red
					float red2 = (float) ((c4.getRed() - c3.getRed()) * (((float) x2 - (float) x) / (c2X - x))+c3.getRed());
					// green
					float green2 = (float) ((c4.getGreen() - c3.getGreen()) * (((float) x2 - (float) x) / (c2X - x))+c3.getGreen());
					// blue
					float blue2 = (float) ((c4.getBlue() - c3.getBlue()) * (((float) x2 - (float) x) / (c2X - x))+c3.getBlue());

					Color bottom = Color.color(red2, green2, blue2);
					inverted_image_writer.setColor(x2, c3Y, bottom);

					// column 
					for (int y3 = y; y3 < c3Y; y3++) {
						float red3 = red2 + (red1 - red2) * ((y3 - y) / (c3Y-y));
						float green3 = green2 + (green1 - green2) * ((y3 - y) / (c3Y-y));
						float blue3 = blue2 + (blue1 - blue2) *((y3 - y) / (c3Y-y));
						inverted_image_writer.setColor(x2, y + y3, Color.color(red3, green3, blue3));
					}

				}

			}
		}
		return inverted_image;
	}

	private Image nearestNeighbor(Image image, int nh, int nw) {
		// Find the width and height of the image to be process
		int width = (int) image.getWidth();
		int height = (int) image.getHeight();
		// Create a new image of that width and height
		WritableImage inverted_image = new WritableImage(nw, nh);
		// Get an interface to write to that image memory
		PixelWriter inverted_image_writer = inverted_image.getPixelWriter();
		// Get an interface to read from the original image passed as the parameter to
		// the function
		PixelReader image_reader = image.getPixelReader();

		Color[][] imageA = new Color[height][width];

		// Iterate over all pixels
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				imageA[y][x] = image_reader.getColor(x, y);
			}
		}

		// Iterate over all pixels
		for (int y = 0; y < nh; y++) {
			for (int x = 0; x < nw; x++) {

				// the coordinate that current pixel take
				int nowY = (int) (y * ((float) height / (float) nh));
				int nowX = (int) (x * ((float) width / (float) nw));

				// For each pixel, get the colour
				// Color color = image_reader.getColor(x, y);
				// Do something (in this case invert) - the getColor function returns colours as
				// 0..1 doubles (we could multiply by 255 if we want 0-255 colours)
				Color color = imageA[nowY][nowX];
				// Note: for gamma correction you may not need the divide by 255 since getColor
				// already returns 0-1, nor may you need multiply by 255 since the Color.color
				// function consumes 0-1 doubles.

				// Apply the new colour
				inverted_image_writer.setColor(x, y, color);
			}
		}
		return inverted_image;
	}

	/**
	 * This method process the image by given power correction.
	 * 
	 * @param image
	 *            the image want to process
	 * @param power
	 *            the index that undergo gamma correction
	 * @return image processed
	 */
	public Image Gamma(Image image, double power) {
		int width = (int) image.getWidth();
		int height = (int) image.getHeight();
		// Create a new image of that width and height
		WritableImage inverted_image = new WritableImage(width, height);
		// Get an interface to write to that image memory
		PixelWriter inverted_image_writer = inverted_image.getPixelWriter();
		// Get an interface to read from the original image passed as the parameter to
		// the function
		PixelReader image_reader = image.getPixelReader();

		// look up table
		double[] lookup = new double[256];
		for (int i = 0; i < lookup.length; i++) {
			double value = Math.pow((double) i / 255, (double) 1 / power);
			// value must be between 0-1
			if (value < 0) {
				value = 0;
			} else if (value > 1) {
				value = 1;
			}
			lookup[i] = value;
			// System.out.println(lookup[i]);
		}

		// Iterate over all pixels
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				// For each pixel, get the colour
				Color color = image_reader.getColor(x, y);

				color = Color.color(lookup[(int) (color.getRed() * 255)], lookup[(int) (color.getGreen() * 255)],
						lookup[(int) (color.getBlue() * 255)]);
				// Note: for gamma correction you may not need the divide by 255 since getColor
				// already returns 0-1, nor may you need multiply by 255 since the Color.color
				// function consumes 0-1 doubles.

				// Apply the new colour
				inverted_image_writer.setColor(x, y, color);
			}
		}
		return inverted_image;
	}

	/**
	 * this method check if the number is close or not
	 * 
	 * @param x
	 *            input number
	 * @param a
	 *            the number that want to do compare
	 * @return true if the two number is close, false otherwise.
	 */
	public boolean checkRange(double x, double a) {
		boolean r = true;
		if (Math.abs(x - a) > 10) {
			r = false;
		}
		return r;
	}

	/**
	 * This two contain the mouse coordinate of the initial movement.
	 */
	double iniX;
	double iniY;

	/**
	 * This method find coordinate from the canvas and draw line.
	 */
	public void drawLine() {

		GraphicsContext gc = contrastLine.getGraphicsContext2D();
		gc.setLineWidth(2);
		gc.setStroke(Color.BLACK);
		gc.setFill(Color.BLUE);

		// Start and draw a line when mouse is pressed
		contrastLine.setOnMousePressed(e -> {

			iniX = e.getX();
			iniY = e.getY();

		});

		contrastLine.setOnMouseDragged(e -> {

		});

		contrastLine.setOnMouseReleased(e -> {
			// set the coordinate must in the canvas, else that is unvaild
			if (e.getX() >= 0 && e.getX() <= 255 && e.getY() >= 0 && e.getY() <= 255) {

				if (checkRange(iniX, x1) && checkRange(iniY, y1)) {

					if (x2 <= e.getX()) {
						x1 = e.getX();
						y1 = e.getY();

						drawStroke();
						Image outputImage = contrastLookup(imageView.getImage());
						// Update the GUI so the new image is displayed
						imageView.setImage(outputImage);
					}
				} else if (checkRange(iniX, x2) && checkRange(iniY, y2)) {
					if (x1 >= e.getX()) {
						x2 = e.getX();
						y2 = e.getY();

						drawStroke();
						Image outputImage = contrastLookup(imageView.getImage());
						// Update the GUI so the new image is displayed
						imageView.setImage(outputImage);
					}
				}
			}
		});

	}

	/**
	 * This method draw contrast graph.
	 */
	private void drawStroke() {
		GraphicsContext gc = contrastLine.getGraphicsContext2D();
		gc.setLineWidth(2);
		gc.setStroke(Color.BLACK);
		gc.setFill(Color.WHITE);
		gc.fillRect(0, 0, contrastLine.getHeight(), contrastLine.getWidth());

		gc.beginPath();
		gc.lineTo(0, 255);
		gc.stroke();
		gc.lineTo(x2, y2);
		gc.stroke();
		gc.closePath();
		gc.beginPath();

		gc.beginPath();
		gc.lineTo(255, 0);
		gc.stroke();
		gc.lineTo(x1, y1);
		gc.stroke();
		gc.closePath();
		gc.beginPath();

		gc.beginPath();
		gc.lineTo(x2, y2);
		gc.stroke();
		gc.lineTo(x1, y1);
		gc.stroke();
		gc.closePath();
		gc.beginPath();

		gc.setFill(Color.BURLYWOOD);
		gc.fillOval(x1 - 5, y1 - 5, 10, 10);
		gc.setFill(Color.PALEVIOLETRED);
		gc.fillOval(x2 - 5, y2 - 5, 10, 10);

		setCoor();

	}

	/**
	 * This method set the coordinate from raw canvas coordinate.
	 */
	public void setCoor() {
		py1 = Math.abs(255 - y1);
		py2 = Math.abs(255 - y2);

		cor1y.setText(Integer.toString((int) x1));
		cor2.setText(Integer.toString((int) x2));
		cor1.setText(Integer.toString((int) py1));
		cor2y.setText(Integer.toString((int) py2));
	}

	/**
	 * This method made a look up table for contrast and process image from specific
	 * value.
	 * 
	 * @param image
	 *            the image that want to process
	 * @return image that is processed
	 */
	private Image contrastLookup(Image image) {

		for (int i = 0; i < x2; i++) {
			contrast[i] = (py2 / x2) * i / 255;
		}
		for (int i = (int) x2; i < x1; i++) {
			contrast[i] = ((((py1 - py2) / (x1 - x2)) * (i - x2)) + py2) / 255;
		}
		for (int i = (int) x1; i < contrast.length; i++) {
			contrast[i] = ((((255 - py1) / (255 - x1)) * (i - x1)) + py1) / 255;
		}
		/*
		 * for (double i : contrast) { System.out.println(i); }
		 */
		int width = (int) image.getWidth();
		int height = (int) image.getHeight();
		// Create a new image of that width and height
		WritableImage inverted_image = new WritableImage(width, height);
		// Get an interface to write to that image memory
		PixelWriter inverted_image_writer = inverted_image.getPixelWriter();
		// Get an interface to read from the original image passed as the parameter to
		// the function
		PixelReader image_reader = image.getPixelReader();

		// Iterate over all pixels
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				// For each pixel, get the colour
				Color color = image_reader.getColor(x, y);
				// Do something (in this case invert) - the getColor function returns colours as
				// 0..1 doubles (we could multiply by 255 if we want 0-255 colours)
				color = Color.color(contrast[(int) (color.getRed() * 255)], contrast[(int) (color.getGreen() * 255)],
						contrast[(int) (color.getBlue() * 255)]);
				// Note: for gamma correction you may not need the divide by 255 since getColor
				// already returns 0-1, nor may you need multiply by 255 since the Color.color
				// function consumes 0-1 doubles.

				// Apply the new colour
				inverted_image_writer.setColor(x, y, color);
			}
		}
		return inverted_image;
	}

	// histogram
	/**
	 * This generate the histogram by given image
	 * 
	 * @param image
	 *            that
	 */
	private void histogram(Image image) {
		int width = (int) image.getWidth();
		int height = (int) image.getHeight();
		PixelReader image_reader = image.getPixelReader();

		// array for each channel
		redHis = new int[256];
		greenHis = new int[256];
		blueHis = new int[256];
		brightHis = new int[256];

		// Iterate over all pixels
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				// For each pixel, get the colour
				Color color = image_reader.getColor(x, y);
				// Do something (in this case invert) - the getColor function returns colours as
				// 0..1 doubles (we could multiply by 255 if we want 0-255 colours)
				redHis[(int) (color.getRed() * 255)]++;
				greenHis[(int) (color.getGreen() * 255)]++;
				blueHis[(int) (color.getBlue() * 255)]++;
				brightHis[((int) (getb(color) * 255))]++;
			}
		}
	}

	/**
	 * This method generate 4 channel histogram base on global variable
	 */
	public void getChart() {
		redHistogram.getData().clear();
		redHistogram.layout();

		blueHistogram.getData().clear();
		blueHistogram.layout();

		greenHistogram.getData().clear();
		greenHistogram.layout();
		brightHistogram.getData().clear();
		brightHistogram.layout();
		// redHistogram.setTitle("Red Chart");

		// Prepare XYChart.Series objects by setting data
		// very very slow!!!
		XYChart.Series<String, Number> series1 = new XYChart.Series<>();

		for (int i = 0; i < redHis.length; i += 5) {

			series1.getData().add(new XYChart.Data<>("Red", redHis[i]));
			redHistogram.getData().add(series1);
			series1 = new XYChart.Series<>();

			series1.getData().add(new XYChart.Data<>("blue", blueHis[i]));
			blueHistogram.getData().add(series1);
			series1 = new XYChart.Series<>();

			series1.getData().add(new XYChart.Data<>("green", greenHis[i]));
			greenHistogram.getData().add(series1);
			series1 = new XYChart.Series<>();

			series1.getData().add(new XYChart.Data<>("brightness", brightHis[i]));
			brightHistogram.getData().add(series1);
			series1 = new XYChart.Series<>();
		}

	}

	/**
	 * This method get the grey level base on the color
	 * 
	 * @param color
	 *            color that want to change to grey level
	 * @return grey level
	 */
	private double getb(Color color) {
		double b = (color.getRed() + color.getBlue() + color.getGreen()) / 3;
		return b;
	}

	/**
	 * This method do equalization base on the image as parameter.
	 * 
	 * @param image
	 *            the image undergo processing
	 * @return the image that is the output
	 */
	public Image equalization(Image image) {
		// generate brightness first
		histogram(image);
		int width = (int) image.getWidth();
		int height = (int) image.getHeight();
		int size = width * height;
		// cumulative distribution of brightness
		int[] brightcumu = new int[256];
		brightcumu[0] = brightHis[0];
		float[] mapping = new float[256];
		for (int i = 1; i < brightHis.length; i++) {
			brightcumu[i] = brightcumu[i - 1] + brightHis[i];
			// create mapping
			mapping[i] = (brightcumu[i] / (float) size);
		}

		// Create a new image of that width and height
		WritableImage inverted_image = new WritableImage(width, height);
		// Get an interface to write to that image memory
		PixelWriter inverted_image_writer = inverted_image.getPixelWriter();
		// Get an interface to read from the original image passed as the parameter to
		// the function
		PixelReader image_reader = image.getPixelReader();

		// Iterate over all pixels
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				// For each pixel, get the colour
				Color color = image_reader.getColor(x, y);
				// Do something (in this case invert) - the getColor function returns colours as
				// 0..1 doubles (we could multiply by 255 if we want 0-255 colours)

				color = Color.gray((mapping[(int) (getb(color) * 255)]));
				// Note: for gamma correction you may not need the divide by 255 since getColor
				// already returns 0-1, nor may you need multiply by 255 since the Color.color
				// function consumes 0-1 doubles.

				// Apply the new colour
				inverted_image_writer.setColor(x, y, color);
			}
		}
		return inverted_image;
	}

	/**
	 * generate a grey image
	 * 
	 * @param image
	 *            the image undergo processing
	 * @return the image that is the output
	 */
	public Image greyImage(Image image) {
		int width = (int) image.getWidth();
		int height = (int) image.getHeight();
		// Create a new image of that width and height
		WritableImage inverted_image = new WritableImage(width, height);
		// Get an interface to write to that image memory
		PixelWriter inverted_image_writer = inverted_image.getPixelWriter();
		// Get an interface to read from the original image passed as the parameter to
		// the function
		PixelReader image_reader = image.getPixelReader();

		// Iterate over all pixels
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				// For each pixel, get the colour
				Color color = image_reader.getColor(x, y);
				// Do something (in this case invert) - the getColor function returns colours as
				// 0..1 doubles (we could multiply by 255 if we want 0-255 colours)
				color = Color.color(getb(color), getb(color), getb(color));
				// Note: for gamma correction you may not need the divide by 255 since getColor
				// already returns 0-1, nor may you need multiply by 255 since the Color.color
				// function consumes 0-1 doubles.

				// Apply the new colour
				inverted_image_writer.setColor(x, y, color);
			}
		}
		return inverted_image;
	}

	/*
	 * -4 -1 0 -1 -4 -1 2 3 2 -1 0 3 4 3 0 -1 2 3 2 -1 -4 -1 0 -1 -4
	 */

	/**
	 * This method apply a filter on the image
	 * 
	 * @param image
	 *            the image undergo processing
	 * @return the image that is the output
	 */
	public Image filter(Image image) {
		int width = (int) image.getWidth();
		int height = (int) image.getHeight();
		float[][] redM = new float[height][width];
		float[][] greenM = new float[height][width];
		float[][] blueM = new float[height][width];
		float min = 10;
		float max = 5;

		// Create a new image of that width and height
		WritableImage inverted_image = new WritableImage(width, height);
		// Get an interface to write to that image memory
		PixelWriter inverted_image_writer = inverted_image.getPixelWriter();
		// Get an interface to read from the original image passed as the parameter to
		// the function
		PixelReader image_reader = image.getPixelReader();

		// generate processed redM
		for (int y = 2; y < height - 2; y++) {
			for (int x = 2; x < width - 2; x++) {

				// for 1 pixel read 25 pixel
				for (int y2 = -2; y2 <= 2; y2++) {
					for (int x2 = -2; x2 <= 2; x2++) {
						Color color = image_reader.getColor(x + x2, y + y2);
						if (Math.abs(y2) == 2 && Math.abs(x2) == 2) {
							redM[y][x] += (-4 * (color.getRed()));
							greenM[y][x] += (-4 * (color.getGreen()));
							blueM[y][x] += (-4 * (color.getBlue()));
						} else if ((Math.abs(y2) == 1 && Math.abs(x2) == 2)
								^ (Math.abs(x2) == 1 && Math.abs(y2) == 2)) {
							redM[y][x] += (-1 * (color.getRed()));
							greenM[y][x] += (-1 * (color.getGreen()));
							blueM[y][x] += (-1 * (color.getBlue()));
						} else if ((Math.abs(y2) == 1 && Math.abs(x2) == 1)) {
							redM[y][x] += (2 * (color.getRed()));
							greenM[y][x] += (2 * (color.getGreen()));
							blueM[y][x] += (2 * (color.getBlue()));
						} else if ((Math.abs(y2) == 0 && Math.abs(x2) == 2)
								^ (Math.abs(x2) == 0 && Math.abs(y2) == 2)) {
							redM[y][x] += (0 * (color.getRed()));
							greenM[y][x] += (0 * (color.getGreen()));
							blueM[y][x] += (0 * (color.getBlue()));
						} else if ((Math.abs(y2) == 1 && Math.abs(x2) == 0)
								^ (Math.abs(x2) == 1 && Math.abs(y2) == 0)) {
							redM[y][x] += (3 * (color.getRed()));
							greenM[y][x] += (3 * (color.getGreen()));
							blueM[y][x] += (3 * (color.getBlue()));
						} else if ((Math.abs(y2) == 0 && Math.abs(x2) == 0)) {
							redM[y][x] += (4 * (color.getRed()));
							greenM[y][x] += (4 * (color.getGreen()));
							blueM[y][x] += (4 * (color.getBlue()));
						}
					}
				}
				// 1pixel end

				// initialise min/max
				if (min == 10 && max == 5) {
					// should be fine here
					min = redM[y][x];
					max = redM[y][x];
				}
				// see if it is min/max
				if (redM[y][x] < min) {
					min = redM[y][x];
				}
				if (redM[y][x] > max) {
					max = redM[y][x];
				}
				if (greenM[y][x] < min) {
					min = greenM[y][x];
				}
				if (greenM[y][x] > max) {
					max = greenM[y][x];
				}
				if (blueM[y][x] < min) {
					min = blueM[y][x];
				}
				if (blueM[y][x] > max) {
					max = blueM[y][x];
				}

			}
		}
		// normalization
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {

				// System.out.println(min+","+max);
				redM[y][x] = ((redM[y][x] - min)) / (max - min);

				greenM[y][x] = ((greenM[y][x] - min)) / (max - min);

				blueM[y][x] = ((blueM[y][x] - min)) / (max - min);

				// write image

				Color color2 = Color.color(redM[y][x], greenM[y][x], blueM[y][x]);
				// Note: for gamma correction you may not need the divide by 255 since getColor
				// already returns 0-1, nor may you need multiply by 255 since the Color.color
				// function consumes 0-1 doubles.
				// Apply the new colour
				inverted_image_writer.setColor(x, y, color2);

			}
		}
		System.out.println(min + "," + max);
		return inverted_image;

	}

	/**
	 * This method apply a filter on the image
	 * 
	 * @param image
	 *            the image undergo processing
	 * @return the image that is the output
	 */
	public Image zeroCrossing(Image image) {
		int width = (int) image.getWidth();
		int height = (int) image.getHeight();
		float[][] redM = new float[height][width];
		float[][] greenM = new float[height][width];
		float[][] blueM = new float[height][width];
		float min = 10;
		float max = 5;

		// Create a new image of that width and height
		WritableImage inverted_image = new WritableImage(width, height);
		// Get an interface to write to that image memory
		PixelWriter inverted_image_writer = inverted_image.getPixelWriter();
		// Get an interface to read from the original image passed as the parameter to
		// the function
		PixelReader image_reader = image.getPixelReader();

		// generate processed redM
		for (int y = 2; y < height - 2; y++) {
			for (int x = 2; x < width - 2; x++) {

				// for 1 pixel read 25 pixel
				for (int y2 = -2; y2 <= 2; y2++) {
					for (int x2 = -2; x2 <= 2; x2++) {
						Color color = image_reader.getColor(x + x2, y + y2);
						if ((Math.abs(y2) == 1 && Math.abs(x2) == 1)) {
							redM[y][x] += ((color.getRed()));
							greenM[y][x] += ((color.getGreen()));
							blueM[y][x] += ((color.getBlue()));
						} else if ((Math.abs(y2) == 1 && Math.abs(x2) == 0)
								^ (Math.abs(x2) == 1 && Math.abs(y2) == 0)) {
							redM[y][x] += (2 * (color.getRed()));
							greenM[y][x] += (2 * (color.getGreen()));
							blueM[y][x] += (2 * (color.getBlue()));
						} else if ((Math.abs(y2) == 0 && Math.abs(x2) == 0)) {
							redM[y][x] += (-16 * (color.getRed()));
							greenM[y][x] += (-16 * (color.getGreen()));
							blueM[y][x] += (-16 * (color.getBlue()));
						} else if ((Math.abs(y2) == 2 && Math.abs(x2) == 0)
								^ (Math.abs(x2) == 0 && Math.abs(y2) == 2)) {
							redM[y][x] += ((color.getRed()));
							greenM[y][x] += ((color.getGreen()));
							blueM[y][x] += ((color.getBlue()));
						}
					}
					// 1pixel end

					// initialise min/max
					if (min == 10 && max == 5) {
						// should be fine here
						min = redM[y][x];
						max = redM[y][x];
					}
					// see if it is min/max
					if (redM[y][x] < min) {
						min = redM[y][x];
					}
					if (redM[y][x] > max) {
						max = redM[y][x];
					}
					if (greenM[y][x] < min) {
						min = greenM[y][x];
					}
					if (greenM[y][x] > max) {
						max = greenM[y][x];
					}
					if (blueM[y][x] < min) {
						min = blueM[y][x];
					}
					if (blueM[y][x] > max) {
						max = blueM[y][x];
					}

				}
			}
		}
		// normalization
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {

				// System.out.println(min+","+max);
				redM[y][x] = ((redM[y][x] - min)) / (max - min);

				greenM[y][x] = ((greenM[y][x] - min)) / (max - min);

				blueM[y][x] = ((blueM[y][x] - min)) / (max - min);

				// write image

				Color color2 = Color.color(redM[y][x], greenM[y][x], blueM[y][x]);
				// Note: for gamma correction you may not need the divide by 255 since getColor
				// already returns 0-1, nor may you need multiply by 255 since the Color.color
				// function consumes 0-1 doubles.
				// Apply the new colour
				inverted_image_writer.setColor(x, y, color2);

			}
		}
		System.out.println(min + "," + max);
		return inverted_image;

	}

	public Image lowPassFilter(Image image) {
		int width = (int) image.getWidth();
		int height = (int) image.getHeight();
		float[][] redM = new float[height][width];
		float[][] greenM = new float[height][width];
		float[][] blueM = new float[height][width];
		float min = 10;
		float max = 5;

		// Create a new image of that width and height
		WritableImage inverted_image = new WritableImage(width, height);
		// Get an interface to write to that image memory
		PixelWriter inverted_image_writer = inverted_image.getPixelWriter();
		// Get an interface to read from the original image passed as the parameter to
		// the function
		PixelReader image_reader = image.getPixelReader();

		// generate processed redM
		for (int y = 2; y < height - 2; y++) {
			for (int x = 2; x < width - 2; x++) {

				// for 1 pixel read 25 pixel
				for (int y2 = -2; y2 <= 2; y2++) {
					for (int x2 = -2; x2 <= 2; x2++) {
						Color color = image_reader.getColor(x + x2, y + y2);
						redM[y][x] += ((color.getRed()));
						greenM[y][x] += ((color.getGreen()));
						blueM[y][x] += ((color.getBlue()));
					}
				}
				// 1pixel end

				// initialise min/max
				if (min == 10 && max == 5) {
					// should be fine here
					min = redM[y][x];
					max = redM[y][x];
				}
				// see if it is min/max
				if (redM[y][x] < min) {
					min = redM[y][x];
				}
				if (redM[y][x] > max) {
					max = redM[y][x];
				}
				if (greenM[y][x] < min) {
					min = greenM[y][x];
				}
				if (greenM[y][x] > max) {
					max = greenM[y][x];
				}
				if (blueM[y][x] < min) {
					min = blueM[y][x];
				}
				if (blueM[y][x] > max) {
					max = blueM[y][x];
				}

			}
		}
		// normalization
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {

				// System.out.println(min+","+max);
				redM[y][x] = ((redM[y][x] - min)) / (max - min);

				greenM[y][x] = ((greenM[y][x] - min)) / (max - min);

				blueM[y][x] = ((blueM[y][x] - min)) / (max - min);

				// write image

				Color color2 = Color.color(redM[y][x], greenM[y][x], blueM[y][x]);
				// Note: for gamma correction you may not need the divide by 255 since getColor
				// already returns 0-1, nor may you need multiply by 255 since the Color.color
				// function consumes 0-1 doubles.
				// Apply the new colour
				inverted_image_writer.setColor(x, y, color2);

			}
		}
		System.out.println(min + "," + max);
		return inverted_image;

	}

	public Image prewitt(Image image) {
		int width = (int) image.getWidth();
		int height = (int) image.getHeight();
		// x
		float[][] redM = new float[height][width];
		float[][] greenM = new float[height][width];
		float[][] blueM = new float[height][width];
		// y
		float[][] redM2 = new float[height][width];
		float[][] greenM2 = new float[height][width];
		float[][] blueM2 = new float[height][width];
		float minx = 10;
		float maxx = 5;
		float miny = 10;
		float maxy = 5;

		// Create a new image of that width and height
		WritableImage inverted_image = new WritableImage(width, height);
		// Get an interface to write to that image memory
		PixelWriter inverted_image_writer = inverted_image.getPixelWriter();
		// Get an interface to read from the original image passed as the parameter to
		// the function
		PixelReader image_reader = image.getPixelReader();

		// generate processed redM
		for (int y = 2; y < height - 2; y++) {
			for (int x = 2; x < width - 2; x++) {

				// for 1 pixel read 25 pixel
				for (int y2 = -1; y2 <= 1; y2++) {
					for (int x2 = -1; x2 <= 1; x2++) {
						Color color = image_reader.getColor(x + x2, y + y2);
						if (x2 == -1) {
							redM[y][x] += ((color.getRed()));
							greenM[y][x] += ((color.getGreen()));
							blueM[y][x] += ((color.getBlue()));

						}
						if (x2 == 1) {
							redM[y][x] -= ((color.getRed()));
							greenM[y][x] -= ((color.getGreen()));
							blueM[y][x] -= ((color.getBlue()));
						}
						if (y2 == -1) {
							redM2[y][x] += ((color.getRed()));
							greenM2[y][x] += ((color.getGreen()));
							blueM2[y][x] += ((color.getBlue()));
						}
						if (y2 == 1) {
							redM2[y][x] -= ((color.getRed()));
							greenM2[y][x] -= ((color.getGreen()));
							blueM2[y][x] -= ((color.getBlue()));
						}
					}
				}
				// 1pixel end

				// initialise minx/maxx
				if (minx == 10 && maxx == 5) {
					// should be fine here
					minx = redM[y][x];
					maxx = redM[y][x];
				}
				// see if it is minx/maxx
				if (redM[y][x] < minx) {
					minx = redM[y][x];
				}
				if (redM[y][x] > maxx) {
					maxx = redM[y][x];
				}
				if (greenM[y][x] < minx) {
					minx = greenM[y][x];
				}
				if (greenM[y][x] > maxx) {
					maxx = greenM[y][x];
				}
				if (blueM[y][x] < minx) {
					minx = blueM[y][x];
				}
				if (blueM[y][x] > maxx) {
					maxx = blueM[y][x];
				}

				// initialise miny/maxy
				if (miny == 10 && maxy == 5) {
					// should be fine here
					miny = redM2[y][x];
					maxy = redM2[y][x];
				}
				// see if it is miny/maxy
				if (redM2[y][x] < miny) {
					miny = redM2[y][x];
				}
				if (redM2[y][x] > maxy) {
					maxy = redM2[y][x];
				}
				if (greenM2[y][x] < miny) {
					miny = greenM2[y][x];
				}
				if (greenM2[y][x] > maxy) {
					maxy = greenM2[y][x];
				}
				if (blueM2[y][x] < miny) {
					miny = blueM2[y][x];
				}
				if (blueM2[y][x] > maxy) {
					maxy = blueM2[y][x];
				}

			}
		}
		float minx2 = 10;
		float maxx2 = 5;
		// normalization
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {

				// System.out.println(min+","+max);
				redM[y][x] = ((redM[y][x] - minx)) / (maxx - minx);
				greenM[y][x] = ((greenM[y][x] - minx)) / (maxx - minx);
				blueM[y][x] = ((blueM[y][x] - minx)) / (maxx - minx);

				redM2[y][x] = ((redM2[y][x] - miny)) / (maxy - miny);

				greenM2[y][x] = ((greenM2[y][x] - miny)) / (maxy - miny);

				blueM2[y][x] = ((blueM2[y][x] - miny)) / (maxy - miny);

				// magnitute
				// square root of 2 is 1.4...???????
				redM[y][x] = (float) Math.sqrt(Math.pow(redM[y][x], 2) + Math.pow(redM2[y][x], 2));
				greenM[y][x] = (float) Math.sqrt(Math.pow(greenM[y][x], 2) + Math.pow(greenM2[y][x], 2));
				blueM[y][x] = (float) Math.sqrt(Math.pow(blueM[y][x], 2) + Math.pow(blueM2[y][x], 2));
				// System.out.println(redM[y][x]);

				// normalization
				if (minx2 == 10 && maxx2 == 5) {
					// should be fine here
					minx2 = redM[y][x];
					maxx2 = redM[y][x];
				}
				// see if it is minx2/maxx2
				if (redM[y][x] < minx2) {
					minx2 = redM[y][x];
				}
				if (redM[y][x] > maxx2) {
					maxx2 = redM[y][x];
				}
				if (greenM[y][x] < minx2) {
					minx2 = greenM[y][x];
				}
				if (greenM[y][x] > maxx2) {
					maxx2 = greenM[y][x];
				}
				if (blueM[y][x] < minx2) {
					minx2 = blueM[y][x];
				}
				if (blueM[y][x] > maxx2) {
					maxx2 = blueM[y][x];
				}

			}
		}

		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				redM[y][x] = ((redM[y][x] - minx2)) / (maxx2 - minx2);

				greenM[y][x] = ((greenM[y][x] - minx2)) / (maxx2 - minx2);

				blueM[y][x] = ((blueM[y][x] - minx2)) / (maxx2 - minx2);

				// write image
				Color color2;
				try {
					color2 = Color.color(redM[y][x], greenM[y][x], blueM[y][x]);
				} catch (IllegalArgumentException e) {
					color2 = Color.color(1, 1, 1);
				}
				// Note: for gamma correction you may not need the divide by 255 since getColor
				// already returns 0-1, nor may you need multiply by 255 since the Color.color
				// function consumes 0-1 doubles.
				// Apply the new colour
				inverted_image_writer.setColor(x, y, color2);

			}
		}
		// System.out.println(min + "," + max);
		return inverted_image;

	}

	private Image thereshold(Image image) {
		int width = (int) image.getWidth();
		int height = (int) image.getHeight();
		// Create a new image of that width and height
		WritableImage inverted_image = new WritableImage(width, height);
		// Get an interface to write to that image memory
		PixelWriter inverted_image_writer = inverted_image.getPixelWriter();
		// Get an interface to read from the original image passed as the parameter to
		// the function
		PixelReader image_reader = image.getPixelReader();

		// Iterate over all pixels
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				// For each pixel, get the colour
				Color color = image_reader.getColor(x, y);
				double c = getb(color);
				if (c < 0.5) {
					c = 0;
				} else {
					c = 1;
				}
				color = Color.color(c, c, c);
				// Note: for gamma correction you may not need the divide by 255 since getColor
				// already returns 0-1, nor may you need multiply by 255 since the Color.color
				// function consumes 0-1 doubles.

				// Apply the new colour
				inverted_image_writer.setColor(x, y, color);
			}
		}
		return inverted_image;
	}

	private Image dithering(Image image) {
		int width = (int) image.getWidth();
		int height = (int) image.getHeight();
		// Create a new image of that width and height
		WritableImage inverted_image = new WritableImage(width, height);
		// Get an interface to write to that image memory
		PixelWriter inverted_image_writer = inverted_image.getPixelWriter();
		// Get an interface to read from the original image passed as the parameter to
		// the function
		PixelReader image_reader = image.getPixelReader();

		float error = 0;

		// to minus the color that add to the color now.
		// Iterate over all pixels
		for (int y = 0; y < height - 2; y += 1) {
			for (int x = 0; x < width; x++) {
				// For each pixel, get the colour
				float color = (float) getb(image_reader.getColor(x, y)) + error;
				float colorout;
				// should be 0/255
				if (color < 0.5) {
					colorout = 0;
					error = color;
				} else {
					colorout = 1;
					error = color - 1;
				}

				// Do something (in this case invert) - the getColor function returns colours as
				// 0..1 doubles (we could multiply by 255 if we want 0-255 colours)
				Color color1 = Color.gray(colorout); // Note: for gamma correction you may not need the divide by 255
														// since getColor
				// already returns 0-1, nor may you need multiply by 255 since the Color.color
				// function consumes 0-1 doubles.

				// Apply the new colour
				inverted_image_writer.setColor(x, y, color1);
			}
			y += 1;
			for (int x = width - 1; x >= 0; x--) {
				// For each pixel, get the colour
				float color = (float) getb(image_reader.getColor(x, y)) + error;
				float colorout;
				// should be 0/255
				if (color < 0.5) {
					colorout = 0;
					error = color;
				} else {
					colorout = 1;
					error = color - 1;
				}

				// Do something (in this case invert) - the getColor function returns colours as
				// 0..1 doubles (we could multiply by 255 if we want 0-255 colours)
				Color color1 = Color.gray(colorout);
				// Note: for gamma correction you may not need the divide by 255 since getColor
				// already returns 0-1, nor may you need multiply by 255 since the Color.color
				// function consumes 0-1 doubles.

				// Apply the new colour
				inverted_image_writer.setColor(x, y, color1);
			}

		}
		return inverted_image;
	}

	private Image floyd(Image image) {
		int width = (int) image.getWidth();
		int height = (int) image.getHeight();
		// Create a new image of that width and height
		WritableImage inverted_image = new WritableImage(width, height);
		// Get an interface to write to that image memory
		PixelWriter inverted_image_writer = inverted_image.getPixelWriter();
		// Get an interface to read from the original image passed as the parameter to
		// the function
		PixelReader image_reader = image.getPixelReader();

		int error = 0;
		int[][] grey = new int[height][width];

		for (int y = 0; y < height; y += 1) {
			for (int x = 0; x < width; x++) {
				int color = (int) (getb(image_reader.getColor(x, y)) * 255);
				grey[y][x] = color;
			}
		}

		// Iterate over all pixels
		for (int y = 0; y < height; y += 1) {
			for (int x = 0; x < width; x++) {
				// For each pixel, get the colour
				int color = grey[y][x];
				float colorout;
				// should be 0/255
				if (color < 128) {
					colorout = 0;
					error = color;
				} else {
					colorout = 1;
					error = color - 256;
				}
				if ((y + 1) < height) {
					grey[y + 1][x] = (int) (grey[y + 1][x] + (error * (5.0 / 16.0)));
					if ((x + 1) < width) {
						grey[y + 1][x + 1] = (int) (grey[y + 1][x + 1] + (error * (1.0 / 16.0)));
					}
					if ((x - 1) >= 0) {
						grey[y + 1][x - 1] = (int) (grey[y + 1][x - 1] + (error * (3.0 / 16.0)));
					}
				}
				if ((x + 1) < width) {
					grey[y][x + 1] = (int) (grey[y][x + 1] + (error * (7 / 16)));
				}
				// Do something (in this case invert) - the getColor function returns colours as
				// 0..1 doubles (we could multiply by 255 if we want 0-255 colours)
				// Color color1 = Color.gray(colorout); // Note: for gamma correction you may
				// not need the divide by 255
				// since getColor
				// already returns 0-1, nor may you need multiply by 255 since the Color.color
				// function consumes 0-1 doubles.

				// Apply the new colour
				inverted_image_writer.setColor(x, y, Color.gray(colorout));
			}
		}
		return inverted_image;
	}

	private Image ordered(Image image) {
		// Find the width and height of the image to be process
		int width = (int) image.getWidth();
		int height = (int) image.getHeight();
		// Create a new image of that width and height
		WritableImage inverted_image = new WritableImage(width, height);
		// Get an interface to write to that image memory
		PixelWriter inverted_image_writer = inverted_image.getPixelWriter();
		// Get an interface to read from the original image passed as the parameter to
		// the function
		PixelReader image_reader = image.getPixelReader();

		int[][] template = { { 1, 9, 3, 11 }, { 13, 5, 15, 7 }, { 4, 12, 2, 10 }, { 16, 8, 14, 6 } };

		// Iterate over all pixels
		for (int y = 0; y < height - 4; y += 4) {
			for (int x = 0; x < width - 4; x += 4) {
				// Color color = image_reader.getColor(x, y);
				// loop though the pattern to get the small part from the image
				double color = 0;

				double left = (double) (getb(image_reader.getColor(x, y)) * 255);
				// scale intensity
				left = left / 255;
				int pattern = (int) Math.min(Math.floor(left * (Math.pow(4, 2) + 1)), Math.pow(4, 2));
				int max = 0;
				for (int y1 = 0; y1 < 4; y1++) {
					for (int x1 = 0; x1 < 4; x1++) {
						if ((x + x1 < width) && (y + y1 < height)) {
							// System.out.println((x1 + x) + "," + (y1 + y));
							if (max < (double) (getb(image_reader.getColor(x + x1, y + y1)) * 255)) {
								max = (int) (getb(image_reader.getColor(x + x1, y + y1)) * 255);
							}
						}
					}
				}

				double interval = (max - left) / (16 - pattern);

				for (int y1 = 0; y1 < 4; y1++) {
					for (int x1 = 0; x1 < 4; x1++) {
						if ((x + x1 < width) && (y + y1 < height)) {
							int val = (int) ((getb(image_reader.getColor(x + x1, y + y1)) * 255) / interval);
							if (template[y1][x1] >= val) {
								inverted_image_writer.setColor(x + x1, y + y1, Color.gray(1));
							} else {
								inverted_image_writer.setColor(x + x1, y + y1, Color.gray(0));
							}

						}
					}
				}
			}
		}
		return inverted_image;
	}

	private Image pattern(Image image) {
		// Find the width and height of the image to be process
		int width = (int) image.getWidth();
		int height = (int) image.getHeight();
		// Create a new image of that width and height
		WritableImage inverted_image = new WritableImage(width, height);
		// Get an interface to write to that image memory
		PixelWriter inverted_image_writer = inverted_image.getPixelWriter();
		// Get an interface to read from the original image passed as the parameter to
		// the function
		PixelReader image_reader = image.getPixelReader();

		int[][] template = { { 1, 9, 3, 11 }, { 13, 5, 15, 7 }, { 4, 12, 2, 10 }, { 16, 8, 14, 6 } };

		// Iterate over all pixels
		for (int y = 0; y < height - 4; y += 4) {
			for (int x = 0; x < width - 4; x += 4) {
				// Color color = image_reader.getColor(x, y);
				// loop though the pattern to get the small part from the image
				double color = 0;

				for (int y1 = 0; y1 < 4; y1++) {
					for (int x1 = 0; x1 < 4; x1++) {
						if ((x + x1 < width) && (y + y1 < height)) {
							// System.out.println((x1 + x) + "," + (y1 + y));
							color += (double) (getb(image_reader.getColor(x1 + x, y1 + y)) * 255);
						}
					}
				}

				color = color / 16.0;
				// Total Scaled intensity
				color = color / 255.0;
				int pattern = (int) Math.min(Math.floor(color * (Math.pow(4, 2) + 1)), Math.pow(4, 2));

				for (int y1 = 0; y1 < 4; y1++) {
					for (int x1 = 0; x1 < 4; x1++) {
						if ((x + x1 < width) && (y + y1 < height)) {
							if (template[y1][x1] >= pattern) {
								inverted_image_writer.setColor(x + x1, y + y1, Color.gray(0));
							} else {
								inverted_image_writer.setColor(x + x1, y + y1, Color.gray(1));
							}

						}
					}
				}
			}
		}
		return inverted_image;
	}

	private Image nonLinearFilter(Image image) {
		// Find the width and height of the image to be process
		int width = (int) image.getWidth();
		int height = (int) image.getHeight();
		// Create a new image of that width and height
		WritableImage inverted_image = new WritableImage(width, height);
		// Get an interface to write to that image memory
		PixelWriter inverted_image_writer = inverted_image.getPixelWriter();
		// Get an interface to read from the original image passed as the parameter to
		// the function
		PixelReader image_reader = image.getPixelReader();

		// Iterate over all pixels
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				ArrayList<Double> listR = new ArrayList<>();
				ArrayList<Double> listG = new ArrayList<>();
				ArrayList<Double> listB = new ArrayList<>();
				for (int y1 = -1; y1 < 1; y1++) {
					for (int x1 = -1; x1 < 1; x1++) {
						if ((x + x1 < width && x + x1 >= 0) && (y + y1 < height && y + y1 >= 0)) {
							// System.out.println((x1 + x) + "," + (y1 + y));
							listR.add(image_reader.getColor(x1 + x, y1 + y).getRed());
							listG.add(image_reader.getColor(x1 + x, y1 + y).getGreen());
							listB.add(image_reader.getColor(x1 + x, y1 + y).getBlue());

						}
					}
				}
				// System.out.println(listR.size());

				// median
				Collections.sort(listR);
				Collections.sort(listG);
				Collections.sort(listB);
				double medianR = listR.get(listR.size() / 2) * 0.6193;
				double medianG = listG.get(listG.size() / 2) * 0.6193;
				double medianB = listB.get(listB.size() / 2) * 0.6193;

				for (int y1 = -1; y1 < 1; y1++) {
					for (int x1 = -1; x1 < 1; x1++) {
						if ((x + x1 < width && x + x1 > 0) && (y + y1 < height && y + y1 > 0)) {

							if (Math.abs(y1) == 1 && Math.abs(x1) == 1) {
								medianR += 0.0113 * (image_reader.getColor(x1 + x, y1 + y).getRed());
								medianG += 0.0113 * (image_reader.getColor(x1 + x, y1 + y).getGreen());
								medianB += 0.0113 * (image_reader.getColor(x1 + x, y1 + y).getBlue());

							} else if ((Math.abs(y1) == 0 && Math.abs(x1) == 1)
									^ (Math.abs(y1) == 1 && Math.abs(x1) == 0)) {
								medianR += 0.0838 * (image_reader.getColor(x1 + x, y1 + y).getRed());
								medianG += 0.0838 * (image_reader.getColor(x1 + x, y1 + y).getGreen());
								medianB += 0.0838 * (image_reader.getColor(x1 + x, y1 + y).getBlue());
							}
						}
					}
				}

				// For each pixel, get the colour
				// Do something (in this case invert) - the getColor function returns colours as
				// 0..1 doubles (we could multiply by 255 if we want 0-255 colours)
				Color color = Color.color(medianR, medianG, medianB);
				// Note: for gamma correction you may not need the divide by 255 since getColor
				// already returns 0-1, nor may you need multiply by 255 since the Color.color
				// function consumes 0-1 doubles.

				// Apply the new colour
				inverted_image_writer.setColor(x, y, color);
			}
		}
		return inverted_image;
	}

	@FXML
	private void save() {

		wim = (WritableImage) image;
		// store pic from drawing sub program.
		String filePath = LocalDateTime.now() + ".png";
		System.out.println(filePath);
		File file = new File(filePath);

		try {
			ImageIO.write(SwingFXUtils.fromFXImage(wim, null), "png", file);
		} catch (Exception s) {

		}

	}
}
package Utility;

public class InputUtility {
	private static int mouseX, mouseY;
	private static boolean mouseLeftDown, mouseRightDown, mouseOnScreen;
	private static boolean mouseLeftTriggered, mouseRightTriggered;
	private static boolean[] keyPressed = new boolean[256];
	private static boolean[] keyTriggered = new boolean[256];

	public static int getMouseX() {
		return InputUtility.mouseX;
	}

	public static void setMouseX(int mouseX) {
		InputUtility.mouseX = mouseX;
	}

	public static int getMouseY() {
		return InputUtility.mouseY;
	}

	public static void setMouseY(int mouseY) {
		InputUtility.mouseY = mouseY;
	}

	public static boolean isMouseLeftDown() {
		return InputUtility.mouseLeftDown;
	}

	public static void setMouseLeftDown(boolean mouseLeftDown) {
		InputUtility.mouseLeftDown = mouseLeftDown;
	}

	public static boolean isMouseRightDown() {
		return InputUtility.mouseRightDown;
	}

	public static void setMouseRightDown(boolean mouseRightDown) {
		InputUtility.mouseRightDown = mouseRightDown;
	}

	public static boolean isMouseOnScreen() {
		return InputUtility.mouseOnScreen;
	}

	public static void setMouseOnScreen(boolean mouseOnScreen) {
		InputUtility.mouseOnScreen = mouseOnScreen;
	}

	public static boolean isMouseLeftClicked() {
		return InputUtility.mouseLeftTriggered;
	}

	public static void setMouseLeftTriggered(boolean v) {
		InputUtility.mouseLeftTriggered = v;
	}

	public static boolean isMouseRightClicked() {
		return InputUtility.mouseRightTriggered;
	}

	public static void setMouseRightTriggered(boolean v) {
		InputUtility.mouseRightTriggered = v;
	}

	public static boolean getKeyPressed(int key) {
		if (key < 0 || key > 255)
			return false;
		return InputUtility.keyPressed[key];
	}

	public static void setKeyPressed(int key, boolean pressed) {
		if (key < 0 || key > 255)
			return;
		else
			InputUtility.keyPressed[key] = pressed;
	}

	public static boolean getKeyTriggered(int key) {
		if (key < 0 || key > 255)
			return false;
		return InputUtility.keyTriggered[key];
	}

	public static void setKeyTriggered(int key, boolean pressed) {
		if (key < 0 || key > 255)
			return;
		InputUtility.keyTriggered[key] = pressed;
	}

	public static void postUpdate() {
		// check on screen
		// mouseOnScreen = mouseX >= 0 && mouseX <=
		// ConfigurableOption.screenWidth && mouseY >= 0
		// && mouseY <= ConfigurableOption.screenHeight;
		mouseLeftTriggered = false;
		mouseRightTriggered = false;

		for (int key = 0; key < 256; key++)
			keyTriggered[key] = false;

	}

}

package View;

import Boundary.BoardCreator;
import gui_main.GUI;

import java.awt.*;

public class MatadorSpil {
    public static void main(String[] args) {
        GUI gui = new GUI(BoardCreator.makeFields(), Color.LIGHT_GRAY);
    }
}

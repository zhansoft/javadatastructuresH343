import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

class Games {
    static ArrayList<Point> blinker = new ArrayList<>(Arrays.asList(
            new Point(4, 5),
            new Point(5, 5),
            new Point(6, 5)
    ));

    static ArrayList<Point> toad = new ArrayList<>(Arrays.asList(
            new Point(4,4),
            new Point(4,5),
            new Point(4,6),
            new Point(5,3),
            new Point(5,4),
            new Point(5,5)
    ));

    static ArrayList<Point> beacon = new ArrayList<>(Arrays.asList(
            new Point(3,3),
            new Point(3,4),
            new Point(4,3),
            new Point(4,4),
            new Point(5,5),
            new Point(5,6),
            new Point(6,5),
            new Point(6,6)
    ));

    static ArrayList<Point> busy = new ArrayList<>(Arrays.asList(
            new Point(39,40),
            new Point(39,41),
            new Point(40,39),
            new Point(40,40),
            new Point(41,40)
    ));


    static ArrayList<Point> glider = new ArrayList<>(Arrays.asList(
            new Point(50,180),
            new Point(51,180),
            new Point(50,181),
            new Point(51,181),
            new Point(60,180),
            new Point(60,179),
            new Point(60,181),
            new Point(61,178),
            new Point(62,177),
            new Point(63,177),
            new Point(61,182),
            new Point(62,183),
            new Point(63,183),
            new Point(65,182),
            new Point(66,181),
            new Point(66,180),
            new Point(66,179),
            new Point(65,178),
            new Point(64,180),
            new Point(67,180),
            new Point(70,181),
            new Point(70,182),
            new Point(70,183),
            new Point(71,181),
            new Point(71,182),
            new Point(71,183),
            new Point(72,180),
            new Point(72,184),
            new Point(74,180),
            new Point(74,179),
            new Point(74,184),
            new Point(74,185),
            new Point(84,182),
            new Point(84,183),
            new Point(85,182),
            new Point(85,183)
    ));

    static ArrayList<Point> biden = new ArrayList<>(Arrays.asList(
            new Point(40, 45),
            new Point(40, 46),
            new Point(40, 47),
            new Point(40, 48),
            new Point(40, 49),
            new Point(40, 50),
            new Point(40, 51),
            new Point(40, 52),
            new Point(40, 53),
            new Point(40, 54),
            new Point(40, 55),
            new Point(41, 45),
            new Point(41, 55),
            new Point(42, 45),
            new Point(42, 47),
            new Point(42, 48),
            new Point(42, 49),
            new Point(42, 50),
            new Point(42, 51),
            new Point(42, 52),
            new Point(42, 53),
            new Point(42, 55),
            new Point(43, 45),
            new Point(43, 47),
            new Point(43, 50),
            new Point(43, 53),
            new Point(43, 55),
            new Point(44, 45),
            new Point(44, 47),
            new Point(44, 50),
            new Point(44, 53),
            new Point(44, 55),
            new Point(45, 48),
            new Point(45, 49),
            new Point(45, 51),
            new Point(45, 52),
            new Point(46, 47),
            new Point(46, 48),
            new Point(46, 49),
            new Point(46, 50),
            new Point(46, 51),
            new Point(46, 52),
            new Point(46, 53),
            new Point(47, 47),
            new Point(47, 48),
            new Point(47, 49),
            new Point(47, 50),
            new Point(47, 51),
            new Point(47, 52),
            new Point(47, 53),
            new Point(48, 47),
            new Point(48, 53),
            new Point(49, 47),
            new Point(49, 53),
            new Point(50, 48),
            new Point(50, 49),
            new Point(50, 50),
            new Point(50, 51),
            new Point(50, 52),
            new Point(51, 47),
            new Point(51, 48),
            new Point(51, 49),
            new Point(51, 50),
            new Point(51, 51),
            new Point(51, 52),
            new Point(51, 53),
            new Point(52, 47),
            new Point(52, 50),
            new Point(52, 53),
            new Point(53, 47),
            new Point(53, 53),
            new Point(54, 47),
            new Point(54, 48),
            new Point(54, 49),
            new Point(54, 50),
            new Point(54, 51),
            new Point(54, 52),
            new Point(54, 53),
            new Point(55, 47),
            new Point(56, 47),
            new Point(57, 47),
            new Point(58, 47),
            new Point(58, 48),
            new Point(58, 49),
            new Point(58, 50),
            new Point(58, 51),
            new Point(58, 52),
            new Point(58, 53),
            new Point(60, 45),
            new Point(60, 46),
            new Point(60, 47),
            new Point(60, 48),
            new Point(60, 49),
            new Point(60, 50),
            new Point(60, 51),
            new Point(60, 52),
            new Point(60, 53),
            new Point(60, 54),
            new Point(60, 55),
            new Point(41, 45),
            new Point(42, 45),
            new Point(43, 45),
            new Point(44, 45),
            new Point(45, 45),
            new Point(46, 45),
            new Point(47, 45),
            new Point(48, 45),
            new Point(49, 45),
            new Point(50, 45),
            new Point(51, 45),
            new Point(52, 45),
            new Point(53, 45),
            new Point(54, 45),
            new Point(55, 45),
            new Point(56, 45),
            new Point(57, 45),
            new Point(58, 45),
            new Point(59, 45),
            new Point(60, 45),
            new Point(41, 55),
            new Point(42, 55),
            new Point(43, 55),
            new Point(44, 55),
            new Point(45, 55),
            new Point(46, 55),
            new Point(47, 55),
            new Point(48, 55),
            new Point(49, 55),
            new Point(50, 55),
            new Point(51, 55),
            new Point(52, 55),
            new Point(53, 55),
            new Point(54, 55),
            new Point(55, 55),
            new Point(56, 55),
            new Point(57, 55),
            new Point(58, 55),
            new Point(59, 55),
            new Point(60, 55)

    ));

}

class bidenLiveCells{
    public static void main (String[] args) {
        Game blue = new GameLiveCells(100, 500, Games.biden);
        blue.run();
    }
}

class bidenMatrix{
    public static void main (String[] args) {
        Game blue = new GameMatrix(100, 500, Games.biden);
        blue.run();
    }
}

class BlinkerLiveCells {
    public static void main (String[] args) {
        Game blinker = new GameLiveCells(10, 500, Games.blinker);
        blinker.run();
    }
}

class BlinkerMatrix {
    public static void main (String[] args) {
        Game blinker = new GameMatrix(10, 500, Games.blinker);
        blinker.run();
    }
}

class ToadLiveCells {
    public static void main (String[] args) {
        Game toad = new GameLiveCells(10, 500, Games.toad);
        toad.run();
    }
}

class ToadMatrix {
    public static void main (String[] args) {
        Game toad = new GameMatrix(10, 500, Games.toad);
        toad.run();
    }
}

class BeaconLiveCells {
    public static void main (String[] args) {
        Game beacon = new GameLiveCells(10, 500, Games.beacon);
        beacon.run();
    }
}

class BeaconMatrix {
    public static void main (String[] args) {
        Game beacon = new GameMatrix(10, 500, Games.beacon);
        beacon.run();
    }
}

class BusyLiveCells {
    public static void main (String[] args) {
        Game busy = new GameLiveCells(80, 50, Games.busy);
        busy.run();
    }
}

class BusyMatrix {
    public static void main (String[] args) {
        Game busy = new GameMatrix(80, 50, Games.busy);
        busy.run();
    }
}

class GliderLiveCells {
    public static void main (String[] args) {
        Game glider = new GameLiveCells(200, 50, Games.glider);
        glider.run();
    }
}

class GliderMatrix {
    public static void main (String[] args) {
        Game glider = new GameMatrix(200, 50, Games.glider);
        glider.run();
    }
}


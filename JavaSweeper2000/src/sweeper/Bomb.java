package sweeper;

public class Bomb {
    private Matrix bombMap;
    private int totalBomb;

    Bomb (int totalBomb) {
        this.totalBomb = totalBomb;
        fixBombCount();
    }

    void start () {
        bombMap = new Matrix(Box.ZERO);
        for (int j = 0; j < totalBomb; j ++)
            placeBomb ();
    }

    Box get (Coord coord) {
        return bombMap.get(coord);
    }

    private void fixBombCount () {
        int maxBombs = Ranges.getSize().x * Ranges.getSize().y /2;
        if (totalBomb > maxBombs)
            totalBomb = maxBombs;
    }

    private void placeBomb () {
        while (true) {
            Coord coord = Ranges.getRandomCoord();
            if (Box.BOMB == bombMap.get(coord))
                continue;
            bombMap.set(coord, Box.BOMB);
            incNumbersAroundBomb(coord);
            break;
        }
    }

    private void incNumbersAroundBomb (Coord coord) {
        for (Coord around : Ranges.getCoordsAround(coord))
            if (Box.BOMB != bombMap.get(around))
                bombMap.set(around, bombMap.get(around).getNextNumberBox());
    }

    int getTotalBombs() {
        return totalBomb;
    }
}

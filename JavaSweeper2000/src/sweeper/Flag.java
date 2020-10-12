package sweeper;

class Flag {
    private Matrix flagMap;
    private int countOfClosedBoxes;

    void start () {
        flagMap = new Matrix(Box.CLOSED);
        countOfClosedBoxes = Ranges.getSize().x * Ranges.getSize().y;
    }

    Box get (Coord coord) {
        return flagMap.get(coord);
    }

    void setOpenedToBox(Coord coord) {
         flagMap.set(coord, Box.OPENED);
         countOfClosedBoxes --;
    }

    void toggleFlagedToBox (Coord coord) {
        switch (flagMap.get(coord)) {
            case FLAGED: setClosedBox(coord); break;
            case CLOSED: setFlagedToBox(coord); break;
        }
    }

    private void setClosedBox(Coord coord) {
        flagMap.set(coord, Box.CLOSED);
    }

    private void setFlagedToBox(Coord coord) {
        flagMap.set(coord, Box.FLAGED);
    }

    int getCountOfClosedBoxes() {
        return countOfClosedBoxes;
    }

    void setBomberToBox(Coord coord) {
        flagMap.set(coord, Box.BOMBED);
    }

    void setOpenedToCloseBomb(Coord coord) {
        if (flagMap.get(coord) == Box.CLOSED)
            flagMap.set(coord, Box.OPENED);
    }

    void setNobobToFlagedSafeBox(Coord coord) {
        if (flagMap.get(coord) == Box.FLAGED)
            flagMap.set(coord, Box.NOBOMB);
    }



    int getCountOfFlagedBoxesAround(Coord coord) {
        int count = 0;
        for (Coord around : Ranges.getCoordsAround(coord))
            if (flagMap.get(around) == Box.FLAGED)
                count ++;
            return count;
    }
}

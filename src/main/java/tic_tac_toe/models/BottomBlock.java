package tic_tac_toe.models;

import tic_tac_toe.gui.Segments;

import java.util.HashMap;

public class BottomBlock extends Block {

    public BottomBlock() {
        this.blockRows = new HashMap<>();
        this.blockRows.put(1, new StringBuilder(Segments.segmentOne.toString()));
        this.blockRows.put(2, new StringBuilder(Segments.segmentOne.toString()));
        this.blockRows.put(3, new StringBuilder(Segments.segmentOne.toString()));

        this.block = new StringBuilder(this.blockRows.get(1).toString() + "\n"
                + this.blockRows.get(2).toString() + "\n"
                + this.blockRows.get(3).toString());
    }

}

package tic_tac_toe.models;

import tic_tac_toe.gui.Segments;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class UpperCenterBlock extends Block {

    public UpperCenterBlock() {

        // segment blocks for the upper and center rows of Tic-Tac Toe
        this.blockRows = new HashMap<>();
        this.blockRows.put(1, new StringBuilder(Segments.segmentOne.toString()));
        this.blockRows.put(2, new StringBuilder(Segments.segmentOne.toString()));
        this.blockRows.put(3, new StringBuilder(Segments.segmentTwo.toString()));

        this.block = new StringBuilder(this.blockRows.get(1).toString() + "\n"
                                     + this.blockRows.get(2).toString() + "\n"
                                     + this.blockRows.get(3).toString() + "\n");
    }
}

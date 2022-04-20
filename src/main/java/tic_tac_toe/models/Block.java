package tic_tac_toe.models;

import java.util.HashMap;

public abstract class Block {

    StringBuilder block;
    HashMap<Integer, Integer> columnPositions;
    HashMap<Integer, StringBuilder> blockRows;
    {
        this.block = new StringBuilder();

        // Create hashmap that pairs the Stringbuilder character positions with 1, 2, 3 to designate their order
        this.columnPositions = new HashMap();
        this.columnPositions.put(1, 21);
        this.columnPositions.put(2, 27);
        this.columnPositions.put(3, 34);
    }

//    public char[] getRowValues(){
//        char[] rowVals = new char[3];
//
//        for(int index=0; index<rowVals.length; index++){
//            rowVals[index] = this.blockRows.get(2).charAt(this.columnPositions.get(index+1));
//        }
//        return rowVals;
//    }

    public char getColumnValue(int column){
        return this.block.charAt(this.columnPositions.get(column));
    }

    public void setCrossOrNoughtInRow(char xOrO, int column) {
        switch(column) {
            case 1:{
                this.block.setCharAt(this.columnPositions.get(1),  xOrO);
                break;
            }
            case 2:{
                this.block.setCharAt(this.columnPositions.get(2),  xOrO);
                break;
            }
            case 3:{
                this.block.setCharAt(this.columnPositions.get(3), xOrO);
                break;
            }
            default: {
                break;
            }
        }
    }

    @Override
    public String toString() {
        return this.block.toString();
    }

}

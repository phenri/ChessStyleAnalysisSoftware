package ua.edu.vntu.gui.chessboard;

import ua.edu.vntu.gui.FormConstants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * @author: Vyacheslav.Bychkovsk
 */
public class Figure extends JPanel implements FormConstants,MouseListener{
    protected Cell parent;
    protected Cells board;
    protected Image image;
    protected boolean isWhite;
    protected String[] possibleMoves;
    protected Position position;

    protected Figure(Cells board){
        super();
        setLayout(null);
        this.position = position;
        setDoubleBuffered(true);
        this.board = board;
        setOpaque(false);
        setBounds(0,0,CELL_SIZE,CELL_SIZE);
        addMouseListener(this);
    }

    public boolean isAvailablePosition(Position pos) {
        System.out.println("in figure");
        return true;
    }

    void setParent(Cell cell){
        parent = cell;
    }
    public Cell getParent(){
        return parent;
    }

    public boolean isWhite(){
        return isWhite;
    }

    public void setPosition(Position position){
        this.position = position;
    }

    public Position getPosition(){
        return position;
    }


    @Override
    public void mouseExited(MouseEvent e) {


    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println(this);
        if(board.isPressed()){
            if (board.getBuffer().isWhite() != this.isWhite() && board.getBuffer().isAvailablePosition(position)){
                parent.addFigure(board.getBuffer());
                parent.reset();
                board.resetPressed();
                board.repaint();
            }
            return;
        }
        board.moveMy(this);
        parent.reset();
    }


}

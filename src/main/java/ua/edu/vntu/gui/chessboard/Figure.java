package ua.edu.vntu.gui.chessboard;

import ua.edu.vntu.gui.Constants;
import ua.edu.vntu.moving.Position;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * @author: Vyacheslav.Bychkovsk
 */
public abstract class Figure extends JPanel implements Constants,MouseListener{
    protected Cell parent;
    protected Cells board;
    protected Image image;
    protected boolean isWhite;
    protected Position position;

    protected Figure(Cells board){
        super(true);
        setLayout(null);
        this.board = board;
        setOpaque(false);
        setBounds(0,0,CELL_SIZE,CELL_SIZE);
        addMouseListener(this);
    }

    public abstract boolean isAvailablePosition(Position pos);

    protected void setParent(Cell cell){
        parent = cell;
    }
    public Cell getParent(){
        return parent;
    }

    protected boolean isWhite(){
        return isWhite;
    }

    protected void setPosition(Position position){
        this.position = position;
    }

    protected Position getPosition(){
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
            if (board.getBuffer().isWhite() != this.isWhite()){
                parent.addFigure(board.getBuffer());
                parent.reset();
                board.resetPressed();
                board.repaint();
            }
            parent.reset();
            return;
        }
        board.captureFigure(this);
        parent.reset();
    }

}

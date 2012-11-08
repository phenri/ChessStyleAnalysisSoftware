package ua.edu.vntu.gui.chessboard;

import ua.edu.vntu.gui.FormConstants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * @author: Vyacheslav.Bychkovsk
 */
public class Figure extends JPanel implements FormConstants,MouseListener,Runnable{
    Cell parent;
    Chessboard board;
//    protected Figure(){
//
//    }
    public Figure(Chessboard board){
        super();
        setLayout(null);
        setDoubleBuffered(true);
        this.board = board;
        setBackground(Color.RED);
//        setOpaque(true);
        setBounds(0,0,CELL_SIZE,CELL_SIZE);
        addMouseListener(this);
    }

    @Override
    public void run() {

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
        System.out.println("Click on figure " + Thread.currentThread());
        board.Listener(this);
        parent.reset();
    }

    void setParent(Cell cell){
        parent = cell;
    }
}

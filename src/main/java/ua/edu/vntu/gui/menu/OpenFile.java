package ua.edu.vntu.gui.menu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ua.edu.vntu.Main;
import ua.edu.vntu.gui.Form;

import javax.annotation.PostConstruct;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileDescriptor;

/**
 * Created with IntelliJ IDEA.
 * User: slavik
 * Date: 13.11.12
 * Time: 20:14
 */
public class OpenFile extends JMenuItem implements ActionListener{
    private JFrame form;

    public OpenFile(){
        super("Open file...");
        addActionListener(this);
    }

    public void setForm(JFrame form){
        this.form = form;
        repaint();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Hello");

        form = (Form) Main.context.getBean("form");

        System.out.println(form);
    }
}

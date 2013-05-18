package ua.edu.vntu;

import org.springframework.context.support.GenericXmlApplicationContext;
import ua.edu.vntu.gui.Form;
import ua.edu.vntu.handlers.CommandActions;
import ua.edu.vntu.handlers.MenuActions;

/**
 * Created with IntelliJ IDEA.
 * User: Bychkovskyy
 * Date: 11.05.13
 * Time: 17:19
 */
public class Application {
    public static void main(String[] args) {
        Form form = new Form();

        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("spring/config.xml");
        ctx.refresh();

        MenuActions menuActions = ctx.getBean(MenuActions.class);

        form.setActions(menuActions);
        form.setCommandActions(ctx.getBean(CommandActions.class));
        menuActions.open();
        menuActions.select();
    }
}

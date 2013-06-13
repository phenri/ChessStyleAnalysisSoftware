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

    public static GenericXmlApplicationContext ctx;

    public static void main(String[] args) {
        Form form = new Form();

        ctx = new GenericXmlApplicationContext();
        ctx.load("spring/config.xml");
        ctx.refresh();

        MenuActions menuActions = ctx.getBean(MenuActions.class);

        form.setActions(menuActions);
        form.setCommandActions(ctx.getBean(CommandActions.class));
//        menuActions.open();
//        menuActions.select();
    }

    //TODO: 1. Зробити діалогове вікно, якщо у відкритому файлі більше ніж одна партія
    //TODO: 2. Зробити кнопки плей і стоп, щоб автоматично переключало хід
    //TODO: 3. ДОРОБИТИ АНАЛІЗ
    //TODO: 4. Зробити кнопку показати результат і виведення резульату

}

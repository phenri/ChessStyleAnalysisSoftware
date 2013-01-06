package ua.edu.vntu.readparty;

import ua.edu.vntu.moving.MovingCommadService;
import ua.edu.vntu.moving.MovingCommand;

public class TestThreading {

    public static void main(String[] args) {
        new TestThreading().start();
    }

    void start(){
        MovingCommand c = new MovingCommadService();
        new A(c);
        new B(c);
        new C(c);

    }


    class A implements Runnable{
        MovingCommand c;

        A(MovingCommand c) {
            this.c = c;
            new Thread(this,"Class A").start();
        }

        @Override
        public void run() {
            c.start(1);
        }
    }
    class B implements Runnable{
        MovingCommand c;

        B(MovingCommand c) {
            this.c = c;
            new Thread(this,"Class B").start();
        }

        @Override
        public void run() {
            c.start(1);
        }
    }
    class C implements Runnable{
        MovingCommand c ;

        C(MovingCommand c) {
            this.c = c;
            new Thread(this,"Class C").start();
        }

        @Override
        public void run() {
            c.start(1);
        }
    }



}

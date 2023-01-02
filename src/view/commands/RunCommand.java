package view.commands;

import controller.Controller;

public class RunCommand extends Command {
    private final Controller controller;

    public RunCommand(String key, String description, Controller controller) {
        super(key, description);
        this.controller = controller;
    }

    @Override
    public void execute() {
        try {
//            System.out.println(this.controller.getRepository().toString());
//            Controller copy = new Controller(this.controller);
//            copy.run();


            this.controller.clone().run();
        } catch (Exception e) {
            System.out.println(e.getMessage());
//            System.err.println(e.toString());
        }
    }
}
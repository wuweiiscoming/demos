package org.wigo.demo.mydog.server.command;

/**
 * @author wuwei
 * @since 2021/5/20
 */
public abstract class AbstractCommand {

    private final AbstractCommand next;

    public AbstractCommand(AbstractCommand next) {
        this.next = next;
    }

    public abstract boolean matches(String opt);

    public abstract void handle(CommandContext context);

    public void handleChain(CommandContext context) {
        if (matches(context.getOpt())) {
            handle(context);
            if (context.isFinished()) {
                return;
            }
        }
        if (next != null) {
            next.handleChain(context);
        }
    }

}

package tw.b2e.receipt.execution;

public class UndefinedCommandExecution extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public final static String UNDEFINED_FORMAT = " %s 不是合法的命令. 命令說明請見'/receipt -help'";
	
	public UndefinedCommandExecution() {super();};
	
	public UndefinedCommandExecution(String command) {
		super(String.format(UNDEFINED_FORMAT, command));
	};
}

package tw.b2e.receipt;

public class ParamValueIsNullExecution extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public ParamValueIsNullExecution() {super();};
	
	public ParamValueIsNullExecution(String msg) {super(msg);};
}
